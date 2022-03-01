# student-management-system # S.M.S

CREATE STUDENT:
POST - http://localhost:8080/SMS/api/v1/student/
{
"firstName": "Petter",
"lastName": "Bergström",
"email": "petter.bergstrom@iths.se",
"phoneNumber": "031"
}

GET STUDENT:
GET - http://localhost:8080/SMS/api/v1/student/{id}
Utan {id} hämtas alla

GET BY LAST NAME:
GET - http://localhost:8080/SMS/api/v1/student/{last-name}
Query -> last-name : <student-last-name>

UPDATE STUDENT:
PATCH - http://localhost:8080/SMS/api/v1/student/name/{id}
Query - > first-name : <new-first-name>
last-name  : <new-last-name>

REMOVE STUDENT:
DELETE - http://localhost:8080/SMS/api/v1/student/{id}