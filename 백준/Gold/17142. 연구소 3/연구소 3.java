import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];
        List<Point> viruses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int stat = Integer.parseInt(st.nextToken());
                if (stat == 2) {
                    viruses.add(new Point(i, j));
                    board[i][j] = 3;
                } else {
                    board[i][j] = stat;
                }
            }
        }
        selectVirus(new ArrayList<>(), viruses, board, n, m, 0);
        if (min == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(min);
        }

    }

    public static void selectVirus(List<Point> now, List<Point> viruses, int[][] board, int n, int m, int x) {
        if (now.size() == m) {
            int[][] copy = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
            }
            bfs(now, copy, n);
            return;
        }

        for (int i = x; i < viruses.size(); i++) { 
            Point choice = viruses.get(i);
            now.add(choice);
            board[choice.x][choice.y] = 2;
            selectVirus(now, viruses, board, n, m, i + 1);
            now.remove(now.size() - 1);
            board[choice.x][choice.y] = 3;
        }
    }

    public static void bfs(List<Point> now, int[][] board, int n) {
        int[][] times = new int[n][n];

        Deque<Point> queue = new ArrayDeque<>();
        for (Point virus : now) queue.add(virus);

        while (!queue.isEmpty()) {
            Point current = queue.remove();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (board[nx][ny] == 1 || board[nx][ny] == 2) continue;
                    if (times[nx][ny] == 0 || times[nx][ny] > times[current.x][current.y] + 1) {
                        times[nx][ny] = times[current.x][current.y] + 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }


        int time = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    if (times[i][j] == 0) return;
                    time = Math.max(times[i][j], time);
                }
            }
        }
        min = Math.min(min, time);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
