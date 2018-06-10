package com.wentong.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhouwentong on 2018/5/27.
 * 检验一个类中有多少方法，方法的行数多少。
 */
public class MethodLineCalculation {

    private static boolean startAnnotation = false;
    private static boolean startMethod = false;
    private static int lineNumber = 0;
    private static final Stack<String> stack = new Stack();
    private static final List<String> LEFT_BRACKETS = Arrays.asList("{");
    private static final List<String> RIGHT_BRACKETS = Arrays.asList("}");

    private static List<MethodCalculation> list = new ArrayList<>();
    private static MethodCalculation methodCalculation = null;

    private static String methodName;
    private static String modifier;


    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhouwentong/Desktop/ConcurrencyTest.java");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                dealAnnotation(line);
                dealMethod(line);
                if (startMethod) {
                    if (methodCalculation == null) {
                        methodCalculation = new MethodCalculation();
                        list.add(methodCalculation);
                    }
                    methodCalculation.setTotalLine(methodCalculation.getTotalLine() + 1);
                    methodCalculation.setMethodName(methodName);
                    methodCalculation.setModifier(modifier);
                } else {
                    methodCalculation = null;
                    methodName = null;
                    modifier = null;
                }
            }
        }
        System.out.println(list);
        int totalMethodLines = 0;
        int totalMethods = 0;
        for (MethodCalculation calculation : list) {
            totalMethodLines = totalMethodLines + calculation.getTotalLine();
            totalMethods++;
        }

        list.sort(Comparator.comparingInt(MethodCalculation::getTotalLine).reversed());
        System.out.println("平均每个方法行数：" + BigDecimal.valueOf(totalMethodLines).divide(BigDecimal.valueOf(totalMethods), 2));
        System.out.println("行数最多的方法是：" + list.get(0).getMethodName());
    }

    public static void dealAnnotation(String line) {
        annotationStart(line);
        annotationEnd(line);
    }

    private static void annotationStart(String line) {
        if (startMethod) {
            return;
        }
        String trimLine = line.trim();
        boolean start = trimLine.startsWith("/*") && !trimLine.endsWith("*/");
        if (start) {
            startAnnotation = true;
            startMethod = false;
        }

    }

    private static void dealMethod(String line) {
        if (startAnnotation || line.trim().startsWith("/*") || line.trim().endsWith("*/") || line.contains("/")) {
            return;
        }
        if (startMethod) {
            if (stack.empty()) {
                startMethod = false;
                return;
            }
            String[] split = line.split("");
            for (String s : split) {
                if (LEFT_BRACKETS.contains(s)) {
                    stack.push(s);
                } else if (RIGHT_BRACKETS.contains(s)) {
                    stack.pop();
                }
            }
        } else {
            String trimLine = line.trim();
            if (trimLine.endsWith("{")) {
                String trimLine2 = trimLine.substring(0, trimLine.length() - 1).trim();
                if (trimLine2.endsWith(")")) {
                    if (trimLine2.contains(",")) {
                        startMethod(trimLine);
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
                            startMethod(trimLine3);
                        }
                    }
                }

            }
        }
    }

    private static void startMethod(String trimLine) {
        if (trimLine.contains("int offset, int fromIndex, int toIndex")) {
            return;
        }
        startMethod = true;
        startAnnotation = false;
        stack.push("{");
        String[] split = trimLine.split(" ");
        modifier = split[0];
        System.out.println(trimLine);
        if (split.length == 2) {
            methodName = split[1];
        } else {
            String substring = trimLine.substring(0, trimLine.indexOf("("));
            String[] split1 = substring.split(" ");
            if (split1.length == 1) {
                methodName = split1[0];
                modifier = "friendly";
            } else {
                modifier = split1[0];
                methodName = split1[2];
            }
        }
    }

    private boolean isSingleLineAnnotation(String line) {
        String trimLine = line.trim();
        return trimLine.startsWith("//");
    }

    /**
     * 看当前行是否是空行
     * 由于获取的是当前操作系统的换行符，所以仅限于同一操作系统的检测
     *
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

    private static void annotationEnd(String line) {
        if (startMethod || !startAnnotation) {
            return;
        }
        String trimLine = line.trim();
        boolean annotationEnd = !trimLine.startsWith("/*") && trimLine.endsWith("*/");
        if (annotationEnd) {
            startAnnotation = false;
            startMethod = false;
        }
    }

    private boolean isMethodStart(String line) {
        return false;
    }

    private boolean isMethodEnd(String line) {
        return false;
    }


}
