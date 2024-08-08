import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[][] board = new int[x][y];
        List<Point> viruses = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    viruses.add(new Point(i, j));
                }
            }
        }
        //System.out.println(viruses);
        selectWall(board, viruses, new ArrayList<>(), 0, 0);
        System.out.println(max);
    }

    public static void selectWall(int[][] board, List<Point> viruses, List<Point> walls, int x, int y) {
        if (walls.size() == 3) {
            //System.out.println(walls);
            int[][] copy = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
            }
            bfs(copy, viruses);
            return;
        }
        for (int i = x; i < board.length; i++) {
            if (i == x) {
                for (int j = y; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        walls.add(new Point(i, j));
                        board[i][j] = 1;
                        selectWall(board, viruses, walls, i, j);
                        board[i][j] = 0;
                        walls.remove(walls.size() - 1);
                    }
                }
            } else {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        walls.add(new Point(i, j));
                        board[i][j] = 1;
                        selectWall(board, viruses, walls, i, j);
                        board[i][j] = 0;
                        walls.remove(walls.size() - 1);
                    }
                }
            }
        }
    }

    public static void bfs(int[][] board, List<Point> viruses) {
        Deque<Point> queue = new ArrayDeque<>();
        for (Point virus : viruses) queue.add(virus);

        while (!queue.isEmpty()) {
            Point current = queue.remove();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length) {
                    if (board[nx][ny] == 0) {
                        board[nx][ny] = 2;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
        int area = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    area++;
                }
            }
        }
        max = Math.max(max, area);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }
}