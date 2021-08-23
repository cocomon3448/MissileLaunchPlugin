package rocketMissile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		System.out.println("[RocketMissile] Plugin Loaded");
		getServer().getPluginManager().registerEvents(new MissileLaunchEvent(), this);
	}
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
//		super.onDisable();
		System.out.println("[RocketMissile] Plugin Disabled");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("settarget")) {
			if(args.length == 0) {
				p.sendMessage("Input coordinates [x] [y] [z]");
			}
			else if(args.length > 0) {
				if(args != null) {
					double[] parsed = new double[3];
					for (int a = 0; a < 3; a++) {
					    parsed[a] = Double.parseDouble(args[a]);
					}
					Location target = new Location (p.getWorld(), parsed[0], parsed[1], parsed[2]);
					LaunchFunc.launchMissile(target, p.getLocation());
				}
			}
		}
		else if(command.getName().equalsIgnoreCase("settargetplayer")) {
			if(args.length == 0) {
				p.sendMessage("Input some nickname [nickname]");
			}
			else if(args.length > 0) {
				Player target = Bukkit.getPlayer(args[0]);
				System.out.println(target.getLocation());
				LaunchFunc.launchMissile(target.getLocation(), p.getLocation());
			}
		}
		return true;
	}
}
