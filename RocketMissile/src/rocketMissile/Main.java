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
		System.out.println("RocketMissle 플러그인이 활성화되었습니다.");
		getServer().getPluginManager().registerEvents(new MissileLaunchEvent(), this);
	}
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
//		super.onDisable();
		System.out.println("플러그인이 비활성화되었습니다.");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(command.getName().equalsIgnoreCase("settarget")) {
			if(args.length == 0) {
				p.sendMessage("커멘드를 길게 쳐주세요");
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
				p.sendMessage("커멘드를 길게 쳐주세요");
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