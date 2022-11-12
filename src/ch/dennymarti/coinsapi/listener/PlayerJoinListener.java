package ch.dennymarti.coinsapi.listener;

import ch.dennymarti.coinsapi.api.ICoinsAPI;
import ch.dennymarti.coinsapi.CoinsPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ICoinsAPI coinsAPI = CoinsPlugin.getInstance().getCoinsAPI();

        if (!coinsAPI.isRegistered(player.getUniqueId())) {
            coinsAPI.register(player.getName(), player.getUniqueId());
        }
    }
}
