package ch.dennymarti.coinsapi.commands;

import ch.dennymarti.coinsapi.api.ICoinsAPI;
import ch.dennymarti.coinsapi.CoinsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player)commandSender;

            ICoinsAPI coinsAPI = CoinsPlugin.getInstance().getCoinsAPI();

            if (args.length == 0) {
                player.sendMessage(coinsAPI.getPrefix() + "Du hast §b" + coinsAPI.getCoins(player.getUniqueId()) + " §7Coins§8.");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("coins.add")) {
                        player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins add §8« §bSpieler §8» « §bCoins §8»");
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (player.hasPermission("coins.remove")) {
                        player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins remove §8« §bSpieler §8» « §bCoins §8»");
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (player.hasPermission("coins.set")) {
                        player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins set §8« §bSpieler §8» « §bCoins §8»");
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage("");
                    player.sendMessage(" §8➜ §7/coins §8« §bSpieler §8»");
                    player.sendMessage(" §8➜ §7/coins add §8« §bSpieler §8» « §bCoins §8»");
                    player.sendMessage(" §8➜ §7/coins remove §8« §bSpieler §8» « §bCoins §8»");
                    player.sendMessage(" §8➜ §7/coins set §8« §bSpieler §8» « §bCoins §8»");
                    player.sendMessage("");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null && coinsAPI.isRegistered(target.getUniqueId())) {
                        player.sendMessage(coinsAPI.getPrefix() + "§e" + target.getName() + " §7hat §b" + coinsAPI.getCoins(target.getUniqueId()) + " §7Coins§8.");
                    } else {
                        player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                    }
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("coins.add")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins add §8« §e" + target.getName() + " §8» « §bCoins §8»");
                        } else {
                            player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                        }
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (player.hasPermission("coins.remove")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins remove §8« §e" + target.getName() + " §8» « §bCoins §8»");
                        } else {
                            player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                        }
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (player.hasPermission("coins.set")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins set §8« §e" + target.getName() + " §8» « §bCoins §8»");
                        } else {
                            player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                        }
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else {
                    player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins help");
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("coins.add")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            try {
                                int coins = Integer.parseInt(args[2]);
                                coinsAPI.addCoins(target.getUniqueId(), coins);
                                player.sendMessage(coinsAPI.getPrefix() + "§e" + target.getName() + " §7wurden §b" + coins + " §7Coins hinzugefügt§8.");
                            } catch (Exception exception) {
                                player.sendMessage(coinsAPI.getPrefix() + "Bitte gebe numerische Werte für die §bCoins §7ein§8.");
                            }
                        } else {
                            player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                        }
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (player.hasPermission("coins.remove")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            try {
                                int coins = Integer.parseInt(args[2]);
                                coinsAPI.removeCoins(target.getUniqueId(), coins);
                                player.sendMessage(coinsAPI.getPrefix() + "§e" + target.getName() + " §7wurden §b" + coins + " §7Coins entfernt§8.");
                            } catch (Exception exception) {
                                player.sendMessage(coinsAPI.getPrefix() + "Bitte gebe numerische Werte für die §bCoins §7ein§8.");
                            }
                        } else {
                            player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                        }
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (player.hasPermission("coins.set")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            try {
                                int coins = Integer.parseInt(args[2]);
                                coinsAPI.setCoins(target.getUniqueId(), coins);
                                player.sendMessage(coinsAPI.getPrefix() + "§e" + target.getName() + " §7wurden die Coins auf §b" + coins + " §7gesetzt§8.");
                            } catch (Exception exception) {
                                player.sendMessage(coinsAPI.getPrefix() + "Bitte gebe numerische Werte für die §bCoins §7ein§8.");
                            }
                        } else {
                            player.sendMessage(coinsAPI.getPrefix() + "Der angegebene Spieler konnte §bnicht §7gefunden werden§8.");
                        }
                    } else {
                        player.sendMessage(coinsAPI.getNoPermissions());
                    }
                } else {
                    player.sendMessage(coinsAPI.getPrefix() + "Verwende §8➜ §7/coins help");
                }
            } else {
                player.sendMessage(coinsAPI.getPrefix() + "Zu viele §bArgumente§8.");
            }
        }
        return false;
    }
}
