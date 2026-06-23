<div align="center">

# 🚀 Enterprise Storage Application

### Secure Cloud File Storage System built with Spring Boot, MongoDB & AWS S3

A scalable enterprise-grade file storage application that enables secure file upload, retrieval, metadata management, and cloud storage integration using AWS S3-compatible storage.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green?style=for-the-badge)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-success?style=for-the-badge)
![AWS S3](https://img.shields.io/badge/AWS-S3-orange?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)

</div>

---

## 📖 Overview

Enterprise Storage Application is a cloud-native file management system developed using Spring Boot. The application stores file metadata in MongoDB while the actual files are stored in AWS S3-compatible object storage.

It is designed to simulate how modern enterprise applications handle document and asset storage at scale.

---

## ✨ Features

### 📤 File Upload
- Upload files securely to cloud storage
- Supports multipart file uploads
- Generates unique storage keys

### 📥 File Download
- Retrieve files instantly
- Download using REST APIs
- Stream files directly from storage

### 📂 File Management
- List uploaded files
- View metadata
- Delete stored files

### ☁ Cloud Storage Integration
- AWS S3 SDK v2
- Compatible with MinIO
- Scalable object storage architecture

### 📊 Metadata Management
- Store file details in MongoDB
- File name tracking
- Content type management
- Upload timestamps

### 🛡 Robust Backend
- Validation using Jakarta Validation
- Exception handling
- RESTful architecture
- Health monitoring with Spring Actuator

---

## 🏗 System Architecture

```text
              ┌─────────────┐
              │   Client    │
              └──────┬──────┘
                     │
                     ▼
            ┌─────────────────┐
            │ Spring Boot API │
            └──────┬──────────┘
                   │
       ┌───────────┴───────────┐
       ▼                       ▼

 ┌───────────────┐     ┌──────────────┐
 │   MongoDB     │     │ AWS S3/MinIO │
 │ File Metadata │     │ Actual Files │
 └───────────────┘     └──────────────┘
```

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|----------|
| Java 21 | Programming Language |
| Spring Boot 3.5 | Backend Framework |
| Spring Web | REST APIs |
| Spring Validation | Input Validation |
| MongoDB | Metadata Storage |
| AWS SDK S3 v2 | Object Storage |
| Spring Actuator | Monitoring |
| Maven | Build Tool |
| Swagger/OpenAPI | API Documentation |

---

## 📁 Project Structure

```bash
src
├── main
│   ├── java
│   │   └── com.app
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── model
│   │       ├── dto
│   │       ├── config
│   │       ├── exception
│   │       └── util
│   │
│   └── resources
│       └── application.properties
│
└── test
```

---

## ⚙ Prerequisites

Before running the application, ensure you have:

- Java 21
- Maven 3.9+
- MongoDB
- AWS S3 Bucket or MinIO Server

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/khushisethi03/Enterprise-Storage-Application.git
```

### Navigate to Project

```bash
cd Enterprise-Storage-Application
```

### Configure MongoDB

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/storage_db
```

### Configure AWS S3

```properties
aws.access-key=YOUR_ACCESS_KEY
aws.secret-key=YOUR_SECRET_KEY
aws.region=ap-south-1
aws.bucket-name=enterprise-storage
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application runs at:

```bash
http://localhost:8080
```

---

## 📚 API Documentation

Swagger UI:

```bash
http://localhost:8080/swagger-ui.html
```

OpenAPI Docs:

```bash
http://localhost:8080/api-docs
```

---

## 📡 Sample REST APIs

### Upload File

```http
POST /api/files/upload
```

### Download File

```http
GET /api/files/{id}
```

### List Files

```http
GET /api/files
```

### Delete File

```http
DELETE /api/files/{id}
```

---

## 📈 Future Enhancements

- JWT Authentication
- Role-Based Access Control (RBAC)
- File Sharing Links
- File Versioning
- Folder Management
- Docker Support
- Kubernetes Deployment
- React Frontend

---

## 🧪 Testing

Run tests using:

```bash
mvn test
```

---

## 👩‍💻 Developer

**Khushi**

Java Backend Developer

### Skills

- Java
- Spring Boot
- MongoDB
- AWS S3
- REST APIs
- Object Storage Systems

---

## ⭐ Support

If you found this project useful:

⭐ Star the repository

🍴 Fork it

📢 Share it

---

<div align="center">

### Built with ❤️ using Spring Boot, MongoDB & AWS S3

</div>
