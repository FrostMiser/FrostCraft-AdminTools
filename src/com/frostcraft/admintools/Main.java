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
				
				
			}
		}
		return true;
	}
}
