package com.ikkerens.homes;

import com.ikkerens.homes.commands.SetHomeCommand;
import com.ikkerens.homes.commands.TeleportCommand;

import com.mbserver.api.ConfigurationManager;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.PluginManager;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.RunMode;
import com.mbserver.api.events.WorldSaveEvent;

@Manifest( name = "MBHomes" )
public class HomesPlugin extends MBServerPlugin implements Listener {
    private Database database;

    @Override
    public void onEnable() {
        final ConfigurationManager config = this.getServer().getConfigurationManager();
        this.database = config.load( this, Database.class );
        config.save( this, this.database );

        final PluginManager pm = this.getPluginManager();
        pm.registerEventHandler( this );
        pm.registerCommand( "home", new TeleportCommand( this ) );
        pm.registerCommand( "sethome", new SetHomeCommand( this ) );
    }

    @EventHandler( concurrency = RunMode.THREADED )
    public void saveDatabase( final WorldSaveEvent event ) {
        if ( ( event == null ) || ( event.getWorld() == this.getServer().getMainWorld() ) )
            this.getServer().getConfigurationManager().save( this, this.database );
    }

    @Override
    public void onDisable() {
        this.saveDatabase( null );
    }

    public Database getDB() {
        return this.database;
    }
}
