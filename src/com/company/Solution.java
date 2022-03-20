package com.company;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.TimeoutException;

public abstract class Solution {

    public static int addDigits(int num) {

        int temp = num;
        num = 0;
        while (temp > 0) {
            num += temp % 10;
            temp = temp / 10;

        }

        return num < 10 ? num : addDigits(num);
    }

    public static int addDigits2(int num) {

        if (num == 0) return 0;

        return num % 9 == 0 ? 9 : num % 9;
    }

    public static int[] getConcatenation(int[] nums) {
        int[] a = new int[nums.length * 2];
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i + nums.length] = nums[i];
        }
        return a;
    }

    public static int finalValueAfterOperations(String[] operations) {
        int x = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].charAt(1) == '+')
                x++;
            else x--;
        }
        return x;
    }

    public static String removeKDigits(String num, int k) {
        int i = 1;
        StringBuilder resl = new StringBuilder();
        while (k > 0) {
            if ((int) num.charAt(i) >= (int) num.charAt(i - 1)) {
                resl.append(num.charAt(i - 1));
            } else k--;

        }
        return resl.toString();
    }


    public static double myPow(double x, int n) {
        if (n == 0 || x == 1) return 1;
        if (n <= -2147483648) return 0.0;
        return n > 0 ? pow(x, n) : (1.0 / (pow(x, -n)));
    }

    private static double pow(double x, int n) {
        double p = 1;
        for (int i = 0; i < n; i++) {
            p *= x;
        }
        return p;
    }

    //An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
    //Given an integer n, return true if n is an ugly number.
    public static ArrayList<Integer> primes_sieveOfEratosthenes(int n) {
        // this method returns the prime numbers which they are <= n

        int[] A = new int[n];
        int j;
        // filling the array
        for (int p = 2; p <= n; p++) {
            A[p - 2] = p;
        }
        // eliminates the prime numbers by assign zero in it is position
        for (int p = 2; p <= Math.sqrt(n); p++) {
            if (A[p] != 0) { // if statement so no need to delete multiples of numbers they are a multiples so its multiples have been deleted , ex : number 4
                j = p * p;
                while (j <= n) {  // the first multiple of a number we should consider deleting is number^2 , because smaller numbers have been deleted , and surly the number^2 must be smaller than n
                    A[j - 2] = 0; // j-2 not j due to my arrangement of the array
                    j += p;  // to eliminate multiples bigger than number^2
                }
            }
        }
        // fill the needed values (prime numbers) in the array list
        ArrayList<Integer> L = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (A[p] != 0) {
                L.add(A[p]);
            }
        }

        return L;
    }

    public static int reverseInt(int x) {
        if (x < -2147483647) return 0;
        boolean signed = (x < 0);
        x = Math.abs(x);
        int i = intSize(x);
        int y = 0;
        while (x != 0) {

            y += ((x % 10) * Math.pow(10, i));

            x /= 10;
            i--;
        }

        if (signed) y = -y;

        if (y <= (Math.pow(-2, 31) + 1) || y >= (Math.pow(2, 31) - 1)) {
            return 0;

        }

        return y;
    }

    public static int intSize(int x) {
        int i = 0;
        while (x >= 10) {
            x /= 10;
            i++;
        }
        return i;
    }

    public static int reverse2(int[] a) {


        Stack stack = new Stack();
        return 0;
    }

    // Problem : Given an array nums of size n, return the majority element.
    //The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);

        int i = 1;
        int recurrence = 1;
        int[] numberMaxRec = new int[]{0, 0};
        while (i < nums.length) {
            if (nums[i - 1] != nums[i]) {
                changeMax(nums, i, recurrence, numberMaxRec);
                recurrence = 1;

            } else {
                recurrence++;
            }
            i++;
        }
        changeMax(nums, i, recurrence, numberMaxRec);
        return numberMaxRec[0];
    }

    private static void changeMax(int[] nums, int i, int recurrence, int[] numberMaxRec) {
        if (recurrence > numberMaxRec[1]) {
            numberMaxRec[1] = recurrence;
            numberMaxRec[0] = nums[i - 1];
        }
    }

    // merge two sorted linked lists
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head;
        ListNode end = new ListNode();

        head = end;


        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                end.next = l1;
                end = end.next;
                l1 = l1.next;
            } else {
                end.next = l2;
                end = end.next;
                l2 = l2.next;
            }
        }

        if (l1 != null)
            end.next = l1;
        else
            end.next = l2;

        return head.next;
    }

    // 171 Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.
    public static int titleToNumber(String columnTitle) {
        int excelHeader = 0;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            excelHeader += charToNumber(columnTitle.charAt(i)) * Math.pow(26, columnTitle.length() - 1 - i);
        }
        return excelHeader;
    }

    public static int charToNumber(char c) {
        return (int) c - 64;
    }

    //Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
    //'.' Matches any single character.
    //'*' Matches zero or more of the preceding element.
    //The matching should cover the entire input string (not partial).

    public static boolean isMatch(String s, String p) {
        char temp;
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if ((s.charAt(i) != p.charAt(j)) && (p.charAt(j) != '.') && (p.charAt(j) != '*')) return false;

            else if (p.charAt(j) == '*') {
                temp = p.charAt(j - 1);

                while (i < s.length() && (s.charAt(i) == temp || temp == '.')) i++;
                j++;
                if (!(j < p.length()) && (i < s.length())) return false;

            }
            // if its '.'
            else {
                j++;
                i++;
            }


        }
        if ((j < p.length()) || (i < s.length())) return false;
        return true;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0)
            nums1 = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        else if (nums2.length == 0)
            nums2 = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        double min = Math.min(nums1[0], nums2[0]);
        double max = Math.max(nums1[nums1.length - 1], nums2[nums2.length - 1]);
        int median = (int) ((min + max) / 2);
        int[] arrayForMedian = new int[2];
        int actualMedianFromNums1 = binarySearch(nums1, median);

        if (nums1[actualMedianFromNums1] == median) return actualMedianFromNums1;

        else if (nums1[actualMedianFromNums1] > median) {
            arrayForMedian[1] = nums1[actualMedianFromNums1];
            if (actualMedianFromNums1 - 1 > -1)
                arrayForMedian[0] = nums1[actualMedianFromNums1 - 1];
            else
                arrayForMedian[0] = Integer.MIN_VALUE;
        } else {
            if (actualMedianFromNums1 - 1 > -1)
                arrayForMedian[1] = nums1[actualMedianFromNums1 - 1];
            else
                arrayForMedian[1] = Integer.MAX_VALUE;
            arrayForMedian[0] = nums1[actualMedianFromNums1];
        }

        int actualMedianFromNums2 = binarySearch(nums2, median);
        if (nums2[actualMedianFromNums2] == median) return actualMedianFromNums2;

        else if (nums1[actualMedianFromNums2] > median) {

            arrayForMedian[1] = Math.min(arrayForMedian[1], nums2[actualMedianFromNums2]);

            if (actualMedianFromNums2 - 1 > -1)
                arrayForMedian[0] = Math.max(arrayForMedian[0], nums2[actualMedianFromNums2 - 1]);
            else
                arrayForMedian[0] = Integer.MIN_VALUE;
        } else {
            if (actualMedianFromNums1 - 1 > -1)
                arrayForMedian[1] = nums1[actualMedianFromNums1 - 1];
            else
                arrayForMedian[1] = Integer.MAX_VALUE;
            arrayForMedian[0] = nums1[actualMedianFromNums1];
        }

        if (actualMedianFromNums2 == median) return actualMedianFromNums2;

        return 0;
    }

    public static int binarySearch(int[] a, int h) {
        return binarySearchIfNotExistReturnMinusOne(a, h, 0, a.length - 1);
    }

    public static int binarySearchIfNotExistReturnMinusOne(int[] a, int h, int start, int end) {
        int mid = (end + start) / 2;
        if (a[mid] == h) return mid;
        if (end - start == 1 && a[end] != h) return -1;
        if (a[mid] > h) end = mid - 1;
        else start = mid + 1;

        return binarySearchIfNotExistReturnMinusOne(a, h, start, end);
    }

    public static int binarySearch(int[] a, int h, int start, int end) {
        int mid = (end + start) / 2;
        if (a[mid] == h) return mid;
        if ((end - start == 1)) {
            return a[end] <= h ? end : start;
        }
        if (a[mid] > h) end = mid;
        else start = mid;

        return binarySearch(a, h, start, end);
    }


    private static void printArray(int[] a) {
        for (int y : a) {
            System.out.print(y + "\t");
        }
        System.out.println();
    }

    //
    public static long divide(long dividend, int divisor) {
        long more = (long) (Math.pow(2, 31) - 1);
        long less = (long) Math.pow(-2, 31);
        if (dividend < less || divisor < less) return less;

        else if (dividend > more || divisor > more) return more;

        long temp = Math.abs(divisor);
        boolean f = (dividend < 0) ^ (divisor < 0);
        int flag = f ? 1 : 0;
        divisor = Math.abs(divisor);
        dividend = Math.abs(dividend);
        for (int i = 1; ; i++) {
            if (divisor > dividend)
                return (long) ((i - 1) * (pow(-1, flag)));
            else if (divisor == dividend)
                return (long) (i * (pow(-1, flag)));
            divisor += temp;
        }

    }

    public static int climbStairs(int n) {
        if (n < 2) return n;

        int isEven = (n % 2 == 0) ? 1 : 0;
        int k = n / 2;
        int p = (n - isEven) / 2;
        int j = n - 5;

        return (int) (Math.pow(2, k) + Math.pow(2, p) + Math.pow(2, j) - 1);
    }
    

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
//        System.out.println((int)0.99999);

    }


}