package dev.jensderuiter.propellersquad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Particle;

@AllArgsConstructor
@Getter
public class CapParticle {

    private Particle.DustOptions options;
    private Location location;

}
