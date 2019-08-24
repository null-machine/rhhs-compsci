import java.util.Random;
import java.util.Arrays;

import java.util.Stack;

class SortingAlgs {

	public static void main(String[] args) {

		int data[] = generateNumberArray(20);
		int[] tempArray;
		long startTime, endTime;
		double elapsedTime;
		displayArray(data);

		// arrays.sort
		System.out.println("\nSorting with Arrays.sort sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		tempArray = javaBuiltInSort(tempArray);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");

		// selection
		System.out.println("\nSorting with Selection sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		tempArray = selectionSort(tempArray);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");

		// bubble sort
		System.out.println("\nSorting with Bubble sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		tempArray = bubbleSort(tempArray);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");

		// insertion
		System.out.println("\nSorting with Insertion sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		tempArray = insertionSort(tempArray);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");

		// merge
		System.out.println("\nSorting with Merge sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		tempArray = mergeSort(tempArray);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");

		// quick
		System.out.println("\nSorting with Quick sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		quickSort(tempArray, 0, tempArray.length - 1);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");

		// radix
		/*
		System.out.println("\nSorting with Radix sort:");
		tempArray = Arrays.copyOf(data,data.length);
		startTime = System.nanoTime();
		radixSort(tempArray);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime) / 1000000.0;
		displayArray(tempArray);
		System.out.println("The sort took: " + elapsedTime + "ms");
		*/
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
	 * selectionSort
	 * Sorts a random array on integers using selection sort
	 * @param the unsorted integer array
	 * @return the sorted integer array
	 */
	private static int[] selectionSort(int[] numbers) {
		int leastIndex;
		int temp;
		for(int i = 0; i < numbers.length; ++i) {
			leastIndex = i;
			for(int j = i; j < numbers.length; ++j) {
				if(numbers[j] < numbers[leastIndex]) {
					leastIndex = j;
				}
			}
			temp = numbers[i];
			numbers[i] = numbers[leastIndex];
			numbers[leastIndex] = temp;
		}
		return numbers;
	}

	/** bubbleSort
	 * Sorts a random array on integers using bubble sort
	 * @param the unsorted integer array
	 * @return the sorted integer array
	 */
	private static int[] bubbleSort(int[] numbers) {
		boolean swapped = true;
		int temp;
		while(swapped == true) {
			swapped = false;
			for(int i = 0; i < numbers.length - 1; ++i) {
				if(numbers[i] > numbers[i + 1]) {
					temp = numbers[i];
					numbers[i] = numbers[i + 1];
					numbers[i + 1] = temp;
					swapped = true;
				}
			}
		}
		return numbers;
	}

	/**
	 * insertionSort
	 * Sorts a random array on integers using bubble sort
	 * @param the unsorted integer array
	 * @return the sorted integer array
	 */
	private static int[] insertionSort(int[] numbers) {
		int j;
		int temp;
		for(int i = 1; i < numbers.length; ++i) {
			j = i;
			while(j > 0 && numbers[j] < numbers[j - 1]) {
				temp = numbers[j];
				numbers[j] = numbers[j - 1];
				numbers[j - 1] = temp;
				--j;
			}
		}
		return numbers;
	}

	/**
	 * mergeSort
	 * Sorts a random array on integers using bubble sort
	 * @param the unsorted integer array
	 * @return the sorted integer array
	 */
	private static int[] mergeSort(int[] numbers) {
		if(numbers.length <= 1) return numbers;

		int[] first = new int[numbers.length / 2];
		int[] second = new int[numbers.length - first.length];
		System.arraycopy(numbers, 0, first, 0, first.length);
		System.arraycopy(numbers, first.length, second, 0, second.length);
		mergeSort(first);
		mergeSort(second);

		int firstIndex = 0;
		int secondIndex = 0;
		int numbersIndex = 0;
		while (firstIndex < first.length && secondIndex < second.length) {
			if (first[firstIndex] < second[secondIndex]) {
				numbers[numbersIndex] = first[firstIndex];
				++firstIndex;
			} else {
				numbers[numbersIndex] = second[secondIndex];
				++secondIndex;
			}
			++numbersIndex;
		}
		System.arraycopy(first, firstIndex, numbers, numbersIndex, first.length - firstIndex);
		System.arraycopy(second, secondIndex, numbers, numbersIndex, second.length - secondIndex);
		return numbers;
	}

	/**
	 * quickSort
	 * Sorts a random array on integers using quick sort
	 * @param the unsorted integer array
	 * @return the sorted integer array
	 */
	public static void quickSort(int[] arr, int start, int end){
		int partition = partition(arr, start, end);
		if(partition-1>start) {
			quickSort(arr, start, partition - 1);
		}
		if(partition+1<end) {
			quickSort(arr, partition + 1, end);
		}
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

	/*
	private static int[] radixSort(int[] numbers) {
		Stack zeroes = new Stack();
		Stack ones = new Stack();
		int currentRad;
		int i = 0;
		int k;
		while(!zeroes.empty() && !ones.empty()) {
			++i;
			currentRad = (int)Math.pow(2, i);
			for(int j = 0; j < numbers.length; ++j) {
				if(numbers[j] / currentRad != 0 && numbers[j] % currentRad == 0) zeroes.push(numbers[j]);
				else ones.push(numbers[j]);
			}
			System.arraycopy(zeroes, 0, numbers, 0, zeroes.size);
			System.arraycopy(ones, 0, numbers, zeroes.length, ones.size);
		}
		return numbers;
	}
	*/

	/**
	 * javaBuiltInSort
	 * Sorts a random array on integers using Arrays.sort
	 * @param the unsorted integer array
	 * @return the sorted integer array
	 */
	private static int[] javaBuiltInSort(int[] numbers) {
		Arrays.sort(numbers);
		return numbers;
	}
}
