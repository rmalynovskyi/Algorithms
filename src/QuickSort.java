import java.util.Arrays;

public class QuickSort {
    private static int countOperations = 0;

    public static void main(String[] args) {
        int[] array = new int[]{2, 6, 8, 5, 3, 9, 1, 10, 7, 4};
        System.out.println(Arrays.toString(sort(array, 0, array.length - 1)));
        System.out.println("Number of operations: " + countOperations);
    }

    private static int[] sort(int[] array, int from, int to) {
        if (array.length <= 1 || from >= to) {
            return array;
        }

        int pivotIndex = (int) Math.floor((from + to) / 2);
        int pivot = array[pivotIndex];
        int leftIndex = from;
        int rightIndex = to;

        while (leftIndex <= rightIndex) {
            countOperations++;
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }

            while (array[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                int temp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }

            if (from < rightIndex) {
                sort(array, from, rightIndex);
            }

            if (to > leftIndex) {
                sort(array, leftIndex, to);
            }
        }

        return array;
    }
}



