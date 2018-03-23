package viralgaming.anonym110.treefarm;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Treefarm extends JavaPlugin {
    private String tag;

    public Treefarm() {
        this.tag = ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "Treefarm" + ChatColor.DARK_GREEN + "]";
    }

    public void onEnable() {
        this.loadConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public void onDisable() {
    }

    private void loadConfig() {
        this.getConfig().addDefault("Config.coords.xMax", 0);
        this.getConfig().addDefault("Config.coords.xMin", 0);
        this.getConfig().addDefault("Config.coords.yMax", 0);
        this.getConfig().addDefault("Config.coords.yMin", 0);
        this.getConfig().addDefault("Config.coords.zMax", 0);
        this.getConfig().addDefault("Config.coords.zMin", 0);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("treefarm")) {
            if(args.length == 0) {
                this.info(p);
                return true;
            }

            if(args[0].equalsIgnoreCase("reload") && (sender.hasPermission("treefarm.reload") || sender.isOp())) {
                this.reloadConfig();
                sender.sendMessage(this.tag + ChatColor.GRAY + " Config reloaded!");
                return true;
            }
        }

        return false;
    }

    public void info(Player p) {
        p.sendMessage(" ");
        p.sendMessage(this.tag + ChatColor.GRAY + " Treefarm 1.2 by anonym110");
        p.sendMessage(this.tag + ChatColor.GRAY + " Developed for prison.viral-gaming.org");
        p.sendMessage(" ");
    }
}
