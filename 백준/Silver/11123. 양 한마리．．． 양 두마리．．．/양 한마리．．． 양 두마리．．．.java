import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, l;
    private static char[][] board;
    private static boolean[][] check;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new char[n][m];
            check = new boolean[n][m];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                String token = st.nextToken();
                for (int k = 0; k < m; k++) {
                    board[j][k] = token.charAt(k);
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (board[j][k] == '#' && !check[j][k]) {
                        count++;
                        dfs(j, k);
                    }
                }
            }
            bw.write(Integer.toString(count));
            bw.newLine();
            count = 0;
        }
        bw.flush();
    }

    private static void dfs(int x, int y) {
        check[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!check[nx][ny] && board[nx][ny] == '#') {
                    dfs(nx, ny);
                }
            }
        }
    }
}