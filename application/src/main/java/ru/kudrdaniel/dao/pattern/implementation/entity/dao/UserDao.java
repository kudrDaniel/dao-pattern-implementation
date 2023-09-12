package ru.kudrdaniel.dao.pattern.implementation.entity.dao;

import lombok.extern.slf4j.Slf4j;
import ru.kudrdaniel.dao.pattern.implementation.db.tables.Users;
import ru.kudrdaniel.dao.pattern.implementation.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static ru.kudrdaniel.dao.pattern.implementation.db.tables.Users.*;

@Slf4j
public final class UserDao extends AbstractDao<User, Long> {
    public static final String SELECT_ALL_USERS = "SELECT * FROM " + USERS_TABLE + ";";
    public static final String INSERT_INTO_USERS = "INSERT INTO " + USERS_TABLE + "(" + USERS_FIRST_NAME + ", " + USERS_LAST_NAME + ") VALUES"
            + "(?, ?);";

    public UserDao() throws SQLException {
        super();
    }

    @Override
    public List<User> getAll() {
        List<User> lst = new LinkedList<>();
        PreparedStatement ps = super.getPrepareStatement(SELECT_ALL_USERS);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                lst.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            super.closePrepareStatement(ps);
        }

        return lst;
    }

    @Override
    public User getEntityById(Long id) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        boolean result = false;
        PreparedStatement ps = super.getPrepareStatement(INSERT_INTO_USERS);
        try {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.execute();
            result = true;
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            super.closePrepareStatement(ps);
        }
        return result;
    }
}
