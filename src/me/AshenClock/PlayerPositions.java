package me.AshenClock;

import me.AshenClock.commands.CommandPosition;
import me.AshenClock.commands.OffPlayers;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerPositions extends JavaPlugin {
    @Override
    public void onEnable() {
        if (getCommand("playerpositions") == null || getCommand("offplayers") == null) {
            getLogger().severe("Commands are missing from plugin.yml; disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("playerpositions").setExecutor(new CommandPosition());
        getCommand("offplayers").setExecutor(new OffPlayers(this));
        getLogger().info("PlayerPositions enabled");
    }
    @Override
    public void onDisable() {
        getLogger().info("PlayerPositions disabled");
    }

}
