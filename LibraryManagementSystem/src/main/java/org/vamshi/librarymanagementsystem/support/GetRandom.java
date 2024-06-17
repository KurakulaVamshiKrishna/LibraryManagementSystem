package org.vamshi.librarymanagementsystem.support;

import java.util.Random;

public class GetRandom {
    int min = 100000001;
    int max = 999999999;
    Random obj = new Random();
    public long getRandom(){
        return obj.nextInt(max - min +1 )+min;
    }

}
