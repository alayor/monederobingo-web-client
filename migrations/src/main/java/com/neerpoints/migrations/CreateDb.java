package com.neerpoints.migrations;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.neerpoints.migrations.db.DevelopmentDatabaseManager;
import com.neerpoints.migrations.db.UnitTestDatabaseManager;
import com.neerpoints.migrations.util.DBUtil;
import org.apache.commons.io.FileUtils;

public class CreateDb {

    private DevelopmentDatabaseManager _developmentDatabaseManager = new DevelopmentDatabaseManager();

    public static void main(String[] args) throws Exception {
        System.out.println("Creating database...");
        CreateDb createDb = new CreateDb();
        createDb.run();
        System.out.println("Database create successfully.");
        Migrate.main(null);
    }

    private void run() throws Exception {
        File file = new File("./database/scripts/createdb.sql");
        String sql = FileUtils.readFileToString(file);
        Connection connection = _developmentDatabaseManager.getConnection(false);
        DBUtil.executeSql(sql, connection);
        runSetupScripts();
    }

    private void runSetupScripts() throws Exception {
        final File[] scripts = loadSetupScripts();
        Connection unitTestConnection = new UnitTestDatabaseManager().getConnection();
        Connection developmentConnection = new DevelopmentDatabaseManager().getConnection();
        for (File script : scripts) {
            System.out.println(script.getName());
            DBUtil.executeScript(script, unitTestConnection);
            DBUtil.executeScript(script, developmentConnection);
        }
    }

    private File[] loadSetupScripts() {
        File dir = new File("./database/scripts/setup");
        File[] filesArray = dir.listFiles();
        List<File> filesFromSetup = new ArrayList<>();
        if (filesArray != null) {
            filesFromSetup = Arrays.asList(filesArray);
        }
        final File[] totalArrayFiles = new File[filesFromSetup.size()];
        filesFromSetup.toArray(totalArrayFiles);
        return totalArrayFiles;
    }
}