package lt.codeacademy;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
    private  static final String NAME = "connection.properties";

    //single klase - objektas, kuri aplikacijoje galima sukurti viena karta

    private static ApplicationProperties instance;
    private final Properties properties;

    private ApplicationProperties(){
        properties = new Properties();
        try (InputStream stream = this.getClass().getResourceAsStream(NAME)) {
            properties.load(stream);
        }catch (Exception e) {
            System.out.printf("Nepavyko nuskaityti %s failo\n", NAME);
        }
    }

    public static ApplicationProperties getInstance(){
        if(instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public String getValue(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
