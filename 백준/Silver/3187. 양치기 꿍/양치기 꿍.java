import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static char[][] board;
    private static boolean[][] check;
    private static int wolf = 0, sheep = 0;
    private static int ansW = 0, ansS = 0;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String token = st.nextToken();
            for (int j = 0; j < m; j++) {
                board[i][j] = token.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                wolf = 0;
                sheep = 0;
                if (!check[i][j] && board[i][j] != '#') {
                    dfs(i, j);
                    if (sheep > wolf) {
                        ansS += sheep;
                    } else {
                        ansW += wolf;
                    }
                }
            }
        }
        bw.write(Integer.toString(ansS) + " " + Integer.toString(ansW));
        bw.flush();
    }

    public static void dfs(int x, int y) {
        check[x][y] = true;
        if (board[x][y] == 'v') {
            wolf++;
        } else if (board[x][y] == 'k') {
            sheep++;
        }
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] != '#') {
                if (!check[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }
}