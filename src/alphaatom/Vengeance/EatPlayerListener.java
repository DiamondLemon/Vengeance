package alphaatom.Vengeance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EatPlayerListener implements Listener {
	public static Vengeance plugin;
	public int exitem;
	public int exdur;
	public boolean canexplode;
	
	public EatPlayerListener(Vengeance instance)  {
		plugin = instance;
	}
	@EventHandler
	public void useItemInHand(PlayerInteractEvent event) {
		final ChatColor RED = ChatColor.RED;
		final ChatColor WHITE = ChatColor.WHITE;
		Action performed = event.getAction();
		if (performed != Action.LEFT_CLICK_AIR && performed != Action.LEFT_CLICK_BLOCK) {
		final Player p = event.getPlayer();
		ItemStack current = p.getItemInHand();
		int itemid = current.getTypeId();
		int exitem = Vengeance.exitem;
		int exdur = Vengeance.exdur;
		int exdurtick = exdur*60*20;
		if (itemid == exitem && p.hasPermission("vengeance.consume")) {
			if (!isCharged(p)) {
		long exitemL = exdurtick;
		int itemamount = current.getAmount();
		int newamount = itemamount - 1;
		ItemStack cStack = p.getItemInHand();
		cStack.setAmount(newamount);
		if (itemamount == 1) {
			p.setItemInHand(null);
		}
		p.sendMessage(RED + "[Vengeance] " + WHITE + "Explosion on death activated for " + exdur + " minutes!");
		setCharged(p, true);
		Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable()
        {
                public void run()
                {
                        {
                                p.sendMessage(RED + "[Vengeance] " + WHITE + "The effect has worn off!");
                                setCharged(p, false);
                        }
                }
        }, exitemL);
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
	public static void CancelAll() {
		Bukkit.getScheduler().cancelAllTasks();
	}
}

