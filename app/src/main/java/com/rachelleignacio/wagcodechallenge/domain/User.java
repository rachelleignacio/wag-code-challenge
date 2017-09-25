package com.rachelleignacio.wagcodechallenge.domain;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class User extends RealmObject {
    @SerializedName("user_id") //for gson parsing of SO response json
    @PrimaryKey
    public int id;

    @SerializedName("display_name")
    public String username;

    @SerializedName("profile_image")
    public String gravatarUrlString;

    @SerializedName("badge_counts")
    public BadgeList badges;

}
