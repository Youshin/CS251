public class kth {
    public static void main(String[] args) {
        int[] list1 = new int[] { 3, 4, 10, 23, 45, 55, 56, 58, 60, 65 };
        int[] list2 = new int[] { 3, 3, 3, 15, 16, 28, 50, 70, 71, 72 };
        int k = 2;

        int Largest = Largest(list1, list2, k);
        int largest = kthSmallestFast(k);
        System.out.println(k + "th Largest is " + largest);
    }
    private static int kthSmallestFast(int k) {
        // System.out.println("this is an O(log k) speed algorithm with meaningful variables name");
        int[] A1 = new int[] { 3, 4, 10, 23, 45, 55, 56, 58, 60, 65 };
        int[] A2 = new int[] { 3, 3, 3, 15, 16, 28, 50, 70, 71, 72 };
        int size1 = A1.length, size2 = A2.length;
        k = A1.length+A2.length-k+1;

        int index1 = 0, index2 = 0, step = 0;
        while (index1 + index2 < k - 1) {
            step = (k - index1 - index2) / 2;
            int step1 = index1 + step;
            int step2 = index2 + step;
            if (size1 > step1 - 1
                    && (size2 <= step2 - 1 || A1[step1 - 1] <(A2[step2 - 1]))) {
                index1 = step1; // commit to element at index = step1 - 1
            } else {
                index2 = step2;
            }
        }
        // the base case of (index1 + index2 == k - 1)
        if (size1 > index1 && (size2 <= index2 || A1[index1]<(A2[index2]))) {
            return A1[index1];
        } else {
            return A2[index2];
        }
    }

    public static int Largest(int[] A, int[] B, int k) {
        if (A == null || B == null) {
            throw new IllegalArgumentException("Arrays cannot be null!");
        }
        k = A.length+B.length-k+1;
        int a = A.length;
        int b = B.length;

        if (k < 1 || k > a + b) {
            throw new IllegalArgumentException("k is not within range!");
        }

        int minSizeA = Math.max(0, k - b);
        int maxSizeA = Math.min(a, k);

        while (minSizeA <= maxSizeA) {
            int sizeA = minSizeA + (maxSizeA - minSizeA) / 2;
            int sizeB = k - sizeA;
            int indexA = sizeA - 1;
            int indexB = sizeB - 1;
            int indexANext = sizeA;
            int indexBNext = sizeB;
            int valA = (indexA < 0) ? Integer.MIN_VALUE : A[indexA];
            int valB = (indexB < 0) ? Integer.MIN_VALUE : B[indexB];
            int valANext = (indexANext >= a) ? Integer.MAX_VALUE : A[indexANext];
            int valBNext = (indexBNext >= b) ? Integer.MAX_VALUE: B[indexBNext];

            if (valA <= valBNext && valB <= valANext) {
                return Math.max(valA, valB);
            } else if (valA > valBNext) {
                maxSizeA = sizeA - 1;
            } else {
                minSizeA = sizeA + 1;
            }
        }

        return 0;
    }
}