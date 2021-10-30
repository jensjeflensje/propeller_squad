package dev.jensderuiter.propellersquad.command;

import dev.jensderuiter.propellersquad.util.PropellerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Invalid amount of arguments.");
            return true;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage("Player not found.");
            return true;
        }
        PropellerUtil.removeMember((Player) sender);
        sender.sendMessage("Cap was removed!");
        return true;
    }

}
