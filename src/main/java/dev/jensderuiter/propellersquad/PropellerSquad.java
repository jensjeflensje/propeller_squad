package dev.jensderuiter.propellersquad;

import dev.jensderuiter.propellersquad.command.RemoveCapCommand;
import dev.jensderuiter.propellersquad.command.SummonCapCommand;
import dev.jensderuiter.propellersquad.model.PropellerCap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PropellerSquad extends JavaPlugin {

    public static HashMap<Player, PropellerCap> squadMembers = new HashMap<>();
    public static PropellerSquad plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("summoncap").setExecutor(new SummonCapCommand());
        this.getCommand("removecap").setExecutor(new RemoveCapCommand());
    }

    @Override
    public void onDisable() {
    }
}
