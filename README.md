# Comments Service REST API
-This project is a REST API for managing comments. It allows users to perform CRUD (Create, Read, Update, Delete) operations on comments.

*Technologies Used
------------------
- Spring Boot
- Spring Data JPA
- H2 Database
- JUnit 5
- Mockito
- Postman

*Building the Application
-------------------------
To build the application, follow these steps:

Clone the repository to your local machine:
bash
Copy code
git clone https://github.com/HafezMohamed2018/comment.git
Navigate to the project directory:
bash
Copy code
cd comments-service
Build the project using Maven:
go
Copy code
mvn clean package
Running the Application
To run the application, execute the generated JAR file:

bash
Copy code
java -jar target/comments-service-1.0.0.jar
The application will start, and the API will be accessible at http://localhost:8080.

Copy code
mvn verify
API Endpoints
GET /api/comments: Retrieves all comments.
POST /api/comments: Creates a new comment.
GET /api/comments/{commentId}: Retrieves a specific comment by ID.
PUT /api/comments/{commentId}: Updates a comment by ID.
DELETE /api/comments/{commentId}: Deletes a comment by ID.
Example Usage
Create a new comment:
bash
Copy code
POST /api/comments
{
    "authorId": "user1",
    "content": "This is a sample comment",
    "tags": ["sample", "test"]
}
Retrieve all comments:
bash
Copy code
GET /api/comments
Update a comment:
bash
Copy code
PUT /api/comments/{commentId}
{
    "content": "Updated comment content",
    "tags": ["updated", "test"]
}
Delete a comment:
bash
Copy code
DELETE /api/comments/{commentId}
