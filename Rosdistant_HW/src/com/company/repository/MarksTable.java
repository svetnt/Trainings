package com.company.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksTable extends BaseTable implements ITableOperations{

    public MarksTable() throws SQLException {
        super("marks");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS marks(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "mark integer NOT NULL," +
                "subject BIGINT NOT NULL," +
                "student BIGINT NOT NULL)", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement(" ALTER TABLE marks ADD FOREIGN KEY (subject) REFERENCES subjects(id)",
                "Cоздан внешний ключ marks.subject -> subjects.id");
        super.executeSqlStatement(" ALTER TABLE marks ADD FOREIGN KEY (student) REFERENCES students(id)",
                "Cоздан внешний ключ marks.student -> students.id");
    }

    // Добавление оценки
    public void addMark(int mark, int subject, int student)  throws SQLException  {
        String sql = "INSERT INTO marks (mark, subject, student) VALUES (" + mark + ", " + subject +  ", " + student + ");";
        String description = "Добавляем оценку " + mark + " по предмету " + subject + " студенту " + student;
        executeSqlStatement(sql, description);
    }

    // Исправление оценки
    public void updateMark(int id, int newValue)  throws SQLException  {
        String sql = "UPDATE marks SET mark=" + newValue + " WHERE id=" + id + ";";
        String description = "Исправляем оценку с id=" + id + " , новая оценка - " + newValue;
        executeSqlStatement(sql, description);
    }

    // Получение списка оценок
    public void getMarks()  throws SQLException  {
        System.out.println();
        System.out.println("Список оценок:");
        String sql = "select students.name, subjects.name, marks.mark, faculties.name from students, subjects, marks, faculties " +
                "where students.id=marks.student and subjects.id=marks.subject and faculties.id=students.faculty";
        ResultSet rs = executeSqlStatementWithResult(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String subject = rs.getString(2);
            int mark = rs.getInt(3);
            String faculty = rs.getString(4);
            System.out.printf("Имя студента: %s, факультет: %s, предмет: %s, оценка: %d %n", name, faculty, subject, mark);
        }
        closeStatements();
    }

    // Получение списка оценок по математике
    public void getMarksMath()  throws SQLException  {
        System.out.println();
        System.out.println("Список оценок по математике:");
        String sql = "select students.name, subjects.name, marks.mark, faculties.name from students, subjects, marks, faculties " +
                "where students.id=marks.student and subjects.id=marks.subject and faculties.id=students.faculty" +
                " and subjects.id = 1";
        ResultSet rs = executeSqlStatementWithResult(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String subject = rs.getString(2);
            int mark = rs.getInt(3);
            String faculty = rs.getString(4);
            System.out.printf("Имя студента: %s, факультет: %s, предмет: %s, оценка: %d %n", name, faculty, subject, mark);
        }
        closeStatements();
    }

    // Получение списка оценок студентов матмеха
    public void getMarksMathFaculty()  throws SQLException  {
        System.out.println();
        System.out.println("Список оценок студентов матмеха:");
        String sql = "select students.name, subjects.name, marks.mark, faculties.name from students, subjects, marks, faculties " +
                "where students.id=marks.student and subjects.id=marks.subject and faculties.id=students.faculty" +
                " and faculties.id = 1";
        ResultSet rs = executeSqlStatementWithResult(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String subject = rs.getString(2);
            int mark = rs.getInt(3);
            String faculty = rs.getString(4);
            System.out.printf("Имя студента: %s, факультет: %s, предмет: %s, оценка: %d %n", name, faculty, subject, mark);
        }
        closeStatements();
    }

    // Получение списка оценок студента
    public void getMarks(String studentName)  throws SQLException  {
        System.out.println();
        System.out.println("Список оценок студента с фамилией " + studentName);
        String sql = "select students.name, subjects.name, marks.mark, faculties.name from students, subjects, marks, faculties " +
                "where students.id=marks.student and subjects.id=marks.subject and faculties.id=students.faculty" +
                " and students.name = '" + studentName + "'";
        ResultSet rs = executeSqlStatementWithResult(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String subject = rs.getString(2);
            int mark = rs.getInt(3);
            String faculty = rs.getString(4);
            System.out.printf("Имя студента: %s, факультет: %s, предмет: %s, оценка: %d %n", name, faculty, subject, mark);
        }
        closeStatements();
    }
}
