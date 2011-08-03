package x1337x.matter123.quicktown.townmaker.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import x1337x.matter123.quicktown.townmaker.TownMaker;
import x1337x.matter123.quicktown.townmaker.exceptions.CommandException;
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
		}
		return false;
	}
public boolean doCommand(CommandSender arg0, Command arg1, String arg2,
		String[] arg3) throws IncompleteRegionException, RegionErrorException, CommandException, IOException{
	Player pplayer = (Player)arg0;
	if(arg3.length == 1){
		throw new CommandException(new Throwable(), " you must give a townname!", "/town create", "Error executing command",pplayer
				);
	}
	else if(arg3.length > 2){
		throw new CommandException(new Throwable(), " To many arguments", "/town create", "Error executing command",pplayer
		);
	}
	
	LocalPlayer player =  new BukkitPlayer(TownMaker.worldeditplugin, new BukkitServerInterface(TownMaker.worldeditplugin, this.plugin.getServer()), pplayer);
	LocalSession session = TownMaker.worldeditapi.getSession((Player) pplayer);
	if(session == null){
		throw new RegionErrorException(new Throwable(),"You need to select a compleate region!",pplayer);
	}
    Region region = session.getRegionSelector().getRegion();
    BufferedWriter w = new BufferedWriter(new FileWriter("plugins/QuickTown/Towns/" + arg3[2] + ".yml"));
    int height = region.getHeight();
    int width = region.getWidth();
    int length = region.getLength();
    String townname = arg3[2];
    w.write("Town name = " + townname.toLowerCase());
    w.write("Town height = " + height);
    w.write("Town width = " + width);
    w.write("Town length = " + length);
    
    int px = pplayer.getLocation().getBlockX();
    int py = pplayer.getLocation().getBlockY();
    int pz = pplayer.getLocation().getBlockZ();
	for (BlockVector vec : region) { 
   Block block =  pplayer.getWorld().getBlockAt(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
   Material material = block.getType();
   int id = material.getId();
   int x = vec.getBlockX();
   int y = vec.getBlockY();
   int z = vec.getBlockZ();
   
   
   int rx = x - px;
   int ry = y - py;
   int rz = z - pz;
   
   String line = rx+ ":" + ry+ ":" + rz + ":"+id;
   w.write(line);
   w.newLine();
   
   
	}
	      
	return false;
	
}
}
