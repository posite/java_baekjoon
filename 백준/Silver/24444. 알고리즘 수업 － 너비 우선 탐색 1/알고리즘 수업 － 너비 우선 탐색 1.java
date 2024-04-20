import java.io.*;
import java.util.*;

public class Main {
    private static int n, m, start;
    private static ArrayList<Integer>[] board;
    private static int[] ans;
    private static boolean[] check;
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        board = new ArrayList[n + 1];
        check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            board[i] = new ArrayList<>();
        }
        ans = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a].add(b);
            board[b].add(a);
        }
        for (int i = 1; i <= n; i++) {
            ans[i] = Integer.MAX_VALUE;
            Collections.sort(board[i]);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        ans[start] = count;
        check[start] = true;
        while (!q.isEmpty()) {
            int temp = q.poll();

            for (int i = 0; i < board[temp].size(); i++) {
                if (ans[board[temp].get(i)] > count && !check[board[temp].get(i)]) {
                    count++;
                    q.add(board[temp].get(i));
                    ans[board[temp].get(i)] = count;
                    check[board[temp].get(i)] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (ans[i] != Integer.MAX_VALUE) {
                bw.write(Integer.toString(ans[i]));
            } else {
                bw.write("0");
            }
            bw.newLine();
        }
        bw.flush();
    }
}