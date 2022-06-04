package com.company.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsTable extends BaseTable implements ITableOperations{

    public StudentsTable() throws SQLException {
        super("students");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS students(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "faculty BIGINT NOT NULL," +
                "group_number integer NOT NULL)", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement(" ALTER TABLE students ADD FOREIGN KEY (faculty) REFERENCES faculties(id)",
                "Cоздан внешний ключ students.faculty -> faculties.id");
    }

    // Добавление студента
    public void addStudent(String name, int group, int faculty)  throws SQLException  {
        String sql = "INSERT INTO students (name, group_number, faculty) VALUES ('" + name + "', " + group + ", " + faculty + ");";
        String description = "Добавляем студента " + name + " из группы " + group + " с факультета " + faculty;
        executeSqlStatement(sql, description);
    }

    // Удаление студента
    public void deleteStudent(int id)  throws SQLException  {
        String sql = "DELETE FROM students WHERE id=" + id + ";";
        String description = "Удаляем студента с id=" + id;
        executeSqlStatement(sql, description);
    }

    // Получение списка студентов
    public void getStudents()  throws SQLException  {
        System.out.println("Список студентов:");
        String sql = "select * from students";
        ResultSet rs = executeSqlStatementWithResult(sql);
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int group = rs.getInt(3);
            System.out.printf("id: %d, name: %s, group: %d %n", id, name, group);
        }
        closeStatements();
    }
}
