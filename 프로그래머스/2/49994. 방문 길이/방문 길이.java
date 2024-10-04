import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int min = -5, max = 5;
        int currentX = 0, currentY = 0;
        boolean[][][][] visited = new boolean[11][11][11][11];
        Deque<Dir> queue = new ArrayDeque<>();
        for(int i=0; i<dirs.length(); i++) {
            char c = dirs.charAt(i);
            if(c == 'U') {
                queue.add(new Dir(1,0));
            } else if(c == 'D') {
                queue.add(new Dir(-1,0));
            } else if(c == 'R') {
                queue.add(new Dir(0,1));
            } else if(c == 'L') {
                queue.add(new Dir(0,-1));
            }
        }
        while(!queue.isEmpty()) {
            Dir current = queue.remove();
            int nx = currentX + current.dx, ny = currentY + current.dy;
            //System.out.println(currentX + " " + currentY + " " + nx + " " + ny);
            if(nx > max || nx <min || ny > max || ny < min) continue;
            if(visited[currentX+5][currentY+5][nx+5][ny+5] || visited[nx+5][ny+5][currentX+5][currentY+5]) {
                currentX = nx;
                currentY = ny;
                continue;
            }
            //System.out.println(currentX + " " + currentY + " " + nx + " " + ny);
            visited[currentX+5][currentY+5][nx+5][ny+5] = true;
            currentX = nx;
            currentY = ny;
            answer++;
        }
        return answer;
    }
    
    public class Dir {
        int dx, dy;
        
        public Dir(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
}