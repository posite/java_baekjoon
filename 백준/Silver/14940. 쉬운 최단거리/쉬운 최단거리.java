import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[][] arr, result;
    private static boolean[][] check;
    private static Point start;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            result = new int[n][m];
            check = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 2) {
                        start = new Point(i, j);
                    } else if (arr[i][j] == 0) {
                        result[i][j] = -2;
                    }
                }
            }
            bfs();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int current = result[i][j];
                    if (current == -2) {
                        System.out.print("0 ");
                    } else {
                        if (current == 0) {
                            if (i == start.x && j == start.y) {
                                System.out.print("0 ");
                            } else {
                                System.out.print("-1 ");
                            }
                        } else {
                            System.out.print(current + " ");
                        }
                    }
                }
                System.out.println();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start.x, start.y));
        check[start.x][start.y] = true;
        while (!q.isEmpty()) {
            Point temp = q.poll();
            int currentX = temp.x;
            int currentY = temp.y;
//            System.out.println(String.format("x: %d, y: %d, count: %d", currentX, currentY, ans[currentX][currentY]));
            for (int i = 0; i < 4; i++) {
                int nx = currentX + dx[i];
                int ny = currentY + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 1) {
//                    System.out.println(String.format("x: %d, y: %d", ny, nx));
                    check[nx][ny] = true;
                    q.add(new Point(nx, ny));
//                    System.out.println(String.format("q size: %d", q.size()));
                    result[nx][ny] = result[currentX][currentY] + 1;
                }
            }
        }
    }
}
