package x1337x.matter123.quicktown.townmaker;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import x1337x.matter123.quicktown.townmaker.commands.CreateCommand;
import x1337x.matter123.quicktown.townmaker.exceptions.PluginMissingException;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class TownMaker extends JavaPlugin {
public static WorldEdit worldedit = null;
public static WorldEditAPI worldeditapi = null;
public static WorldEditPlugin worldeditplugin = null;
public TownMaker instance = null;
Logger log = this.getServer().getLogger();
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		TownMaker.worldedit = null;
	    TownMaker.worldeditapi = null;
	    TownMaker.worldeditplugin = null;
	    this.instance = null;
	    log(Level.INFO,"Made by matters123 and 1337 is enabled");
	    
	}


	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
	try {
		this.instance = this;
		worldeditplugin = getWorldEdit();
		worldeditapi = worldeditplugin.getAPI();
        worldedit = worldeditplugin.getWorldEdit();
        this.getCommand("town").setExecutor(new CreateCommand(this));
		
	} catch (PluginMissingException e) {
		// TODO Auto-generated catch block
		log(Level.SEVERE, e.getMessage());
		this.getServer().getPluginManager().disablePlugin(this);
	}
	log(Level.INFO,"Made by matters123 and 1337 is enabled");
	}
public WorldEditPlugin getWorldEdit() throws PluginMissingException{
	Plugin worldedit = this.getServer().getPluginManager().getPlugin("World Edit");
	
	if(worldedit == null){
		throw new PluginMissingException(new Throwable(), " could not be found disabling..", "World Edit");
	}
	
	else{
		return (WorldEditPlugin) worldedit;
	}
}
public String getName()
{
  PluginDescriptionFile pdfFile = getDescription();
  return pdfFile.getName();
}
public String getVersion() {
  PluginDescriptionFile pdfFile = getDescription();
  return pdfFile.getVersion();
}
public void log(Level level,String message){
	String newmessage = "[" + getName() + " : " + getVersion() + "] " + message;
	log.log(level, newmessage);
}
}
