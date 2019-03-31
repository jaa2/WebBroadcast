package edu.illinois.jaa2.webbroadcast;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class WebBroadcast extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load the description file to get the plugin's name and version
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled!");

        // Save default config if no config file exists
        saveResource("config.yml", false);

        // Load config and set up broadcasts
        FileConfiguration configuration = getConfig();
        ConfigLoader configLoader = new ConfigLoader(configuration);
        List<Broadcast> broadcasts = configLoader.getBroadcasts();
        setUpBroadcasts(broadcasts);
    }

    @Override
    public void onDisable() {
        //Load the description file to get the plugin's name and version
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been disabled!");
    }

    /**
     * Starts running broadcasts
     * @param broadcasts list of broadcasts to run
     */
    private void setUpBroadcasts(List<Broadcast> broadcasts) {
        for (Broadcast broadcast : broadcasts) {
            BroadcastSender sender = new BroadcastSender(broadcast);
            sender.runTaskTimerAsynchronously(this, 1L, broadcast.getInterval() * 20);
        }
    }
}
