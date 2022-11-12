package ch.dennymarti.coinsapi.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private static final File file = new File("plugins/CoinsAPI/", "config.yml");
    private static final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

    public void createFile() {
        if (!file.exists()) {
            yamlConfiguration.set("Coins-Commands", true);
            try {
                yamlConfiguration.save(file);
            } catch (IOException ioException) {
                System.out.println("[CoinsAPI] config.yml-Datei konnte nicht erstellt werden.");
                System.out.println(ioException.getMessage());
            }
        }
    }

    public boolean isCoinsCommandEnabled() {
        return yamlConfiguration.getBoolean("Coins-Commands");
    }
}
