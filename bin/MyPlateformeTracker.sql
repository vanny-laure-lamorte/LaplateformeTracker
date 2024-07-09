-- Active: 1707919785972@@localhost@3306@plateformetracker
CREATE DATABASE PlateformeTracker;
-- DROP DATABASE PlateformeTracker;


USE PlateformeTracker;

CREATE TABLE login (
    studentID INT,
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    age INT,
    field VARCHAR(255), 
    averageGrade DOUBLE DEFAULT 0
);

INSERT INTO student(firstName, lastName, age, field ) VALUES
('Lucas','Martinie', '28', 'Software'),
('Thanh','Lemelle', '30', 'Cyber'),
('Vanny','Lamorte', '18', 'IA'),
('Lucy','Madec', '21', 'Web'),
('Alicia','Cordial', '22', 'Software');

CREATE TABLE grade (
    studentId VARCHAR(255),
    grade VARCHAR(255),
    date DATE,
    subjectName VARCHAR(255)
);

INSERT INTO grade(studentId, grade, date, subjectName) VALUES
('1','15','2024-07-05', "Anglais" ),
('1','18','2024-07-06', "Python" ),
('1','19','2024-07-04', "JavaScript" ),
('2','15','2024-07-02',"Anglais" ),
('2','15','2024-07-02',"VM" ),
('2','15','2024-07-02',"SQL" ),
('3','15', '2024-07-02', "Anglais"),
('3','19', '2024-07-03', "SQL"),
('3','15', '2024-07-02', "Python"),
('4','9', '2024-07-04',"Anglais" ),
('4','9', '2024-07-04',"Python" ),
('4','9', '2024-07-04',"Anglais" ),
('4','9', '2024-07-04',"Anglais" ),
('4','9', '2024-07-04',"Python" ),
('5','20','2024-07-05', "JavaScript");


