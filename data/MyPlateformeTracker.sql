-- Active: 1707919785972@@localhost@3306@plateformetracker
CREATE DATABASE PlateformeTracker;
-- DROP DATABASE PlateformeTracker;

USE PlateformeTracker;

CREATE TABLE login (
    studentID INT,
    email VARCHAR(255),
    password VARCHAR(255)
);

INSERT INTO login(studentID, email, password ) VALUES
(1,'lucasM@gmail.com','009d7b6c160458832d80582fb8132d9f259bccd8e5b7ca3051732b3187bcb44e'),
(2,'thanhL@gmail.com', '6b16fe348f3f9a5da3912b0eb0f76333e3099105443787396280867fbb33de30'),
(3,'vannyL@gmail.com', '4551bf0b433c1aa310fb34420a640c825eca79969117c95f32b8bddbe0c15d72'),
(4,'lucyM@gmail.com','c2fd916be68a5de81748dbfea16ae3b42a02a7c33b6e85d7ab0e8c514ba89cd2'),
(5,'aliciaC@gmail.com', '3bd44b4351597dfb632ff7617e95d5d9d00c0ce84761decf06bd3ecc44157312');

-- Lucas: lucasL@gmail.com, lucasM123!
-- Thanh: thanhL@gmail.com, thanhL123!
-- Vanny: vannyL@gmail.com, vannyL123!
-- Lucy: lucyM@gmail.com, lucyM123!
-- Alicia: aliciaC@gmail.com, aliciaC123!

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
('Elise','Martinie', '22', 'Software'),
('Clément','Martinie', '22', 'Software'),
('Camille','Noe', '28', 'Software'),
('Thanh','Lemelle', '30', 'Cyber'),
('Noah','Lemelle', '18', 'Cyber'),
('Vanny','Lamorte', '18', 'IA'),
('Gerard','Lamorte', '33', 'IA'),
('Claire','Lamorte', '33', 'IA'),
('Christine','Guediguian','33', 'IA'),
('Lucille','Caron', '29', 'IA'),
('Thibault','Caron', '21', 'IA'),
('Lucy','Madec', '21', 'Web'),
('Loan','Madec', '22', 'Web'),
('Lucas','Iribaren', '20', 'Web'),
('Hugo','Esquer', '33', 'Web'),
('Alicia','Cordial', '28', 'DPO'),
('Celine','Dubois', '32', 'DPO'),
('Marie','Dubois', '40', 'DPO'),
('Jean','Dubois', '40', 'DPO');

DROP TABLE grades;
CREATE TABLE grades (
    studentId VARCHAR(255),
    grade DOUBLE,
    date DATE,
    subjectName VARCHAR(255)
);

INSERT INTO grades(studentId, grade, date, subjectName) VALUES
('1',15,'2024-07-05', "English" ),
('1',18,'2024-07-06', "Python" ),
('1',19,'2024-07-04', "JavaScript" ),
('2',15,'2024-07-02',"English" ),
('2',15,'2024-07-02',"VM" ),
('2',15,'2024-07-02',"SQL" ),
('3',15, '2024-07-02', "English"),
('3',19, '2024-07-03', "SQL"),
('3',15, '2024-07-02', "Python"),
('4',9, '2024-07-04',"English" ),
('4',12, '2024-07-04',"Python" ),
('4',14, '2024-07-04',"English" ),
('4',13, '2024-07-04',"English" ),
('4',10, '2024-07-04',"Python" ),
('5',5,'2024-07-05', "JavaScript")
('5',15, '2024-07-02', "English"),
('5',19, '2024-07-03', "SQL"),
('6',5, '2024-07-02', "Python"),
('6',9, '2024-07-04',"English" ),
('6', 10, '2024-07-04',"Python" ),
('7',10, '2024-07-04',"English" ),
('7',4, '2024-07-04',"English" ),
('7',5, '2024-07-04',"Python" );


