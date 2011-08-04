package x1337x.matter123.quicktown.townmaker.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

import x1337x.matter123.quicktown.townmaker.TownMaker;
import x1337x.matter123.quicktown.townmaker.exceptions.CommandException;
import x1337x.matter123.quicktown.townmaker.exceptions.NoPermissionException;
import x1337x.matter123.quicktown.townmaker.exceptions.RegionErrorException;

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
			
				return doCommand(arg0,arg1,arg2,arg3);
			}
	    catch (IncompleteRegionException e) {
			// TODO Auto-generated catch block
			
		}
	    catch (RegionErrorException e) {
			// TODO Auto-generated catch block
			
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.getPlayer().sendMessage(e.getMessage() + e.getPermission());
		}
		return false;
	}
public boolean doCommand(CommandSender arg0, Command arg1, String arg2,
		String[] arg3) throws IncompleteRegionException, RegionErrorException, CommandException, IOException, NoPermissionException{
	Player pplayer = (Player)arg0;
	if(!plugin.has("town.create.name", pplayer)){
		throw new NoPermissionException(new Throwable(), "you do not have the permission node ", pplayer, "town.create.name");
	}
	if(arg3.length == 1){
		throw new CommandException(new Throwable(), " you must give a townname!", "/town create", "Error executing command",pplayer
				);
	}
	else if(arg3.length > 2){
		throw new CommandException(new Throwable(), " To many arguments", "/town create", "Error executing command",pplayer
		);
	}
	
	LocalPlayer player =  new BukkitPlayer(plugin.worldeditplugin, new BukkitServerInterface(plugin.worldeditplugin, this.plugin.getServer()), pplayer);
	LocalSession session = plugin.worldeditapi.getSession(pplayer);
	if(session == null){
		throw new RegionErrorException(new Throwable(),"You need to select a compleate region!",pplayer);
	}
    Region region = session.getRegionSelector().getRegion();
    if(region == null){
		throw new RegionErrorException(new Throwable(),"You need to select a compleate region!",pplayer);
	}
    Configuration c = new Configuration(new File("plugins/QuickTown/Towns/" + arg3[1] + ".yml"));
    File f = new File("plugins/QuickTown/Towns");
    if(!f.exists()){
    	f.mkdirs();
    }
    int ax = Integer.MAX_VALUE;
    int ay = Integer.MAX_VALUE;
    int az = Integer.MAX_VALUE;
    
    
    int height = region.getHeight();
    int width = region.getWidth();
    int length = region.getLength();
   
    String townname = arg3[1];
   
    c.setProperty("town.name", townname.toLowerCase());
    c.setProperty("town.height",height);
    c.setProperty("town.width", width);
    c.setProperty("town.length", length);
   
    for(BlockVector vec : region){
    	
    	 		
    	  
    	 ax= Math.min(ax,vec.getBlockX());
    	 ay= Math.min(ay,vec.getBlockY());
    	 az= Math.min(az,vec.getBlockZ());
    	
    }
    
   int blocks = 0;
	for(BlockVector vec : region){
	    Block block =  pplayer.getWorld().getBlockAt(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
		int bx= vec.getBlockX()-ax;
		int by= vec.getBlockY()-ay;
		int bz= vec.getBlockZ()-az;
		
		c.setProperty("town.blocks." + bx + "."+ by + "." + bz,block.getTypeId());
		blocks++;
	}
	c.setProperty("town.blockamount", blocks);
	      c.save();
	      pplayer.sendMessage("Town Saved! with "  + blocks + " blocks in it");
	return true;
	
}
}
