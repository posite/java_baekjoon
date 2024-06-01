import java.util.*;

class Solution {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};
        for(int i=0; i<5; i++) {
            bfs(places[i], answer, i);
        }
        return answer;
    }
    
    public void bfs(String[] place, int[] answer, int now) {
        int[][] visited = new int[5][5];
        Deque<Point> queue = new ArrayDeque<>();
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(place[i].charAt(j) == 'P') {
                    for(int k=0; k<5; k++) {
                        Arrays.fill(visited[k], 10);
                    }
                    visited[i][j] = 0;
                    queue.offer(new Point(i, j));
                    while(!queue.isEmpty()) {
                        Point current = queue.poll();
                        //System.out.println(current.x + " " + current.x + " " + now);
                        if(visited[current.x][current.y] <= 2) {
                            if(place[current.x].charAt(current.y) == 'P') {
                                if(current.x != i || current.y != j) {
                                    answer[now] = 0;
                                    return;
                                }  
                            }
                        } else {
                            continue;
                        }
                        for(int k=0; k<dx.length; k++) {
                            int nx = current.x + dx[k];
                            int ny = current.y + dy[k];
                            if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                                if(place[nx].charAt(ny) != 'X') {
                                    if(visited[nx][ny] > visited[current.x][current.y] + 1) {
                                        visited[nx][ny] = visited[current.x][current.y] + 1;
                                        queue.offer(new Point(nx, ny));
                                    }
                                }
                            }
                        }
                    }
                }
            }
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