import java.util.Arrays;

public class SelectionSort {
    private static int countOperations = 0;

    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 8, 5, 3, 8, 1, 0, 7, 4};
        System.out.println(Arrays.toString(sort(array)));
        System.out.println("Number of operations: " + countOperations);
    }

    private static int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                countOperations++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }
}
