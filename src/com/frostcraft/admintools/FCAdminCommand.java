package com.frostcraft.admintools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
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
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin adminsword" + ChatColor.GREEN + " - Summon a powerful admin sword.");
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
					p.sendMessage(ChatColor.LIGHT_PURPLE + "fcadmin flyspeed [P]" + ChatColor.GREEN + " - Set fly speed of a player.");
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
						if (args.length == 2) {
							Player vanishFrom = Bukkit.getPlayer(args[1]);
							if (vanishFrom != null) {
								vanishFrom.hidePlayer(p);
								p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools]" + ChatColor.GREEN + "You have vanished from " + vanishFrom.getName() + ".");
							}
							else {
								p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools]" + ChatColor.GREEN + "Player not found.");
							}
						}
						else {						
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
					  			if (en.getType().equals(EntityType.ARROW) ||
					  				en.getType().equals(EntityType.SPECTRAL_ARROW) ||	
						  			en.getType().equals(EntityType.BAT) ||
						  			en.getType().equals(EntityType.BLAZE) ||
									en.getType().equals(EntityType.BOAT) ||
					  				en.getType().equals(EntityType.CAVE_SPIDER) ||
					  				en.getType().equals(EntityType.CREEPER) ||
									en.getType().equals(EntityType.DRAGON_FIREBALL) ||
					  				en.getType().equals(EntityType.DROPPED_ITEM) ||
					  				en.getType().equals(EntityType.ENDER_DRAGON) ||
					  				en.getType().equals(EntityType.ENDER_SIGNAL) ||
					  				en.getType().equals(EntityType.ENDERMAN) ||
					  				en.getType().equals(EntityType.EGG) ||
					  				en.getType().equals(EntityType.EXPERIENCE_ORB) ||
					  				en.getType().equals(EntityType.FIREBALL) ||
					  				en.getType().equals(EntityType.FIREWORK) ||
					  				en.getType().equals(EntityType.GHAST) ||
					  				en.getType().equals(EntityType.GIANT) ||
					  				en.getType().equals(EntityType.MAGMA_CUBE) ||
					  				en.getType().equals(EntityType.PIG_ZOMBIE) ||
					  				en.getType().equals(EntityType.SHULKER) ||
					  				en.getType().equals(EntityType.SHULKER_BULLET) ||
					  				en.getType().equals(EntityType.SILVERFISH) ||
					  				en.getType().equals(EntityType.SLIME) ||
					  				en.getType().equals(EntityType.SMALL_FIREBALL) ||
					  				en.getType().equals(EntityType.SPIDER) ||
					  				en.getType().equals(EntityType.SQUID) ||
					  				en.getType().equals(EntityType.SKELETON) ||
					  				en.getType().equals(EntityType.WITCH) ||
					  				en.getType().equals(EntityType.WITHER) || 
					  				en.getType().equals(EntityType.WITHER_SKULL) ||
					  				en.getType().equals(EntityType.ZOMBIE)
					  				
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
						if (args.length < 4) {
							Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid command format. Use /fcadmin ban [P] [Days] [Reason]");
						}
						else {
							Player banPlayer = null;
							try {
								banPlayer = Bukkit.getServer().getPlayer(args[1]);
							}
							catch (Exception e) {
								p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Player " + args[1] + " not found, or no player specified.");
							}
							
							if (banPlayer !=null) {
								int days = 0;
								try {
									days = Integer.parseInt(args[2]);	
								}
								catch (Exception e) {
									days = 0;
								}

								if (days > 0 && days < 1000) {
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(new Date());
									calendar.add(Calendar.DATE, days);
									
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
											
									String reason = ChatColor.RED + "Temp banned for " + args[3];
									for (int i=4;i<args.length;i++)
									{
										reason = reason + " " + args[i]; 
									}
									
									reason = reason + " until " + sdf.format(calendar.getTime());
									
									Bukkit.getBanList(Type.NAME).addBan(banPlayer.getName(), reason, calendar.getTime(), p.getName());
									banPlayer.kickPlayer("Banned: " + reason);
									Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[FrostCraft Admin] " + ChatColor.GREEN + banPlayer.getName() + ChatColor.GREEN + " has been temporarily banned until " + sdf.format(calendar.getTime()) + " for: " + ChatColor.ITALIC + reason);				
								}
								else {
									p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Invalid number of days. Days must be between 1 and 1,000.");
								}
							}

						
						}
					}
					else if (args[0].equalsIgnoreCase("tpworld")) 	{
						World world = Bukkit.getServer().getWorld(args[1]);

						if (world == null) { 
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools]" + ChatColor.GREEN + "World not found.");
						}
						else {
							Bukkit.getServer().getPlayer(sender.getName()).teleport(world.getSpawnLocation());
							p.sendMessage(ChatColor.RED + "[FrostCraft-AdminTools] Teleported to the world requested.");
						}
					}
					else if (args[0].equalsIgnoreCase("createworld")) 	{
						World world = Bukkit.getServer().getWorld(args[1]);
						if (world==null)
						{
							WorldCreator worldCreator = new WorldCreator(args[1]);
							worldCreator.environment(World.Environment.NORMAL);
							worldCreator.generateStructures(false);
							world = worldCreator.createWorld();
						}
						p.sendMessage(ChatColor.RED + "[FrostCraft-AdminTools] World created.");
					}
					else if (args[0].equalsIgnoreCase("flyspeed")) {
						Player target = Bukkit.getServer().getPlayer(args[1]);
						target.setFlySpeed(Float.parseFloat(args[2]));
						return true;
					}
					else if (args[0].equalsIgnoreCase("point")) 	{
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools]" + ChatColor.GREEN + "Invalid use of command, use /point [name]");
						}
						
						String pointName = args[1];
						
						String worldName = plugin.getConfig().getString("point." + pointName + ".world");
						double x = Double.parseDouble(plugin.getConfig().getString("point." + pointName + ".x"));
						double y = Double.parseDouble(plugin.getConfig().getString("point." + pointName + ".y"));
						double z = Double.parseDouble(plugin.getConfig().getString("point." + pointName + ".z"));
					
						World world = Bukkit.getWorld(worldName);
						Location location = new Location(world, x, y, z);						
						
						p.teleport(location);
						
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Teleported to " + pointName + " point.");
					}  					
					else if (args[0].equalsIgnoreCase("addpoint")) 	{
						
						if (args.length < 2) {
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools]" + ChatColor.GREEN + "Invalid use of command, use /addpoint [name]");
						}
						
						Location location = p.getLocation();
						String pointName = args[1];
						
						plugin.getConfig().set("point." + pointName + ".x",location.getX());
						plugin.getConfig().set("point." + pointName + ".y",location.getY());
						plugin.getConfig().set("point." + pointName + ".z",location.getZ());
						
						plugin.saveConfig();
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Added new point, " + pointName + ".");
					}  
					else if (args[0].equalsIgnoreCase("removepoint")) 	{
							
							if (args.length < 2) {
								p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools]" + ChatColor.GREEN + "Invalid use of command, use /removepoint [name]");
							}
							
							String pointName = args[1];
							
							plugin.getConfig().set("point." + pointName,null);

							plugin.saveConfig();
							p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Removed point " + pointName + ".");
					}  
					else if (args[0].equalsIgnoreCase("listpoints")) 	{
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Command not available.");
					}
					else if (args[0].equalsIgnoreCase("broadcast")) {
						String message = ChatColor.RED + args[1];
						for (int i=2;i<args.length;i++) {
							message = message + " " + args[i]; 
						}
						Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Broadcast created.");
					}
					else if (args[0].equalsIgnoreCase("adminsword")) {
						ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD,1);
						itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
						itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 10);
						itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);
						itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
						itemStack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
						itemStack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
						itemStack.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 10);
						itemStack.addUnsafeEnchantment(Enchantment.MENDING, 10);
					
						p.sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] " + ChatColor.GREEN + "Sword summoned.");
					}					
				}
			}
		}
		return true;
	}
}
