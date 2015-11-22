/**
 * A basic implementation of the quicksort algorithm for integers.
 *
 * This will check the weight attribute on each element for comparison
 * in the sorting. An example class edge is used (such as in a graph).
 * An example usage is with Kruskal's algorithm.
 */
public class Weighted_Quicksort {
    private class qsort {
        edge[] a;
        private void sort(int left, int right) {
            int i = left;
            int j = right;
            int pivot = a[left + (right - left) / 2].weight;
            while(i <= j) {
                while(a[i].weight < pivot) i++;
                while(a[j].weight > pivot) j--;
                if(i <= j) {
                    edge temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    i++;
                    j--;
                }
            }
            if(left < j) sort(left, j);
            if(i < right) sort(i, right);
        }
        public qsort(edge[] a) {
            if(a == null || a.length == 0) return;
            this.a = a;
            sort(0, a.length - 1);
        }
    }

    public Weighted_Quicksort() {
        edge[] a = new edge[]{
            new edge(0,1,10),
            new edge(0,2,4),
            new edge(0,3,7),
            new edge(0,4,2),
            new edge(1,2,8),
            new edge(1,3,3),
            new edge(1,4,1),
            new edge(2,3,6),
            new edge(2,4,4),
            new edge(3,4,5)
        };
        qsort sorted = new qsort(a);

        for(int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    private class edge {
        int start;
        int end;
        int weight;

        edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public String toString() {
            return start+","+end+","+weight;
        }
    }
}
