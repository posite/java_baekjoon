import java.util.*;
import java.io.*;

public class Main{
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        char[][] board = new char[x][y];
        boolean[][][][] visited = new boolean[x][y][x][y];

        Point red = null, blue = null;
        for (int i = 0; i < x; i++) {
            String row = br.readLine();
            for (int j = 0; j < y; j++) {
                if (row.charAt(j) == 'R') {
                    red = new Point(i, j);
                    board[i][j] = '.';
                } else if (row.charAt(j) == 'B') {
                    blue = new Point(i, j);
                    board[i][j] = '.';
                } else {
                    board[i][j] = row.charAt(j);
                }
            }
        }

        visited[red.x][red.y][blue.x][blue.y] = true;
        Deque<State> queue = new ArrayDeque<>();
        queue.offer(new State(red, blue, 0));
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.count == 10) continue;
            red = current.red;
            blue = current.blue;
            for (int i = 0; i < 4; i++) {
                Point nRed = new Point(red.x + dx[i], red.y + dy[i]);
                Point nBlue = new Point(blue.x + dx[i], blue.y + dy[i]);
                boolean isRedEscape = false, isBlueEscape = false;
                while (board[nRed.x][nRed.y] != '#') {
                    if (board[nRed.x][nRed.y] == 'O') {
                        nRed.x += dx[i];
                        nRed.y += dy[i];
                        isRedEscape = true;
                        break;
                    }
                    nRed.x += dx[i];
                    nRed.y += dy[i];
                }

                while (board[nBlue.x][nBlue.y] != '#') {
                    if (board[nBlue.x][nBlue.y] == 'O') {
                        nBlue.x += dx[i];
                        nBlue.y += dy[i];
                        isBlueEscape = true;
                        break;
                    }
                    nBlue.x += dx[i];
                    nBlue.y += dy[i];
                }

                nRed.x -= dx[i];
                nRed.y -= dy[i];
                nBlue.x -= dx[i];
                nBlue.y -= dy[i];

                if (nRed.x == nBlue.x && nRed.y == nBlue.y) {
                    if (calculateDistance(red, nRed) > calculateDistance(blue, nBlue)) {
                        nRed.x -= dx[i];
                        nRed.y -= dy[i];
                    } else {
                        nBlue.x -= dx[i];
                        nBlue.y -= dy[i];
                    }
                }

                State nextState = new State(nRed, nBlue, current.count + 1);
                if (isRedEscape && !isBlueEscape) {
                    System.out.println('1');
                    return;
                }
                if (!isBlueEscape && !visited[nRed.x][nRed.y][nBlue.x][nBlue.y]) {
                    visited[nRed.x][nRed.y][nBlue.x][nBlue.y] = true;
                    queue.add(nextState);
                }
            }
        }
        System.out.print("0");
    }

    private static int calculateDistance(Point before, Point after) {
        return Math.abs(before.x - after.x) + Math.abs(before.y - after.y);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class State {
        Point red, blue;
        int count;

        public State(Point red, Point blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }
}
