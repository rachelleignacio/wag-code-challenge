package com.rachelleignacio.wagcodechallenge.domain;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class BadgeList extends RealmObject {
    @SerializedName("gold")
    public int goldBadgeCount;

    @SerializedName("silver")
    public int silverBadgeCount;

    @SerializedName("bronze")
    public int bronzeBadgeCount;
}
