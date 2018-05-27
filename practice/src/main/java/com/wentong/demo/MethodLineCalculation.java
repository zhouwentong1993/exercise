package com.wentong.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by zhouwentong on 2018/5/27.
 * 检验一个类中有多少方法，方法的行数多少。
 */
public class MethodLineCalculation {

    private boolean startAnnotation = false;
    private boolean startMethod = false;
    private int lineNumber = 0;

    public static void main(String[] args) throws Exception{
        File file = new File("/Users/zhouwentong/Desktop/ConcurrencyTest.java");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

            }
        }
    }

    private boolean isAnnotationStart(String line) {
        String trimLine = line.trim();
        boolean start = trimLine.startsWith("/*") && !trimLine.endsWith("*/");

        return start;
    }

//    private boolean

    private boolean isSingleLineAnnotation(String line) {
        String trimLine = line.trim();
        return trimLine.startsWith("//");
    }

    /**
     * 看当前行是否是空行
     * 由于获取的是当前操作系统的换行符，所以仅限于同一操作系统的检测
     * @param line 待检测的行
     * @return 如果待检测字符串 equals 换行符，true，否则 false。
     */
    private boolean isBlankLine(String line) {
        return line.equals(System.getProperty("line.separator"));
    }

    private String firstMeaningfulWord(String line) {
        char[] chars = line.toCharArray();
        for (char aChar : chars) {
            if (aChar != ' ') {
                return String.valueOf(aChar);
            }
        }
        return "";
    }

    private boolean isAnnotationEnd(String line) {
        String trimLine = line.trim();
        return !trimLine.startsWith("/*") && trimLine.endsWith("*/");
    }

    private boolean isMethodStart(String line) {
        return false;
    }

    private boolean isMethodEnd(String line) {
        return false;
    }



}
