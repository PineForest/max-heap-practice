package algorithms.practice;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// 2  1  3  0  4  6  5
public class MaxHeap {
    private static void heapify(int[] heap) {
        for (int i = heap.length - 1; i > 0; i -= 2) {
            maxifyParent(heap, i);
            if (i == heap.length - 1 && heap.length % 2 == 0) {
                ++i;
            }
        }
    }

    private static void maxifyParent(int[] heap, int childIndex) {
        // odd means that the array has no right child at the end so the comparison is simplified
        boolean missingRight = childIndex % 2 != 0;
        int leftIndex = missingRight ? childIndex : childIndex - 1;
        int parentIndex = (leftIndex - 1) / 2;
        int maxIndex;
        if (missingRight) {
            maxIndex = heap[parentIndex] > heap[leftIndex] ? parentIndex : leftIndex;
        } else {
            int rightIndex = childIndex;
            maxIndex = max(heap, parentIndex, leftIndex, rightIndex);
        }
        if (parentIndex != maxIndex) {
            int max = heap[maxIndex];
            heap[maxIndex] = heap[parentIndex];
            heap[parentIndex] = max;
        }
    }

    private static int max(int[] array, int index1, int index2, int index3) {
        int maxIndex = array[index1] > array[index2] ? index1 : index2;
        return array[maxIndex] > array[index3] ? maxIndex : index3;
    }

    private static int[] convert(String[] stringArray) {
        int[] result = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            result[i] = Integer.parseInt(stringArray[i]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] stringArray = line.split(",");
            int[] array = convert(stringArray);
            heapify(array);
            System.out.println(array[0]);
        }
    }
}
