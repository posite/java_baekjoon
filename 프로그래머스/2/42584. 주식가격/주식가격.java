import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0; i<length; i++) {
            while(!stack.isEmpty()) {
                int prevIndex = stack.peek();
                if(prices[prevIndex] > prices[i]) {
                    answer[prevIndex] = i - prevIndex;
                    stack.pop();
                    continue;
                } 
                break;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = length - index -1;
        }
        return answer;
    }
}