import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        int[][] visited = new int[n][n];
        ArrayList<Point> start = new ArrayList<>();
        ArrayList<Point> dest = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int carX = Integer.parseInt(st.nextToken()) - 1;
        int carY = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int destX = Integer.parseInt(st.nextToken());
            int destY = Integer.parseInt(st.nextToken());
            start.add(new Point(startX - 1, startY - 1));
            dest.add(new Point(destX - 1, destY - 1));
            /*map[startX - 1][startY - 1] = (i + 1) * 2;
            map[destX - 1][destY - 1] = (i + 1) * (-2);*/
        }
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(carX, carY));
        for (int i = 0; i < m; i++) {
            while (!queue.isEmpty()) {
                Point current = queue.poll();
                //System.out.println(current.x + " " + current.y);
                for (int j = 0; j < dx.length; j++) {
                    int nx = current.x + dx[j];
                    int ny = current.y + dy[j];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (map[nx][ny] == 1) continue;
                        if (visited[nx][ny] == 0) {
                            if (nx != carX || ny != carY) {
                                visited[nx][ny] = visited[current.x][current.y] + 1;
                                queue.add(new Point(nx, ny));
                                continue;
                            }
                        }
                        if (visited[nx][ny] > visited[current.x][current.y] + 1) {
                            visited[nx][ny] = visited[current.x][current.y] + 1;
                            queue.add(new Point(nx, ny));
                        }
                    }
                }
            }
            /*for (int[] times : visited) {
                for (int time : times) {
                    System.out.print(time + " ");
                }
                System.out.println();
            }
            System.out.println();*/
            int shortest = Integer.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < start.size(); j++) {
                Point startC = start.get(j);
                if (shortest == visited[startC.x][startC.y]) {
                    Point prev = start.get(index);
                    if (prev.x > startC.x) {
                        index = j;
                        continue;
                    }
                    if (prev.x == startC.x) {
                        if (prev.y > startC.y) {
                            index = j;
                            continue;
                        }
                    }
                    continue;
                }
                if (shortest > visited[startC.x][startC.y]) {
                    index = j;
                    shortest = visited[startC.x][startC.y];
                }
            }
            Point startPoint = start.get(index);
            if (shortest == 0) {
                if (startPoint.x != carX || startPoint.y != carY) {
                    //System.out.println("도착 불가");
                    System.out.println("-1");
                    return;
                }
            }
            //System.out.println("index: " + index);
            //System.out.println("start " + startPoint.x + " " + startPoint.y);
            Point destPoint = dest.get(index);
            carX = destPoint.x;
            carY = destPoint.y;
            //System.out.println("dest " + destPoint.x + " " + destPoint.y);
            visited = new int[n][n];
            queue.offer(startPoint);
            while (!queue.isEmpty()) {
                Point current = queue.poll();
                //System.out.println(current.x + " " + current.y);
                for (int j = 0; j < dx.length; j++) {
                    int nx = current.x + dx[j];
                    int ny = current.y + dy[j];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (map[nx][ny] == 1) continue;
                        if (visited[nx][ny] == 0) {
                            if (nx != startPoint.x || ny != startPoint.y) {
                                visited[nx][ny] = visited[current.x][current.y] + 1;
                                queue.add(new Point(nx, ny));
                                continue;
                            }
                        }
                        if (visited[nx][ny] > visited[current.x][current.y] + 1) {
                            visited[nx][ny] = visited[current.x][current.y] + 1;
                            queue.add(new Point(nx, ny));
                        }
                    }
                }
            }
            /*for (int[] times : visited) {
                for (int time : times) {
                    System.out.print(time + " ");
                }
                System.out.println();
            }
            System.out.println();*/
            if (visited[destPoint.x][destPoint.y] == 0) {
                //System.out.println("도착 불가");
                System.out.println("-1");
                return;
            }
            /*for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(visited[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println(shortest + " " + visited[dest.x][dest.y]);*/
            //System.out.println("to customer " + shortest + "  destination " + visited[destPoint.x][destPoint.y]);
            if (fuel < shortest + visited[destPoint.x][destPoint.y]) {
                //System.out.println("연료 부족");
                System.out.println("-1");
                return;
            }
            fuel = fuel - shortest + visited[destPoint.x][destPoint.y];
            start.remove(index);
            dest.remove(index);
            visited = new int[n][n];
            /*System.out.println();
            System.out.println("fuel " + fuel);
            System.out.println();*/
            queue.offer(destPoint.clone());
        }
        System.out.println(fuel);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        protected Point clone() {
            return new Point(x, y);
        }
    }
}