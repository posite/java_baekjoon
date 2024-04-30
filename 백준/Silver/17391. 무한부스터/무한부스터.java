import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] ans = new int[n][m];
        for (int[] a : ans) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        ans[0][0] = 0;
        while (!q.isEmpty()) {
            Point temp = q.poll();
            int move = board[temp.x][temp.y];
            for (int i = 1; i <= move; i++) {
                if (i + temp.x < n) {
                    if (ans[i + temp.x][temp.y] > ans[temp.x][temp.y] + 1) {
                        q.add(new Point(i + temp.x, temp.y));
                        ans[i + temp.x][temp.y] = ans[temp.x][temp.y] + 1;
                    }
                }
                if (i + temp.y < m) {
                    if (ans[temp.x][i + temp.y] > ans[temp.x][temp.y] + 1) {
                        q.add(new Point(temp.x, i + temp.y));
                        ans[temp.x][i + temp.y] = ans[temp.x][temp.y] + 1;
                    }
                }
            }
        }
        bw.write(Integer.toString(ans[n - 1][m - 1]));
        bw.flush();
    }
}