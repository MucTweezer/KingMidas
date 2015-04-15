package me.MucTweezer.KingMidas;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TouchListener implements Listener {

	private KingMidas plugin;
	
	public TouchListener (KingMidas tempPlugin) {
		plugin = tempPlugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	// Just looking for the touch block event.
	@EventHandler
	public void onBlockTouch(PlayerInteractEvent event) {
		int tempInt = plugin.getTypeID(event.getPlayer().getName());
		
		if (tempInt != -1 && event.getAction() == Action.LEFT_CLICK_BLOCK) {
			event.getClickedBlock().setTypeId(plugin.getTypeID(event.getPlayer().getName()));
		}
	}
}
