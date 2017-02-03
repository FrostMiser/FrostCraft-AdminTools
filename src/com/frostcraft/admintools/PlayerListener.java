package com.frostcraft.admintools;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	/********************************************************************************
	 * PLAYER JOIN EVENT
	 ********************************************************************************/
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Hide vanished players
		Player p = event.getPlayer();
		for (UUID vanishUUID : Main.vanishList) {
			p.hidePlayer(Bukkit.getServer().getPlayer(vanishUUID));
		}
	}

	
	/********************************************************************************
	 * ASYNC PLAYER CHAT EVENT
	 ********************************************************************************/	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (Main.muteList.contains(event.getPlayer().getUniqueId())) {
			event.setCancelled(true); //mute player
		}
	}	
}
