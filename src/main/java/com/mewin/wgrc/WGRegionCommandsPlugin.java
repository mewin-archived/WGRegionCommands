package com.mewin.wgrc;


import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.mewin.WGCustomFlags.flags.CustomSetFlag;
import com.sk89q.worldguard.protection.flags.StringFlag;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class WGRegionCommandsPlugin extends JavaPlugin {
    public static final StringFlag CMD_FLAG = new StringFlag("cmd");
    public static final CustomSetFlag PLAYER_COMMAND_ENTER_FLAG = new CustomSetFlag("player-enter-command", CMD_FLAG);
    public static final CustomSetFlag PLAYER_COMMAND_LEAVE_FLAG = new CustomSetFlag("player-leave-command",CMD_FLAG);
    public static final CustomSetFlag SERVER_COMMAND_ENTER_FLAG = new CustomSetFlag("server-enter-command", CMD_FLAG);
    public static final CustomSetFlag SERVER_COMMAND_LEAVE_FLAG = new CustomSetFlag("server-leave-command", CMD_FLAG);
    
    private RegionListener listener;
    private WGCustomFlagsPlugin custPlugin;
    
    @Override
    public void onEnable()
    {
        Plugin plug = getServer().getPluginManager().getPlugin("WGCustomFlags");
        
        if (plug == null || !(plug instanceof WGCustomFlagsPlugin) || !plug.isEnabled())
        {
            getLogger().warning("Could not load WorldGuard Custom Flags Plugin, disabling");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        else
        {
            custPlugin = (WGCustomFlagsPlugin) plug;
        }
        
        custPlugin.addCustomFlag(PLAYER_COMMAND_ENTER_FLAG);
        custPlugin.addCustomFlag(PLAYER_COMMAND_LEAVE_FLAG);
        custPlugin.addCustomFlag(SERVER_COMMAND_ENTER_FLAG);
        custPlugin.addCustomFlag(SERVER_COMMAND_LEAVE_FLAG);
        
        listener = new RegionListener(this);
        
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable()
    {
        
    }
}
