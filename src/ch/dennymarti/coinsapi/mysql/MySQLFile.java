package ch.dennymarti.coinsapi.mysql;

import ch.dennymarti.coinsapi.CoinsPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLFile {

    private static final File file = new File("plugins/CoinsAPI/", "mysql.yml");
    private static final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

    private MySQL mysql = CoinsPlugin.getInstance().getMySQL();

    private String host;
    private String username;
    private String database;
    private String password;
    private String port;

    public void createFile() {
        if (!file.exists()) {
            yamlConfiguration.set("host", "127.0.0.1");
            yamlConfiguration.set("port", "3306");
            yamlConfiguration.set("database", "coinsapi");
            yamlConfiguration.set("username", "root");
            yamlConfiguration.set("password", "12345");

            try {
                yamlConfiguration.save(file);
            } catch (IOException ioException) {
                mysql.printException("Die mysql.yml Datei konnte nicht erstellt werden!", ioException.getMessage());
            }
        }
    }

    public void readFile() {
        if (file.exists()) {
            host = yamlConfiguration.getString("host");
            username = yamlConfiguration.getString("username");
            database = yamlConfiguration.getString("database");
            password = yamlConfiguration.getString("password");
            port = yamlConfiguration.getString("port");
        }
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public String getDatabase() {
        return this.database;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}