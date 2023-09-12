package ru.kudrdaniel.dao.pattern.implementation.db;

import ru.kudrdaniel.dao.pattern.implementation.db.tables.Users;
import ru.kudrdaniel.dao.pattern.implementation.utility.Query;

import java.sql.ResultSet;

public final class Initialize {
    public static void initDB() {
        Users.init();
    }
}
