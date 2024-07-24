import java.util.*;

class Solution {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        int size = board.length;
        boolean[][][] visited = new boolean[size][size][2];
        visited[0][0][0] = true;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0, 1, 0});
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int x1 = current[0], y1 = current[1];
            int x2 = current[2], y2 = current[3];
            int distance = current[4];
            
            //도착 시 종료
            if (x2 == size - 1 && y2 == size - 1) {
                return distance;
            }
            
            for(int[] nextPosition: getNextPosition(current, board, size)) {
                int horizontal = 0;
                if(nextPosition[1] == nextPosition[3]) horizontal = 1;
                if(!visited[nextPosition[0]][nextPosition[1]][horizontal]) {
                    queue.add(new int[]{nextPosition[0], nextPosition[1], nextPosition[2], nextPosition[3], distance + 1});
                    visited[nextPosition[0]][nextPosition[1]][horizontal] = true;
                }
            }
            
        }
    
        return -1;
    }
    
    private static List<int[]> getNextPosition(int[] current, int[][] board, int size) {
        List<int[]> nextPositionList = new ArrayList<>();
        int x1 = current[0], y1 = current[1];
        int x2 = current[2], y2 = current[3];
        
        //전체를 상하좌우 이동
        for(int i=0; i<4; i++) {
            int nx1 = x1 + dx[i], ny1 = y1 + dy[i];
            int nx2 = x2 + dx[i], ny2 = y2 + dy[i];
            if (nx1 >= 0 && nx1 < size && nx2 >= 0 && nx2 < size && ny1 >= 0 && ny1 < size && ny2 >= 0 && ny2 < size) {
                if (board[nx1][ny1] == 0 && board[nx2][ny2] == 0) {
                    nextPositionList.add(new int[]{nx1, ny1, nx2, ny2});
                }
            }
        }
        
        //수직인 경우 회전
        if(y1 == y2) {
            if(y1 > 0) {
                if(board[x1][y1 -1] == 0 && board[x2][y1 - 1] == 0) {
                    nextPositionList.add(new int[]{x2, y2-1, x2, y2});
                    nextPositionList.add(new int[]{x1, y1-1, x1, y1});
                }
            }
            if(y1 < size -1){
                if(board[x1][y1 + 1] == 0 && board[x2][y2 + 1] == 0) {
                    nextPositionList.add(new int[]{x2, y2, x2, y2+1});
                    nextPositionList.add(new int[]{x1, y1, x1, y1+1});
                }
            } 
        }
        
        if(x1 == x2) {
            if(x1 > 0) {
                if(board[x1-1][y1] == 0 && board[x2-1][y2] == 0) {
                    nextPositionList.add(new int[]{x2-1, y2, x2, y2});
                    nextPositionList.add(new int[]{x1-1, y1, x1, y1});
                }
            }
            if(x1 < size -1){
                if(board[x1+1][y1] == 0 && board[x2+1][y2] == 0) {
                    nextPositionList.add(new int[]{x2, y2, x2+1, y2});
                    nextPositionList.add(new int[]{x1, y1, x1+1, y1});
                }
            } 
        }
        
        
        return nextPositionList;
    }
}