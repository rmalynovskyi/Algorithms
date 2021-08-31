import java.util.Arrays;

public class BinarySearch {
    private static int countOperations = 0;

    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 8, 5, 3, 8, 1, 0, 7, 4};
        System.out.println(search(array, 7));
        System.out.println("Number of operations: " + countOperations);
    }

    private static int search(int[] array, int a) {
        Arrays.sort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
        int start = 0;
        int end = array.length - 1;
        int middle;
        boolean isFound = false;
        int position = -1;

        while (!isFound && start <= end) {
            countOperations++;
            middle = (int) Math.floor((start + end) / 2);
            if (array[middle] == a) {
                isFound = true;
                position = middle;
                return position;
            }
            if (a < array[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return position;
    }
}
