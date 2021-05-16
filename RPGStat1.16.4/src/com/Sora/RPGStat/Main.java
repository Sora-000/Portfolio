package com.Sora.RPGStat;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.Sora.RPGStat.Any.MenuUI;
import com.Sora.RPGStat.Any.Transaction;
import com.Sora.RPGStat.Stat.StatAttack;
import com.Sora.RPGStat.Stat.Stat;
import com.Sora.RPGStat.Stat.StatGUI;
import com.Sora.RPGStat.Stat.StatUtile;

public class Main extends JavaPlugin implements Listener {
	public Stat s = new Stat();
	public StatGUI gui = new StatGUI();
	public StatUtile utile = new StatUtile();
	public MenuUI menu = new MenuUI();
	public Transaction trans = new Transaction();
	public StatAttack attack = new StatAttack();
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		PluginDescriptionFile pdFile = this.getDescription();
		Bukkit.getConsoleSender().sendMessage("§9" + pdFile.getName() + " 플러그인 활성화");
	}

	public void onDisable() {
		PluginDescriptionFile pdFile = this.getDescription();
		Bukkit.getConsoleSender().sendMessage("§c" + pdFile.getName() + " 플러그인 비활성화");
	}

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		s.CreateNewStat(e.getPlayer());
		s.appStat(e.getPlayer());
		e.setJoinMessage("§a" + e.getPlayer().getName() + "§f님께서 입장하셨습니다!");
		e.getPlayer().sendMessage("§e/stat§f으로 스탯을 올릴 수 있습니다!");
		e.getPlayer().sendMessage("§e/menu§f로 메뉴를 열 수 있습니다!");
		e.getPlayer().sendMessage("§a"+utile.getLv1()+"레벨 이하에게 경험치 300% 이벤트 적용!");
		e.getPlayer().sendMessage("§a"+utile.getLv1()+"레벨 초과 "+utile.getLv2()+"레벨 이하에게 경험치 250% 이벤트 적용!");
		e.getPlayer().sendMessage("§a"+utile.getLv2()+"레벨 초과 "+utile.getLv3()+"레벨 이하에게 경험치 150% 이벤트 적용!");
		e.getPlayer().sendMessage("§a즐 §e겁 §c다!");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("공지")) {
			if (sender instanceof Player && !(sender.getName().equals("Gog_2020"))) {
				((Player) sender).kickPlayer(getName());
				return true;	
			}
			Bukkit.broadcastMessage("§a"+utile.getLv1()+"레벨 이하에게 경험치 300% 이벤트 적용!");
			Bukkit.broadcastMessage("§a"+utile.getLv1()+"레벨 초과 "+utile.getLv2()+"레벨 이하에게 경험치 250% 이벤트 적용!");
			Bukkit.broadcastMessage("§a"+utile.getLv2()+"레벨 초과 "+utile.getLv3()+"레벨 이하에게 경험치 150% 이벤트 적용!");
			return true;
		}

		if (label.equalsIgnoreCase("heal")) {
			if (sender instanceof Player && !(sender.getName().equals("Gog_2020"))) {
				((Player) sender).kickPlayer(getName());
				return true;	
			}
			if (args.length == 0 && sender instanceof Player) {
				Player p = (Player) sender;
				p.setHealth(p.getMaxHealth());
				p.setFoodLevel(20);
				p.sendMessage("§a당신은 회복되었습니다.");
				return true;
			} else if (args.length > 0 && getServer().getPlayer(args[0]) instanceof Player) {
				Player p = getServer().getPlayer(args[0]);
				if (args[0].equalsIgnoreCase(p.getName())) {
					p.setHealth(p.getMaxHealth());
					p.setFoodLevel(20);
					p.sendMessage("§a당신은 회복되었습니다.");
					sender.sendMessage("§a" + p.getName() + "님은 회복되었습니다.");
					return true;
				}
			}
		}

		if (!(sender instanceof Player))
			return false;

		if (label.equalsIgnoreCase("stat") || label.equalsIgnoreCase("스탯")) {
			if (args.length == 0) 
				gui.StatusGUI((Player) sender);
			else if (args.length > 0 && args[0] != null) {
				@SuppressWarnings("unchecked")
				List<Player> pl = (List<Player>) getServer().getOnlinePlayers();// 온라인밖에 안 되는 듯
				
				for (Player player : pl)
					if (player.getName().equals(args[0])) {
						s.appStat(player);
						return true;
					}
			}
			return true;
		}

		if (label.equalsIgnoreCase("inv")) {
			if (sender instanceof Player && !(sender.getName().equals("Gog_2020"))) {
				((Player) sender).kickPlayer(getName());
				return true;	
			}
			if (args.length > 0 && args[0] != null) {
				@SuppressWarnings("unchecked")
				List<Player> pl = (List<Player>) getServer().getOnlinePlayers();// 온라인밖에 안 되는 듯
				
				for (Player player : pl)
					if (player.getName().equals(args[0])) {
						Player p = (Player) sender;
						Inventory inv = (Inventory) player.getInventory();
						p.openInventory(inv);
						return true;
					}
			}
			//설명
			return true;
		}
		if (label.equalsIgnoreCase("menu") || label.equalsIgnoreCase("메뉴")) {
			menu.Menu((Player) sender);
			return true;
		}

		if (label.equalsIgnoreCase("거래")) {
			if (args.length > 0 && args[0] != null) {
				trans.request(args[0], (Player) sender, getServer());
			} else {

			}
			return true;
		}
		return false;
	}

//	public void Chat(PlayerChatEvent e) {
//		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
//		  if (e.getMessage().contains(p.getName())) {
//		    e.setMessage(e.getMessage().replace(p.getName(), ChatColor.RED + p.getName()));
//		  }
//		}
//	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if (ChatColor.stripColor(e.getView().getTitle()).equals("스탯")) {
			gui.StatGUIClicked(e);
		}
		if (ChatColor.stripColor(e.getView().getTitle()).equals("Player")) {
			e.setCancelled(false);
		}
		if (ChatColor.stripColor(e.getView().getTitle()).equals("메뉴")) {
			menu.MenuClicked(e);
		}
		if (ChatColor.stripColor(e.getView().getTitle()).equals("거래")) {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void ExpEvent(PlayerExpChangeEvent e) {
		s.ExpUp(e);
		
	}

	@EventHandler
	public void EntityAttack(EntityDamageByEntityEvent e) {
		attack.Attack(e);

	}
}