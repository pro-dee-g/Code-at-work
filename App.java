package com.dsa;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(longestValidParanthesis(")()())"));
        System.out.println(Arrays.toString(sortNumbersUptoCertainLimit(new int[]{0,1,0,2,3,2,1,0,1,2,0,0,0,5}, 5)));
        System.out.println(Arrays.toString(incrementByOne(new int[]{9,9,9})));

        System.out.println(longestCommonPrefix(new String[]{"fslower","flow","flight"},"flower", 0, 2));
    }

    private static String longestCommonPrefix(String [] arr, String largest, int start, int end) {
        if(start == end) {
            return arr[start];
        }
        int mid = (start + end) / 2;
        String left = longestCommonPrefix(arr, largest, start, mid);
        String right = longestCommonPrefix(arr, largest, mid + 1, end);
        return commonPrefix(left, right);
    }

    private static String commonPrefix(String left, String right) {
        String min = left.length() > right.length() ? right : left;
        String max = left.length() > right.length() ? left : right;
        while(min.length() > 0 && !max.startsWith(min))min = min.substring(0, min.length() - 1);
        return min;
    }

    private static int[] incrementByOne(int[] arr) {
        int lastIncrementedIndex = arr.length - 1;
        arr[lastIncrementedIndex] += 1;
        while(lastIncrementedIndex > 0 && arr[lastIncrementedIndex] > 9) {
            arr[lastIncrementedIndex] -= 10;
            arr[--lastIncrementedIndex] += 1;
        }
        if(arr[0] == 10) {
            arr[0] = 0;
            int [] newArr = new int[arr.length + 1];
            newArr[0] = 1;
            newArr[1] = 0;
            for(int i = 2; i < newArr.length; i++) {
                newArr[i] = arr[i - 1];
            }
            return newArr;
        }
        return arr;
    }


    private static int longestValidParanthesis(String str) {
        int maxLenght = 0;
        Stack<Character> stack = new Stack<>();
        if(str == null || str.length() == 0) {
            return 0;
        }
        stack.push(str.charAt(0));
        int localMax = 0;
        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i) == ')' && stack.isEmpty()) {
                maxLenght = Math.max(maxLenght, localMax);
                localMax = 0;
            } else
            if(str.charAt(i) == ')' && stack.peek() == '(') {
                stack.pop();
                localMax += 2;
            } else {
                stack.push(str.charAt(i));
            }
        }
        maxLenght = Math.max(maxLenght, localMax);
        return maxLenght;
    }

    private static int [] sortNumbersUptoCertainLimit(int [] arr, int limit) {
        int [] count = new int[limit + 1];
        for(int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int j = 0;
        for(int i = 0; i < limit + 1; i++) {
            while(count[i] > 0) {
                arr[j++] = i;
                count[i]--;
            }
        }
        return arr;
    }
}
