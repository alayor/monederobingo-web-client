package com.lealpoints.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DbBuilder<T> {

    public abstract String sql();

    public abstract Object[] values();

    public abstract T build(ResultSet resultSet) throws SQLException;
}