package ru.kudrdaniel.dao.pattern.implementation.db.tables;

import lombok.extern.slf4j.Slf4j;
import ru.kudrdaniel.dao.pattern.implementation.utility.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public final class Users {
    public static final String USERS_TABLE = "users";
    public static final String USERS_ID = "id";
    public static final String USERS_FIRST_NAME = "first_name";
    public static final String USERS_LAST_NAME = "last_name";
    private static final String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + USERS_TABLE + ";";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + "("
            + USERS_ID + " bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,"
            + USERS_FIRST_NAME + " varchar(50) NOT NULL,"
            + USERS_LAST_NAME + " varchar(50) NOT NULL" + ");";

    public static void init() {
        try (Query query = new Query()) {
            query.execute(DROP_TABLE_USERS);
            query.execute(CREATE_TABLE_USERS);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
