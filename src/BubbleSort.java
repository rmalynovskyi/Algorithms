import java.util.Arrays;

public class BubbleSort {
    private static int countOperations = 0;

    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 8, 5, 3, 8, 1, 0, 7, 4};
        System.out.println(Arrays.toString(sort(array)));
        System.out.println("Number of operations: " + countOperations);
    }

    private static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                countOperations++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
