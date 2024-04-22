import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] board;
    private static boolean[][] check;
    private static int[][] distances;
    private static boolean winnable = false;
    private static final int[] dx = {-2, -2, 0, 0, 2, 2};
    private static final int[] dy = {-1, 1, -2, 2, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        check = new boolean[n][n];
        distances = new int[n][n];

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r1, c1));
        check[r1][c1] = true;
        distances[r1][c1] = 0;
        while (!q.isEmpty()) {
            Point temp = q.poll();
            if (temp.x == r2 && temp.y == c2) {
                winnable = true;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !check[nx][ny]) {
                    q.add(new Point(nx, ny));
                    distances[nx][ny] = distances[temp.x][temp.y] + 1;
                    check[nx][ny] = true;
                }
            }
        }
        if (winnable) {
            bw.write(Integer.toString(distances[r2][c2]));
        } else {
            bw.write("-1");
        }
        bw.flush();
    }
}