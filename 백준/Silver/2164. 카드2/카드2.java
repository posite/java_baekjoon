import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        if (n == 1) {
            bw.write("1");
            bw.flush();
            return;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }
        int last = 0;
        while (!deque.isEmpty()) {
            int del = deque.pollFirst();
            if (!deque.isEmpty()) {
                int addLast = deque.pollFirst();
                deque.add(addLast);
            } else {
                last = del;
                break;
            }

        }
        bw.write(Integer.toString(last));
        bw.flush();
    }
}