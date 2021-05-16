package com.Sora.RPGStat.Stat;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpChangeEvent;

import com.Sora.RPGStat.Effect.Sound;

public class Stat {
	StatUtile utile = new StatUtile();
	Sound s = new Sound();
	int statCnt = utile.getStatCnt();
	
	public void CreateNewStat(Player p) {
		File filename = new File("plugins/RPGStat/Stat/" + p.getUniqueId().toString() + ".yml");
		File folder_Location1 = new File("plugins/RPGStat");
		File folder_Location2 = new File("plugins/RPGStat/Stat");
		try {
			if(!filename.exists()) {
				folder_Location1.mkdir();
				folder_Location2.mkdir();
				filename.createNewFile();
				BufferedWriter w = new BufferedWriter(new FileWriter(filename));
				w.append("레벨:0" + "\r\n" + "경험치:0" + "\r\n" + "최대경험치:1" + "\r\n" + 
						"근력:0" + "\r\n" + 
						"체력:0" + "\r\n" + 
						"민첩:0" + "\r\n" + 
						"지력:0" + "\r\n" + 
						"행운:0" + "\r\n" + 
						"방어:0" + "\r\n" + 
						"스텟포인트:0" + "\r\n" + 
						"이름:" + p.getName());
				w.close();
			}
			
		}catch(IOException e) {}
	}
	public int[] getStat(String player) {
		File filename = new File("plugins/RPGStat/Stat/" + player + ".yml");
		int[] stat = new int[statCnt];
		try {
			BufferedReader r = new BufferedReader(new FileReader(filename));
			for(int i = 0; i < statCnt; i++) {
				stat[i] = Cutter(r.readLine());
			}
			r.close();
		}catch(IOException e) {}
		return stat;
	}
	public int Cutter(String line) {
		String[] cut = line.split(":");
		return Integer.parseInt(cut[1]);
	}
	
	public void setStat(Player p, int[] stat)
	{
		File filename = new File("plugins/RPGStat/Stat/" + p.getUniqueId().toString() + ".yml");
		try
		{
			BufferedWriter w = new BufferedWriter(new FileWriter(filename));
			w.append("레벨:" + stat[0] + "\r\n" + 
					"경험치:" + stat[1] + "\r\n" + "최대경험치:" + stat[2] + "\r\n" + 
					"근력:" + stat[3] + "\r\n" + 
					"체력:" + stat[4] + "\r\n" + 
					"민첩:" + stat[5] + "\r\n" + 
					"지력:" + stat[6] + "\r\n" + 
					"행운:" + stat[7] + "\r\n" + 
					"방어:" + stat[8] + "\r\n" + 
					"스텟포인트:" + stat[9] + "\r\n" + 
					"이름:" + p.getName());
			w.close();
			
		}
		catch (IOException locallIoException){}
		appStat(p);
	}
	
	public void UpStat(int[] stat, Player p, int num)
	{
		if(stat[9] > 0)
		{
			if(stat[num] < 800) 
			{
				stat[num] = stat[num] + 1;
				stat[9] = stat[9] - 1;
				setStat(p, stat);
				s.SP(p, org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.8F);
				switch(num)
				{
					case 3: p.sendMessage("§e[§9SYSTEM§e] §a근력이 1 상승했습니다");break;
					case 4: p.sendMessage("§e[§9SYSTEM§e] §a체력이 1 상승했습니다");
							p.setHealth(p.getHealth()+utile.getHp(stat[0]));break;
					case 5: p.sendMessage("§e[§9SYSTEM§e] §a민첩이 1 상승했습니다");break;
					case 6: p.sendMessage("§e[§9SYSTEM§e] §a지력이 1 상승했습니다");break;
					case 7: p.sendMessage("§e[§9SYSTEM§e] §a행운이 1 상승했습니다");break;
					case 8: p.sendMessage("§e[§9SYSTEM§e] §a방어가 1 상승했습니다");break;
				}
			}
			else
			{
				s.SP(p, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				p.sendMessage("§e[§9SYSTEM§e] §c이 스텟은 더 이상 올릴 수 없습니다");
			}
				
		}
		else
		{
			s.SP(p, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			p.sendMessage("§e[§9SYSTEM§e] §c스텟 포인트가 부족합니다");
		}
	}
	
	public void ResetStat(int[] stat, Player p) {
		for(int i = 3; i < 10; i++) stat[i] = 0;
		
		stat[9] = stat[0] * utile.getSp();
		
		setStat(p, stat);
		s.SP(p, org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.8F);
		
		p.sendMessage("§e[§9SYSTEM§e] §a스탯을 초기화했습니다");
	}
	
	public void ExpUp(PlayerExpChangeEvent e) {
		int[] stat = new int[utile.getStatCnt()];
		stat = getStat(e.getPlayer().getUniqueId().toString());
		int xp;
		
		
		if(stat[0] <= utile.getLv1())
			xp = (e.getAmount()*3);
		else if(stat[0] <= utile.getLv2())
			xp = (int) (e.getAmount()*2.5);
		else if(stat[0] <= utile.getLv3())
			xp = (int) (e.getAmount()*1.5);
		else 
			xp = (e.getAmount());
		
		
		if(!(e.getPlayer().getWorld().getName().endsWith("_nether") && e.getPlayer().getLocation().getBlockY() >= 125)) {
			stat[1] += xp;
			e.getPlayer().sendMessage("§e["+xp+"경험치 획득!]");
			LevelUP(stat, e.getPlayer());
			setStat(e.getPlayer(), stat);
		}else {
			e.getPlayer().sendMessage("§c[이곳에서 경험치를 획득할 수 없습니다!]");
		}
	}
	
	@SuppressWarnings("deprecation")
	public int[] LevelUP(int[] stat, Player p)
	{
		if(stat[1] >= stat[2]) {
		while(stat[1] >= stat[2])
		{
			stat[1] = stat[1] - stat[2];
			stat[0] = stat[0] + 1;
			stat[2] = (int) ((Math.pow(stat[0], 2.5) - Math.pow(stat[0]-1, 2.5))*1);
			stat[9] = stat[9] + 3;
			p.sendMessage("§a[레벨업!]");
		}
		s.SP(p, org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 0.8F);
		setStat(p, stat);
		
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20);
		}
		return stat;
	}
	
	@SuppressWarnings("deprecation")
	public void appStat(Player p) 
	{
		int[] stat = new int[utile.getStatCnt()];
		stat = getStat(p.getUniqueId().toString());
		
		stat[2] = (int) ((Math.pow(stat[0], 2.5) - Math.pow(stat[0]-1, 2.5))*1);
		LevelUP(stat, p);
		
		name(stat, p);
		
		p.setMaxHealth((double) 10 + (stat[4] * utile.getHp(stat[0])));
		p.setWalkSpeed((float) (0.2 + (stat[5] * utile.getSpeed(stat[0]))));
	}
	
	public void name(int[] stat, Player p) {
		String lv = "§f[§eLv.§f" + stat[0] + "] ";
		p.setDisplayName(lv + p.getName());
		p.setPlayerListName(lv + p.getName());
		
	}
	
	

}
