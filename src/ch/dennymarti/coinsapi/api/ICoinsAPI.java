package ch.dennymarti.coinsapi.api;

import java.util.UUID;

public interface ICoinsAPI {

    String getPrefix();

    String getNoPermissions();

    Integer getCoins(UUID uuid);

    void addCoins(UUID uuid, int coins);

    void removeCoins(UUID uuid, int coins);

    void setCoins(UUID uuid, int coins);

    boolean isRegistered(UUID uuid);

    void register(String playername, UUID uuid);
}
