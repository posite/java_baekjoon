import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] check;
    static String[][] arr;
    static int n, m;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    static int max = 0;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new String[n][m];
            check = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                String[] inputs = br.readLine().split("");
                for (int j = 0; j < m; j++) {
                    arr[i][j] = inputs[j];
                }
            }
            dfs(0, 0, 1, arr[0][0]);
            bw.write(Integer.toString(max));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dfs(int x, int y, int depth, String text) {
        max = Math.max(max, depth);

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny]) {
                continue;
            }
            if (!text.contains(String.valueOf(arr[nx][ny]))) {
                check[nx][ny] = true;
                dfs(nx, ny, depth + 1, text + arr[nx][ny]);
                check[nx][ny] = false;
            }
        }
    }
}