package com.rachelleignacio.wagcodechallenge.network;

import java.util.List;

/**
 * Created by rachelleignacio on 9/22/17.
 * stackoverflow returns the user list in a json array named items
 */

public class SoResponseWrapper<T> {
    public List<T> items;
}
