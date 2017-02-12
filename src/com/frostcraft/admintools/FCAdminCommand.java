package com.frostcraft.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FCAdminCommand implements CommandExecutor {
	Plugin plugin;
	
	public FCAdminCommand (Plugin frostWolfPlugin) {
		plugin = frostWolfPlugin;
	}
	
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
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin addpoint [Name]" + ChatColor.GREEN + " - Add an admin waypoint.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin ban [P]" + ChatColor.GREEN + " - Ban a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin blockinfo" + ChatColor.GREEN + " - Get information about a block in hand.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin clearlag[P]" + ChatColor.GREEN + " - Clear things that may be causing lag.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin gm" + ChatColor.GREEN + " - Change your gamemode.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin kick [P] [Msg]" + ChatColor.GREEN + " - Kick a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin kickwarn [P]" + ChatColor.GREEN + " - Kick a player and add a warning.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin listpoints" + ChatColor.GREEN + " - List admin waypoints.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin mute [P]" + ChatColor.GREEN + " - Mute a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin point [Name]" + ChatColor.GREEN + " - Teleport to a waypoint.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin powerup" + ChatColor.GREEN + " - Power up with awesome effects.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin seeinv [P]" + ChatColor.GREEN + " - View a player inventory.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin seeinvender [P]" + ChatColor.GREEN + " - View a player ender inventory.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin setspeed [P]" + ChatColor.GREEN + " - Set speed of a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin tempban [P]" + ChatColor.GREEN + " - Temp ban a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin time" + ChatColor.GREEN + " - Set the world time for all worlds on the server.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin togglephysics" + ChatColor.GREEN + " - Turn physics on or off.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin tpl [P]" + ChatColor.GREEN + " - Teleport to a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin tpworld" + ChatColor.GREEN + " - Teleport to a different world.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin createworld" + ChatColor.GREEN + " - Create a new world.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin removepoint [Name]" + ChatColor.GREEN + " - Remove an admin waypoint.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin unmute [P]" + ChatColor.GREEN + " - UnMute a player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin vanish" + ChatColor.GREEN + " - Vanish.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin vanish [P]" + ChatColor.GREEN + " - Vanish only from a specific player.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin broadcast" + ChatColor.GREEN + " - Broadcast a message.");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin joinmessage" + ChatColor.GREEN + " - Set message players see when they join.");
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
								Bukkit.getBanList(Type.NAME).addBan(banPlayer.getName(), message, null, p.getName());
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
						if (args.length < 2) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid parameter, use /unmute [player].");
						}
						
						Player mutePlayer = null;
						
						try {
							mutePlayer = Bukkit.getServer().getPlayer(args[1]);
						}
						catch (Exception e) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player not found.");
						}
						
						if (mutePlayer !=null) {
							Main.muteList.remove(mutePlayer.getUniqueId());
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + mutePlayer.getName() + " is no longer muted.");
						}
					}
					//Clear out things that may be causing lag
					else if (args[0].equalsIgnoreCase("clearlag")) {
						for (Chunk c : p.getWorld().getLoadedChunks()) {
					  		for (Entity en : c.getEntities()) {
					  			if (en.getType().equals(EntityType.FIREBALL) ||
					  				en.getType().equals(EntityType.WITHER_SKULL) ||
					  				en.getType().equals(EntityType.CREEPER) ||
					  				en.getType().equals(EntityType.ZOMBIE) ||
					  				en.getType().equals(EntityType.SKELETON) ||
					  				en.getType().equals(EntityType.BLAZE) ||
					  				en.getType().equals(EntityType.SHULKER) ||
					  				en.getType().equals(EntityType.SHULKER_BULLET) ||
					  				en.getType().equals(EntityType.DROPPED_ITEM) ||
					  				en.getType().equals(EntityType.ENDERMAN) ||
					  				en.getType().equals(EntityType.EXPERIENCE_ORB) ||
					  				en.getType().equals(EntityType.MAGMA_CUBE) ||
					  				en.getType().equals(EntityType.ARROW) ||
					  				en.getType().equals(EntityType.BAT) ||
					  				en.getType().equals(EntityType.EGG) ||
					  				en.getType().equals(EntityType.FIREWORK) ||
					  				en.getType().equals(EntityType.ENDER_SIGNAL) ||
					  				en.getType().equals(EntityType.GHAST) ||
					  				en.getType().equals(EntityType.GIANT) ||
					  				en.getType().equals(EntityType.PIG_ZOMBIE) ||
					  				en.getType().equals(EntityType.SILVERFISH) ||
					  				en.getType().equals(EntityType.WITCH) ||
					  				en.getType().equals(EntityType.WITHER) ||
					  				en.getType().equals(EntityType.ENDER_DRAGON) ||
					  				en.getType().equals(EntityType.SKELETON) ||
					  				en.getType().equals(EntityType.SLIME) ||
					  				en.getType().equals(EntityType.SMALL_FIREBALL) ||
					  				en.getType().equals(EntityType.SPIDER) ||
					  				en.getType().equals(EntityType.SQUID) ||
									en.getType().equals(EntityType.DRAGON_FIREBALL) ||
									en.getType().equals(EntityType.BOAT) ||
					  				en.getType().equals(EntityType.CAVE_SPIDER)
					  				)
					  			{
					  				en.remove();
					  			}
					  		}
						}
					}
					//Get useful information about a block in hand
					else if (args[0].equalsIgnoreCase("blockinfo")) {
						ItemStack item = p.getInventory().getItemInMainHand();
						p.sendMessage("Durability: " + item.getDurability());
						p.sendMessage("Amount: " + item.getAmount());
						p.sendMessage("Max Stack Size: " + item.getMaxStackSize());
						p.sendMessage("Type: " + item.getType().toString());
						p.sendMessage("Data: " + item.getData().toString());
						p.sendMessage("ID: " + item.getType().getId());
						p.sendMessage("Enchantments: " + item.getEnchantments().toString());
					}	
					else if (args[0].equalsIgnoreCase("powerup")) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,10000,10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,10000,10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,10000,10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,10000,10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,10000,10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,10000,10));
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
