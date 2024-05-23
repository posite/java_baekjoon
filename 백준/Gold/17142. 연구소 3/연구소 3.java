import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int max = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Point> viruses = new ArrayList<>();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 2) {
                    board[i][j] = 3;
                    viruses.add(new Point(i, j));
                    continue;
                }
                board[i][j] = value;
            }
        }
        activate(board, viruses, 0, 0, m);
        if (max == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    private static void activate(int[][] board, ArrayList<Point> viruses, int start, int count, int max) {
        if (count == max) {
            int[][] temp = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                temp[i] = board[i].clone();
            }
            bfs(temp);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            Point virus = viruses.get(i);
            board[virus.x][virus.y] = 2;
            activate(board, viruses, i + 1, count + 1, max);
            board[virus.x][virus.y] = 3;
        }
    }

    private static void bfs(int[][] board) {
        int size = board.length;
        int[][] ans = new int[size][size];
        for (int[] an : ans) {
            Arrays.fill(an, Integer.MAX_VALUE);
        }
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 2) {
                    ans[i][j] = 0;
                    queue.offer(new Point(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int k = 0; k < dx.length; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];
                if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                    if (board[nx][ny] == 1) continue;
                    if (ans[nx][ny] > ans[current.x][current.y] + 1) {
                        //board[nx][ny] = 2;
                        if (board[nx][ny] == 3) {
                            board[nx][ny] = 2;
                            ans[nx][ny] = ans[current.x][current.y] + 1;
                            queue.offer(new Point(nx, ny));
                            continue;
                        }
                        queue.offer(new Point(nx, ny));
                        ans[nx][ny] = ans[current.x][current.y] + 1;
                    }
                }
            }
        }
       /* for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
        int cur = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (ans[i][j] == Integer.MAX_VALUE) {
                    if (board[i][j] == 0) {
                        //System.out.println();
                        return;
                    }
                    //System.out.print("* ");
                } else {
                    if (board[i][j] == 2) {
                        continue;
                    }
                    if (ans[i][j] > cur) {
                        cur = ans[i][j];
                    }
                    //System.out.print(ans[i][j] + " ");
                }
            }
            //System.out.println();
        }
        /*for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (ans[i][j] == Integer.MAX_VALUE) {
                    System.out.print("* ");
                } else {
                    System.out.print(ans[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println(cur);
        System.out.println();*/
        if (max == -1) {
            max = cur;
            return;
        }
        if (max > cur) {
            max = cur;
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