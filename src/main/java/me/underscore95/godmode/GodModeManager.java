package me.underscore95.godmode;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageEventSystem;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageModule;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GodModeManager extends DamageEventSystem {
    private final Set<UUID> godModePlayers = ConcurrentHashMap.newKeySet();

    public void toggleGodMode(Player player, Ref<EntityStore> ref, Store<EntityStore> store) {
        UUIDComponent uuidComp = store.getComponent(ref, UUIDComponent.getComponentType());
        assert uuidComp != null;
        UUID uuid = uuidComp.getUuid();
        if (godModePlayers.remove(uuid)) {
            player.sendMessage(Message.raw("God mode disabled").color(Color.RED));
        } else {
            godModePlayers.add(uuid);
            player.sendMessage(Message.raw("God mode enabled").color(Color.GREEN));
        }
    }

    @Override
    public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull Damage damage) {
        Ref<EntityStore> targetRef = archetypeChunk.getReferenceTo(i);
        UUIDComponent component = store.getComponent(targetRef, UUIDComponent.getComponentType());
        assert component != null;
        if (!godModePlayers.contains(component.getUuid())) return;
        damage.setAmount(0);
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(UUIDComponent.getComponentType());
    }

    @Override
    @Nullable
    public SystemGroup<EntityStore> getGroup() {
        return DamageModule.get().getFilterDamageGroup();
    }

}
