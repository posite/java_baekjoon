import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] check;
    private static int[][] arr;
    private static int n, m;
    private static int max = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static ArrayList<Integer> area = new ArrayList<Integer>();

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            check = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!check[i][j] && arr[i][j] == 1) {
                        dfs(i, j, 1);
                        area.add(max);
                        max = 0;
                    }
                }
            }
            bw.write(Integer.toString(area.size()));
            bw.newLine();
            if (area.isEmpty()) {
                bw.write("0");
            } else {
                bw.write(Integer.toString(Collections.max(area)));
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dfs(int x, int y, int depth) {
        if (arr[x][y] == 1) {
            max++;
            check[x][y] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 1) {
                    check[nx][ny] = true;
                    dfs(nx, ny, depth + 1);
                }
            }
        }
    }
}