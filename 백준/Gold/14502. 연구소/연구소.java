import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Point> viruses = new ArrayList();
        int[][] board = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                int current = Integer.parseInt(st.nextToken());
                board[i][j] = current;
                if(current == 2) {
                    viruses.add(new Point(i, j));
                }
            }
        }
        block(board, 0, 0, 0, viruses);
        
        System.out.println(max);
    }
    
    private static void block(int[][] board, int x, int y, int count, ArrayList<Point> viruses) {
        if(count == 3) {
            int[][] boardCopy = new int[board.length][board[0].length];
            for(int i=0; i<board.length; i++) {
                boardCopy[i] = board[i].clone();
            }
            bfs(boardCopy, viruses);
            return;
        }
        for(int i=x; i<board.length; i++) {
            if(i==x) {
                for(int j=y; j<board[0].length; j++) {
                    if(board[i][j] == 0) {
                        board[i][j] = 1;
                        block(board, i, j, count+1, viruses);
                        board[i][j] = 0;
                    }
                }
            } else {
                for(int j=0; j<board[0].length; j++) {
                    if(board[i][j] == 0) {
                        board[i][j] = 1;
                        block(board, i, j, count+1, viruses);
                        board[i][j] = 0;
                    }
                }
            }
        }
    }
    
    private static void bfs(int[][] board, ArrayList<Point> viruses) {
        int row = board.length;
        int column = board[0].length;
        int count = 0;
        Queue<Point> queue = new LinkedList<>();
        for(Point virus: viruses) {
            queue.offer(virus);
        }
        while(!queue.isEmpty()) {
            Point virus = queue.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];
                if(nx>=0 && nx < row && ny >=0 && ny<column) {
                    if(board[nx][ny] == 0) {
                        board[nx][ny] = 2;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
        
        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                if(board[i][j]==0) {
                    count++;
                }
            }
        }
        if(count > max) {
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