package org.example;

public class SortAlgorithms {
    public static void countingSort(Student[] arr) {
        int n = arr.length;
        int max = arr[0].cityCode;
        int min = arr[0].cityCode;

        for (Student s : arr) {
            if (s.cityCode > max) max = s.cityCode;
            if (s.cityCode < min) min = s.cityCode;
        }

        int range = max - min + 1;
        int[] count = new int[range];
        Student[] output = new Student[n];

        for (Student s : arr)
            count[s.cityCode - min]++;

        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            Student s = arr[i];
            output[count[s.cityCode - min] - 1] = s;
            count[s.cityCode - min]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void radixSort(Student[] arr) {
        int max = 0;
        for (Student s : arr)
            if (s.cityCode > max) max = s.cityCode;

        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortByDigit(arr, exp);
    }

    private static void countingSortByDigit(Student[] arr, int exp) {
        int n = arr.length;
        Student[] output = new Student[n];
        int[] count = new int[10];

        for (Student s : arr)
            count[(s.cityCode / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            Student s = arr[i];
            int digit = (s.cityCode / exp) % 10;
            output[count[digit] - 1] = s;
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Student[] arr, int low, int high) {
        int pivot = arr[high].cityCode;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].cityCode < pivot) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
