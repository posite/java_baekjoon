import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        boolean[][][][] visited = new boolean[x][y][x][y];

        char[][] board = new char[x][y];
        Point red = null, blue = null;
        for (int i = 0; i < x; i++) {
            String line = br.readLine();
            for (int j = 0; j < y; j++) {
                char current = line.charAt(j);
                board[i][j] = current;
                if (current == 'B') {
                    blue = new Point(i, j);
                }
                if (current == 'R') {
                    red = new Point(i, j);
                }
            }
        }

        Deque<State> queue = new ArrayDeque<>();
        queue.add(new State(red, blue, 0));
        while (!queue.isEmpty()) {
            State current = queue.remove();
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

                if (isRedEscape && !isBlueEscape) {
                    System.out.println(1);
                    return;
                }
                if (!isBlueEscape && !visited[nRed.x][nRed.y][nBlue.x][nBlue.y]) {
                    visited[nRed.x][nRed.y][nBlue.x][nBlue.y] = true;
                    queue.add(new State(nRed, nBlue, current.count + 1));
                }
            }
        }
        System.out.println(0);
    }

    public static int calculateDistance(Point before, Point after) {
        return Math.abs(before.x - after.x) + Math.abs(before.y - after.y);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class State {
        Point red, blue;
        int count;

        public State(Point red, Point blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }
}