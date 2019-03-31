package edu.illinois.jaa2.webbroadcast;

import java.net.URL;

class Broadcast {
    private URL url;
    private int interval;

    /**
     * Creates a broadcast with a given URL and interval (in seconds)
     * @param url URL to broadcast to
     * @param interval seconds between each broadcast
     */
    Broadcast(URL url, int interval) {
        this.url = url;
        this.interval = interval;
    }


    /**
     * Get the URL to broadcast to
     * @return URL as a String
     */
    URL getUrl() {
        return url;
    }

    /**
     * Get the interval, in seconds, between broadcasts
     * @return interval in seconds
     */
    int getInterval() {
        return interval;
    }
}
