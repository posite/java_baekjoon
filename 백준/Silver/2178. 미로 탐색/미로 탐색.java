import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static boolean[][] check;
    private static int[][] arr;
    private static int node, line;
    private static Queue<Point> q = new LinkedList<>();
    private static int[][] ans;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            node = Integer.parseInt(st.nextToken());
            line = Integer.parseInt(st.nextToken());
            ans = new int[node + 1][line + 1];
            arr = new int[node + 1][line + 1];
            check = new boolean[node + 1][line + 1];
            for (int i = 0; i < node; i++) {
                String[] inputs = br.readLine().split("");
                for (int j = 0; j < line; j++) {
                    arr[i][j] = Integer.parseInt(inputs[j]);
                }
            }
            bfs(0, 0);
            sb.append(ans[node - 1][line - 1] + 1);
            bw.write(sb.toString());
            bw.flush();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bfs(int startX, int startY) {
        int currentX = startX;
        int currentY = startY;
        q.add(new Point(currentX, currentY));
        check[startX][startY] = true;
        while (!q.isEmpty()) {
            Point temp = q.poll();
            currentX = temp.x;
            currentY = temp.y;
            for (int i = 0; i < 4; i++) {
                int nx = currentX + dx[i];
                int ny = currentY + dy[i];
                if (nx < 0 || nx >= node || ny < 0 || ny >= line || check[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] == 1) {
                    check[nx][ny] = true;
                    q.add(new Point(nx, ny));
                    ans[nx][ny] = ans[currentX][currentY] + 1;
                }
            }
        }
    }

}