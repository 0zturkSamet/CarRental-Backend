# Car Rental Service - RESTful API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## About

**CarRental-Backend** is an enterprise-grade RESTful API service designed to power modern car rental platforms. Built with cutting-edge technologies including Spring Boot 3 and Java 21, this backend solution provides a robust, scalable, and secure foundation for managing vehicle rental operations.

### Purpose & Vision

This project addresses the core backend requirements of car rental businesses by offering a complete, production-ready API that handles everything from user authentication to reservation management. Whether you're building a startup car-sharing platform or modernizing an existing rental service, this backend provides the essential infrastructure you need to get started quickly while maintaining professional code quality.

### Key Highlights

- **Production-Ready Architecture**: Enterprise-level design following industry best practices, clean code principles, and SOLID design patterns
- **Modern Technology Stack**: Leverages the latest Java 21 LTS features and Spring Boot 3.3.5 for optimal performance and maintainability
- **Security First**: Implements JWT-based stateless authentication, BCrypt password encryption, and role-based access control (RBAC) to ensure data protection
- **Developer Friendly**: Comprehensive Swagger/OpenAPI documentation, clear code structure, and extensive configuration options make integration and customization straightforward
- **Deployment Ready**: Docker support with docker-compose configuration enables seamless deployment across any environment

### Use Cases

This API is ideal for:
- **Car Rental Businesses**: Traditional car rental companies looking to establish an online presence
- **Car Sharing Platforms**: Peer-to-peer car sharing services requiring secure user and vehicle management
- **Fleet Management**: Companies managing vehicle fleets and rental operations
- **Learning & Portfolio Projects**: Developers seeking a real-world example of professional Spring Boot application architecture

### Who Is This For?

- **Backend Developers** seeking to understand production-level Spring Boot architecture
- **Entrepreneurs** building car rental or mobility-as-a-service platforms
- **Students & Job Seekers** looking for a portfolio project that demonstrates professional development skills
- **Development Teams** needing a solid foundation to build upon for custom rental solutions

## Features

- **JWT Authentication & Authorization** - Secure token-based authentication with role-based access control (Admin/User)
- **Car Management** - Complete CRUD operations for vehicle inventory management
- **Reservation System** - Booking management with date validation and conflict prevention
- **User Management** - User registration, profile management, and role assignment
- **Image Upload** - Multi-image support for vehicle listings
- **Contact System** - Message/inquiry handling for customer support
- **Pagination & Filtering** - Efficient data retrieval with pageable results
- **API Documentation** - Interactive Swagger/OpenAPI documentation
- **Health Monitoring** - Spring Boot Actuator for application health checks
- **Docker Support** - Containerized deployment with Docker Compose

## Tech Stack

### Backend
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.3.5** - Latest stable Spring Boot framework
- **Spring Security 6** - Modern security configuration with SecurityFilterChain
- **Spring Data JPA** - Data persistence with Hibernate
- **PostgreSQL 16** - Robust relational database
- **JWT (JJWT 0.12.6)** - Modern JSON Web Token implementation
- **ModelMapper 3.2.1** - DTO mapping
- **Lombok** - Boilerplate code reduction
- **Springdoc OpenAPI 2.6.0** - API documentation

### DevOps & Tools
- **Maven** - Dependency management and build automation
- **Docker & Docker Compose** - Containerization
- **Git** - Version control

## Architecture

This project follows a layered architecture pattern:

```
├── Controller Layer    → REST endpoints (HTTP)
├── Service Layer       → Business logic
├── Repository Layer    → Data access (JPA)
└── Domain Layer        → Entity models
```

**Design Patterns:**
- Repository Pattern
- DTO Pattern
- Dependency Injection
- Builder Pattern (Lombok)

## Prerequisites

Choose one of the following setup methods:

### Option 1: Docker (Recommended)
- [Docker](https://www.docker.com/get-started) 20.10+
- [Docker Compose](https://docs.docker.com/compose/) 2.0+

### Option 2: Local Development
- [Java 21](https://adoptium.net/) or higher
- [Maven 3.9+](https://maven.apache.org/)
- [PostgreSQL 16](https://www.postgresql.org/download/)

## Quick Start

### Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/0zturkSamet/CarRental-Backend.git
   cd CarRental-Backend
   ```

2. **Set up environment variables (optional)**
   ```bash
   cp .env.example .env
   # Edit .env with your custom values
   ```

3. **Start the application**
   ```bash
   docker-compose up -d
   ```

4. **Access the application**
   - API Base URL: `http://localhost:9090/car-rental/api`
   - Swagger UI: `http://localhost:9090/car-rental/api/swagger-ui.html`
   - Health Check: `http://localhost:9090/car-rental/api/actuator/health`

5. **Stop the application**
   ```bash
   docker-compose down
   ```

### Local Development Setup

1. **Clone and navigate**
   ```bash
   git clone https://github.com/0zturkSamet/CarRental-Backend.git
   cd CarRental-Backend
   ```

2. **Set up PostgreSQL database**
   ```sql
   CREATE DATABASE prorent_db;
   CREATE USER prorentuser WITH ENCRYPTED PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE prorent_db TO prorentuser;
   ```

3. **Configure environment variables**
   ```bash
   # Linux/Mac
   export DB_URL=jdbc:postgresql://localhost:5432/prorent_db
   export DB_USERNAME=prorentuser
   export DB_PASSWORD=your_password
   export JWT_SECRET=$(openssl rand -base64 64)

   # Windows (PowerShell)
   $env:DB_URL="jdbc:postgresql://localhost:5432/prorent_db"
   $env:DB_USERNAME="prorentuser"
   $env:DB_PASSWORD="your_password"
   $env:JWT_SECRET="your_generated_secret_here"
   ```

4. **Build and run**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

## API Documentation

### Interactive Documentation
Once the application is running, access the Swagger UI at:
```
http://localhost:9090/car-rental/api/swagger-ui.html
```

### Main Endpoints

#### Authentication
- `POST /register` - Register a new user
- `POST /login` - Login and receive JWT token

#### Cars (Public)
- `GET /car/visitors/all` - Get all cars
- `GET /car/visitors/{id}` - Get car by ID
- `GET /car/visitors/carpage` - Get paginated cars

#### Cars (Admin Only)
- `POST /car/admin/{imageId}/add` - Add new car
- `PUT /car/admin/auth` - Update car
- `DELETE /car/admin/{id}/auth` - Delete car

#### Users (Authenticated)
- `GET /user` - Get all users (Admin only)
- `GET /user/{id}` - Get user by ID
- `PATCH /user/{id}` - Update user
- `DELETE /user/{id}` - Delete user

#### Reservations
- `POST /reservations/add` - Create reservation
- `GET /reservations/{id}` - Get reservation by ID
- `GET /reservations/auth/all` - Get user's reservations
- `PUT /reservations/admin/auth` - Update reservation (Admin)
- `DELETE /reservations/admin/{id}/auth` - Delete reservation (Admin)

#### Messages
- `POST /contactus/visitors` - Send contact message
- `GET /contactus` - Get all messages (Admin)
- `DELETE /contactus/{id}` - Delete message (Admin)

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SERVER_PORT` | Application port | `9090` |
| `DB_URL` | PostgreSQL connection URL | `jdbc:postgresql://localhost:5432/prorent_db` |
| `DB_USERNAME` | Database username | `prorentuser` |
| `DB_PASSWORD` | Database password | `changeme` |
| `JWT_SECRET` | Secret key for JWT signing | *Must be changed* |
| `JWT_EXPIRATION_MS` | JWT token expiration time | `86400000` (24h) |
| `CORS_ALLOWED_ORIGINS` | Allowed CORS origins | `http://localhost:3000,http://localhost:4200` |
| `JPA_DDL_AUTO` | Hibernate DDL mode | `update` |
| `JPA_SHOW_SQL` | Show SQL logs | `false` |

See `.env.example` for a complete list.

## Security Features

- **JWT Authentication** - Stateless token-based authentication
- **Password Encryption** - BCrypt password hashing
- **Role-Based Access Control** - Admin and User roles
- **CORS Configuration** - Configurable allowed origins
- **SQL Injection Protection** - JPA/Hibernate parameterized queries
- **Secure Headers** - Spring Security defaults
- **Environment Variables** - No hardcoded credentials

## Database Schema

Main entities:
- **User** - User accounts with roles
- **Car** - Vehicle inventory
- **Reservation** - Booking records
- **ImageFile** - Vehicle images
- **Message** - Customer inquiries
- **Role** - User authorization roles

## Development

### Running Tests
```bash
./mvnw test
```

### Building for Production
```bash
./mvnw clean package -DskipTests
java -jar target/carrental-1.0.0.jar
```

### Code Quality
This project follows:
- Clean Code principles
- SOLID principles
- RESTful API best practices
- Spring Boot conventions
- Java naming conventions

## Project Structure

```
src/main/java/com/prorent/carrental/
├── config/              # Configuration classes
├── controller/          # REST controllers
├── domain/              # JPA entities
│   └── enumeration/     # Enums
├── exception/           # Custom exceptions
├── helper/              # Utility classes
├── repository/          # Data access layer
├── security/            # Security configuration
│   ├── config/          # Security configs
│   └── service/         # User details service
└── service/             # Business logic
    └── dto/             # Data Transfer Objects
```

## Troubleshooting

### Port already in use
If port 9090 is occupied, change it:
```bash
export SERVER_PORT=8080
```

### Database connection issues
1. Verify PostgreSQL is running
2. Check credentials in environment variables
3. Ensure database `prorent_db` exists

### Docker build fails
```bash
docker-compose down -v
docker-compose build --no-cache
docker-compose up
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact & Author

**Samet Ozturk**
- GitHub: [@0zturkSamet](https://github.com/0zturkSamet)
- LinkedIn: [Add your LinkedIn]
- Email: [Add your email]

---

**Note for Recruiters**: This project demonstrates professional backend development skills including:
- Modern Java & Spring Boot development
- RESTful API design
- Security implementation (JWT, Spring Security)
- Database design and ORM
- Docker containerization
- API documentation
- Clean architecture and design patterns
- Production-ready configuration management

Made with ❤️ using Spring Boot 3 and Java 21
