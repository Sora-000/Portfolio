package com.Sora.RPGStat.Any;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.Sora.RPGStat.Stat.StatGUI;

public class MenuUI {
	
	public StatGUI gui = new StatGUI();
	public Menu m = new Menu();
	
	
	
	public void Menu(Player p) {

		Inventory inv = Bukkit.createInventory(null, 27, "§0메뉴");
		
		gui.Stack("§f스폰", Material.RED_BED, 0, 1, Arrays.asList("§8 "+ 
				"스폰지점으로 이동"), 10, inv);
		
//		gui.Stack("§거래", Material.CHEST, 0, 1, Arrays.asList(), 12, inv);
		
		gui.Stack("§f자살", Material.WITHER_SKELETON_SKULL, 0, 1, Arrays.asList(), 12, inv);
		
		gui.Head("§f스탯", Arrays.asList(),14,inv, p.getName());
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public void MenuClicked(InventoryClickEvent e)
	{
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equals("메뉴"))
		{
			if(e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR) && e.getCurrentItem().hasItemMeta())
			{
				switch((ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					case "스폰": m.spawn(p);break;
//					case "거래": m.Transaction(p);break;
					case "자살": m.kill(p);;break;
					case "스탯": gui.StatusGUI(p);break;
				}
			}
		}
		
	}
}
