package com.rachelleignacio.wagcodechallenge.domain;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class User {
    private int id;
    private String username;
    private String gravatarUrlString;
    private int goldBadgeCount;
    private int silverBadgeCount;
    private int bronzeBadgeCount;

    public User(int id,
                String username,
                String gravatarUrl,
                int goldBadgeCount,
                int silverBadgeCount,
                int bronzeBadgeCount) {
        this.id = id;
        this.username = username;
        this.gravatarUrlString = gravatarUrl;
        this.goldBadgeCount = goldBadgeCount;
        this.silverBadgeCount = silverBadgeCount;
        this.bronzeBadgeCount = bronzeBadgeCount;
    }

    public String getUsername() {
        return username;
    }

    public String getGravatarUrlString() {
        return gravatarUrlString;
    }

    public int getGoldBadgeCount() {
        return goldBadgeCount;
    }

    public int getSilverBadgeCount() {
        return silverBadgeCount;
    }

    public int getBronzeBadgeCount() {
        return bronzeBadgeCount;
    }
}
