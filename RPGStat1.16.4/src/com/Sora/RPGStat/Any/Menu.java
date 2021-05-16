package com.Sora.RPGStat.Any;

import org.bukkit.entity.Player;

public class Menu {
	public Transaction trans = new Transaction();
	
	public void spawn(Player p){
		p.teleport(p.getWorld().getSpawnLocation());
		p.sendMessage("§e이동 완료!");
	}
//	public void Transaction(Player p){
//		trans.Trans(p1);
//	}
	public void kill(Player p){
		p.setHealth(0);
	}
}
