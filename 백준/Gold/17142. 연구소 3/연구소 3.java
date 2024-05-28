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
        ArrayList<Point> viruses = new ArrayList<>();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 2) {
                    viruses.add(new Point(i, j));
                }
                board[i][j] = value;
            }
        }
        activate(board, viruses, new ArrayList<Point>(), 0, 0, m);
        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
    
    private static void activate(int[][] board, ArrayList<Point> viruses, ArrayList<Point> activated, int start, int count, int max) {
        if(max == count) {
            int[][] boardCopy = new int[board.length][board.length];
            for(int i=0; i<board.length; i++) {
                boardCopy[i] = board[i].clone();
            }
            bfs(boardCopy, activated);
        }
        for(int i=start; i< viruses.size(); i++) {
            Point virus = viruses.get(i);
            activated.add(virus);
            activate(board, viruses, activated, i+1, count+1, max);
            activated.remove(activated.size() -1);
        }
    }
    
    private static void bfs(int[][] board, ArrayList<Point> activated) {
        int length = board.length;
        int[][] ans = new int[length][length];
        for (int[] an : ans) {
            Arrays.fill(an, Integer.MAX_VALUE);
        }
        Queue<Point> queue = new LinkedList<>();
        for(Point virus: activated) {
            ans[virus.x][virus.y] = 0;
            queue.offer(virus);
        }
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx>=0 && nx<length && ny >=0 && ny < length) {
                    if(board[nx][ny] == 1) continue;
                    if(ans[nx][ny] > ans[current.x][current.y] + 1) {
                        ans[nx][ny] = ans[current.x][current.y] + 1;
                        queue.offer(new Point(nx, ny));
                    }
                } 
            }
        }
        int time = 0;
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++) {
                if(ans[i][j] == Integer.MAX_VALUE) {
                    if(board[i][j] == 0) {
                        return;
                    }
                } else {
                   if(board[i][j] == 2) {
                       continue;
                   } 
                   if(ans[i][j] > time) {
                       time = ans[i][j];
                   }
                }
            }
        }
        if (min == -1) {
            min = time;
            return;
        }
        if(min > time) {
            min = time;
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