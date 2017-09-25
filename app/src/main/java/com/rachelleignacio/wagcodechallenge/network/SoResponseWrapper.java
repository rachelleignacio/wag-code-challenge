package com.rachelleignacio.wagcodechallenge.network;

import java.util.List;

/**
 * Created by rachelleignacio on 9/22/17.
 * Since stackoverflow returns the user list in a json array named items, this is a wrapper class
 * for easily parsing to a list of Users.
 */

public class SoResponseWrapper<T> {
    public List<T> items;
}
