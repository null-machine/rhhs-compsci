/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

/**
 *
 * @author Glen
 */
public class Sorting {

    public static int binarySearch(int[] arr, int x) {
        int min = 0;
        int max = arr.length - 1;
        int mid = (max + min) / 2;
        while (min <= max) {
            if (arr[mid] == x) {
                return mid;
            } else if (x > arr[mid]) {
                min = mid + 1;
                mid = (max + min) / 2;
            } else {
                min = mid - 1;
                mid = (max + min) / 2;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        int[] arr = {10, 11, 12, 13, 14, 15};
        System.out.println(binarySearch(arr, 11));
    }
}