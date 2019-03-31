package edu.illinois.jaa2.webbroadcast;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class WebBroadcast extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load the description file to get the plugin's name and version
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        //Load the description file to get the plugin's name and version
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been disabled!");
    }
}
