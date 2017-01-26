package com.frostcraft.admintools;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] Plugin enabled.");
	}
	
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] Plugin disabled.");
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		Player p;
		try {
			p = (Player) sender;	
		}
		catch (Exception e) {
			p = null;
			sender.sendMessage(ChatColor.RED + "[FrostCraft-AdminTools] This command must be issued by a player.");
			return true;
		}
		
		//fcadmin command
		if (p != null) {
			if (cmd.getName().equalsIgnoreCase("fcadmin")) {
				
				if (args.length <= 0) {
					p.sendMessage(ChatColor.LIGHT_PURPLE + "FrostCraft-AdminTools Commands");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "==============================");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin kick [P] [Msg]" + ChatColor.GREEN + " - Kick a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin kickwarn [P]" + ChatColor.GREEN + " - Kick a player and add a warning.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin ban [P]" + ChatColor.GREEN + " - Ban a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin tempban [P]" + ChatColor.GREEN + " - Temp ban a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin mute [P]" + ChatColor.GREEN + " - Mute a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin unmute [P]" + ChatColor.GREEN + " - UnMute a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin vanish" + ChatColor.GREEN + " - Vanish.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin vanish [P]" + ChatColor.GREEN + " - Vanish only from a specific player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin seeinv [P]" + ChatColor.GREEN + " - View a player inventory.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin seeinvender [P]" + ChatColor.GREEN + " - View a player ender inventory.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin tpl [P]" + ChatColor.GREEN + " - Teleport to a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin gm" + ChatColor.GREEN + " - Change your gamemode.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin time" + ChatColor.GREEN + " - Set the world time.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin togglephysics" + ChatColor.GREEN + " - Turn physics on or off.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin addpoint [Name]" + ChatColor.GREEN + " - Add an admin waypoint.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin listpoints" + ChatColor.GREEN + " - List admin waypoints.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin point [Name]" + ChatColor.GREEN + " - Teleport to a waypoint.");
				}
			}
		}
		return true;
	}
}
