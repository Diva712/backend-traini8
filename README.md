# Government Training Centers Registry - MVP
## Overview
This project is a **Spring Boot** application that provides APIs for managing government-funded training centers. It allows users to create and retrieve training center details, with filtering options for **state, city, and courses offered**.
## Project Structure
```
Gov-Training-Centers
│── src/main/java
│   ├── com.example.govtrainingcenters
│   │   ├── config
│   │   │   ├── ApplicationConfig.java
│   │   ├── controller
│   │   │   ├── TrainingCenterController.java
│   │   ├── dao
│   │   │   ├── TrainingCenterDao.java (extends JpaRepository)
│   │   ├── dto
│   │   │   ├── AddressDTO.java
│   │   │   ├── TrainingCenterDTO.java
│   │   ├── entity
│   │   │   ├── Address.java
│   │   │   ├── TrainingCenter.java
│   │   ├── exception
│   │   │   ├── GlobalExceptionHandler.java
│   │   ├── requestpojo
│   │   │   ├── AddressRequest.java
│   │   │   ├── TrainingCenterRequest.java
│   │   ├── responsepojo
│   │   │   ├── AddressResponse.java
│   │   │   ├── TrainingCenterResponse.java
│   │   │   ├── ApiErrorResponse.java
│   │   │   ├── ApiResponse.java
│   │   ├── service
│   │   │   ├── TrainingCenterService.java (interface)
│   │   │   ├── TrainingCenterServiceImpl.java (implementation)
```
## Getting Started

### Clone the Repository
To run this Spring Boot application, first clone the repository using the following command:

```sh
git clone https://github.com/Diva712/backend-traini8.git
```
or manually copy the code from GitHub.

## Requirements
For building and running the application you need:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/downloads/#java17?er=221886)
- [Spring Tool Suite (STS)](https://spring.io/tools)
- [MySQL Server](https://dev.mysql.com/downloads/)
- [Postman](https://www.postman.com/downloads/)
  
## Setup Your Database
1. Download MySQL Database
2. Once database server install create a database with name "trainingdb", using command in MySQL command client ```bash CREATE DATABASE trainingdb; ```
3. After that change the application.properties configuration based on the your database configuration.
4. Modify the src/main/resources/application.properties file with your MySQL credentials.
   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/trainingdb
    spring.datasource.username=your_username # Replace with your MySQL username
    spring.datasource.password=your_password # Replace with your MySQL password
   ```

## Running the application locally
Import cloned project inside the STS IDE using import exsisting maven project option provided inside the IDE.
Once import inside the IDE , there are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.govtrainingcenters.GovTrainingCentersApplication` class from your IDE.

Alternatively We have a STS IDE so there is a inbuilt support for maven so we can run maven command easily.
right click on project -> run as -> maven build -> hit this command on Goal Section
```shell
  clean package
```
```shell
clean spring-boot:run
```

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```
## API Endpoints
Use Postman Tool For Testing API Endpoints.
### 1. Create a Training Center
**POST**: `http://localhost:8080/api/v1/training-centers`

#### Request Body (JSON)
```json
{
    "centerName": "Tech Kuku23 Academy",
    "centerCode": "ABC1232XYZ72",
    "address": {
        "detailedAddress": "123, Knowledge Park",
        "city": "Mumbai",
        "state": "Maharashtra",
        "pincode": "560001"
    },
    "studentCapacity": 200,
    "coursesOffered": ["Kotlin"],
    "contactEmail": "dd@gmail.com",
    "contactPhone": "9876542210"
}
```

#### Response (JSON)
```json
{
    "message": "Training Center created successfully",
    "status": 201,
    "data": {
        "id": 15,
        "centerName": "Tech Kuku23 Academy",
        "centerCode": "ABC1232XYZ72",
        "address": {
            "detailedAddress": "123, Knowledge Park",
            "city": "Mumbai",
            "state": "Maharashtra",
            "pincode": "560001"
        },
        "studentCapacity": 200,
        "coursesOffered": ["Kotlin"],
        "contactEmail": "dd@gmail.com",
        "contactPhone": "9876542210",
        "createdOn": 1739529596432
    }
}
```

### 2. Get All Training Centers
**GET**: `http://localhost:8080/api/v1/training-centers`

#### Response Example (JSON)
```json
{
    "message": "Training Centers retrieved successfully",
    "status": 200,
    "data": [
        {
            "id": 3,
            "centerName": "Tech Innovators Academy",
            "centerCode": "ABC123XYZ782",
            "address": {
                "detailedAddress": "123, Knowledge Park",
                "city": "Bangalore",
                "state": "Karnataka",
                "pincode": "560001"
            },
            "studentCapacity": 200,
            "coursesOffered": ["Java", "Spring Boot", "Microservices", "DevOps"],
            "contactEmail": "dd@gmail.com",
            "contactPhone": "9876543210",
            "createdOn": 1739472921944
        }
    ]
}
```

### 3. Filter Training Centers
- **By State**: `GET http://localhost:8080/api/v1/training-centers?state=Maharashtra`
- **By City**: `GET http://localhost:8080/api/v1/training-centers?city=Pune`
- **By Course**: `GET http://localhost:8080/api/v1/training-centers?course=Java`
- **Multiple Filters**: `GET http://localhost:8080/api/v1/training-centers?course=Java&state=Maharashtra`

### Error Handling
#### Validation Error (400)
```json
{
  "statusCode": 400,
  "message": "contactEmail: must be a valid email",
  "path": "/api/training-centers",
  "timestamp": "2024-02-13T15:30:10"
}
```

#### Entity Not Found (404)
```json
{
  "statusCode": 404,
  "message": "Training center not found",
  "path": "/api/training-centers/100",
  "timestamp": "2024-02-13T15:30:10"
}
```

#### Internal Server Error (500)
```json
{
  "statusCode": 500,
  "message": "An unexpected error occurred. Please try again later.",
  "path": "/api/training-centers",
  "timestamp": "2024-02-13T15:30:10"
}
```

## Object Mapping in Project
- **POJO Objects**: Used for handling incoming request data.
- **DTO Objects**: Used for transforming and carrying data between layers.
- **Entity Objects**: Used for database interactions.
- **Flow:** `POJO Object → DTO Object → Entity Object`(vice-versa) (ModelMapper is used for conversions).

## Future Enhancements
1. **Pagination**: Implement pagination for large training center lists.
2. **Sorting**: Add sorting based on time, alphabetical order, etc.
3. **Dockerization**: Containerize the project for easy deployment.
---

I have submitted my task and ensured that all requirements are met. I am looking forward to the next step in the recruitment process.

