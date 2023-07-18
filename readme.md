# Social Media Application

This is a simple social media application built with Spring Boot, Hibernate, and PostgreSQL. The application allows users to create and view posts, follow other users, and like posts.

## Features

- Create a new user
- Get all users
- Create a new post
- Get all posts
- Follow a user
- Like a post

## Technologies Used

- Java 17
- Spring Boot 3.1.3
- Hibernate
- PostgreSQL

## Prerequisites

- Java 8 or higher
- PostgreSQL database

## How to Run

1. Clone this repository to your local machine.
```
git clone <repository_url>
```

2. Update the database configuration in the `application.properties` file located in the `src/main/resources` directory. Provide the appropriate database URL, username, and password.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/social_media
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

```

3. Build the application using Maven or your preferred build tool.
```
mvn clean install
```

4. Run the application using the following command:
```
mvn spring-boot:run
```

5. The application will start, and the RESTful API will be accessible at `http://localhost:8080/api`.

## API Documentation

### User Endpoints

- **Create a new user:** `POST /api/users`
- **Get all users:** `GET /api/users`

### Post Endpoints

- **Create a new post:** `POST /api/posts`
- **Get all posts:** `GET /api/posts`

### Following and Likes

- **Follow a user:** `POST /api/users/{followerId}/follow/{followingId}`
- **Like a post:** `POST /api/users/{userId}/like/{postId}`

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Feedback

**Was it easy to complete the task using AI?**

    Yes, that was quite easy. 

**How long did task take you to complete?**

    It took me around 2 hours, maybe less.

**Was the code ready to run after generation? What did you have to change to make it usable?**

    Almost all the code was ready to run after generation, but if it wasn't, I just wrote more prompts to ChatGPT.

**Which challenges did you face during completion of the task?**

    Lack of knowledge of the ChatGPT.

**Which specific prompts you learned as a good practice to complete the task?**

    For example, step-by-step, error handling, testing and documentation prompts. 