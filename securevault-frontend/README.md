🔐 SecureVault
End-to-End Encrypted File Storage & Team Workspace
<p align="center"> <img src="https://img.shields.io/badge/Frontend-React%20%2B%20TypeScript-blue?style=for-the-badge"/> <img src="https://img.shields.io/badge/Backend-Spring%20Boot-green?style=for-the-badge"/> <img src="https://img.shields.io/badge/Database-MySQL-orange?style=for-the-badge"/> <br/> <img src="https://img.shields.io/badge/Encryption-AES--256--GCM-yellow?style=for-the-badge"/> <img src="https://img.shields.io/badge/License-MIT-lightgrey?style=for-the-badge"/> </p>

SecureVault is a full-stack, end-to-end encrypted platform built to securely store files, manage teams, collaborate, and communicate without compromising privacy.

All encryption happens client-side using AES-256-GCM, ensuring that even the backend never sees plaintext data — making SecureVault a zero-knowledge storage system.

✨ Key Features
🔐 End-to-End Encryption

Client-side AES-256-GCM encryption

Backend stores only encrypted blobs

IVs & keys stored safely on client

Zero-knowledge architecture

👥 Teams & Collaboration

Create and manage teams

Invite members via email

Assign roles (Admin / Editor / Viewer)

Encrypted team chat

Team activity logs

Team-based encrypted file vault

🗂️ Secure File Vault

Upload & manage fully encrypted files

File version history

Expiring shareable links (with optional password)

Per-team access control

📝 Encrypted Notes

Full notes vault

Notes encrypted with AES-256 before storage

Zero-knowledge note editor

🔑 Password Vault

Securely store passwords, secrets, API keys

AES-256 client-side encryption

Never leaves the browser unencrypted

🛡️ Security Dashboard

Session & device management

Login activity

Two-factor authentication (OTP-based)

Self-destruct mode (wipe all data instantly)

🚀 Tech Stack
🖥️ Frontend

⚛️ React + TypeScript

⚡ Vite

🎨 Tailwind CSS + shadcn/ui

🔐 Web Crypto API (AES-256-GCM)

📦 React Query + Context API

🛠️ Backend

☕ Spring Boot (Java 21)

🗄 MySQL

🔐 JWT Authentication

🌐 REST API Architecture

📦 JPA / Hibernate

📁 Project Structure
securevault/
 ├── securevault-frontend/    # React + TypeScript (Vite)
 └── securevault-backend/     # Spring Boot + MySQL

🧩 Frontend Setup
git clone <repo-url>

cd securevault-frontend
npm install
npm run dev

Update API URL:

src/lib/api.ts

export const API_BASE = "http://localhost:8080/api";

⚙️ Backend Setup
cd securevault-backend
mvn clean install
mvn spring-boot:run

Configure MySQL in:

src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/securevault
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

🌐 Deployment Guide
Frontend Hosting

Vercel

Netlify

Cloudflare Pages

Backend Hosting

Render

Railway

AWS EC2

Docker

📸 Screenshots

(Add screenshots here for better presentation)

🤝 Contributing

Pull requests are welcome!
Please open an issue for discussions or feature requests.

📜 License

MIT License © 2025 SecureVault

⭐ Support the Project

If this project helped you, consider giving it a star ⭐ on GitHub — it motivates future development!
