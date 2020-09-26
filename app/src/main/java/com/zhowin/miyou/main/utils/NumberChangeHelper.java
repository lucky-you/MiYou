package com.zhowin.miyou.main.utils;

public class NumberChangeHelper {

    public static String getNumberText(int number) {
        if (number <= 1000) {
            return String.valueOf(number);
        } else if (1000 < number && number <= 10000) {
            return String.format("%.2f", number / 1000) + "K";
        } else if (10000 < number && number <= 100000) {
            return String.format("%.2f", number / 10000) + "w";
        } else if (100000 < number && number <= 1000000) {
            return String.format("%.2f", number / 100000) + "W";
        }
        return String.valueOf(number);
    }
}
