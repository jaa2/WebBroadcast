package edu.illinois.jaa2.webbroadcast;

import org.bukkit.configuration.ConfigurationSection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ConfigLoader {

    private ConfigurationSection config;

    /**
     * Create a ConfigLoader using a given loaded config file
     * @param config loaded configuration file
     */
    ConfigLoader(ConfigurationSection config) {
        this.config = config;
    }

    /**
     * Finds all broadcasts from the config file
     * @return list of broadcasts
     */
    List<Broadcast> getBroadcasts() {
        List<Map<?, ?>> configBroadcasts = config.getMapList("broadcasts");

        List<Broadcast> broadcasts = new ArrayList<>();
        for (Map<?, ?> map : configBroadcasts) {
            if (map.containsKey("url") && map.containsKey("interval")) {
                Object urlValue = map.get("url");
                Object intervalValue = map.get("interval");

                if (urlValue instanceof String && intervalValue instanceof Integer) {
                    String urlStr = (String) urlValue;
                    Integer interval = (Integer) intervalValue;
                    try {
                        broadcasts.add(new Broadcast(new URL(urlStr), interval));
                    } catch (MalformedURLException e) {
                        WebBroadcast.logger.warning("Malformed URL in config: " + urlStr);
                    }
                }
            }
        }

        WebBroadcast.logger.info(broadcasts.size() + " broadcast(s) loaded.");

        return broadcasts;
    }
}
