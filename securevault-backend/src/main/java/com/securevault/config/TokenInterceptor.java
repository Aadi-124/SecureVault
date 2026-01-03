package com.securevault.config;

import com.securevault.model.User;
import com.securevault.model.Session;
import com.securevault.service.SessionService;
import com.securevault.service.UserService;
import com.securevault.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

public class TokenInterceptor implements HandlerInterceptor {

    private final UserService userService;
    private final SessionService sessionService;
    private final JwtUtil jwtUtil;

    public TokenInterceptor(UserService userService,
                            SessionService sessionService,
                            JwtUtil jwtUtil) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Allow CORS preflight
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true;

        // Allow auth-related endpoints
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/auth")) return true;

        // Extract and validate JWT
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            return reject(request,response, "Missing token");
        }

        String token = auth.substring(7).trim();

        if (!jwtUtil.validateToken(token)) {
            return reject(request, response, "Invalid or expired token");
        }

        // Extract email from token
        String email = jwtUtil.getEmailFromToken(token);
        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            return reject(request,response, "User not found");
        }

        // Attach current user to request
        User user = userOpt.get();
        request.setAttribute("currentUser", user);

        // Update session last active (matching JWT stored at login time)
        sessionService.findByToken(token)
                .ifPresent(sessionService::refreshLastActive);

        return true;
    }

    // private boolean reject(HttpServletResponse res, String msg) throws Exception {
    //     res.setStatus(401);
    //     res.setContentType("application/json");
    //     res.getWriter().write("{\"message\":\"" + msg + "\"}");
    //     return false;
    // }

    private boolean reject(HttpServletRequest req,
                       HttpServletResponse res,
                       String msg) throws Exception {

    // Add CORS headers manually for error responses
    String origin = req.getHeader("Origin");
    if (origin != null) {
        res.setHeader("Access-Control-Allow-Origin", origin);
        res.setHeader("Vary", "Origin");
    }

    res.setHeader("Access-Control-Allow-Credentials", "true");
    res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    res.setContentType("application/json");
    res.getWriter().write("{\"message\":\"" + msg + "\"}");

    return false;
}

}
