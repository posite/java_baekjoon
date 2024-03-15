import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준알고리즘 1303번 전쟁 - 전투

public class Main {
    static int N, M, nowX, nowY;
    static char[][] arr;

    static boolean[][] visit;
    static int[] dirX = {0, 0, -1, 1}; // 상 하 좌 우
    static int[] dirY = {-1, 1, 0, 0}; // 상 하 좌 우
    static int sum_B, sum_W;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[M][N];
        visit = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int result_W = 0;
        int result_B = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && arr[i][j] == 'W') {
                    sum_W = 1;
                    DFS_W(i, j);
                    result_W += (sum_W * sum_W);
                }
                if (!visit[i][j] && arr[i][j] == 'B') {
                    sum_B = 1;
                    DFS_B(i, j);
                    result_B += (sum_B * sum_B);
                }
            }
        }
        System.out.println(result_W + " " + result_B);

    }

    static void DFS_W(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            nowX = dirX[i] + x;
            nowY = dirY[i] + y;

            if (range_check() && !visit[nowX][nowY] && arr[nowX][nowY] == 'W') {
                sum_W++;
                DFS_W(nowX, nowY);
            }
        }

    } // End of DFS

    static void DFS_B(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            nowX = dirX[i] + x;
            nowY = dirY[i] + y;

            if (range_check() && !visit[nowX][nowY] && arr[nowX][nowY] == 'B') {
                sum_B++;
                DFS_B(nowX, nowY);
            }
        }

    } // End of DFS

    static boolean range_check() {
        return (nowX >= 0 && nowY >= 0 && nowX < M && nowY < N);
    } // End of range_check
}