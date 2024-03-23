"# Learning-Navigator" 



## Requirements

## Student Endpoints
- GET /students - Get all the students
- GET /students/{registrationId} - Get a student by registrationId
- POST /students - Register a new student
# Request Body
```json
{
   "name":"Harsh Nayak"
}
```
- UPDATE /students/{registrationId} - Update details of a student
# Request Body
```json
{
   "name":"Sahil Nayak"
}
```
- DELETE /students/{registrationId} - Delete a student


## Subject Endpoints
- GET /subjects - Get all subjects
- GET /subjects/{subjectId} - Get a subject by subjectId
- POST /subjects - Register a new subject
# Request Body
```json
{
   "name":"Computer Networks"
}
```
- UPDATE /subjects/{subjectId} - Update details of a subject
# Request Body
```json
{
   "name":"English"
}
```
- DELETE /subjects/{subjectId} - Delete a subject




## Setup

To run this application locally, follow these steps:

1. Clone this repository:

   ```bash
   git clone 
   ```

2. Build the application using Maven:
    ```bash
   mvnw clean package
   ```

3. Run the application:
    ```bash
    java -jar target/-0.0.1-SNAPSHOT.jar
    ```

## Testing
Run the JUnit test cases using:

    mvnw test

The tests include Mockito for mocking dependencies and verifying interactions between components.

## Postman Collection
https://elements.getpostman.com/redirect?entityId=7585977-91431c0d-2f04-429f-ba63-8bb8b9310765&entityType=collection

## License
Harsh Nayak