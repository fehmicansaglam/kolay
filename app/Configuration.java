import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import play.Play;
import play.exceptions.UnexpectedException;
import play.utils.Properties;

public final class Configuration {

    private static Properties _properties;

    private Configuration() {

    }

    public static void load() {

        _properties = new Properties();
        try {
            _properties.load(new FileInputStream(Play.configuration.getProperty("kolay.conf")));
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(e);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    public static void store() {

        try {
            _properties.store(new FileOutputStream(Play.configuration.getProperty("kolay.conf")));
        } catch (FileNotFoundException e) {
            throw new UnexpectedException(e);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    public static void update(String key, String value, boolean batch) {

        _properties.put(key, value);
        if (!batch)
            store();
    }

    public static String get(String key) {

        return _properties.get(key);
    }

    public static Properties properties() {

        Properties properties = new Properties();
        properties.putAll(_properties);
        return properties;
    }

}
