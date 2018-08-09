package com.frostcraft.admintools;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Main plugin;

    PlayerListener(Main plugin) { this.plugin = plugin; }

	/********************************************************************************
	 * PLAYER JOIN EVENT
	 ********************************************************************************/
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Hide vanished players
		Player p = event.getPlayer();
		for (UUID vanishUUID : Main.vanishList) {
			p.hidePlayer(plugin, Bukkit.getServer().getPlayer(vanishUUID));
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

  /********************************************************************************
   * PLAYER QUIT EVENT
   ********************************************************************************/
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
      Player p;

      p = event.getPlayer();

      Main.verifiedList.remove(p.getUniqueId());
  }
}