package com.frostcraft.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FCAdminCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
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
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin time" + ChatColor.GREEN + " - Set the world time for all worlds on the server.");
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
						if (Main.enablePhysics) {
							Main.enablePhysics = false;
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Physics disabled.");
						}
						else {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Physics enabled.");
							Main.enablePhysics = true;
						}
					}
					else if (args[0].equalsIgnoreCase("tpl")) {
						if (args.length < 2) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "No player specified.");							
						}
						Player tplPlayer = null;
						try {
							tplPlayer = Bukkit.getServer().getPlayer(args[1]);
						}						
						catch (Exception e) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player " + args[1] + " not found, or no player specified.");
						}
						
						if (tplPlayer != null) {
							p.teleport(tplPlayer);
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "You have been teleported to " + tplPlayer.getName());
						}
					}
					else if (args[0].equalsIgnoreCase("vanish")) 	{
						boolean isVanished = false;
						
						if (Main.vanishList.contains(p.getUniqueId())) {	isVanished = true; }
								
						if (isVanished)
						{
							for (Player pList: Bukkit.getServer().getOnlinePlayers()) {
								pList.showPlayer(p);
							}
							Main.vanishList.remove(p.getUniqueId());
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "You are no longer vanished.");
						}
						else
						{
							for (Player pList: Bukkit.getServer().getOnlinePlayers()) {
								pList.hidePlayer(p);
							}
							Main.vanishList.add(p.getUniqueId());
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "You have vanished!");
						}
					}
					else if (args[0].equalsIgnoreCase("time")) 	{
						if (args.length < 2) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid parameter, use /time [day|night].");
						}
						if (args[1].equalsIgnoreCase("day")) {
							for (World world : Bukkit.getServer().getWorlds()) {
								world.setTime(1000);
							}
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Time set to day in all worlds.");
						}
						else if (args[1].equalsIgnoreCase("night")) {
							for (World world : Bukkit.getServer().getWorlds()) {
								world.setTime(13000);
							}
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Time set to night in all worlds.");							
						}
						else {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid parameter, use /time [day|night].");
						}
					}										
					else if (args[0].equalsIgnoreCase("mute")) 	{
						if (args.length < 2) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid parameter, use /mute [player].");
						}
						
						Player mutePlayer = null;
						
						try {
							mutePlayer = Bukkit.getServer().getPlayer(args[1]);
						}
						catch (Exception e) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player not found.");
						}
						
						if (mutePlayer !=null) {
							Main.muteList.add(mutePlayer.getUniqueId());
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + mutePlayer.getName() + " has been muted.");
						}
					}								
					else if (args[0].equalsIgnoreCase("unmute")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}								
					else if (args[0].equalsIgnoreCase("kickwarn")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}
					else if (args[0].equalsIgnoreCase("tempban")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}					
					else if (args[0].equalsIgnoreCase("addpoint")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}
					else if (args[0].equalsIgnoreCase("removepoint")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}		
					else if (args[0].equalsIgnoreCase("listpoints")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}
				}
			}
		}
		return true;
	}
}
