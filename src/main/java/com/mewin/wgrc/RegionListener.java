package com.mewin.wgrc;

import com.mewin.WGRegionEvents.events.RegionEnteredEvent;
import com.mewin.WGRegionEvents.events.RegionEvent;
import com.mewin.WGRegionEvents.events.RegionLeftEvent;
import java.util.Set;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class RegionListener implements Listener {

    private WGRegionCommandsPlugin plugin;
    
    public RegionListener(WGRegionCommandsPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onRegionLeft(RegionLeftEvent e)
    {
        if (e.getRegion().getFlag(WGRegionCommandsPlugin.PLAYER_COMMAND_LEAVE_FLAG) != null)
        {
            parseCmd(e.getPlayer(), (Set<String>) e.getRegion().getFlag(WGRegionCommandsPlugin.PLAYER_COMMAND_LEAVE_FLAG), e);
        }
        
        if (e.getRegion().getFlag(WGRegionCommandsPlugin.SERVER_COMMAND_LEAVE_FLAG) != null)
        {
            parseCmd(plugin.getServer().getConsoleSender(),(Set<String>) e.getRegion().getFlag(WGRegionCommandsPlugin.SERVER_COMMAND_LEAVE_FLAG), e);
        }
    }
    
    @EventHandler
    public void onRegionEntered(RegionEnteredEvent e)
    {
        if (e.getRegion().getFlag(WGRegionCommandsPlugin.PLAYER_COMMAND_ENTER_FLAG) != null)
        {
            parseCmd(e.getPlayer(), (Set<String>) e.getRegion().getFlag(WGRegionCommandsPlugin.PLAYER_COMMAND_ENTER_FLAG), e);
        }
        
        if (e.getRegion().getFlag(WGRegionCommandsPlugin.SERVER_COMMAND_ENTER_FLAG) != null)
        {
            parseCmd(plugin.getServer().getConsoleSender(), (Set<String>) e.getRegion().getFlag(WGRegionCommandsPlugin.SERVER_COMMAND_ENTER_FLAG), e);
        }
    }
    
    private void parseCmd(CommandSender cs, Set<String> cmds, RegionEvent e)
    {
        for (String cmd : cmds)
        {
            cmd = cmd.replaceAll("\\{player\\}", e.getPlayer().getName());
            cmd = cmd.replaceAll("\\{region\\}", e.getRegion().getId());
            cmd = cmd.replaceAll("\\{comma\\}", ",");

            if (cmd.startsWith("/"))
            {
                cmd = cmd.substring(1);
            }
            
            plugin.getServer().dispatchCommand(cs, cmd);
        }
    }
}
