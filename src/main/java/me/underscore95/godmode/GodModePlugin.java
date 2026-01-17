package me.underscore95.godmode;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class GodModePlugin extends JavaPlugin {

    private final GodModeManager godModeManager;

    public GodModePlugin(@Nonnull JavaPluginInit init) {
        super(init);

        godModeManager = new GodModeManager();
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new GodModeCommand(godModeManager));

        this.getEntityStoreRegistry().registerSystem(godModeManager);
    }
}