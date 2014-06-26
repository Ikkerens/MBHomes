package com.ikkerens.homes;

import java.util.HashMap;
import java.util.Map;

import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class Database {
    private final Map< String, Location > homes;

    public Database() {
        this.homes = new HashMap< String, Location >();
    }

    public void setHome( final Player player, final Location location ) {
        this.homes.put( player.getLoginName().toLowerCase(), location );
    }

    public Location getHome( final Player player ) {
        return this.homes.get( player.getLoginName().toLowerCase() );
    }
}
