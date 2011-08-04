package com.matter123.QuickTown;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.config.Configuration;

public class Town {

	private String name;
	private QuickTown qt;

	public Town(String name,QuickTown plugin) {
		this.name=name;
		this.qt=plugin;
	}
	public void AddToUndo(int x,int y,int z,int id) {
		mnx=mnx<x?mnx:x;
		mny=mny<x?mny:x;
		mnz=mnz<x?mnz:x;
		mnx=mnx>x?mnx:x;
		mny=mny>x?mny:x;
		mxz=mxz>x?mxz:x;
		if(!undo.containsKey(x)) {
			undo.put(x, new HashMap<Integer,HashMap<Integer,Integer>>());
		}
		if(!undo.get(x).containsKey(y)) {
			undo.get(x).put(y, new HashMap<Integer,Integer>());
		}
		undo.get(x).get(y).put(z, id);
	}
	public int getUndoAt(int x,int y,int z) {
		if(!undo.containsKey(x))return 0;
		if(!undo.get(x).containsKey(y))return 0;
		if(!undo.get(x).get(y).containsKey(z))return 0;
		return undo.get(x).get(y).get(z);
	}
	int mnx=0;
	int mny=0;
	int mnz=0;
	int mxx=0;
	int mxy=0;
	int mxz=0;
	World world;
	private HashMap<Integer,HashMap<Integer,HashMap<Integer,Integer>>>undo=new HashMap<Integer,HashMap<Integer,HashMap<Integer,Integer>>>();
	public void create(Location start) {
		world=start.getWorld();
		final Configuration c=new Configuration(new File("plugins/QuickTown/"+name+".yml"));
		c.load();
		final int ax=start.getBlockX()-c.getInt("town.width", 1)/2;
		final int ay=start.getBlockY();
		final int az=start.getBlockX()-c.getInt("town.length", 1)/2;
		final int length=c.getInt("town.length", 1);
		final int width=c.getInt("town.width", 1);
		final int height=c.getInt("town.height", 1);
		qt.getServer().getScheduler().scheduleAsyncDelayedTask(qt, new Runnable() {
			@Override
			public void run() {
				for(int x=ax;x<ax+length;x++) {
					for(int y=ay;y<ay+height;y++) {
						for(int z=az;z<az+width;z++) {
							AddToUndo(x,y,z,world.getBlockTypeIdAt(x, y, z));
						}
					}
				}
				qt.getServer().broadcastMessage("You may lag some as a city is being built");
				qt.getServer().getScheduler().scheduleAsyncDelayedTask(qt, new Runnable() {

					@Override
					public void run() {
						for(int rx=0;rx<length;rx++) {
							for(int ry=0;ry<height;ry++) {
								for(int rz=0;rz<width;rz++) {
									int x=ax+rx;
									int y=ay+ry;
									int z=az+rz;
									int id=c.getInt("town.blocks."+rx+"."+ry+"."+rz, 0);
									world.getBlockAt(x, y, z).setTypeId(id);
								}
							}
						}
					}
					
				});
			}
			
		});
	}
	public void undo() {
		for(int x=mnx;x<=mxx;x++) {
			for(int y=mny;y<=mxy;y++) {
				for(int z=mnz;z<=mxz;z++) {
					world.getBlockAt(x, y, z).setTypeId(this.getUndoAt(x, y, z));
				}
			}
		}
	undo.clear();	
	}

}
