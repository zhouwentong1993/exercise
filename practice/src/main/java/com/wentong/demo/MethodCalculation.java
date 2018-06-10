package com.wentong.demo;

/**
 * Created by zhouwentong on 2018/6/6.
 */
public class MethodCalculation {
    private int startLine;
    private int endLine;
    private String methodName;
    private int totalLine;
    private String modifier;


    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(int totalLine) {
        this.totalLine = totalLine;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "MethodCalculation{" +
                "startLine=" + startLine +
                ", endLine=" + endLine +
                ", methodName='" + methodName + '\'' +
                ", totalLine=" + totalLine +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
