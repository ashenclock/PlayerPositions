package me.AshenClock.commands;

import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.DoubleTag;
import net.querz.nbt.tag.Tag;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OffPlayers implements CommandExecutor {
    private final JavaPlugin plugin;

    public OffPlayers(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server server = sender.getServer();
        int i = 1;
        DateFormat obj = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
        if (server.getWorlds().isEmpty()) {
            sender.sendMessage(ChatColor.RED + "No worlds are loaded.");
            return true;
        }
        File playerData = new File(server.getWorlds().get(0).getWorldFolder(), "playerdata");
        for(OfflinePlayer p : server.getOfflinePlayers()){
            if(!p.isOnline()) {
                Date data = new Date(p.getLastPlayed());
                try {
                    File playerFile = new File(playerData, p.getUniqueId() + ".dat");
                    if (!playerFile.isFile()) {
                        continue;
                    }
                    NamedTag n = NBTUtil.read(playerFile);
                    CompoundTag c = (CompoundTag) n.getTag();
                    Tag<?> b = c.get("Pos");
                    if (!(b instanceof net.querz.nbt.tag.ListTag<?> positions) || positions.size() < 3) {
                        continue;
                    }
                    List<DoubleTag> h = (List<DoubleTag>) positions.getValue();
                    sender.sendMessage(ChatColor.YELLOW + "" + (i++) + ". Name: " + ChatColor.AQUA + p.getName() + ChatColor.YELLOW + " Last Login: " + ChatColor.AQUA + obj.format(data) + "\n" +
                            ChatColor.GREEN + "X: " + ChatColor.AQUA + h.get(0).getValue().intValue() + ChatColor.GREEN + " Y: " + ChatColor.AQUA + h.get(1).getValue().intValue() + ChatColor.GREEN + " Z: " + ChatColor.AQUA + h.get(2).getValue().intValue());
                } catch (IOException | ClassCastException e) {
                    plugin.getLogger().warning("Could not read player data for " + p.getName());
                }
            }
        }
    return true;
    }
}
