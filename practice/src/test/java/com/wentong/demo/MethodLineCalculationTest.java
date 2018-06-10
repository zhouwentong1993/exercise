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

    @Test
    public void testSubstring() {
        String s = "() {";
        System.out.println(s.substring(0, s.length() -1));
        String trimLine2 = "public void test (Object o)";
        String trimLine3 = trimLine2.substring(trimLine2.indexOf('(') + 1, trimLine2.length() - 1);
        System.out.println(trimLine3);
    }

    @Test
    public void testMethodStart() {
        String line1 = "    public boolean addAll(Collection<? extends E> c) {";
        String line2 = "        int numNew = a.length;";
        String line3 = "System.arraycopy(a, 0, elementData, size, numNew);";
        String line4 = "if (numMoved > 0) {";

        System.out.println(methodStart(line1));
        System.out.println(methodStart(line2));
        System.out.println(methodStart(line3));
        System.out.println(methodStart(line4));
        String trimLine = " public ArrayList() {";
        String trimLine2 = trimLine.substring(trimLine.length() - 1).trim();
        System.out.println(trimLine2);
    }

    @Test
    public void testString() {
        String s = "    public static void main(){}";
        String[] split = s.split("");
        for (String s1 : split) {
            System.out.println(s1);
        }
        System.out.println(split);
    }

    private boolean methodStart(String line) {
        String trimLine = line.trim();
        if (trimLine.endsWith("{")) {
            String trimLine2 = trimLine.substring(0, trimLine.length() - 1).trim();
            if (trimLine2.endsWith(")")) {
                if (trimLine2.contains(",")) {
                    return true;
                } else {
                    String trimLine3 = trimLine2.substring(trimLine2.indexOf('(') + 1, trimLine2.length() - 1).trim();
                    if (trimLine3.contains("<") && trimLine3.contains(">")) {
                        String substring = trimLine3.substring(trimLine3.indexOf('<') + 1, trimLine3.indexOf('>'));
                        if (!(substring.contains("&") || substring.contains("|"))) {
                            trimLine3 = trimLine3.replace("<", "").replace(">", "").replace(substring, "");
                        }
                    }
                    String[] split = trimLine3.split(" ");
                    if (split.length == 2) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Test
    public void testString1() {
        String s = "private void grow(int minCapacity) {";
        String[] split = s.split(" ");
        System.out.println(split[0]);
        System.out.println(split[2].substring(0,split[2].lastIndexOf("(")));
        String s1 = "SubList(AbstractList<E> parent,";
        String substring = s1.substring(0, s1.indexOf("("));
        System.out.println(substring);
    }

}