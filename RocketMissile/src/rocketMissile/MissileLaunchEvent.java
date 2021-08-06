package rocketMissile;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class MissileLaunchEvent implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent event)
	{
	    if (event.getEntity() instanceof FallingBlock)
	    {
	    	if (event.getEntityType() == EntityType.FALLING_BLOCK) {
	            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
	            if (fallingBlock.getMaterial() == Material.BEDROCK) {
	            	Location blockWhereFallingLands = event.getBlock().getLocation();
	            	System.out.println(blockWhereFallingLands);
	    	        blockWhereFallingLands.getWorld().createExplosion(blockWhereFallingLands, 10.0f);
	                fallingBlock.getLocation().getBlock().setType(Material.AIR);
	                fallingBlock.remove();
	                event.setCancelled(true);
	                event.getBlock().setType(Material.AIR);
	                
	            }
	        }
	    }
	}

}