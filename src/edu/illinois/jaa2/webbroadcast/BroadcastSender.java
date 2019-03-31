package edu.illinois.jaa2.webbroadcast;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class BroadcastSender extends BukkitRunnable {
    private Broadcast broadcast;

    BroadcastSender(Broadcast broadcast) {
        this.broadcast = broadcast;
    }

    @Override
    public void run() {
        URL url = broadcast.getUrl();
        try {
            url = makeUrlReplacements(url);
        } catch (MalformedURLException e) {
            Bukkit.getLogger().warning("URL is malformed after replacements: " + url);
        }

        try {
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());
            is.close();
            Bukkit.getLogger().info("Broadcast sent to " + url);
        } catch (IOException e) {
            Bukkit.getLogger().warning("IOException while making request to " + broadcast.getUrl().toString()
                + ": " + e.getLocalizedMessage());
        }
    }


    /**
     * Makes replacements to the URL to insert special features about the server
     * @param url whose contents should be replaced
     * @return url with replacements made
     * @throws MalformedURLException if the resulting URL is malformed
     */
    private URL makeUrlReplacements(URL url) throws MalformedURLException {
        String urlStr = url.toExternalForm();
        urlStr = urlStr.replace("{NUM_PLAYERS}",
                Integer.toString(Bukkit.getServer().getOnlinePlayers().size()));
        urlStr = urlStr.replace("{SERVER_NAME}", Bukkit.getServer().getName());
        return new URL(urlStr);
    }
}
