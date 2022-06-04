package com.company.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectsTable extends BaseTable implements ITableOperations{

    public SubjectsTable() throws SQLException {
        super("subjects");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS subjects(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL)", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
    }

    // Добавление предмета
    public void addSubject(String name)  throws SQLException  {
        String sql = "INSERT INTO subjects (name) VALUES ('" + name + "');";
        String description = "Добавляем Предмет " + name;
        executeSqlStatement(sql, description);
    }

    // Добавление 3 основных предметов
    public void addSubjects()  throws SQLException  {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO subjects (name) VALUES (?);");
        pstmt.setString(1, "Математика");
        pstmt.execute();
        pstmt.setString(1, "Информатика");
        pstmt.execute();
        pstmt.setString(1, "Русский язык");
        pstmt.execute();
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
        System.out.println("Добавляем 3 основных предмета");
    }

    // Получить список предметов
    public void getSubjects()  throws SQLException  {
        System.out.println("Список предметов:");
        String sql = "select * from subjects";
        ResultSet rs = executeSqlStatementWithResult(sql);
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            System.out.printf("id: %d, name: %s %n", id, name);
        }
        closeStatements();
    }
}
