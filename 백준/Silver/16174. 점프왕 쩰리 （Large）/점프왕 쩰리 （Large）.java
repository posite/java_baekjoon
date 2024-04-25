import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] board;
    private static boolean[][] check;
    private static boolean winnable = false;
    private static final int[] dx = {1, 0};
    private static final int[] dy = {0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        check = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        if (winnable) {
            bw.write("HaruHaru");
        } else {
            bw.write("Hing");
        }
        bw.flush();
    }

    private static void dfs(int x, int y) {
        check[x][y] = true;
        int move = board[x][y];
        for (int i = 0; i < dx.length; i++) {
            int nx = x + move * dx[i];
            int ny = y + move * dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (!check[nx][ny]) {
                    if (nx == n - 1 && ny == n - 1) {
                        winnable = true;
                        return;
                    }
                    dfs(nx, ny);
                }
            }
        }
    }
}