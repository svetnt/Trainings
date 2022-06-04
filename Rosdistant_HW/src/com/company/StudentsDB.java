package com.company;

import com.company.repository.FacultiesTable;
import com.company.repository.MarksTable;
import com.company.repository.StudentsTable;
import com.company.repository.SubjectsTable;
import org.h2.tools.DeleteDbFiles;

import java.sql.*;

public class StudentsDB {
    // Блок объявления констант
    public static final String DB_URL = "jdbc:h2:/Users/mariya.dolgopolova/IdeaProjects/Rosdistant_HW/db/students";
    public static final String DB_Driver = "org.h2.Driver";
    public static final String DB_DIR = "/Users/mariya.dolgopolova/IdeaProjects/Rosdistant_HW/db";
    public static final String DB_FILE = "students";

    // Таблицы СУБД
    StudentsTable students;
    SubjectsTable subjects;
    MarksTable marksTable;
    FacultiesTable faculties;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Инициализация
    public StudentsDB(boolean renew) throws SQLException, ClassNotFoundException {
        if (renew)
            DeleteDbFiles.execute(DB_DIR, DB_FILE, false);
        Class.forName(DB_Driver);
        // Инициализируем таблицы
        students = new StudentsTable();
        subjects = new SubjectsTable();
        marksTable = new MarksTable();
        faculties = new FacultiesTable();
    }

    // Создание всех таблиц и ключей между ними
    public void createTablesAndForeignKeys() throws SQLException {
        faculties.createTable();
        students.createTable();
        students.createForeignKeys();
        subjects.createTable();
        marksTable.createTable();
        marksTable.createForeignKeys();
    }

    public void fillTables() throws SQLException {
        faculties.addFaculties();

        students.addStudent("Иванов", 144, 1);
        students.addStudent("Петров", 144, 1);
        students.addStudent("Андреев", 205, 2);
        students.addStudent("Сидоров", 205, 2);
        students.deleteStudent(3);

        subjects.addSubjects();
        subjects.addSubject("Программирование");

        marksTable.addMark(5, 1, 1);
        marksTable.addMark(4, 1, 2);
        marksTable.addMark(2, 1, 4);
        marksTable.addMark(4, 2, 1);
        marksTable.addMark(4, 2, 2);
        marksTable.addMark(3, 2, 4);
        marksTable.addMark(5, 3, 1);
        marksTable.addMark(5, 3, 2);
        marksTable.addMark(5, 3, 4);
        marksTable.addMark(5, 4, 4);
    }


    public static void main(String[] args) {
        try{
            StudentsDB studentsDB = new StudentsDB(true);
            studentsDB.createTablesAndForeignKeys();
            studentsDB.fillTables();
            studentsDB.students.getStudents();
            studentsDB.subjects.getSubjects();
            studentsDB.marksTable.getMarks();
            studentsDB.marksTable.getMarksMath();
            studentsDB.marksTable.getMarksMathFaculty();
            studentsDB.marksTable.getMarks("Иванов");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
