### Learning-Navigator

# Problem Description
The exam registration service is a critical component of a Learning Management System. Generally, exam registration requires thorough Authentication and Authorization. For this assessment, your task is to develop a simplified version of the exam registration service that meets the specified requirements below.

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


## Exam Endpoints
- GET /exams - Get all exams
- GET /exams/{examId} - Get an exam by examId
- POST /exams/subject/{subjectId} - Register a new exam
- POST /exams/{examId} - Register Student to exam
# Request Body
```json
{
	"studentId": 1
}
```

## Postman Collection
https://elements.getpostman.com/redirect?entityId=7585977-240cbeea-74e9-48bf-b291-5606e5da1255&entityType=collection

## License
Harsh Nayak