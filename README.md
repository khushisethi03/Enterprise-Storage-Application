# 🚀 Enterprise Storage Application

### Secure Cloud File Storage System built with Spring Boot, MongoDB & AWS S3

A scalable enterprise-grade file storage application that enables secure file upload, retrieval, metadata management, and cloud storage integration using AWS S3-compatible storage.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green?style=for-the-badge)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-success?style=for-the-badge)
![AWS S3](https://img.shields.io/badge/AWS-S3-orange?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)

---

## 📖 Overview

Enterprise Storage Application is a cloud-native file management system developed using Spring Boot. The application stores file metadata in MongoDB while the actual files are stored in AWS S3-compatible object storage.

## ✨ Features

- 📤 Secure File Upload
- 📥 File Download & Retrieval
- 📂 File Management
- ☁ AWS S3 / MinIO Integration
- 📊 MongoDB Metadata Storage
- 🛡 Validation & Exception Handling
- 📚 Swagger/OpenAPI Documentation
- 📈 Spring Boot Actuator Monitoring

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

## 🛠 Tech Stack

- Java 21
- Spring Boot
- MongoDB
- AWS SDK S3 v2
- Maven
- Swagger/OpenAPI
- Spring Validation
- Spring Actuator

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/khushisethi03/Enterprise-Storage-Application.git
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

## 📚 API Documentation

```text
http://localhost:8080/swagger-ui.html
```

## 👩‍💻 Developer

Khushi 

Java Backend Developer

---

⭐ If you found this project useful, consider starring the repository.
