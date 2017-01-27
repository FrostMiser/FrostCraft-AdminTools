package com.frostcraft.admintools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	Boolean enablePhysics = true;
	
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] Plugin enabled.");
	}
	
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] Plugin disabled.");
	}
	
	/********************************************************************************
	 * 			BLOCK PHYSICS EVENT
	 ********************************************************************************/	
  	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent event)
  	{
  		if (!enablePhysics) {
  				event.setCancelled(true);
  		}
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
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin removepoint [Name]" + ChatColor.GREEN + " - Remove an admin waypoint.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin addpoint [Name]" + ChatColor.GREEN + " - Add an admin waypoint.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin listpoints" + ChatColor.GREEN + " - List admin waypoints.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin point [Name]" + ChatColor.GREEN + " - Teleport to a waypoint.");
				}
				else {
					if (args[0].equalsIgnoreCase("kick")) {
						if (args.length < 3) {
							Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid command format. Use /fcadmin kick [P] [Msg]");
							
						}
						else {
							try {
								Player kickPlayer = Bukkit.getServer().getPlayer(args[1]);
								
								String message = ChatColor.RED + args[2];
								for (int i=3;i<args.length;i++)
								{
									message = message + " " + args[i]; 
								}
								kickPlayer.kickPlayer("Kicked: " + message);
								Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[FrostCraft Admin] " + ChatColor.GREEN + kickPlayer.getName() + ChatColor.GREEN + " has been kicked for: " + ChatColor.ITALIC + message);			
							}
							catch (Exception e) {
								p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player " + args[1] + " not found, or no player specified." + e.toString());
							}							
						}
					}
					else if (args[0].equalsIgnoreCase("ban")) {
						if (args.length < 3) {
							Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid command format. Use /fcadmin ban [P] [Msg]");
							
						}
						else {
							try {
								Player banPlayer = Bukkit.getServer().getPlayer(args[1]);
								
								String message = ChatColor.RED + args[2];
								for (int i=3;i<args.length;i++)
								{
									message = message + " " + args[i]; 
								}
								banPlayer.setBanned(true);
								banPlayer.kickPlayer("Banned: " + message);
								Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[FrostCraft Admin] " + ChatColor.GREEN + banPlayer.getName() + ChatColor.GREEN + " has been banned for: " + ChatColor.ITALIC + message);			
							}
							catch (Exception e) {
								p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player " + args[1] + " not found, or no player specified.");
							}							
						}
					}
					else if (args[0].equalsIgnoreCase("gm")) {
						if (p.getGameMode().equals(GameMode.CREATIVE)) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "GameMode changed to Survival.");
						}
						else {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "GameMode changed to Creative.");
						}
					}
					else if (args[0].equalsIgnoreCase("seeinv")) {
						Player invPlayer = null;
						try {
							invPlayer = Bukkit.getServer().getPlayer(args[1]);
						}						
						catch (Exception e) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player " + args[1] + " not found, or no player specified.");
						}
						
						if (invPlayer != null) {
							p.openInventory(invPlayer.getInventory());
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Showing player inventory for " + args[1] + ".");
						}
					}
					else if (args[0].equalsIgnoreCase("seeinvender")) {
						Player invPlayer = null;
						try {
							invPlayer = Bukkit.getServer().getPlayer(args[1]);
						}						
						catch (Exception e) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player " + args[1] + " not found, or no player specified.");
						}
						
						if (invPlayer != null) {
							p.openInventory(invPlayer.getEnderChest());
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Showing player ender inventory for " + args[1] + ".");
						}
					}					
					else if (args[0].equalsIgnoreCase("togglephysics")) {
						if (enablePhysics) {
							enablePhysics = false;
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Physics disabled.");
						}
						else {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Physics enabled.");
							enablePhysics = true;
						}
					}
				}
			}
		}
		return true;
	}
}
