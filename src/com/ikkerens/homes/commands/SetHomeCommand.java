package com.ikkerens.homes.commands;

import com.ikkerens.homes.HomesPlugin;
import com.ikkerens.homes.events.SetHomeEvent;
import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class SetHomeCommand implements CommandExecutor {
    private final HomesPlugin plugin;

    public SetHomeCommand( final HomesPlugin plugin ) {
        this.plugin = plugin;
    }

    @Override
    public void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        if ( !( sender instanceof Player ) ) {
            sender.sendMessage( "This command may not be executed by the console." );
            return;
        }

        if ( !sender.hasPermission( "ikkerens.homes.use" ) ) {
            sender.sendMessage( "You do not have permission to set your home." );
            return;
        }

        final Player player = (Player) sender;
        final Location location = player.getLocation();

        final SetHomeEvent event = new SetHomeEvent( player, location );
        this.plugin.getPluginManager().triggerEvent( event );

        if ( !event.isCancelled() ) {
            this.plugin.getDB().setHome( player, location );
            player.sendMessage( "Your home has been set to your current location!" );
        }
    }

}
