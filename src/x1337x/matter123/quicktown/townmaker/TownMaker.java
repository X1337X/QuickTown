package x1337x.matter123.quicktown.townmaker;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import x1337x.matter123.quicktown.townmaker.exceptions.PluginMissingException;

import com.sk89q.worldedit.WorldEdit;

public class TownMaker extends JavaPlugin {

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}
public WorldEdit getWorldEdit() throws PluginMissingException{
	Plugin worldedit = this.getServer().getPluginManager().getPlugin("World Edit");
	
	if(worldedit == null){
		throw new PluginMissingException(new Throwable(), " could not be found disabling..", "World Edit");
	}
	else{
		return (WorldEdit) worldedit;
	}
}
}
