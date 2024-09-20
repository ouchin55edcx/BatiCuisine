    package org.ouchin.config;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DatabaseConfig {

        private static final String DB_URL = "jdbc:postgresql://localhost:5432/baticuisine";
        private static final String USER = "batiuser";
        private static final String PASS = "ouchin55edcx";

        private static DatabaseConfig instance;
        private final Connection connection;

        private DatabaseConfig(){
            try {
                connection = DriverManager.getConnection(DB_URL,USER,PASS);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static DatabaseConfig getInstance(){
            if (instance == null){
                instance = new DatabaseConfig();
            }
            return instance;
        }

        public Connection getConnection(){
            return connection;
        }
    }
