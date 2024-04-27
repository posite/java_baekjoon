import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, start;
    private static ArrayList<Integer>[] board;
    private static int[] ans;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        board = new ArrayList[n + 1];
        ans = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < board.length; i++) {
            board[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a].add(b);
            board[b].add(a);
        }
        for (ArrayList<Integer> integers : board) {
            Collections.sort(integers, Collections.reverseOrder());
        }
        ans[start] = 0;
        dfs(start);
        for (int i = 1; i < ans.length; i++) {
            if (ans[i] == 0 && i != start) {
                bw.write("-1");
            } else {
                bw.write(Integer.toString(ans[i]));
            }
            bw.newLine();
        }
        bw.flush();
    }

    private static void dfs(int node) {
        visited[node] = true;
        for (int next : board[node]) {
            if (!visited[next]) {
                ans[next] = ans[node] + 1;
                dfs(next);
            }
        }
    }
}