import java.awt.*;
import java.io.*;
import java.util.Queue;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int loop = Integer.parseInt(st.nextToken());
        ArrayList<Point> board = new ArrayList();
        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board.add(new Point(x, y));
        }
        int[][] visited = new int[2000][2000];
        for (Point point : board) {
            Queue<Point> q = new LinkedList<>();
            for (int[] ints : visited) {
                Arrays.fill(ints, Integer.MAX_VALUE);
            }
            visited[point.x][point.y] = 0;
            ArrayList<Integer> ans = new ArrayList<>();
            q.add(point);
            while (!q.isEmpty()) {
                Point temp = q.poll();
                if (temp.x == temp.y) {
                    ans.add(visited[temp.x][temp.y]);
                    continue;
                }
                if (temp.x * 2 <= temp.y + 3) {
                    if (visited[temp.x * 2][temp.y + 3] > visited[temp.x][temp.y] + 1) {
                        q.add(new Point(temp.x * 2, temp.y + 3));
                        visited[temp.x * 2][temp.y + 3] = visited[temp.x][temp.y] + 1;
                    }
                }

                if (visited[temp.x + 1][temp.y] > visited[temp.x][temp.y] + 1) {
                    q.add(new Point(temp.x + 1, temp.y));
                    visited[temp.x + 1][temp.y] = visited[temp.x][temp.y] + 1;
                }
            }
            int min = Collections.min(ans);
            bw.write(Integer.toString(min));
            bw.newLine();
            bw.flush();
        }
    }
}