import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Point red = null;
        Point blue = null;
        Set<State> visited = new HashSet<>();
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String content = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = content.charAt(j);
                if (content.charAt(j) == 'R') {
                    board[i][j] = '.';
                    red = new Point(i, j);
                } else if (content.charAt(j) == 'B') {
                    blue = new Point(i, j);
                    board[i][j] = '.';
                } else if (content.charAt(j) == 'O') {
                    board[i][j] = content.charAt(j);
                }
            }
        }
        //System.out.println(red.x + " " + red.y + " " + blue.x + " " + blue.y);
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(red, blue, 0));
        visited.add(queue.peek());
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.count == 10) {
                continue;
            }
            //System.out.println(current.red.x + " " + current.red.y + " " + current.blue.x + " " + current.blue.y + " " + current.count);
            red = current.red;
            blue = current.blue;
            for (int i = 0; i < dx.length; i++) {
                Point nRed = new Point(red.x + dx[i], red.y + dy[i]);
                Point nBlue = new Point(blue.x + dx[i], blue.y + dy[i]);
                boolean isRedOut = false, isBlueOut = false;

                while (board[nRed.x][nRed.y] != '#') {
                    if (board[nRed.x][nRed.y] == 'O') {
                        nRed.x += dx[i];
                        nRed.y += dy[i];
                        isRedOut = true;
                        break;
                    }
                    nRed.x += dx[i];
                    nRed.y += dy[i];
                }

                while (board[nBlue.x][nBlue.y] != '#') {
                    if (board[nBlue.x][nBlue.y] == 'O') {
                        nBlue.x += dx[i];
                        nBlue.y += dy[i];
                        isBlueOut = true;
                        break;
                    }
                    nBlue.x += dx[i];
                    nBlue.y += dy[i];
                }

                //이동했을 때 벽일 수 있으므로 좌표 복원
                nRed.x -= dx[i];
                nRed.y -= dy[i];
                nBlue.x -= dx[i];
                nBlue.y -= dy[i];

                //위치가 같을 때 멀리서 온 공(뒤에 있던 공)을 한 칸 뒤로 가게 한다
                if (nRed.x == nBlue.x && nRed.y == nBlue.y) {
                    if (getDistance(red, nRed) > getDistance(blue, nBlue)) {
                        nRed.x -= dx[i];
                        nRed.y -= dy[i];
                    } else {
                        nBlue.x -= dx[i];
                        nBlue.y -= dy[i];
                    }
                }

                State next = new State(nRed, nBlue, current.count + 1);
                if (isRedOut && !isBlueOut) {
                    System.out.println(1);
                    return;
                } else if (!isBlueOut && !visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        System.out.println(0);
    }

    private static int getDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
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
