import java.util.*;
import java.io.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int numOfCustomer = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        int[][] board = new int[size][size];
        int[][] distances = new int[size][size];
        List<Point> customerStartPoint = new ArrayList<>();
        List<Point> customerDestinationPoint = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) - 1;
        int startY = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < numOfCustomer; i++) {
            st = new StringTokenizer(br.readLine());
            int cStartX = Integer.parseInt(st.nextToken()) - 1;
            int cStartY = Integer.parseInt(st.nextToken()) - 1;
            customerStartPoint.add(new Point(cStartX, cStartY));

            int cDestinationX = Integer.parseInt(st.nextToken()) - 1;
            int cDestinationY = Integer.parseInt(st.nextToken()) - 1;
            customerDestinationPoint.add(new Point(cDestinationX, cDestinationY));
        }

        Deque<Point> queue = new ArrayDeque<>();

        for (int i = 0; i < numOfCustomer; i++) {
            distances = new int[size][size];
            queue = new ArrayDeque<>();
            queue.offer(new Point(startX, startY));
            while (!queue.isEmpty()) {
                Point current = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = current.x + dx[j];
                    int ny = current.y + dy[j];
                    if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                        if (board[nx][ny] == 1) continue;
                        if (nx == startX && ny == startY) continue;
                        if (distances[nx][ny] == 0 || distances[nx][ny] > distances[current.x][current.y] + 1) {
                            distances[nx][ny] = distances[current.x][current.y] + 1;
                            queue.offer(new Point(nx, ny));
                        }
                    }
                }
            }
            /*for (int j = 0; j < size; j++) {
                System.out.println(Arrays.toString(distances[j]));
            }*/

            Point nextStart = null;
            Point nextDestination;
            int index = -1;
            int distance = Integer.MAX_VALUE;
            for (int j = 0; j < customerStartPoint.size(); j++) {
                Point customer = customerStartPoint.get(j);
                if (distance > distances[customer.x][customer.y]) {
                    nextStart = customer;
                    index = j;
                    distance = distances[customer.x][customer.y];
                    continue;
                }
                if (distance == distances[customer.x][customer.y]) {
                    if (nextStart.x > customer.x) {
                        nextStart = customer;
                        index = j;
                        continue;
                    }
                    if (nextStart.x == customer.x && nextStart.y > customer.y) {
                        nextStart = customer;
                        index = j;
                    }
                }
            }
            //System.out.println(distance);
            if (distance == 0) {
                if (nextStart.x != startX && nextStart.y != startY) {
                    System.out.println("-1");
                    return;
                }
            }
            startX = nextStart.x;
            startY = nextStart.y;
            nextDestination = customerDestinationPoint.get(index);
            distances = new int[size][size];
            queue = new ArrayDeque<>();
            queue.offer(new Point(startX, startY));
            while (!queue.isEmpty()) {
                Point current = queue.poll();
                if (current.x == nextDestination.x && current.y == nextDestination.y) break;
                for (int j = 0; j < 4; j++) {
                    int nx = current.x + dx[j];
                    int ny = current.y + dy[j];
                    if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                        if (board[nx][ny] == 1) continue;
                        if (nx == startX && ny == startY) continue;
                        if (distances[nx][ny] == 0 || distances[nx][ny] > distances[current.x][current.y] + 1) {
                            distances[nx][ny] = distances[current.x][current.y] + 1;
                            queue.offer(new Point(nx, ny));
                        }
                    }
                }
            }
            if (distances[nextDestination.x][nextDestination.y] == 0) {
                System.out.println("-1");
                return;
            }
            if (fuel < distance + distances[nextDestination.x][nextDestination.y]) {
                //System.out.println("연료 부족");
                System.out.println("-1");
                return;
            }
            fuel = fuel - distance + distances[nextDestination.x][nextDestination.y];
            startX = nextDestination.x;
            startY = nextDestination.y;
            customerStartPoint.remove(index);
            customerDestinationPoint.remove(index);
        }
        System.out.println(fuel);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}