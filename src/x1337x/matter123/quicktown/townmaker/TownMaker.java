package x1337x.matter123.quicktown.townmaker;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import x1337x.matter123.quicktown.townmaker.commands.CreateCommand;
import x1337x.matter123.quicktown.townmaker.exceptions.PluginMissingException;
import x1337x.matter123.quicktown.townmaker.listeners.sListener;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

@SuppressWarnings("deprecation")
public class TownMaker extends JavaPlugin {
public  WorldEditPlugin worldeditplugin = null;
public  WorldEdit worldedit = null;
public  WorldEditAPI worldeditapi = null;
public TownMaker instance = null;
Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		this.worldedit = null;
	    this.worldeditapi = null;
	    this.worldeditplugin = null;
	    this.instance = null;
	    log(Level.INFO,"Made by matters123 and 1337 is disabled");
	    
	}



	@Override
	public void onEnable() {
		this.instance = this;
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new sListener(this), Priority.Highest, this);
	this.getCommand("town").setExecutor(new CreateCommand(this));
	log(Level.INFO,"Made by matters123 and 1337 is enabled");
	}
public WorldEditPlugin getWorldEdit(){
	Plugin worldedit = this.getServer().getPluginManager().getPlugin("WorldEdit");
	
	if(worldedit == null){
		try {
			throw new PluginMissingException(new Throwable(), " could not be found disabling..", "WorldEdit");
		} catch (PluginMissingException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, e.getMessage());
			this.getServer().getPluginManager().disablePlugin(this);
		}
	}
	
	
		return (WorldEditPlugin) worldedit;
	
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
public void onWorldEditEnable(){
	this.worldeditplugin = getWorldEdit();
	this.worldedit = worldeditplugin.getWorldEdit();
	this.worldeditapi = worldeditplugin.getAPI();
	
}
}
