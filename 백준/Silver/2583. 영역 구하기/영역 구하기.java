import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] check;
    static int[][] arr;
    static int n, m;
    static int max = 0;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    static ArrayList<Integer> area = new ArrayList<Integer>();

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            check = new boolean[n][m];
            for (int i = 0; i < k; i++) {
                String[] inputs = br.readLine().split(" ");
                int ax = Integer.parseInt(inputs[0]);
                int ay = Integer.parseInt(inputs[1]);
                int bx = Integer.parseInt(inputs[2]);
                int by = Integer.parseInt(inputs[3]);
                for (int j = ax; j < bx; j++) {
                    for (int l = ay; l < by; l++) {
                        arr[l][j] = 1;
                    }
                }
            }
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    bw.write(arr[i][j] + " ");
//                }
//                bw.newLine();
//            }
//            bw.flush();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!check[i][j] && arr[i][j] == 0) {
                        dfs(i, j, 1);
                        area.add(max);
                        max = 0;
                    }
                }
            }

            bw.write(Integer.toString(area.size()));
            bw.newLine();
            area.stream().sorted().forEach(number -> {
                try {
                    bw.write(number.toString() + " ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dfs(int x, int y, int depth) {
        max++;
        if (arr[x][y] == 0) {
            check[x][y] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 0) {
                    check[nx][ny] = true;
                    dfs(nx, ny, depth + 1);
                }
            }
        }
    }
}