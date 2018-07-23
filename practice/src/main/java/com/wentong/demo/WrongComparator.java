package com.wentong.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WrongComparator {
    public static void main(String[] args) {

        // 在视频中用的是 1，2,3,4,5,因为 jdk7 将 -127 - 128 的数字缓存了，所以参与计算的是 int 类型
        String[] s = {"0","10000","20000","30000","40000"};
        List<Integer> list = new ArrayList<>();
        for (String s1 : s) {
            list.add(Integer.valueOf(s1));
        }
        int i = Collections.binarySearch(list, 10000, new IntegerComparator());
        System.out.println(i);
    }

    static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer i, Integer j) {
            return i < j ? -1 : (i == j ? 0 : 1);
        }
    }
}

