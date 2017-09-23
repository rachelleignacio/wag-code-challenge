package com.rachelleignacio.wagcodechallenge.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class User {
    @SerializedName("user_id")
    public int id;

    @SerializedName("display_name")
    public String username;

    @SerializedName("profile_image")
    public String gravatarUrlString;

    @SerializedName("badge_counts")
    public BadgeList badges;

}
