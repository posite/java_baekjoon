import java.util.*;
import java.io.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board;
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int virusCount = Integer.parseInt(st.nextToken());
            board = new int[size][size];
            List<Point> viruses = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 2) {
                        viruses.add(new Point(i, j));
                        board[i][j] = 3;
                    }
                }
            }
            selectVirus(board, viruses, new ArrayList<>(), virusCount, 0);
            if (min == Integer.MAX_VALUE) {
                System.out.println("-1");
            } else {
                System.out.println(min);
            }
        } catch (IOException e) {

        }
    }

    public static void selectVirus(int[][] board, List<Point> viruses, List<Point> now, int virusCount, int start) {
        if (now.size() == virusCount) {
            int[][] copy = new int[board.length][board[0].length];
            for (int i = 0; i < copy.length; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, copy[0].length);
            }
            activateVirus(copy, now);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            now.add(viruses.get(i));
            selectVirus(board, viruses, now, virusCount, i + 1);
            now.remove(now.size() - 1);
        }
    }

    public static void activateVirus(int[][] board, List<Point> now) {
        int time = 0;
        int length = board.length;
        Deque<Point> queue = new ArrayDeque<>();
        int[][] results = new int[length][length];
        for (Point virus : now) {
            board[virus.x][virus.y] = 2;
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && nx < length && ny >= 0 && ny < length) {
                    if (board[nx][ny] == 1 || board[nx][ny] == 2) continue;
                    if (results[nx][ny] > results[current.x][current.y] + 1 || results[nx][ny] == 0) {
                        results[nx][ny] = results[current.x][current.y] + 1;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 0) {
                    if (results[i][j] == 0) {
                        return;
                    }
                }
                if (board[i][j] == 3) continue;
                time = Math.max(time, results[i][j]);
            }
        }
        /*for (int i = 0; i < length; i++) {
            System.out.println(Arrays.toString(results[i]));
        }
        System.out.println();*/
        min = Math.min(time, min);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}