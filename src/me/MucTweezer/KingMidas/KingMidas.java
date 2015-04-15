package me.MucTweezer.KingMidas;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KingMidas extends JavaPlugin {
	
	private Logger log;
	private Map<String, Integer> midasPlayers;
	
	public void onEnable() {
		midasPlayers = new HashMap<String, Integer>();
		new TouchListener(this);
		log = this.getLogger();
		
		log.info("King Midas plugin has been enabled");

	}
	public void onDisable() {
		log.info("King Midas plugin has been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (senderIsPlayer(sender) && correctNumberOfArgs(args) && cmd.getName().equals("kingmidas")) {
			if (args[0].equals("off")) {
				midasPlayers.put(sender.getName(), -1);
				log.info(sender.getName() + " has disabled King Midas.");
				sender.sendMessage(ChatColor.GOLD + "King Midas has been disabled.");
			} else {
				if (isBlock(Integer.parseInt(args[0]))) {
					midasPlayers.put(sender.getName(), Integer.parseInt(args[0]));
					log.info("Player " + sender.getName() + " has enabled King Midas for block ID " + args[0] + ".");
					sender.sendMessage(ChatColor.GOLD + "King Midas enabled for block ID " + args[0] + ".");
				} else {
					sender.sendMessage(ChatColor.GOLD + "That is not a valid block ID for King Midas to use.");
				}
			}
			return true;
		} else {
			return false;
		}
	}
	private boolean senderIsPlayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			return false;
		}
	}
	private boolean correctNumberOfArgs(String[] args) {
		if (args.length == 1) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isBlock(int blockID) {
		return (blockID < 256);
	}
	
	public int getTypeID(String tempPlayerName) {
		if (midasPlayers.get(tempPlayerName) == null) {
			return -1;
		} else {
			return midasPlayers.get(tempPlayerName);
		}
	}
}
