package x1337x.matter123.quicktown.townmaker.commands;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import x1337x.matter123.quicktown.townmaker.TownMaker;
import x1337x.matter123.quicktown.townmaker.exceptions.RegionError;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.BukkitServerInterface;
import com.sk89q.worldedit.regions.Region;

public class CreateCommand implements CommandExecutor {
	TownMaker plugin;
public CreateCommand(TownMaker plugin){
	this.plugin = plugin;
}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
	    try {
			try {
				return doCommand(arg0,arg1,arg2,arg3);
			} catch (RegionError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IncompleteRegionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
public boolean doCommand(CommandSender arg0, Command arg1, String arg2,
		String[] arg3) throws IncompleteRegionException, RegionError{
	Player pplayer = (Player)arg0;
	LocalPlayer player =  new BukkitPlayer(TownMaker.worldeditplugin, new BukkitServerInterface(TownMaker.worldeditplugin, this.plugin.getServer()), pplayer);
	LocalSession session = TownMaker.worldeditapi.getSession((Player) pplayer);
	if(session == null){
		throw new RegionError(new Throwable(),"You need to select a compleate region!");
	}
    Region region = session.getRegionSelector().getRegion();
	for (BlockVector vec : region) { 
   Block block =  pplayer.getWorld().getBlockAt(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
   int x = vec.getBlockX();
   int y = vec.getBlockY();
   int z = vec.getBlockZ();
   int px = pplayer.getLocation().getBlockX();
   int py = pplayer.getLocation().getBlockY();
   int pz = pplayer.getLocation().getBlockZ();
   
   int rx = x - px;
   int ry = y - py;
   int rz = z - pz;
   
	}
	      
	return false;
	
}
}
