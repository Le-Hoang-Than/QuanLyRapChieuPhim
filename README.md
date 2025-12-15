# 🎬 Cinema Backend

**Cinema Backend** là backend của hệ thống **đặt vé xem phim**, được xây dựng bằng **Spring Boot**. Hệ thống cung cấp các API REST để quản lý phim, rạp chiếu, phòng chiếu, ghế ngồi, lịch chiếu và đặt vé.

Hệ thống hỗ trợ **quản lý người dùng**, **xác thực JWT**, và có thể tích hợp dễ dàng với frontend.

---

## 🧰 Công nghệ sử dụng
- **Ngôn ngữ:** Java 8
- **Framework:** Spring Boot 2.6.2
- **Cơ sở dữ liệu:** MySQL (chính), PostgreSQL (tùy chọn)
- **ORM:** Spring Data JPA (Hibernate)
- **Bảo mật:** Spring Security + JWT
- **Tiện ích:** Lombok, ModelMapper
- **Build tool:** Maven

---

## 🛠 Cài đặt và chạy project

### 1. Yêu cầu
- Java 8
- Maven 3.6+
- MySQL (hoặc PostgreSQL)

### 2. Clone repository
```bash
git clone https://github.com/Le-Hoang-Than/QuanLyRapChieuPhim.git
```
### 3. Tạo cơ sở dữ liệu
```bash
CREATE DATABASE cinema;
```
### 4. Cấu hình kết nối cơ sở dữ liệu
```bash
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/cinema?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Port server
server.port=8080
```

### 5. Chạy project
**Bước 1:** Truy cập file CinemaBackEndApplication.java
**Bước 2:** Trong hàm init() comment các đoạn liên quan đến ghế ngồi
**Bước 3:** Trong terminel chạy lệnh sau:
```bash
mvn clean install
```
**Bước 4:** Chạy 1 lần đầu app rồi bỏ comment đoạn trên rồi chạy lại để add data ghế ngồi cho phòng 1
```bash
mvn spring-boot:run
```
