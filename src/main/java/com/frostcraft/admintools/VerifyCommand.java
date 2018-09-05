package com.frostcraft.admintools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VerifyCommand implements CommandExecutor {
    Main plugin;

    public VerifyCommand(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        java.util.UUID uuid;
        try {
            player = (Player) sender;
            uuid = player.getUniqueId();
        }
        catch (Exception e) {
            sender.sendMessage("This command must be issued by a player.");
            return false;
        }

        // Make sure min args are met.
        // TODO: Find a better way to do this.
        if (args.length < 1) {
            sender.sendMessage("Usage:");
            sender.sendMessage("verify <key>");

            return false;
        }

        String code;
        try {
            code = plugin.getConfig().getString(uuid.toString());
            if(code.equals(args[0])) {
                plugin.verifiedList.add(uuid);
                sender.sendMessage("Verified..");
            }
        }
        // Todo: Actually specify the error, please..
        catch (Exception err) {
            Bukkit.getServer().getConsoleSender().sendMessage("Ambiguous Error: " + err);
            sender.sendMessage("You are not added to the list.");
            return false;
        }

        return true;
    }
}
