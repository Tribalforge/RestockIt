//Copyright (C) 2014 Dr Daniel Naylor (dualspiral)
//Original RestockIt Copyright (c) 2011-2014 Chris Price (xCP23x)
//This software uses the GNU GPL v2 license
//See http://github.com/xCP23x/RestockIt/blob/master/README and http://github.com/xCP23x/RestockIt/blob/master/LICENSE for details

package org.cp23.restockit.permissionmanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Class that implements @link { RIPermissionManager } to use PermissionsEx.
 */
class PermissionsExPermissionManager extends RIPermissionManager {

    /**
     * The PermissionsEx PermissionManager.
     */
    private final PermissionManager pm;
    
    /**
     * Constructs the permission manager.
     */
    PermissionsExPermissionManager() {
        pm = PermissionsEx.getPermissionManager();
    }
    
    /**
     * Determines whether PermissionsEx is available.
     * @returns <code>true</code> if PermissionsEx is on the server.
     */ 
    static boolean isAvailable() {
        return Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx") 
                && Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx") instanceof PermissionsEx;
    }
        
    /**
     * Gets whether the player has the defined permission.
     * @param player The @link { Player } to check.
     * @param permission The permission to check for.
     * @return <code>true</code> if the player has the requested permission.
     */
    @Override
    protected boolean checkPermission(Player player, String permission) {
        return pm.has(player, permission, player.getWorld().getName());
    }

    @Override
    protected String getPermissionName() {
        return "PermissionsEx";
    }
    
}
