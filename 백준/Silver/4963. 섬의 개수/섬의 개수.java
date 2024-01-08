import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] check;
    static int[][] arr;
    static int n, m;
    static int count = 0;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언

        try {
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
                if (n == 0 && m == 0) {
                    break;
                }
                arr = new int[n][m];
                check = new boolean[n][m];
                for (int i = 0; i < m; i++) {
                    String[] inputs = br.readLine().split(" ");
                    for (int j = 0; j < n; j++) {
                        arr[j][i] = Integer.parseInt(inputs[j]);
                    }
                }
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!check[j][i] && arr[j][i] == 1) {
                            count++;
                        }
                        dfs(j, i, 0);
                    }
                }
                bw.write(Integer.toString(count));
                bw.newLine();
                count = 0;
                bw.flush();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int dfs(int x, int y, int depth) {
        if (x < 0 || x >= n || y < 0 || y >= m || check[x][y]) {
            return depth;
        }
        if (arr[x][y] == 1 && !check[x][y]) {
            check[x][y] = true;
            return dfs(x - 1, y, depth + 1) + dfs(x, y - 1, depth + 1) +
                    dfs(x + 1, y, depth + 1) + dfs(x, y + 1, depth + 1) +
                    dfs(x - 1, y - 1, depth + 1) + dfs(x - 1, y + 1, depth + 1) +
                    dfs(x + 1, y - 1, depth + 1) + dfs(x + 1, y + 1, depth + 1);
        } else {
            return depth;
        }
    }
}