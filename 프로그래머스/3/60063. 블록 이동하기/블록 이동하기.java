import java.util.*;

class Solution {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] board) {
        int answer = 0;
        int size = board.length;
        boolean[][][] visited = new boolean[size][size][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 1, 0});
        visited[0][0][0] = true;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int x1 = current[0], y1 = current[1];
            int x2 = current[2], y2 = current[3];
            int distance = current[4];
            
            if(x2 == size-1 && y2 == size -1) {
                return distance;
            }
            
            for(int[] positions: getNextPositions(board, current, size)) {
                int horizontal = 0;
                if(positions[1]==positions[3]) horizontal = 1;
                if(!visited[positions[0]][positions[1]][horizontal]) {
                    queue.offer(new int[]{positions[0], positions[1], positions[2], positions[3], distance+1});
                    visited[positions[0]][positions[1]][horizontal] = true;
                }
            }
            
        }
        return answer;
    }
    
    private static List<int[]> getNextPositions(int[][] board, int[] current, int size) {
        List<int[]> positions = new ArrayList<>();
        int x1 = current[0], y1 = current[1];
        int x2 = current[2], y2 = current[3];
        for(int i=0; i<dx.length; i++) {
            int nx1 = x1 + dx[i], ny1 = y1 + dy[i];
            int nx2 = x2 + dx[i], ny2 = y2 + dy[i];
            if(nx1 >= 0 && nx1 < size && ny1 >= 0 && ny1 < size && nx2 >= 0 && nx2 <size && ny2 >= 0 && ny2 <size) {
                if(board[nx1][ny1] == 0 && board[nx2][ny2] == 0) {
                    positions.add(new int[]{nx1, ny1, nx2, ny2});
                }
            }
        }
        
        //수직인 경우 수평으로 만들기
        if(y1 == y2) {
            //왼쪽 이동
            if(y1 > 0) {
                if(board[x1][y1-1] == 0 && board[x2][y2-1] == 0) {
                    positions.add(new int[]{x2, y1-1, x2, y2});
                    positions.add(new int[]{x1, y1-1, x1, y1});
                }
            }
            
            //오른쪽 이동
            if(y1 < size -1) {
                if(board[x1][y1+1] == 0 && board[x2][y2+1] == 0) {
                    positions.add(new int[]{x1, y1, x1, y2+1});
                    positions.add(new int[]{x2, y2, x2, y2+1});
                }
            }
        }
        
        //수평인 경우 수직으로 만들기
        if(x1 == x2) {
            //위쪽 이동
            if(x1 > 0) {
                if(board[x1-1][y1] == 0 && board[x2-1][y2] == 0) {
                    positions.add(new int[]{x1-1, y1, x1, y1});
                    positions.add(new int[]{x2-1, y2, x2, y2});
                }
            }
            //아래쪽 이동
            if(x1 < size -1) {
                if(board[x1+1][y1] == 0 && board[x2+1][y2] == 0) {
                    positions.add(new int[]{x1, y1, x1+1, y1});
                    positions.add(new int[]{x2, y2, x2+1, y2});
                }
            }
        }
        return positions;
    }
}