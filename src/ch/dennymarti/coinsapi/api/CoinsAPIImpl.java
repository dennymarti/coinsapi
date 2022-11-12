package ch.dennymarti.coinsapi.api;

import ch.dennymarti.coinsapi.CoinsPlugin;
import ch.dennymarti.coinsapi.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinsAPIImpl implements ICoinsAPI {

    private final MySQL mysql = CoinsPlugin.getInstance().getMySQL();

    @Override
    public String getPrefix() {
        return "§8» §bCoins §8● §7";
    }

    @Override
    public String getNoPermissions() {
        return getPrefix() + "Dafür hast du keine §bRechte§8!";
    }

    @Override
    public Integer getCoins(UUID uuid) {
        int coins = 0;
        if (isRegistered(uuid)) {
            try {
                ResultSet result = mysql.getResult("SELECT coins FROM playercoins " +
                        "WHERE uuid = '" + uuid + "';");
                if (result.next()) {
                    coins = result.getInt("coins");
                }
            } catch (SQLException sqlException) {
                mysql.printException("Eine SQL-Abfrage konnte nicht ausgefuehrt werden!", sqlException.getMessage());
            }
        }
        return coins;
    }

    @Override
    public void addCoins(UUID uuid, int coins) {
        setCoins(uuid, getCoins(uuid) + coins);
    }

    @Override
    public void removeCoins(UUID uuid, int coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }

    @Override
    public void setCoins(UUID uuid, int coins) {
        if (isRegistered(uuid)) {
            if (coins < 0) {
                coins = 0;
            }
            mysql.executeQuery("UPDATE playercoins SET coins = '" + coins + "' " +
                    "WHERE uuid = '" + uuid + "';");
        }
    }

    @Override
    public boolean isRegistered(UUID uuid) {
        boolean registered = false;
        try {
            ResultSet result = mysql.getResult("SELECT * FROM playercoins " +
                    "WHERE uuid = '" + uuid.toString() + "';");
            if (result.next()) {
                registered = true;
            }
        } catch (SQLException sqlException) {
            mysql.printException("Eine SQL-Abfrage konnte nicht ausgefuehrt werden!", sqlException.getMessage());
        }
        return registered;
    }

    @Override
    public void register(String playername, UUID uuid) {
        if (!isRegistered(uuid)) {
            mysql.executeQuery("INSERT INTO playercoins (playername, uuid, coins) VALUES (" +
                    "'" + playername + "', '" + uuid + "', '0');");
        }
    }
}
