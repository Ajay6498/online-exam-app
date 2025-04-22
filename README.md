# online-exam-app

# 📝 Online Exam Application

A web-based Online Exam System built with **Spring Boot (MVC)** and **Thymeleaf**. This application allows students to take exams and admins to create, assign, and manage those exams.

---

## 📌 Features

### ✅ Homepage
- Options to login/register as **Student** or **Admin**.

### 👨‍🎓 Student Module
- **Registration & Login**
- **Dashboard**
  - View assigned exams
  - Take exams
  - Submit answers
  - View submission status

### 👨‍💼 Admin Module
- **Registration & Login**
- **Dashboard**
  - Create exams
  - Assign exams to students
  - View all exams and student performances

---

## 🛠️ Tech Stack

| Layer       | Technology             |
|-------------|------------------------|
| Backend     | Spring Boot, Spring MVC|
| Frontend    | Thymeleaf, HTML, CSS   |
| Database    | MySQL / H2 (Configurable) |
| Build Tool  | Maven                  |

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.example.exam/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── model/
│   │       └── repository/
│   └── resources/
│       ├── templates/
│       │   ├── home.html
│       │   ├── student-login.html
│       │   ├── student-dashboard.html
│       │   ├── admin-dashboard.html
│       └── application.properties
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL or H2 database

### Running the Project
1. Clone the repository
2. Configure database in `application.properties`
3. Run the project using:

```bash
./mvnw spring-boot:run
```

4. Access at `http://localhost:8080/`

---

## 📸 Screenshots

> Screenshots of homepage, login pages, and dashboards are available in the `/screenshots` folder.

---

## 🤝 Contributing

Feel free to fork the project and submit pull requests. For major changes, please open an issue first.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

