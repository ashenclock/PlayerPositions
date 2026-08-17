package me.AshenClock.commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CommandPosition implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }
        int i = 1;
        player.sendMessage(ChatColor.YELLOW + "Your current position: " + format(player));
        for (Player other : player.getServer().getOnlinePlayers()) {
            if (!player.equals(other)) {
                player.sendMessage(ChatColor.YELLOW + (i++) + ". " + ChatColor.WHITE
                        + other.getName() + ChatColor.GRAY + " (" + format(other) + ChatColor.GRAY + ")");
            }
        }
        return true;
    }

    private String format(Player player) {
        return ChatColor.GREEN + "X: " + ChatColor.AQUA + (int) player.getLocation().getX()
                + ChatColor.GREEN + " Y: " + ChatColor.AQUA + (int) player.getLocation().getY()
                + ChatColor.GREEN + " Z: " + ChatColor.AQUA + (int) player.getLocation().getZ()
                + ChatColor.GREEN + " World: " + ChatColor.AQUA + player.getWorld().getName();
    }
}
