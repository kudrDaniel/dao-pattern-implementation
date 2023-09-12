package ru.kudrdaniel.dao.pattern.implementation.utility;

import lombok.extern.slf4j.Slf4j;
import ru.kudrdaniel.dao.pattern.implementation.db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public final class Query implements AutoCloseable {
    private final Connection connection;

    public Query() throws SQLException {
        this.connection = ConnectionPool.getConnection();
    }

    public void execute(String sql) throws SQLException {
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
