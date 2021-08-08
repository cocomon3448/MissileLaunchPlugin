package rocketMissile;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class LaunchFunc extends JavaPlugin implements Listener{
	
	
	private static double distanceSquared(Vector from, Vector to)
    	{
        	double dx = to.getBlockX() - from.getBlockX();
        	double dz = to.getBlockZ() - from.getBlockZ();
       		return dx * dx + dz * dz;
	}
	public static Vector calculateVelocity(Vector from, Vector to, int heightGain)
    	{
		//approx.
		double gravity = 0.15;
		// Block locations
		int endGain = to.getBlockY() - from.getBlockY();
		double horizDist = Math.sqrt(distanceSquared(from, to));
		// Height gain
		int gain = heightGain;
		double maxGain = gain > (endGain + gain) ? gain : (endGain + gain);
		// Solve quadratic equation for velocity
		double a = -horizDist * horizDist / (4 * maxGain);
		double b = horizDist;
		double c = -endGain;
		double slope = -b / (2 * a) - Math.sqrt(b * b - 4 * a * c) / (2 * a);
		// Vertical velocity
		double vy = Math.sqrt(maxGain * gravity);
		// Horizontal velocity
		double vh = vy / slope;
		// Calculate horizontal direction
		int dx = to.getBlockX() - from.getBlockX();
		int dz = to.getBlockZ() - from.getBlockZ();
		double mag = Math.sqrt(dx * dx + dz * dz);
		double dirx = dx / mag;
		double dirz = dz / mag;
		// Horizontal velocity components
		double vx = vh * dirx;
		double vz = vh * dirz;
		return new Vector(vx, vy, vz);
    	}
	public static void launchMissile(Location target, Location p) {
		@SuppressWarnings("deprecation")
		FallingBlock fblk = p.getWorld().spawnFallingBlock(p, Material.BEDROCK, (byte) 0);
		fblk.setDropItem(false);
		fblk.setVelocity(calculateVelocity(p.toVector(), target.toVector(), 6));
	}
}
