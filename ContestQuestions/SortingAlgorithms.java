/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingalgorithms;

import java.util.Scanner;

/**
 *
 * @author Glen
 */
public class SortingAlgorithms {

    /**
     * @param args the command line arguments
     */
    /*
    public static int bubbleSort(int [] arr1){
    for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1.length - 1; j++){
                if(arr1[j] > arr1[j + 1]){
                    int temp = arr1[j];
                    arr1[j] = arr1[j + 1];
                    arr1[j+1] = temp;
                }
            }
        }
    } */
    public static int binarySearch(int[] arr, int x) {
        int min = 0;
        int max = arr.length - 1;
        int mid = (max + min) / 2;
        while (min <= max) {
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                min = mid + 1;
                mid = (max + min) / 2;
            } else {
                max = mid - 1;
                mid = (max + min) / 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        int[] arr1 = {-100, 10, 45, 100, 90, 85};

        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + "\t");
        }
        System.out.println();
    }
}
