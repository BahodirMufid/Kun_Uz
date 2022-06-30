package com.company.util;
// PROJECT NAME Kun_Uz
// TIME 17:57
// MONTH 06
// DAY 22

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    public static String getRandomSmsCode(){
        int n = random.nextInt(89999)+10000; //10000 - 99999

        return String.valueOf(n);
    }

}
