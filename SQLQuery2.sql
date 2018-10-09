CREATE TABLE Mark
(
  studentID INT NOT NULL,
  subjectID INT NOT NULL,
  mark INT NOT NULL,
  PRIMARY KEY (studentID),
  UNIQUE (subjectID)
);

CREATE TABLE Subject
(
  subjectID INT NOT NULL,
  subjectName INT NOT NULL,
  semester INT NOT NULL,
  PRIMARY KEY (subjectID)
);

CREATE TABLE Teacher
(
  teacherID INT NOT NULL,
  teacherName INT NOT NULL,
  teacherDate INT NOT NULL,
  teacherGender INT NOT NULL,
  teacherAddress INT NOT NULL,
  position INT NOT NULL,
  PRIMARY KEY (teacherID)
);

CREATE TABLE Teach
(
  teacherID INT NOT NULL,
  subjectID INT NOT NULL,
  PRIMARY KEY (teacherID, subjectID),
  FOREIGN KEY (teacherID) REFERENCES Teacher(teacherID),
  FOREIGN KEY (subjectID) REFERENCES Subject(subjectID)
);

CREATE TABLE Student
(
  studentID INT NOT NULL,
  studentName INT NOT NULL,
  studentDoB INT NOT NULL,
  studentGender INT NOT NULL,
  studentAddress INT NOT NULL,
  classID INT NOT NULL,
  parentsID INT NOT NULL,
  PRIMARY KEY (studentID),
  FOREIGN KEY (studentID) REFERENCES Mark(studentID),
  UNIQUE (classID),
  UNIQUE (parentsID)
);

CREATE TABLE Class
(
  classID INT NOT NULL,
  className INT NOT NULL,
  studentID INT NOT NULL,
  PRIMARY KEY (classID),
  FOREIGN KEY (studentID) REFERENCES Student(studentID)
);

CREATE TABLE Parents
(
  parentsID INT NOT NULL,
  parentsName INT NOT NULL,
  parentsPhone INT NOT NULL,
  studentID INT NOT NULL,
  PRIMARY KEY (parentsID),
  FOREIGN KEY (studentID) REFERENCES Student(studentID),
  UNIQUE (studentID)
);

CREATE TABLE Study
(
  studentID INT NOT NULL,
  subjectID INT NOT NULL,
  PRIMARY KEY (studentID, subjectID),
  FOREIGN KEY (studentID) REFERENCES Student(studentID),
  FOREIGN KEY (subjectID) REFERENCES Subject(subjectID)
);
