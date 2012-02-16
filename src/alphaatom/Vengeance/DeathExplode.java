package alphaatom.Vengeance;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathExplode implements Listener {
	public static Vengeance plugin;
	public int exsize;
	public boolean exsuicide;
	
	public DeathExplode(Vengeance instance) {
		plugin = instance;
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Entity eType = event.getEntity();
			Location place = eType.getLocation();
			Player player = (Player) event.getEntity();
			EntityDamageEvent damagecause = player.getLastDamageCause();
			DamageCause damagecausestr = damagecause.getCause();
			String damagecausestr_ing = damagecausestr.toString();
			if(isCharged(player)) {
				PlayerDeathEvent event2 = (PlayerDeathEvent) event;
				String deathprepend = event2.getDeathMessage();
				String deathappend = " and left a huge explosion!";
				String deathmsg = deathprepend + deathappend;
				event2.setDeathMessage(deathmsg);
				exsuicide = Vengeance.exsuicide;
				if (exsuicide || !damagecausestr_ing.equals("SUICIDE")) {
				exsize = Vengeance.exsize;
				EatPlayerListener.CancelAll();
				float exitemF = exsize;
				if (!player.hasPermission("vengeance.damage")) {
					exitemF = Float.valueOf(0);
				}
				player.getWorld().createExplosion(place, exitemF);
				setCharged(player, false);
				}
			}
		}
	}
	public void setCharged(Player player, Boolean bool) {
		if (isExplode.isExplode.containsKey(player)) {
			isExplode.isExplode.put(player, bool);
		} else {
			isExplode.isExplode.put(player, bool);
		}
	}
	public boolean isCharged(Player player) {
		if (isExplode.isExplode.containsKey(player)) {
			if (isExplode.isExplode.get(player)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}