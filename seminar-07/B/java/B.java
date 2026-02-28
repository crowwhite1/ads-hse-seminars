import java.io.*;
import java.util.*;

public class B {

    static class MinHeap {
        private final ArrayList<Integer> a = new ArrayList<>();

        public MinHeap(int[] data) {
            for (int x : data) a.add(x);
            heapify();
        }

        public int size() {
            return a.size();
        }

        public boolean isEmpty() {
            return a.isEmpty();
        }

        public Integer peek() {
            if (a.isEmpty()) return null;
            return a.get(0);
        }

        public void push(int x) {
            a.add(x);
            shiftUp(a.size() - 1);
        }

        public Integer pop() {
            if (a.isEmpty()) return null;
            int top = a.get(0);
            int last = a.remove(a.size() - 1);
            if (!a.isEmpty()) {
                a.set(0, last);
                shiftDown(0, a.size());
            }
            return top;
        }

        private void shiftUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (a.get(p) <= a.get(i)) break;
                int tmp = a.get(p);
                a.set(p, a.get(i));
                a.set(i, tmp);
                i = p;
            }
        }

        private void shiftDown(int i, int n) {
            while (true) {
                int l = 2 * i + 1;
                if (l >= n) break;
                int r = l + 1;
                int j = l;
                if (r < n && a.get(r) < a.get(l)) j = r;
                if (a.get(i) <= a.get(j)) break;
                int tmp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, tmp);
                i = j;
            }
        }

        private void heapify() {
            int n = a.size();
            for (int i = n / 2 - 1; i >= 0; i--) {
                shiftDown(i, n);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine().trim());
        String[] parts = in.readLine().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        MinHeap heap = new MinHeap(arr);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            out.append(heap.pop());
            if (i + 1 < n) out.append(' ');
        }

        System.out.print(out.toString());
    }
}