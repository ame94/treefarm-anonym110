package viralgaming.anonym110.treefarm;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    private final Treefarm plugin;

    public PlayerListener(Treefarm plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void treefarmGriefing(BlockPlaceEvent event) {
        if (
                event.getBlock().getX() > this.plugin.getConfig().getInt("Config.coords.xMin") &&
                event.getBlock().getX() < this.plugin.getConfig().getInt("Config.coords.xMax") &&
                event.getBlock().getY() > this.plugin.getConfig().getInt("Config.coords.yMin") &&
                event.getBlock().getY() < this.plugin.getConfig().getInt("Config.coords.yMax") &&
                event.getBlock().getZ() > this.plugin.getConfig().getInt("Config.coords.zMin") &&
                event.getBlock().getZ() < this.plugin.getConfig().getInt("Config.coords.zMax") &&
                !event.getPlayer().isOp() &&
                event.getPlayer().getGameMode() != GameMode.CREATIVE &&
                !event.isCancelled() &&
                !event.getPlayer().hasPermission("treefarm.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void choppingTree(BlockBreakEvent event) {
        if (
            event.getBlock().getX() > this.plugin.getConfig().getInt("Config.coords.xMin") &&
            event.getBlock().getX() < this.plugin.getConfig().getInt("Config.coords.xMax") &&
            event.getBlock().getY() > this.plugin.getConfig().getInt("Config.coords.yMin") &&
            event.getBlock().getY() < this.plugin.getConfig().getInt("Config.coords.yMax") &&
            event.getBlock().getZ() > this.plugin.getConfig().getInt("Config.coords.zMin") &&
            event.getBlock().getZ() < this.plugin.getConfig().getInt("Config.coords.zMax")) {
                Location loc = event.getBlock().getLocation();
                loc.setY(loc.getY() - 1.0D);

            if (loc.getBlock().getType() == Material.DIRT) {
                if (
                    event.getBlock().getType() == Material.LOG &&
                    !event.isCancelled() &&
                    event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                        event.setCancelled(true);
                        event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation()).setTypeIdAndData(6, event.getBlock().getData(), false);
                        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.LOG, 1));
                        ItemStack newLog = new ItemStack(Material.LOG);

                } else if (
                    event.getBlock().getType() == Material.SAPLING &&
                    !event.isCancelled() &&
                    event.getPlayer().getGameMode() != GameMode.CREATIVE &&
                    !event.getPlayer().hasPermission("treefarm.destroy")) {
                        event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeafDecay(LeavesDecayEvent event) {
        if (
            event.getBlock().getX() > this.plugin.getConfig().getInt("Config.coords.xMin") + 2 &&
            event.getBlock().getX() < this.plugin.getConfig().getInt("Config.coords.xMax") + 2 &&
            event.getBlock().getY() > this.plugin.getConfig().getInt("Config.coords.yMin") + 2 &&
            event.getBlock().getY() < this.plugin.getConfig().getInt("Config.coords.yMax") + 2 &&
            event.getBlock().getZ() > this.plugin.getConfig().getInt("Config.coords.zMin") + 2 &&
            event.getBlock().getZ() < this.plugin.getConfig().getInt("Config.coords.zMax") + 2) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
        }
    }
}
