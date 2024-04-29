import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static boolean[] visited;
    private static int[] ans;
    private static ArrayList<Integer>[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        board = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        ans = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            board[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[b].add(a);
        }
        st = new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        bw.write(Integer.toString(dfs(dest, 0)));
        bw.flush();
    }

    private static int dfs(int dest, int depth) {
        visited[dest] = true;
        int sum = 0;
        if (board[dest].isEmpty()) return depth;
        for (int i = 0; i < board[dest].size(); i++) {
            if (!visited[board[dest].get(i)]) {
                sum += dfs(board[dest].get(i), 1);
            }
        }
        return sum + depth;
    }
}