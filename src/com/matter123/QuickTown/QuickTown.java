package com.matter123.QuickTown;//i am not claiming ownership in any way by the package name

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class QuickTown extends JavaPlugin{
	Logger log;
	@Override
	public void onDisable() {
		log.info("QuickTown v"+this.getDescription().getVersion()+" by 1337 and matter123"+" Disabled");
	}

	@Override
	public void onEnable() {
		log=this.getServer().getLogger();
		log.info("QuickTown v"+this.getDescription().getVersion()+" by 1337 and matter123"+" Enabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		// TODO Auto-generated method stub
		return super.onCommand(sender, command, label, args);
	}
	
}
