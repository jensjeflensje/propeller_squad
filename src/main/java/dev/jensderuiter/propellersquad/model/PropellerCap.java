package dev.jensderuiter.propellersquad.model;

import dev.jensderuiter.propellersquad.PropellerSquad;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class PropellerCap {

    private double propellerAngle = 0;
    private BukkitTask task;

    public PropellerCap(Player player) {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                List<CapParticle> particles = new ArrayList<>();
                Location baseLocation = player.getLocation().clone();
                baseLocation.setY(baseLocation.getY() + 1.93);


                // propeller stand
                for (double i = 0; i < 0.4; i = i + 0.1) {
                    Location loc = baseLocation.clone();
                    loc.setY(loc.getY() + i);
                    particles.add(new CapParticle(
                            new Particle.DustOptions(Color.fromRGB(255, 50, 50), 0.5F),
                            loc
                    ));
                }


                // propeller
                Location basePropellerLocation = baseLocation.clone();
                basePropellerLocation.setY(basePropellerLocation.getY() + 0.4);
                propellerAngle = propellerAngle + Math.PI / 10;
                if (propellerAngle == Math.PI) propellerAngle = 0;
                double distanceSin = Math.sin(propellerAngle) / 2;
                double distanceCos = Math.cos(propellerAngle) / 2;

                for (double i = 0; i < 1; i = i + 0.05) {
                    // first half
                    Location loc = basePropellerLocation.clone();
                    loc.setX(loc.getX() + (distanceSin * -i));
                    loc.setZ(loc.getZ() + (distanceCos * -i));
                    particles.add(new CapParticle(
                            new Particle.DustOptions(Color.fromRGB(255, 20, 20), 0.3F),
                            loc
                    ));

                    // second half
                    loc = basePropellerLocation.clone();
                    loc.setX(loc.getX() + (distanceSin * i));
                    loc.setZ(loc.getZ() + (distanceCos * i));
                    particles.add(new CapParticle(
                            new Particle.DustOptions(Color.fromRGB(255, 20, 20), 0.3F),
                            loc
                    ));
                }

                // cap top
                for (int color = 0; color < 6; color++) {
                    Color colorObj;
                    switch (color) {
                        case 0:
                            colorObj = Color.fromRGB(0, 255, 0);
                            break;
                        case 1:
                            colorObj = Color.fromRGB(255, 255, 0);
                            break;
                        case 2:
                            colorObj = Color.fromRGB(0, 0, 255);
                            break;
                        case 3:
                            colorObj = Color.fromRGB(255, 165, 0);
                            break;
                        case 4:
                            colorObj = Color.fromRGB(255, 0, 0);
                            break;
                        case 5:
                            colorObj = Color.fromRGB(128, 0, 128);
                            break;
                        default:
                            colorObj = Color.fromRGB(0, 0, 0);
                            break;
                    }
                    double angle = Math.PI / 6 * color * 2;
                    distanceSin = Math.sin(angle);
                    distanceCos = Math.cos(angle);
                    for (double position = -0.4; position < 0.4; position = position + 0.1) {
                        Location loc = baseLocation.clone();
                        loc.setX(loc.getX() - (distanceSin * (position)));
                        loc.setZ(loc.getZ() - (distanceCos * (position)));

                        particles.add(new CapParticle(
                                new Particle.DustOptions(colorObj, (float) ((float) position * 2 + 0.4)),
                                loc
                        ));
                    }
                }


                // loop and spawn all the particles
                for (CapParticle particle : particles) {
                    player.spawnParticle(Particle.REDSTONE,
                            particle.getLocation(),
                            1,
                            particle.getOptions());
                }


            }
        }.runTaskTimer(PropellerSquad.plugin, 0, 2);
    }

    public void destroy() {
        task.cancel();
    }

}
