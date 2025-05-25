## Spring Boot Basics - Product Management Application

This project serves as a foundational example of a web application built using the Spring Boot framework. It demonstrates core concepts including Spring MVC for handling web requests, Spring Data JPA for database interaction, Spring Security for authentication and authorization, and Thymeleaf for server-side template rendering. The application provides basic Create, Read, Update, and Delete (CRUD) functionalities for managing a list of products.

### Project Overview

The primary goal of this application is to showcase a simple yet functional product management system. Users can view a list of products, and administrators have additional privileges to add, edit, and delete products. The application incorporates security measures to differentiate between standard users and administrators, ensuring that only authorized personnel can perform sensitive operations. It utilizes an in-memory H2 database by default for ease of setup and testing, but it can be configured to connect to other databases like MySQL through the `application.properties` file. Upon startup, the application initializes the database with some sample product data for demonstration purposes.

### Technologies Used

The application leverages a modern Java stack centered around the Spring ecosystem:

*   **Spring Boot:** Provides a streamlined way to create stand-alone, production-grade Spring-based Applications that you can "just run". It simplifies the setup and configuration of Spring applications.
*   **Spring MVC:** Implements the Model-View-Controller design pattern for building web applications. It handles incoming HTTP requests, processes them, and renders appropriate views.
*   **Spring Data JPA:** Simplifies data access layers by reducing the amount of boilerplate code required. It provides repository support for Java Persistence API (JPA).
*   **Spring Security:** Offers comprehensive security services for Java applications, including authentication and authorization. This project uses it to manage user login and role-based access control.
*   **Thymeleaf:** A modern server-side Java template engine for web and standalone environments. It allows for natural HTML templates that can be correctly displayed in browsers and also work as static prototypes.
*   **Hibernate:** The default JPA implementation used by Spring Data JPA for object-relational mapping (ORM).
*   **H2 Database:** An in-memory relational database primarily used for development and testing purposes. The application is configured to use H2 by default.
*   **MySQL Connector/J:** Includes the driver necessary for connecting to a MySQL database, should you choose to configure it.
*   **Lombok:** A Java library that automatically plugs into your editor and build tools to spice up your java. It helps reduce boilerplate code for model/data objects (e.g., getters, setters, constructors).
*   **Maven:** A build automation tool used primarily for Java projects. It manages the project's build, reporting, and documentation from a central piece of information (POM file).
*   **Bootstrap:** A popular front-end framework used for styling the web pages, ensuring a responsive and consistent user interface.
*   **Java 21:** The version of the Java Development Kit used for this project.

### Features

The application implements the following key features:

*   **Product Listing:** All authenticated users can view the list of available products on the main index page.
*   **Product Creation (Admin):** Users with the 'ADMIN' role can access a form to add new products to the system, specifying name, price, and quantity.
*   **Product Editing (Admin):** Administrators can select a product to view its details and modify its name, price, or quantity.
*   **Product Deletion (Admin):** Administrators can remove products from the system.
*   **Product Search:** Users can search for products by name.
*   **User Authentication:** Users must log in to access the application. The system uses form-based login.
*   **Role-Based Authorization:** Access to specific functionalities (like adding, editing, deleting products) is restricted based on user roles (USER vs. ADMIN). Method-level security annotations (`@PreAuthorize`) are used in the controller to enforce these rules.
*   **Sample Data Initialization:** The application pre-populates the database with a few sample products on startup for immediate testing and demonstration.
*   **Custom Login Page:** Provides a dedicated page for user login.
*   **Access Denied Page:** Redirects users to a specific page if they attempt to access resources they are not authorized for.

### Setup and Running

To set up and run this project locally, follow these steps:

1.  **Prerequisites:**
    *   Java Development Kit (JDK) version 21 or later installed.
    *   Apache Maven build tool installed.
    *   Git installed (for cloning the repository).

2.  **Clone the Repository:**
    ```bash
    git clone https://github.com/VendeTT1/Tp-Spring-Boot-basics.git
    cd Tp-Spring-Boot-basics
    ```

3.  **Build the Project (Optional):**
    Maven will automatically download dependencies when you run the application, but you can build it explicitly:
    ```bash
    mvn clean install
    ```

4.  **Run the Application:**
    You can run the application using the Spring Boot Maven plugin:
    ```bash
    mvn spring-boot:run
    ```
    The application will start, and the embedded H2 database will be initialized with sample data as defined in the `CommandLineRunner` bean within `SpringMvcApplication.java`.

5.  **Access the Application:**
    Open your web browser and navigate to `http://localhost:8080`. You will be redirected to the login page (`/login`).

6.  **Login Credentials:**
    Use the following credentials defined in `SecurityConfig.java`:
    *   User Role: `user1` / `1234` or `user2` / `1234`
    *   Admin Role: `admin` / `1234`

7.  **Database Configuration:**
    By default, the application uses the H2 in-memory database. The connection details are typically configured automatically by Spring Boot. If you wish to use a different database like MySQL, you will need to:
    *   Ensure a MySQL server is running.
    *   Create a database schema for the application.
    *   Update the `src/main/resources/application.properties` file with your MySQL connection URL, username, and password. For example:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update # Or create, create-drop
        ```
    *   Make sure the MySQL dependency is present in `pom.xml` (it is included in this project).

### Project Structure

The project follows a standard Maven project structure:

*   `src/main/java/ma/enset/springmvc`: Root package for the application's Java source code.
    *   `SpringMvcApplication.java`: The main class that bootstraps the Spring Boot application. Contains the `main` method and the `CommandLineRunner` for data initialization.
    *   `entities`: Contains JPA entity classes representing the data model.
        *   `Product.java`: Defines the `Product` entity with fields like id, name, price, quantity, and validation constraints.
    *   `repository`: Contains Spring Data JPA repository interfaces for data access.
        *   `ProductRepository.java`: Interface extending `JpaRepository` for `Product` entity, providing standard CRUD methods and a custom `findByName` method.
    *   `security`: Contains security configuration classes.
        *   `SecurityConfig.java`: Configures Spring Security, including password encoding, in-memory user details management, HTTP security rules (form login, authorization), and exception handling.
    *   `web`: Contains Spring MVC controllers that handle web requests.
        *   `ProductController.java`: Handles requests related to products (listing, adding, editing, deleting, searching) and user authentication (login, logout). Uses annotations like `@Controller`, `@GetMapping`, `@PostMapping`, `@RequestParam`, and `@PreAuthorize`.
*   `src/main/resources`: Contains application resources.
    *   `application.properties`: Configuration file for Spring Boot application properties (e.g., database connection, server port).
    *   `templates`: Contains Thymeleaf HTML template files for the user interface.
        *   `layout.html`: Base layout template used by other views.
        *   `product.html`: Displays the list of products.
        *   `new-product.html`: Form for adding a new product.
        *   `view-product.html`: Form for viewing/editing an existing product.
        *   `find-product.html`: Displays the result of a product search.
        *   `login.html`: Custom login page.
        *   `notAuthorized.html`: Page displayed for access denied errors.
*   `src/test/java`: Contains unit and integration tests (basic structure present).
*   `pom.xml`: Maven Project Object Model file defining project dependencies, build configuration, and metadata.
*   `mvnw`, `mvnw.cmd`: Maven wrapper scripts.

### Security Configuration Details

Security is configured in `SecurityConfig.java` using Spring Security. Key aspects include:

*   **Authentication:** Uses an `InMemoryUserDetailsManager` to store user credentials directly in memory. This is suitable for demonstration but should be replaced with a database-backed `UserDetailsService` in production.
*   **Password Encoding:** Uses `BCryptPasswordEncoder` to securely encode passwords before storing and comparison.
*   **Authorization:**
    *   Uses `HttpSecurity` to define authorization rules based on URL patterns and user roles.
    *   Permits access to `/login`, `/public/**`, and `/webjars/**` (for static resources like Bootstrap) without authentication.
    *   Requires authentication for all other requests (`anyRequest().authenticated()`).
    *   Uses method-level security (`@EnableGlobalMethodSecurity(prePostEnabled = true)`) allowing `@PreAuthorize` annotations on controller methods (e.g., `@PreAuthorize("hasRole('ADMIN')")` on methods in `ProductController`).
    *   Redirects users to `/notAuthorized` upon access denial.
*   **Login:** Configures a custom form login page at `/login`.
*   **CSRF Protection:** Enabled by default (unless explicitly disabled), helping prevent Cross-Site Request Forgery attacks.

This documentation provides a comprehensive overview of the Spring Boot Basics project. It covers the essential aspects needed to understand, set up, run, and potentially extend the application.
