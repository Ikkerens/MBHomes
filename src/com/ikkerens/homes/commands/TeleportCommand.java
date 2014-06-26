package com.ikkerens.homes.commands;

import com.ikkerens.homes.HomesPlugin;
import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class TeleportCommand implements CommandExecutor {
    private final HomesPlugin plugin;

    public TeleportCommand( final HomesPlugin plugin ) {
        this.plugin = plugin;
    }

    @Override
    public void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        if ( !( sender instanceof Player ) ) {
            sender.sendMessage( "This command may not be executed by the console." );
            return;
        }

        if ( !sender.hasPermission( "ikkerens.homes.use" ) ) {
            sender.sendMessage( "You do not have permission to teleport to your home." );
            return;
        }

        final Player player = (Player) sender;
        final Location home = this.plugin.getDB().getHome( player );

        if ( home != null ) {
            player.teleport( home );
            player.sendMessage( "Welcome home!" );
        } else
            player.sendMessage( "You do not have a home to teleport to!" );
    }

}
