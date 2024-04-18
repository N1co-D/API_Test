package petstore.utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    private final Properties properties;

    public ConfProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла!");
            throw new RuntimeException();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}