package com.lealpoints.repository.fixtures;

public class ConfigurationRepositoryFixture {

    public static final String INSERT_CONFIGURATION =
            "INSERT INTO configuration (name, description, value) VALUES" +
                    "  ('name1', 'desc1', 'value1')," +
                    "  ('name2', 'desc2', 'value2')," +
                    "  ('name3', 'desc3', 'value3');";
}
