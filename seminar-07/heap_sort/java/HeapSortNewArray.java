import java.util.*;

public class HeapSortNewArray {
    public class MinHeap<T extends Comparable<T>> {
        private final ArrayList<T> a = new ArrayList<>();

        public MinHeap() {}

        public MinHeap(Collection<? extends T> data) {
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

        private void shiftUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (a.get(p).compareTo(a.get(i)) <= 0) break;
                swap(p, i);
                i = p;
            }
        }

        private void shiftDown(int i, int n) {
            while (true) {
                int l = 2 * i + 1;
                if (l >= n) break;
                int r = l + 1;
                int j = l;
                if (r < n && a.get(r).compareTo(a.get(l)) < 0) j = r;
                if (a.get(i).compareTo(a.get(j)) <= 0) break;
                swap(i, j);
                i = j;
            }
        }

        private void heapify() {
            int n = a.size();
            for (int i = n / 2 - 1; i >= 0; i--) {
                shiftDown(i, n);
            }
        }

        private void swap(int i, int j) {
            T t = a.get(i);
            a.set(i, a.get(j));
            a.set(j, t);
        }
    }
    public <T extends Comparable<T>> List<T> heapSortNewArray(List<T> arr) {
        MinHeap<T> heap = new MinHeap<>(arr);
        ArrayList<T> result = new ArrayList<>(arr.size());
        while (!heap.isEmpty()) result.add(heap.pop());
        return result;
    }
}