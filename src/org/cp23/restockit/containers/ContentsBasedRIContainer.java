//Copyright (C) 2014 Dr Daniel Naylor (dualspiral)
//Original RestockIt Copyright (c) 2011-2014 Chris Price (xCP23x)
//This software uses the GNU GPL v2 license
//See http://github.com/xCP23x/RestockIt/blob/master/README and http://github.com/xCP23x/RestockIt/blob/master/LICENSE for details

package org.cp23.restockit.containers;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;

/**
 * Represents a container where the restocking is performed based on the 
 * contents of the chest.
 */
class ContentsBasedRIContainer extends RIContainer {

    public ContentsBasedRIContainer(Block container, Sign sign) {
        super(container, sign);
    }
    
}
