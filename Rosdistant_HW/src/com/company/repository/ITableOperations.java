package com.company.repository;

import java.sql.SQLException;

// Операции с таблицами
public interface ITableOperations {
    void createTable() throws SQLException; // создание таблицы
    void createForeignKeys() throws SQLException; // создание связей между таблицами
}
