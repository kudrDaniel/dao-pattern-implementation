package ru.kudrdaniel.dao.pattern.implementation.entity.dao;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;
import ru.kudrdaniel.dao.pattern.implementation.db.ConnectionPool;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public abstract class AbstractDao<E, K> implements AutoCloseable {
    private Connection connection;

    public AbstractDao() throws SQLException {
        this.connection = ConnectionPool.getConnection();
    }

    public abstract List<E> getAll();

    public abstract E getEntityById(K id);

    public abstract E update(E entity);

    public abstract boolean delete(K id);

    public abstract boolean create(E entity);

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return ps;
    }

    public void closePrepareStatement(@NonNull PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
