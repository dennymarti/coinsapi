package ch.dennymarti.coinsapi;

import ch.dennymarti.coinsapi.api.CoinsAPIImpl;
import ch.dennymarti.coinsapi.api.ICoinsAPI;
import ch.dennymarti.coinsapi.commands.CoinsCommand;
import ch.dennymarti.coinsapi.listener.PlayerJoinListener;
import ch.dennymarti.coinsapi.mysql.MySQL;
import ch.dennymarti.coinsapi.mysql.MySQLFile;
import ch.dennymarti.coinsapi.utils.ConfigFile;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CoinsPlugin extends JavaPlugin {

    private ConfigFile configFile;
    private MySQLFile mysqlFile;
    private MySQL mysql;
    private ICoinsAPI coinsAPI;

    private static CoinsPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        configFile = new ConfigFile();
        configFile.createFile();

        mysqlFile = new MySQLFile();
        mysqlFile.createFile();
        mysqlFile.readFile();

        mysql = new MySQL(mysqlFile.getHost(), mysqlFile.getPort(), mysqlFile.getDatabase(),
                mysqlFile.getUsername(), mysqlFile.getPassword());
        mysql.connect();
        mysql.createTable();

        if (mysql.isConnected()) {
            Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
            if (configFile.isCoinsCommandEnabled()) {
                getCommand("coins").setExecutor(new CoinsCommand());
            }
        }
        coinsAPI = new CoinsAPIImpl();
    }

    @Override
    public void onDisable() {
        mysql.disconnect();
    }

    public static CoinsPlugin getInstance() {
        return instance;
    }

    public MySQL getMySQL() {
        return mysql;
    }

    public ICoinsAPI getCoinsAPI() {
        return coinsAPI;
    }
}
