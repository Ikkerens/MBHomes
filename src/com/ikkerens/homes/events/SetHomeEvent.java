package com.ikkerens.homes.events;

import com.mbserver.api.events.CancellableEvent;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class SetHomeEvent extends CancellableEvent {
    private final Player   player;
    private final Location location;

    public SetHomeEvent( final Player player, final Location location ) {
        this.player = player;
        this.location = location;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Location getLocation() {
        return this.location;
    }
}
