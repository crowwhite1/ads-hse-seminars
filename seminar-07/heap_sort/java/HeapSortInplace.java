import java.util.*;


public class HeapSortInplace {
    public class MaxHeap<T extends Comparable<T>> {
        public final ArrayList<T> a = new ArrayList<>();

        public MaxHeap() {}

        public MaxHeap(Collection<? extends T> data) {
            if (data != null) {
                a.addAll(data);
                heapify();
            }
        }

        public int size() {
            return a.size();
        }

        public boolean isEmpty() {
            return a.isEmpty();
        }

        public T peek() {
            return a.isEmpty() ? null : a.get(0);
        }

        public void push(T x) {
            a.add(x);
            shiftUp(a.size() - 1);
        }

        public T pop() {
            if (a.isEmpty()) return null;
            T top = a.get(0);
            T last = a.remove(a.size() - 1);
            if (!a.isEmpty()) {
                a.set(0, last);
                shiftDown(0, a.size());
            }
            return top;
        }

        public void shiftUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (a.get(p).compareTo(a.get(i)) >= 0) break;
                T tmp = a.get(p);
                a.set(p, a.get(i));
                a.set(i, tmp);
                i = p;
            }
        }

        public void shiftDown(int i, int n) {
            while (true) {
                int l = 2 * i + 1;
                if (l >= n) break;
                int r = l + 1;
                int j = l;
                if (r < n && a.get(r).compareTo(a.get(l)) > 0) j = r;
                if (a.get(i).compareTo(a.get(j)) >= 0) break;
                T tmp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, tmp);
                i = j;
            }
        }

        public void heapify() {
            int n = a.size();
            for (int i = n / 2 - 1; i >= 0; --i) {
                shiftDown(i, n);
            }
        }
    }
    
    public <T extends Comparable<T>> void heapSortInplace(List<T> arr) {
        MaxHeap<T> heap = new MaxHeap<>(arr);
        arr.clear();
        arr.addAll(heap.a);

        int n = arr.size();
        for (int end = n - 1; end > 0; --end) {
            T tmp = arr.get(0);
            arr.set(0, arr.get(end));
            arr.set(end, tmp);
            heap.shiftDown(0, end);
        }
    }
}