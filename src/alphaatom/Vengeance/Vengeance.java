package alphaatom.Vengeance;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Vengeance extends JavaPlugin {
	
	public static Vengeance plugin;
	public static int exsize;
	public static int exdur;
	public static int exitem;
	public static boolean exnodam;
	public static boolean exsuicide;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final EatPlayerListener playerListener = new EatPlayerListener(this);
	public final DeathExplode deathListener = new DeathExplode(this);
	public static FileConfiguration config;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " deactivated!");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.playerListener, this);
		pm.registerEvents(this.deathListener, this);
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " v" + pdffile.getVersion() + " activated!");
		try{
			config = getConfig();
			File Vengeance = new File("plugins" + File.separator + "Vengeance" + File.separator + "config.yml");
			Vengeance.mkdir();
			if(!config.contains("Explosion.size")){
			    config.set("Explosion.size", 4);
			}
			if(!config.contains("Explosion.duration")){
				config.set("Explosion.duration", 10);
			}
            if(!config.contains("Explosion.item")){
                config.set("Explosion.item", 289);
            }
            if (!config.contains("Explosion.allow-explosion-on-suicide")){
            	config.set("Explosion.allow-explosion-on-suicide", true);
            }
            if (!config.contains("Explosion.no-damage")) {
            	config.set("Explosion.no-damage", false);
            }
			saveConfig();
			exitem = config.getInt("Explosion.item");
			exdur = config.getInt("Explosion.duration");
			exsize = config.getInt("Explosion.size");
			exsuicide = config.getBoolean("Explosion.allow-explosion-on-suicide");
			exnodam = config.getBoolean("Explosion.no-damage");
			if (exsize > 10) {
				this.logger.warning("Warning! Explosion size is over 10, beware of potential server damage");
			}
			if (exnodam) {
				exsize = 0;
			}
		}catch(Exception e1){
		e1.printStackTrace();
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	if (sender instanceof Player) {
		if (cmd.getName().equalsIgnoreCase("v") && sender.hasPermission("vengeance.check")) {
			final ChatColor RED = ChatColor.RED;
			final ChatColor WHITE = ChatColor.WHITE;
			if (args.length > 1) {
				sender.sendMessage(RED + "[Vengeance] " + WHITE + "Too many arguments! Usage: /v check");
				return false;
			} 
			if (args.length < 1) {
				sender.sendMessage(RED + "[Vengeance] " + WHITE + "Not enough arguments! Usage: /v check");
				return false;
			} else {
				Player player = (Player) sender;
				if (isCharged(player)) {
					sender.sendMessage(RED + "[Vengeance] " + WHITE + "Explosion on death is: active");
					return true;
				} else {
					sender.sendMessage(RED + "[Vengeance] " + WHITE + "Explosion on death is: not active");
					return true;
				}
			}
		}
	}
	return false;
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
