package database.config;

import java.io.InputStream;

public class PropertiesFactory {
    private static DatabaseProperties properties;

    private PropertiesFactory() {}

    public synchronized static DatabaseProperties getProperties() {
        if (properties == null) {
            init();
        }
        return properties;
    }

    private static void init() {
        String fileNameProperties = "application.properties";

        properties = new DatabaseProperties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream stream = loader.getResourceAsStream(fileNameProperties)) {
            properties.load(stream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
