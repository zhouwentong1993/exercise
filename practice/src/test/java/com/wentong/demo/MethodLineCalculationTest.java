package com.wentong.demo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhouwentong on 2018/5/27.
 */
public class MethodLineCalculationTest {

    @Test
    public void testTrim() {
        String s = "      s  s23 ";
        System.out.println(s);
        String trim = s.trim();
        System.out.println(trim);
        Assert.assertEquals(trim.length(), 6);
    }

}