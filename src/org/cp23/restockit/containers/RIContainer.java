//Copyright (C) 2014 Dr Daniel Naylor (dualspiral)
//Original RestockIt Copyright (c) 2011-2014 Chris Price (xCP23x)
//This software uses the GNU GPL v2 license
//See http://github.com/xCP23x/RestockIt/blob/master/README and http://github.com/xCP23x/RestockIt/blob/master/LICENSE for details

package org.cp23.restockit.containers;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;

/**
 * Class that represents any RestockIt container and associated sign.
 */
public abstract class RIContainer {
    
    protected Block container;
    protected Sign sign;
    
    public static RIContainer getRIContainer(Block block) {
        // Is the block actually a container? If not, return null.
        Sign sign = RIContainerUtils.getRestockItSignForContainer(block);
        if (sign == null) {
            return null;
        }
        
        // Check on sign row 3 for the type of container
        if ("incinerator".equals(sign.getLine(2).toLowerCase())) {
            return new IncineratorRIContainer(block, sign);
        }
        
        if ("contents".equals(sign.getLine(2).toLowerCase())) {
            return new ContentsBasedRIContainer(block, sign);
        }
        
        return new StandardRIContainer(block, sign);
    }
    
    protected RIContainer(Block container, Sign sign) {
        this.container = container;
        this.sign = sign;
    }
}
