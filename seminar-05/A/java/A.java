import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

    private static class Node {
        String value;
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private static class Stack {
        private Node top;

        Stack() {
            top = null;
        }

        void push(String value) {
            top = new Node(value, top);
        }

        String pop() {
            if (isEmpty()) return null;
            String value = top.value;
            top = top.next;
            return value;
        }

        String peek() {
            return isEmpty() ? null : top.value;
        }

        boolean isEmpty() {
            return top == null;
        }
    }

    // ===== Вспомогательные функции =====
    private static boolean isNumber(String s) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    private static boolean isOperator(String s) {
        return s.length() == 1 && "+-*/".indexOf(s.charAt(0)) >= 0;
    }

    private static int priority(String op) {
        return (op.equals("+") || op.equals("-")) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] tokens = line.trim().split("\\s+");

        Stack stack = new Stack();
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (String token : tokens) {

            // --- Число ---
            if (isNumber(token)) {
                if (!first) result.append(' ');
                result.append(token);
                first = false;
            }

            // --- Оператор ---
            else if (isOperator(token)) {
                String top = stack.peek();
                while (top != null &&
                       isOperator(top) &&
                       priority(top) >= priority(token)) {

                    if (!first) result.append(' ');
                    result.append(stack.pop());
                    first = false;

                    top = stack.peek();
                }
                stack.push(token);
            }

            // --- Открывающая скобка ---
            else if (token.equals("(")) {
                stack.push(token);
            }

            // --- Закрывающая скобка ---
            else if (token.equals(")")) {
                String cur = stack.pop();
                while (cur != null && !cur.equals("(")) {
                    if (!first) result.append(' ');
                    result.append(cur);
                    first = false;
                    cur = stack.pop();
                }
            }
        }

        // --- Выгружаем остаток стека ---
        while (!stack.isEmpty()) {
            String cur = stack.pop();
            if (!cur.equals("(")) {
                if (!first) result.append(' ');
                result.append(cur);
                first = false;
            }
        }

        System.out.println(result.toString().trim());
    }
}
