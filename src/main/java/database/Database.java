package database;

import database.config.DatabaseProperties;
import database.config.PropertiesFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Database instance;

    private static final DatabaseProperties properties = PropertiesFactory.getProperties();

    public Database() {
        init();
    }

    public synchronized static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void init() {
        createSchema();
        createTable();
    }

    private void createSchema() {
        String query = """
                create schema if not exists scanword;
                """;
        execute(query);

    }

    private void createTable() {
        String query = """
                create table if not exists scanword.words(
                    id serial primary key,
                    word text,
                    definition text
                )
                """;

        execute(query);
    }

    public void insertWord(String word, String definition) {
        String sql = """
        insert into scanword.words
        (word, definition)
        values
        ('%s', '%s')
        """;
        execute(String.format(sql, word, definition));
    }

    public List<Map<String, String>> selectAll(String... columnNames) {
        List<Map<String, String>> result = new ArrayList<>();

        String sql = """
                select * from scanword.words
                """;

        try(Connection connection = connect();
            Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                Map<String, String> fields = new HashMap<>();
                for (String columnName : columnNames) {
                    fields.put(columnName, set.getString(columnName));
                }
                result.add(fields);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public void execute(String query) {
        try(Connection connection = connect();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Connection connect() throws Exception {
        return DriverManager.getConnection(
                properties.getUrl(),
                properties.getLogin(),
                properties.getPassword()
        );
    }

    public void clear() {
        String query = """
                TRUNCATE scanword.words RESTART IDENTITY
                """;
        execute(query);
    }

}
