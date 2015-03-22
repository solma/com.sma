package com.shuoma;
import java.util.*;
public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        //-1, -2147483648
        System.out.println(fractionToDecimal(15641, 49950));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "NaN";
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder();

        String sign = (numerator > 0 ^ denominator > 0) ? "-" : "";
        // conver to long first then take the abs
        long n = Math.abs((long)numerator), d = Math.abs((long)denominator);
        res.append(sign + n / d);
        if (n % d == 0) return res.toString();
        else res.append(".");
        Map<Long, Integer> fractionalPart = new HashMap<Long, Integer>();
        long r = n % d;
        while (r > 0) {
            if (fractionalPart.containsKey(r)) {
                res.insert(fractionalPart.get(r), "(");
                res.append(")");
                break;
            }
            fractionalPart.put(r, res.length());
            System.out.println(r + " " + fractionalPart.get(r));
            r *= 10;
            res.append(r / d);
            //System.out.println(r/d + " " + r + " " + d);
            r %= d;
        }
        return res.toString();
    }
}