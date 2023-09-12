package ru.kudrdaniel.dao.pattern.implementation.db;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public final class ConnectionPool {
    private static final JdbcConnectionPool connectionPool;

    static {
        String dbPath = Paths.get("db", "database").toAbsolutePath().toString();
        connectionPool = JdbcConnectionPool.create("jdbc:h2:" + dbPath, "app", "_App3412");
        log.info("Created connection pool for :\"" + dbPath + "\" database");
    }

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
}
