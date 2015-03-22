package com.shuoma;
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int num = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            num += (arr[i] - 'A' + 1) * Math.pow(26, arr.length - 1 - i);
        }
        return num;
    }
}
