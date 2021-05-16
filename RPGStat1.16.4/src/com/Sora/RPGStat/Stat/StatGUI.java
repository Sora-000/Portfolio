package com.Sora.RPGStat.Stat;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;


@SuppressWarnings("deprecation")
public class StatGUI 
{
	public Stat s = new Stat();
	StatUtile utile = new StatUtile();
	int statCnt = utile.getStatCnt();
	public void Stack(String display, Material i, int data, int stack, List<String> lore, int loc, Inventory inv) 
	{
		ItemStack item = new MaterialData(i, (byte) data).toItemStack(stack);
		ItemMeta item_Meta = item.getItemMeta();
		item_Meta.setDisplayName(display);
		item_Meta.setLore(lore);
		item.setItemMeta(item_Meta);
		inv.setItem(loc, item);
	}
	public void Head(String display, List<String> lore, int loc, Inventory inv, String name) 
	{
	    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
	    SkullMeta item = (SkullMeta)skull.getItemMeta();
	    item.setOwner(name);
		
	    item.setDisplayName(display);
	    item.setLore(lore);
		
		skull.setItemMeta((ItemMeta)item);
		inv.setItem(loc, skull);
	}
	
	public void StatusGUI(Player p) 
	{
		s.appStat(p);
		int[] stat = new int[utile.getStatCnt()];
		stat = s.getStat(p.getUniqueId().toString());
		
		Inventory inv = Bukkit.createInventory(null, 45, "§0스탯");
		for(int i = 0; i < 45;i++) 
			if(!((10<=i&&i<=16)||(19<=i&&i<=25)||(28<=i&&i<=34)))
				Stack("§c", Material.WHITE_STAINED_GLASS_PANE,0,1,Arrays.asList(""),i,inv);
		
		Head("§f" + p.getName(), Arrays.asList("§b레벨 : §f" + stat[0],
				"§e경험치 : §f" + stat[1] + " / " + stat[2],
				"§a스탯 포인트 : " + stat[9]),4,inv, p.getName());
		
		Stack("§f[§c근력§f]", Material.IRON_SWORD,0,1,Arrays.asList("§8 "+ 
				stat[3] + " / 800","§8근력 1당 근접 공격력"+(float)utile.getDm()),19,inv);
		
		Stack("§f[§a체력§f]", Material.IRON_CHESTPLATE,0,1,Arrays.asList("§8 "+ 
				stat[4] + " / 800","§8체력 1당 체력"+(float)utile.getHp()),21,inv);
		
		Stack("§f[§7민첩§f]", Material.LEATHER_BOOTS,0,1,Arrays.asList("§8 "+ 
				stat[5] + " / 800","§8민첩 1당 이동속도"+utile.getSpeed()*500+"%","§8민첩 1당 화살 공격력"+utile.getAd()),23,inv);
		
//		Stack("§f[§9지력§f]", 369,0,1,Arrays.asList("§f"+ 
//				stat[6]),23,inv);
//		
//		Stack("§f[§e행운§f]", 322,0,1,Arrays.asList("§f"+ 
//				stat[7]),24,inv);
		
		Stack("§f[§9방어§f]", Material.SHIELD,0,1,Arrays.asList("§8 "+ 
				stat[8] + " / 800","§8방어 1당 방어력" + utile.getDf()),25,inv);
		
		Stack("§f[§b스탯 초기화§f]", Material.NETHER_STAR,0,1,Arrays.asList(),40,inv);
		
//		Stack("§f[§e칭호§f]", Material.EMERALD,0,1,Arrays.asList("§8 "+ 
//				""),42,inv);
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public void StatGUIClicked(InventoryClickEvent e)
	{
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		if(ChatColor.stripColor(e.getView().getTitle()).equals("스탯"))
		{
			if(e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR) && e.getCurrentItem().hasItemMeta())
			{
				int[] stat = new int[utile.getStatCnt()];
				stat = s.getStat(p.getUniqueId().toString());
				switch((ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())))
				{
					case "[근력]": s.UpStat(stat, p, 3); StatusGUI(p);break;
					case "[체력]": s.UpStat(stat, p, 4); StatusGUI(p);break;
					case "[민첩]": s.UpStat(stat, p, 5); StatusGUI(p);break;
					case "[지력]": s.UpStat(stat, p, 6); StatusGUI(p);break;
					case "[행운]": s.UpStat(stat, p, 7); StatusGUI(p);break;
					case "[방어]": s.UpStat(stat, p, 8); StatusGUI(p);break;
					case "[스탯 초기화]": s.ResetStat(stat, p); StatusGUI(p);break;
				}
			}
		}
		
	}
}
