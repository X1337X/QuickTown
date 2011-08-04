package com.matter123.QuickTown;//i am not claiming ownership in any way by the package name

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class QuickTown extends JavaPlugin{
	Logger log;
	HashMap<Player,Town>townlist=new HashMap<Player,Town>();
	@Override
	public void onDisable() {
		log.info("QuickTown Reader v"+this.getDescription().getVersion()+" by 1337 and matter123"+" Disabled");
	}

	@Override
	public void onEnable() {
		log=this.getServer().getLogger();
		log.info("QuickTown Reader v"+this.getDescription().getVersion()+" by 1337 and matter123"+" Enabled");
	}

	//@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You cant do that");
		}
		if(args.length==0) {
			sender.sendMessage(ChatColor.RED+"Enter a name of a Town to make");
		}
		if(!hasPermission((Player) sender,"town.make."+args[0])) {
			sender.sendMessage(ChatColor.RED+"You cant do that");
		}
		if(command.getName().equalsIgnoreCase("newtown"))
			townlist.put((Player) sender, new Town(args[0], this));
		else {
			townlist.get((Player) sender).undo();
			townlist.remove((Player) sender);
		}
		return true;
	}

	private boolean hasPermission(Player sender, String string) {
		return sender.hasPermission(new Permission(string, PermissionDefault.OP));
	}
	
}
