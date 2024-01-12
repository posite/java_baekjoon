import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] arr, dp;
    private static int n, m;
    private static int[] dx = {1, 0, 0, -1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = -1;
                }
            }
            bw.write(Integer.toString(dfs(0, 0)));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int dfs(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1;
        }
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            if (arr[x][y] > arr[nx][ny]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }
}