/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package com.ape.test;

/**
 * @auther qiys@hzzh.com
 * @date 2018-03-09
 */
public class Test0309 {

    public class Solution {

        int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int[] dp = new int[prices.length];

        public int solve(int[] prices, int n) {
            if (n == 0)
                return 0;
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                max = Math.max(max, prices[i] + solve(prices, n - i));
            }
            return max;
        }

        public int solveWithMemoUpToBottom(int[] prices, int n) {
            if (n == 0 || dp[n] > 0)
                return dp[n];
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                max = Math.max(max, prices[i] + solve(prices, n - i));
            }
            dp[n] = max;
            return max;
        }

        public int solveBottomToUp(int[] prices, int n) {
            int[] dp = new int[prices.length];
            for (int i = 1; i <= n; i++) {
                int max = Integer.MIN_VALUE;
                for (int j = 1; j <= i; j++) {
                    max = Math.max(max, prices[j] + prices[i - j]);
                }
                dp[i] = max;
            }
            return dp[n];
        }

    }

    public static void main(String args[]) {
        Solution s = new Test0309().new Solution();
        //      System.out.println(s.solve(s.prices, 1));
        //      System.out.println(s.solve(s.prices, 2));
        //      System.out.println(s.solve(s.prices, 3));
        //      System.out.println(s.solve(s.prices, 4));
        //      System.out.println(s.solve(s.prices, 5));
//        System.out.println(s.solveBottomToUp(s.prices, 1));
//        System.out.println(s.solveBottomToUp(s.prices, 2));
//        System.out.println(s.solveBottomToUp(s.prices, 3));
//        System.out.println(s.solveBottomToUp(s.prices, 4));
//        System.out.println(s.solveBottomToUp(s.prices, 5));

        StringBuilder sb = new StringBuilder("sadsadsadqewqdwq!=");
        System.out.println(sb);
        System.out.println(sb.length());
        System.out.println(sb.delete(sb.length()-1,sb.length()));
    }
}
