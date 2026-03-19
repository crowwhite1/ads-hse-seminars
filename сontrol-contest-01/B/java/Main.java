import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        for (int i = 0; i < 297; i++) {
            out.println("get");
            out.flush();
            br.readLine();
        }

        out.println("put 3");
        out.flush();

        out.println("put 4");
        out.flush();

        out.println("get");
        out.flush();

        int a = Integer.parseInt(br.readLine());

        if (a == 3) {
            out.println("queue");
        } else {
            out.println("stack");
        }
        out.flush();
    }
}