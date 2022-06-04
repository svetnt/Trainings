package com.company.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacultiesTable extends BaseTable implements ITableOperations{

    public FacultiesTable() throws SQLException {
        super("faculties");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS faculties(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL)", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
    }

    // Добавление факультетов
    public void addFaculties()  throws SQLException  {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO faculties (name) VALUES (?);");
        pstmt.setString(1, "Матмех");
        pstmt.execute();
        pstmt.setString(1, "Физфак");
        pstmt.execute();
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
        System.out.println("Добавляем факультеты");
    }
}
