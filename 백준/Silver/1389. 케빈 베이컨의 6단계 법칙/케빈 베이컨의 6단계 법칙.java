import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] board;
    private static boolean[] check;
    private static int[] distances;
    private static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][n + 1];
        check = new boolean[n + 1];
        distances = new int[n + 1];
        ans = new int[n + 1];


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = 1;
            board[b][a] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                distances[j] = Integer.MAX_VALUE;
            }
            distances[i] = 0;
            bfs(i);
            for (int j = 1; j <= n; j++) {
                ans[i] += distances[j];
            }
        }
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if (min > ans[i]) {
                min = ans[i];
                index = i;
            }
        }
        bw.write(Integer.toString(index));
        bw.flush();
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (check[temp]) break;

            for (int i = 1; i <= n; i++) {
                if (board[temp][i] == 1 && !check[i]) {
                    if (distances[i] > distances[temp] + 1) {
                        q.add(i);
                        distances[i] = distances[temp] + 1;
                    }
                }
            }
        }
    }
}