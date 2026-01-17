package me.underscore95.godmode;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class GodModeCommand extends AbstractPlayerCommand {

    private final GodModeManager godModeManager;

    public GodModeCommand(GodModeManager godModeManager) {
        super("godmode", "Toggle god mode");
        this.godModeManager = godModeManager;
        this.requirePermission("godmode.toggle");
        this.addAliases("god");
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = commandContext.senderAs(Player.class);
        godModeManager.toggleGodMode(player, ref, store);
    }
}
