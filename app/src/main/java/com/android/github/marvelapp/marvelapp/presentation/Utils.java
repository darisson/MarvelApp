package com.android.github.marvelapp.marvelapp.presentation;

import java.util.Random;

public class Utils {

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
