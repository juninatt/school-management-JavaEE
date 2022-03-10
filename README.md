# student-management-system # S.M.S

-----------------------------------------------------------
STUDENT PATHS:
Create student:
POST - http://localhost:8080/SMS/api/v1/student/
{
"firstName": "Mr",
"lastName": "Student",
"email": "student.se",
"phoneNumber": "031"
}

Get student:
GET - http://localhost:8080/SMS/api/v1/student/{id}
Utan {id} hämtas alla

Get student by last name:
GET - http://localhost:8080/SMS/api/v1/student/{last-name}
Query -> last-name : <student-last-name>

Update student name:
PATCH - http://localhost:8080/SMS/api/v1/student/name/{id}
Query - > first-name : <new-first-name>
last-name  : <new-last-name>

Remove student:
DELETE - http://localhost:8080/SMS/api/v1/student/{id}

Add subject:
PATCH - http://localhost:8080/SMS/api/v1/student/addsubject/{id}
Query - > subject : <subject-id>
-----------------------------------------------------------
TEACHER PATHS:
Create teacher:
POST - http://localhost:8080/SMS/api/v1/teacher/
{
"firstName": "Mrs",
"lastName": "Teacher",
"email": "teacher.se",
"phoneNumber": "031"
}

Get teacher:
GET - http://localhost:8080/SMS/api/v1/teacher/{id}
Utan {id} hämtas alla

Get teacher by last name:
GET - http://localhost:8080/SMS/api/v1/teacher/{last-name}
Query -> last-name : <teacher-last-name>

Update teacher name:
PATCH - http://localhost:8080/SMS/api/v1/teacher/name/{id}
Query - > first-name : <new-first-name>
last-name  : <new-last-name>

Remove teacher:
DELETE - http://localhost:8080/SMS/api/v1/teacher/{id}
-----------------------------------------------------------
SUBJECT PATHS:
Create subject:
POST - http://localhost:8080/SMS/api/v1/subject/
{
"name":"Subject",
"points":"7,5"
}

Get subject:
GET - http://localhost:8080/SMS/api/v1/subject/{id}
Utan {id} hämtas alla

Get subject by points:
GET - http://localhost:8080/SMS/api/v1/subject/{points}
Query -> points : <subject-points>

Update  name:
PATCH - http://localhost:8080/SMS/api/v1/subject/name/{id}
Query - > name : <new-name>

Remove subject:
DELETE - http://localhost:8080/SMS/api/v1/subject/{id}