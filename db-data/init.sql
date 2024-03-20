CREATE DATABASE IF NOT EXISTS salary_prediction;
GO
USE salary_prediction;
GO
CREATE TABLE IF NOT EXISTS Candidate (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Education VARCHAR(15),
    Experience INT,
    Location VARCHAR(15),
    Job_Title VARCHAR(15),
    Age INT,
    Gender VARCHAR(15),
    Salary FLOAT
);
GO
CREATE TABLE IF NOT EXISTS Users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255)
);
GO
CREATE TABLE IF NOT EXISTS Roles (
                                     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255)
);
GO
CREATE TABLE IF NOT EXISTS Privileges (
                                     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255)
);
