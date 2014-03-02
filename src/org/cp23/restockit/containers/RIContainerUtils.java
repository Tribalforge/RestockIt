//Copyright (C) 2014 Dr Daniel Naylor (dualspiral)
//Original RestockIt Copyright (c) 2011-2014 Chris Price (xCP23x)
//This software uses the GNU GPL v2 license
//See http://github.com/xCP23x/RestockIt/blob/master/README and http://github.com/xCP23x/RestockIt/blob/master/LICENSE for details

package org.cp23.restockit.containers;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.cp23.restockit.RestockIt;
import org.cp23.restockit.enums.ListType;

/**
 * Singleton class that contains static methods.
 */
public final class RIContainerUtils {

    /**
     * Gets the RestockIt Sign for the selected container, if it exists.
     *
     * @param container The container to get the RI sign for
     * @return The @link{ Sign } if it is a RestockIt container, or
     * <code>null</code> otherwise.
     */
    public static Sign getRestockItSignForContainer(Block container) {
        // If the object isn't a container, return null.
        if (RestockIt.isContainer(container.getType())) {
            return null;
        }

        Sign ret = getSignFromBlock(container);
        if (ret != null) {
            return ret;
        }
        
        // If the object is a double block, do it again on the other block!    
        Block block = getDoubleChest(container);
        if (block != null) {
            return getSignFromBlock(block);
        }
        
        return null;
    }

    private static Sign getSignFromBlock(Block container) {
        
        // First, check the block below.
        Block test = container.getWorld().getBlockAt(container.getX(), container.getY() - 1, container.getZ());
        if (test.getState() instanceof Sign) {
            Sign sign = (Sign)test.getState();    
            if (isRISign(sign)) {
                return sign;
            }
        }
        
        // Check the block above.
        test = container.getWorld().getBlockAt(container.getX(), container.getY() + 1, container.getZ());
        if (test.getState() instanceof Sign) {
            Sign sign = (Sign)test.getState();    
            if (isRISign(sign)) {
                return sign;
            }
        }
        
        // Can't get the sign.
        return null;
    }
    
    private static boolean isRISign(Sign sign) {
        if ((sign.getType() == Material.WALL_SIGN || sign.getType() == Material.SIGN_POST) && RIContainerUtils.isRISign(sign)) {   
            String str = sign.getLine(1).toLowerCase();
            return (str.contains("restockit") || str.contains("restock it") || str.contains("full chest") || str.contains("full dispenser"));
        }
        
        return false;
    }
    
    /**
     * Get the second half of the chest if it is a double chest.
     * @param cont The container the check for a double.
     * @return The second @link { Block } if it is a double chest, or <code>null</code> otherwise.
     */
    public static Block getDoubleChest(Block cont){
        //Return chest if there is one next to the block given
        if(!RestockIt.isInList(cont.getType(), ListType.DOUBLE)) return null; //Only continue for containers that can be doubled
        int x = cont.getX(), y = cont.getY(), z = cont.getZ();
        World world = cont.getWorld();
        if(RestockIt.isInList(world.getBlockAt(x+1, y, z).getType(), ListType.DOUBLE)) return world.getBlockAt(x+1, y, z);
        if(RestockIt.isInList(world.getBlockAt(x-1, y, z).getType(), ListType.DOUBLE)) return world.getBlockAt(x-1, y, z);
        if(RestockIt.isInList(world.getBlockAt(x, y, z+1).getType(), ListType.DOUBLE)) return world.getBlockAt(x, y, z+1);
        if(RestockIt.isInList(world.getBlockAt(x, y, z-1).getType(), ListType.DOUBLE)) return world.getBlockAt(x, y, z-1);
        return null;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private RIContainerUtils() {
    }
}
