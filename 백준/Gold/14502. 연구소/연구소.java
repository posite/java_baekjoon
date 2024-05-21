import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int max = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        block(board, 0, 0, 0);
        System.out.println(max);
    }

    private static void block(int[][] board, int count, int x, int y) {
        //System.out.println(x + " " + y + " " + count);
        if (count == 3) {
            int[][] temp = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                temp[i] = board[i].clone();
            }
            bfs(temp);
            return;
        }
        for (int i = x; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    block(board, count + 1, i, j);
                    board[i][j] = 0;
                }
            }
        }
    }

    private static void bfs(int[][] board) {
        int row = board.length;
        int column = board[0].length;
        int count = 0;
        /*
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        */
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 2) {
                    queue.offer(new Point(i, j));
                    board[i][j] = 3;
                    while (!queue.isEmpty()) {
                        Point current = queue.poll();
                        for (int k = 0; k < dx.length; k++) {
                            int nx = current.x + dx[k];
                            int ny = current.y + dy[k];
                            if (nx >= 0 && nx < row && ny >= 0 && ny < column) {
                                if (board[nx][ny] == 0) {
                                    board[nx][ny] = 3;
                                    queue.offer(new Point(nx, ny));
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(board));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        if (count > max) {
            max = count;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}