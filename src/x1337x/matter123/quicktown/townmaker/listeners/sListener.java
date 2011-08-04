package x1337x.matter123.quicktown.townmaker.listeners;

import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

import x1337x.matter123.quicktown.townmaker.TownMaker;

public class sListener extends ServerListener {
 TownMaker plugin = null;
	public sListener(TownMaker args) {
		this.plugin = args;
	
	}
public void onPluginEnable(PluginEnableEvent event){
	String name = event.getPlugin().getDescription().getName();
	if(name.equals("WorldEdit")){
		plugin.onWorldEditEnable();
	}
}
}
