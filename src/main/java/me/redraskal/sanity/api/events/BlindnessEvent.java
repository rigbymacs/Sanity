package me.redraskal.sanity.api.events;

import me.redraskal.sanity.Main;
import me.redraskal.sanity.api.SanityEvent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Redraskal_2 on 11/7/2016.
 */
public class BlindnessEvent extends SanityEvent {

    @Override
    public int foodLevel() {
        return 2;
    }

    @Override
    public void execute(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1), true);
        new BukkitRunnable() {
            public void run() {
                if(player.isDead() || player.isOnline()) {
                    this.cancel();
                } else {
                    if(player.getFoodLevel() > foodLevel()) {
                        player.removePotionEffect(PotionEffectType.BLINDNESS);
                        this.cancel();
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20L);
    }
}
