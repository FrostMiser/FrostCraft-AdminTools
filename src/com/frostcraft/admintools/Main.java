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
	
	}
}
