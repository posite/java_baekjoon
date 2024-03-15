import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static char[][] board;
    private static boolean[][] check;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int myArea, enemyArea;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[m][n];
        check = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String split = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = split.charAt(j);
            }
        }
        int myMax = 0;
        int enemyMax = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!check[i][j]) {
                    if (board[i][j] == 'W') {
                        myArea = 1;
                        dfsW(i, j);
                        myMax += (myArea * myArea);
                    } else {
                        enemyArea = 1;
                        dfsB(i, j);
                        enemyMax += (enemyArea * enemyArea);
                    }
                }
            }
        }
        System.out.println(myMax + " " + enemyMax);
    }

    public static void dfsW(int x, int y) {
        check[x][y] = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (!check[nx][ny] && board[nx][ny] == 'W') {
                    myArea++;
                    dfsW(nx, ny);
                }
            }
        }
    }

    public static void dfsB(int x, int y) {
        check[x][y] = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (!check[nx][ny] && board[nx][ny] == 'B') {
                    enemyArea++;
                    dfsB(nx, ny);
                }
            }
        }
    }
}