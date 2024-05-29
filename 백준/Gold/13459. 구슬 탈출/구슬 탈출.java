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
        char[][] board = new char[n][m];
        Point red = null;
        Point blue = null;
        boolean[][][][] visited = new boolean[n][m][n][m];
        for(int i=0; i<n; i++) {
            String token = new StringTokenizer(br.readLine()).nextToken();
            for(int j=0; j<m; j++) {
                if(token.charAt(j) == 'R') {
                    red = new Point(i, j);
                    board[i][j] = '.';
                    continue;
                }
                if(token.charAt(j) == 'B') {
                    blue = new Point(i, j);
                    board[i][j] = '.';
                    continue;
                }
                board[i][j] = token.charAt(j);
            }
        }
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(red, blue, 0));
        visited[red.x][red.y][blue.x][blue.y] = true;
        while(!queue.isEmpty()) {
            State current = queue.poll();
            if(current.count == 10) {
                continue;
            }
            red = current.red;
            blue = current.blue;
            for(int i=0; i<4; i++) {
                Point nRed = new Point(red.x + dx[i], red.y + dy[i]);
                Point nBlue = new Point(blue.x + dx[i], blue.y + dy[i]);
                boolean isRedEscape = false, isBlueEscape = false;
                while(board[nRed.x][nRed.y] != '#') {
                    if(board[nRed.x][nRed.y] == 'O') {
                        nRed.x += dx[i];
                        nRed.y += dy[i];
                        isRedEscape = true;
                        break;
                    }
                    nRed.x += dx[i];
                    nRed.y += dy[i];
                }
                
                while(board[nBlue.x][nBlue.y] != '#') {
                    if(board[nBlue.x][nBlue.y] == 'O') {
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
                
                if(nRed.x == nBlue.x && nRed.y == nBlue.y) {
                    if(getDistance(red, nRed) > getDistance(blue, nBlue)) {
                        nRed.x -= dx[i];
                        nRed.y -= dy[i];
                    } else {
                        nBlue.x -= dx[i];
                        nBlue.y -= dy[i];
                    }
                }
                State nextState = new State(nRed, nBlue, current.count +1);
                if(isRedEscape && !isBlueEscape) {
                    System.out.print("1");
                    return;
                } else if (!isBlueEscape && !visited[nRed.x][nRed.y][nBlue.x][nBlue.y]) {
                    visited[nRed.x][nRed.y][nBlue.x][nBlue.y] = true;
                    queue.add(nextState);
                }
            }
        }
        System.out.print("0");
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