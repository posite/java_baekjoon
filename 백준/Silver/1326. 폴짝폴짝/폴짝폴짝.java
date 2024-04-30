import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] board = new int[n + 1];
        int[] ans = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            ans[i] = Integer.MAX_VALUE;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        ans[a] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (board[temp] != 0) {
                for (int i = 1; (temp - i * board[temp]) >= 1; i++) {
                    int dest = temp - i * board[temp];
                    if (ans[dest] > ans[temp] + 1) {
                        q.add(dest);
                        ans[dest] = ans[temp] + 1;
                    }
                }
                for (int i = 1; (temp + i * board[temp]) <= n; i++) {
                    int dest = temp + i * board[temp];
                    if (ans[dest] > ans[temp] + 1) {
                        q.add(dest);
                        ans[dest] = ans[temp] + 1;
                    }
                }
            }
        }
        if (ans[b] == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(ans[b]));
        }
        bw.flush();
    }
}