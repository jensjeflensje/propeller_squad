package dev.jensderuiter.propellersquad.util;

import dev.jensderuiter.propellersquad.PropellerSquad;
import dev.jensderuiter.propellersquad.model.PropellerCap;
import org.bukkit.entity.Player;

public class PropellerUtil {
    
    public static void addMember(Player player) {
        PropellerSquad.squadMembers.put(player, new PropellerCap(player));
    }

    public static void removeMember(Player player) {
        PropellerSquad.squadMembers.get(player).destroy();
        PropellerSquad.squadMembers.remove(player);
    }
    
}
