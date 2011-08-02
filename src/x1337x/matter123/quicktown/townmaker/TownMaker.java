package x1337x.matter123.quicktown.townmaker;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import x1337x.matter123.quicktown.townmaker.exceptions.PluginMissingException;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class TownMaker extends JavaPlugin {
public static WorldEdit worldedit = null;
public static WorldEditAPI worldeditapi = null;
public static WorldEditPlugin worldeditplugin = null;
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
	try {
		worldeditplugin = getWorldEdit();
		worldeditapi = worldeditplugin.getAPI();
        worldedit = worldeditplugin.getWorldEdit();
		
	} catch (PluginMissingException e) {
		// TODO Auto-generated catch block
		this.getServer().getPluginManager().disablePlugin(this);
	}
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
}
