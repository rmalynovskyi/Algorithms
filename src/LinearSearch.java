public class LinearSearch {
    private static int countOperations = 0;

    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 8, 5, 3, 8, 1, 0, 7, 4};
        System.out.println(search(array, 7));
        System.out.println("Number of operations: " + countOperations);
    }

    private static int search(int[] array, int a) {
        for (int i = 0; i < array.length; i++) {
            countOperations++;
            if (array[i] == a) {
                return i;
            }
        }
        return -1;
    }
}
