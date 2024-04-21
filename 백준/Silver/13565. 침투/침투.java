import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] board;
    private static boolean[][] check;
    private static boolean elect = false;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] token = st.nextToken().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(token[j]);
            }
        }

        for (int i = 0; i < m; i++) {
            if (!check[0][i]) {
                dfs(0, i);
            }
        }
        if (elect) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
    }

    private static void dfs(int x, int y) {
        check[x][y] = true;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 0) {
                if (!check[nx][ny]) {
                    if (nx == n - 1) {
                        elect = true;
                        return;
                    }
                    dfs(nx, ny);
                }
            }
        }
    }
}