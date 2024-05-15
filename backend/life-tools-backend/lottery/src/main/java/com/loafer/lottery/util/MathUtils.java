package com.loafer.lottery.util;

/**
 * 数学工具类
 * @author loafer
 * @since 2024-05-16 02:57:01
 **/
public class MathUtils {

    /**
     * 组合数计算
     * @param total
     * @param target
     * @return
     */
    public static long combinatorial(long total, long target) {
        long result = 1;
        for (long i = 1; i <= target; i++) {
            result *= (total - target + i);
            result /= i;
        }
        return result;
    }

    /**
     * 排列数计算
     * @param total
     * @param target
     * @return
     */
    public static long permutation(long total, long target) {
        long result = 1;
        for (long i = total; i >= total - target + 1; i--) {
            result *= i;
        }
        return result;
    }
}
