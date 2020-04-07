import java.util.Random;
import java.util.Arrays;

import java.util.Stack;

class SlowntSort {
	
	public static void main(String[] args) {
		
		int data[] = generateNumberArray(20);
		int[] tempArray;
		long startTime, endTime;
		double elapsedTime;
		displayArray(data);
		
		// quick
		System.out.println("\nSorting with Quick sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		quickSort(tempArray, 0, tempArray.length - 1);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");
		
	}
	
	/**
	* generateNumberArray
	* Creates a random array on integers
	* @param size of array
	* @return the generated integer array
	*/
	private static int[] generateNumberArray(int numOfElements) {
		int[] generated = new int[numOfElements];
		//add unique numbers into array in order
		for (int i = 0 ; i< generated.length;i++)
		generated[i]=i;
		
		//shuffle the numbers randomly
		Random randomNumber = new Random();
		for (int i = 0 ; i< generated.length;i++) {
			//swap to random positions
			int temp;
			int first = randomNumber.nextInt(generated.length);
			int second = randomNumber.nextInt(generated.length);
			temp = generated[first];
			generated[first]=generated[second];
			generated[second]=temp;
		}
		
		return generated;
	}
	
	/**
	* displayArray
	* Sorts a random array on integers using selection sort
	* @param the integer array
	*/
	private static void displayArray(int[] numbers) {
		for (int i = 0 ; i< numbers.length;i++) {
			System.out.print(numbers[i]+" ");
		}
		System.out.println("");
	}
	
	/**
	* quickSort
	* Sorts a random array on integers using quick sort
	* @param the unsorted integer array
	* @return the sorted integer array
	*/
	public static void quickSort(int[] arr, int start, int end){
		int temp;
		if (end - start < 1) {
			return;
		}
		int pivot = arr[(end - start) / 2 + start];
		int left = start;
		int right = end;
		while (left <= right) {
			while (arr[left] < pivot) left++;
			while (arr[right] > pivot) right--;
			if (left <= right) {
				temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++; right--;
			}
		}
		if (start < right) quickSort(arr, start, right);
		if (end > left) quickSort(arr, left, end);
		
	}
	
	public static int partition(int[] arr, int start, int end){
		int pivot = arr[end];
		
		for(int i=start; i<end; i++){
			if(arr[i]<pivot){
				int temp= arr[start];
				arr[start]=arr[i];
				arr[i]=temp;
				start++;
			}
		}
		
		int temp = arr[start];
		arr[start] = pivot;
		arr[end] = temp;
		
		return start;
	}
}
