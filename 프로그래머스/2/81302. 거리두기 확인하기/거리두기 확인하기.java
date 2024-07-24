import java.util.*;

class Solution {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Deque<int[]> queue = new ArrayDeque<>();
        outer: for(int i=0; i<5; i++) {
            String[] place = places[i];
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    if(place[j].charAt(k) == 'P') {
                        queue.clear();
                        queue.offer(new int[]{j, k, 0});
                        while(!queue.isEmpty()) {
                            int[] current = queue.poll();
                            if(place[current[0]].charAt(current[1]) == 'P' && current[2] != 0) {
                                answer[i] = 0;
                                continue outer;
                            }
                            if(current[2] == 2) continue;
                            for(int l=0; l<4; l++) {
                                int nx = current[0] + dx[l];
                                int ny = current[1] + dy[l];
                                if(nx == j && ny == k) continue;
                                if(nx >= 0 && nx < 5 && ny >=0 && ny < 5) {
                                    if(place[nx].charAt(ny) != 'X') {
                                        queue.offer(new int[]{nx, ny, current[2]+1});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            answer[i] = 1;
        }
        
        return answer;
    }
}