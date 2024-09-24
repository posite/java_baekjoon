import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int length = numbers.length;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, numbers[0]});
        queue.add(new int[]{1, -numbers[0]});
        while(!queue.isEmpty()) {
            int[] current = queue.remove();
            if(current[0] == length) {
                if(current[1] == target) answer++;
                continue;
            }
            queue.add(new int[]{current[0]+1, current[1] + numbers[current[0]]});
            queue.add(new int[]{current[0]+1, current[1] - numbers[current[0]]});
        }
        return answer;
    }
    
    
}