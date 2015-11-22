/**
 * A basic implementation of the quicksort algorithm for integers.
 *
 * @author Zack Zatkin-Gold
 */
public class Quicksort {
    private int[] a;

    private class qsort {
        private void sort(int left, int right) {
            int i = left;
            int j = right;
            int pivot = a[left + (right - left) / 2];
            while(i <= j) {
                while(a[i] < pivot) i++;
                while(a[j] > pivot) j--;
                if(i <= j) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    i++;
                    j--;
                }
            }
            if(left < j) sort(left, j);
            if(i < right) sort(i, right);
        }
        public qsort() {
            if(a == null || a.length == 0) return;
            sort(0, a.length - 1);
        }
    }

    public Quicksort() {
        a = new int[]{10,4,7,2,8,3,1,6,4,5};
        qsort sort = new qsort();

        for(int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
}
