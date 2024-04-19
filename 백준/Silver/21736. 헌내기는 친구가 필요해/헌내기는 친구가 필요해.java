import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static char[][] board;
    private static boolean[][] check;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        check = new boolean[n][m];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < n; i++) {
            String token = new StringTokenizer(br.readLine(), "").nextToken();
            for (int j = 0; j < m; j++) {
                board[i][j] = token.charAt(j);
                if (token.charAt(j) == 'I') {
                    startX = i;
                    startY = j;
                }
            }
        }

        dfs(startX, startY);
        if (ans != 0) {
            bw.write(Integer.toString(ans));
        } else {
            bw.write("TT");
        }
        bw.flush();
    }

    private static void dfs(int x, int y) {
        check[x][y] = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] != 'X') {
                if (!check[nx][ny]) {
                    if (board[nx][ny] == 'P') {
                        ans++;
                    }
                    dfs(nx, ny);
                }
            }
        }
    }
}
