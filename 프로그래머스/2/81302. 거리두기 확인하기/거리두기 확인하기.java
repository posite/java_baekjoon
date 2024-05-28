import java.util.*;

class Solution {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};
        int[][] visited = new int[5][5];
        int placeIndex = 0;
        Queue<Point> queue = new LinkedList<>();
        for(String[] place: places) {
            //System.out.println(placeIndex);
            for(int i=0; i<5; i++) {
                if (answer[placeIndex] == 0) {
                    break;
                }
                for(int j=0; j<5; j++) {
                    if(answer[placeIndex] == 0) {
                        break;
                    }
                    if(place[i].charAt(j)=='P') {
                        //System.out.println(i + " " + j);
                        visited = new int[5][5];
                        queue.clear();
                        queue.offer(new Point(i, j));
                        while(!queue.isEmpty()) {
                            Point current = queue.poll();
                            //System.out.println(current.x + " " + current.y + " " + visited[current.x][current.y]);
                            if(visited[current.x][current.y] <=2) {
                                if (current.x != i || current.y != j) {
                                   if(place[current.x].charAt(current.y) == 'P') {
                                        answer[placeIndex] = 0;
                                        break;
                                    } 
                                }
                                
                            } else {
                                continue;
                            }
                            for(int k=0; k<4; k++) {
                                int nx = current.x + dx[k];
                                int ny = current.y + dy[k];
                                if(nx>=0 && nx<5 && ny>=0 && ny<5) {
                                    if(place[nx].charAt(ny) != 'X') {
                                        if(nx != i || ny != j) {
                                            if(visited[current.x][current.y] <= 1) {
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
            placeIndex++;
        }
        return answer;
    }
    
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}