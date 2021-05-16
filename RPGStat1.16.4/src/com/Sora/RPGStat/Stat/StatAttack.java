package com.Sora.RPGStat.Stat;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class StatAttack {
	StatUtile utile = new StatUtile();
	Stat s = new Stat();
	
	public void Attack(EntityDamageByEntityEvent e) 
	{
		if (e.getDamager() != null) // 					대미지를 준 대상이 있을 때
		{
			int[] stat = new int[utile.getStatCnt()];
			
			if(e.getDamager() instanceof Projectile)// 		화살에 맞았을 때
			{
				Projectile pj = (Projectile) e.getDamager();
				if (pj.getShooter() instanceof Player) // 	쏜 사람이 플레이어
				{
					Player p = (Player) pj.getShooter();
					stat = s.getStat(p.getUniqueId().toString());
					
					double plusdamage = e.getDamage() + (stat[5] * utile.getAd());
					
					e.setDamage(plusdamage);
					p.sendMessage("§a" + (int) plusdamage + "§f의 피해를 입혔습니다");
				}
				if (e.getEntity() instanceof Player) // 	맞은 사람이 플레이어
				{
					Player p = (Player) e.getEntity();
					stat = s.getStat(p.getUniqueId().toString());
					
					double minusdamage = e.getDamage() - (stat[8] * utile.getDf());
					
					if (minusdamage < 0)
						minusdamage = 0;
					
					e.setDamage(minusdamage);
					p.sendMessage("§c" + (int) minusdamage + "§f의 피해를 입었습니다");
				}
			}else //                                   		 근접 대미지
			{
				if (e.getDamager() instanceof Player)// 	때린 사람이 플레이어
				{
					Player p = (Player) e.getDamager();
					stat = s.getStat(p.getUniqueId().toString());
					
					double plusdamage = e.getDamage() + (stat[3] * utile.getDm(stat[0]));
					
					e.setDamage(plusdamage);
					p.sendMessage("§a" + (int) plusdamage + "§f의 피해를 입혔습니다");
				}
				if (e.getEntity() instanceof Player) // 	맞은 사람이 플레이어
				{
					Player p = (Player) e.getEntity();
					stat = s.getStat(p.getUniqueId().toString());
					
					double minusdamage = e.getDamage() - (stat[8] * utile.getDf());
					
					if (minusdamage < 0)
						minusdamage = 0;
					
					e.setDamage(minusdamage);
					p.sendMessage("§c" + (int) minusdamage + "§f의 피해를 입었습니다");
				}
			}

		}
	}
}
