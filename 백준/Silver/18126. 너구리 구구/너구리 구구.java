import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] board;
    private static boolean[] check;
    private static long[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        board = new int[n + 1][n + 1];
        check = new boolean[n + 1];
        ans = new long[n + 1];
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[a][b] = c;
            board[b][a] = c;
        }
        ans[1] = 0;
        dfs(1);
        long max = 0;
        for (int i = 1; i < n + 1; i++) {
            if (max < ans[i]) {
                max = ans[i];
            }
        }
        bw.write(Long.toString(max));
        bw.flush();
    }

    private static void dfs(int node) {
        check[node] = true;
        for (int i = 1; i < n + 1; i++) {
            if (!check[i] && board[node][i] != 0) {
                ans[i] = ans[node] + board[node][i];
                dfs(i);
            }
        }
    }
}
