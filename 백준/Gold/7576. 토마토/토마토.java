import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][m];

            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] inputs = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    int tomato = Integer.parseInt(inputs[j]);
                    arr[i][j] = tomato;
                    if (tomato == 1) {
                        points.add(new Point(i, j));
                    }
                }
            }

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            int[][] ans = new int[n][m];
            Queue<Point> q = new LinkedList<>();
            for (Point p : points) {
                ans[p.x][p.y] = 0;

                q.add(p);
            }

            while (!q.isEmpty()) {
                Point temp = q.poll();
                int currentX = temp.x;
                int currentY = temp.y;
                for (int i = 0; i < 4; i++) {
                    int nx = currentX + dx[i];
                    int ny = currentY + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] == 1 || arr[nx][ny] == -1) {
                        continue;
                    }
                    if (arr[nx][ny] == 0) {
                        arr[nx][ny] = 1;
                        q.add(new Point(nx, ny));
                        ans[nx][ny] = ans[currentX][currentY] + 1;
                    }
                }
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 0) {
                        System.out.println("-1");
                        return;
                    }
                    if (ans[i][j] > max) {
                        max = ans[i][j];
                    }
                }
            }
            bw.write(Integer.toString(max));
            bw.flush();
            bw.close();
        } catch (IOException e) {

        }
    }
}