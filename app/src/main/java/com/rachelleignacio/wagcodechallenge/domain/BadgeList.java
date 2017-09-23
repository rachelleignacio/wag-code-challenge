package com.rachelleignacio.wagcodechallenge.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class BadgeList {
    @SerializedName("gold")
    public int goldBadgeCount;

    @SerializedName("silver")
    public int silverBadgeCount;

    @SerializedName("bronze")
    public int bronzeBadgeCount;
}
