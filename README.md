# student-management-system # S.M.S

-----------------------------------------------------------
STUDENT PATHS:
Create student:
POST - http://localhost:8080/SMS/api/v1/students/
{
"firstName": "<first-name>",
"lastName": "<last-name>",
"email": "<email>",
"phoneNumber": "<phone-number>"
}

Get student:
GET - http://localhost:8080/SMS/api/v1/students/{id}
Utan {id} hämtas alla

Get student by last name:
GET - http://localhost:8080/SMS/api/v1/students/{last-name}
Query -> last-name : <student-last-name>

Update student name:
PATCH - http://localhost:8080/SMS/api/v1/students/name/{id}
Query - > first-name : <new-first-name>
last-name  : <new-last-name>

Remove student:
DELETE - http://localhost:8080/SMS/api/v1/students/{id}

Add subject:
PATCH - http://localhost:8080/SMS/api/v1/students/addsubject/{id}
Query - > subject : <subject-id>
-----------------------------------------------------------
TEACHER PATHS:
Create teacher:
POST - http://localhost:8080/SMS/api/v1/teachers/
{
"firstName": "<first-name>",
"lastName": "<last-name>",
"email": "<email>",
"phoneNumber": "<phone-number>"
}

Get teacher:
GET - http://localhost:8080/SMS/api/v1/teachers/{id}
Utan {id} hämtas alla

Get teacher by last name:
GET - http://localhost:8080/SMS/api/v1/teachers/{last-name}
Query -> last-name : <teacher-last-name>

Update teacher name:
PATCH - http://localhost:8080/SMS/api/v1/teachers/name/{id}
Query - > first-name : <new-first-name>
last-name  : <new-last-name>

Remove teacher:
DELETE - http://localhost:8080/SMS/api/v1/teachers/{id}
-----------------------------------------------------------
SUBJECT PATHS:
Create subject:
POST - http://localhost:8080/SMS/api/v1/subjects/
{
"name":"<subject-name>",
"points":"<subject-points>"
}

Get subject:
GET - http://localhost:8080/SMS/api/v1/subjects/{id}
Utan {id} hämtas alla

Get subject by points:
GET - http://localhost:8080/SMS/api/v1/subjects/{points}
Query -> points : <subject-points>

Get subject participants information:
GET - http://localhost:8080/SMS/api/v1/subjects/{id}/students

Update  name:
PATCH - http://localhost:8080/SMS/api/v1/subjects/name/{id}
Query - > name : <new-name>

Remove subject:
DELETE - http://localhost:8080/SMS/api/v1/subjects/{id}