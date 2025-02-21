# Hospital Management System - Backend
![image](https://github.com/user-attachments/assets/c220dfae-b0eb-48cd-b3ce-6262dbead006)

![image](https://github.com/user-attachments/assets/d2e4a295-3d0d-4e07-8b7a-77631ba7ee7c)


![image](https://github.com/user-attachments/assets/f5c2784c-3eb0-4f1f-a9d2-9b5701bdaa5b)


![image](https://github.com/user-attachments/assets/84faeca8-1934-49b2-993d-dacf456d6d69)


Welcome to the Hospital Management System backend project!

This is a Spring Boot application designed to manage hospital operations, including patient and doctor records, diagnoses, prescriptions, and reports. 

The system is built with scalability, efficiency, and ease of use in mind.

# Features

CRUD Operations: Manage doctors and patients with Create, Read, Update, and Delete functionalities.

Diagnosis and Prescriptions: Create patient diagnoses and generate prescriptions.

Reporting: Generate detailed reports for patients and doctors.

Relational Database: Built with MySQL for efficient data handling and storage.

Testing: Comprehensive unit testing using JUnit 5 to ensure reliability.

Documentation: Fully documented domain and service classes using javaDoc.

# Technologies Used

Backend: Spring Boot, Java 17

Database: MySQL

API Documentation: javaDoc

Testing: JUnit 5

Build Tool: Maven

Getting Started

Prerequisites

Before running the application, ensure you have the following installed:

Java 17 or higher

MySQL 8.0 or higher

Maven 3.8 or higher

IDE (e.g., IntelliJ IDEA, Eclipse)

# Installation

1. Clone the Repository:
    git clone https://github.com/Braxon2/hospital-menagement.git
    cd hospital-menagement
2. Set Up MySQL Database:

Create a database named hospital_management in MySQL.

Update the application.properties file with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management

spring.datasource.username=your_username

spring.datasource.password=your_password

3. Build the project 
mvn clean install

4.Run the Application:
mvn spring-boot:run

5.Access the API:

The application will run on http://localhost:8080.

Use tools like Postman or cURL to interact with the API endpoints.

## API Endpoints
Here are some of the key endpoints available in the application:

# Doctors
GET /api/doctors - Get all doctors.

GET /api/doctors/{id} - Get a doctor by ID.

POST /api/doctors - Create a new doctor.

PUT /api/doctors/{id} - Update a doctor by ID.

DELETE /api/doctors/{id} - Delete a doctor by ID.

# Patients
GET /api/patients - Get all patients.

GET /api/patients/{id} - Get a patient by ID.

POST /api/patients - Create a new patient.

PUT /api/patients/{id} - Update a patient by ID.

DELETE /api/patients/{id} - Delete a patient by ID.

# Diagnoses and Prescriptions
POST /api/diagnoses - Create a new diagnosis.

POST /api/prescriptions - Generate a prescription.

## Testing

The project includes comprehensive unit tests for domain and service classes using JUnit 5. To run the tests, use the following command:
mvn test

## Documentation
The project is fully documented using javaDoc.
