package com.Sora.RPGStat.Any;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Transaction {
	public void request(String arg, Player p, Server server) {
		if (arg.equals("수락")) {
		} else if (arg.equals("거절")) {
		} else {
			@SuppressWarnings("unchecked")
			List<Player> pl = (List<Player>) server.getOnlinePlayers();
			for (Player player : pl)
				if (player.getName().equalsIgnoreCase(arg)) {
					player.sendMessage(p.getName()+"님이 거래를 신청하셨습니다.");
					Trans(p, player);

				}
		}
	}
	
	public void Trans(Player p1, Player p2) {
		Inventory inv = Bukkit.createInventory(null, 27, "§0거래");
		
		p1.openInventory(inv);
		p2.openInventory(inv);
	}
	
}
