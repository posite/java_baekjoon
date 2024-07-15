import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0; i<prices.length; i++) {
            //System.out.println(stack);
            if(stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int before = stack.pop();
            answer[before] = prices.length - before - 1;
        }
        return answer;
    }
}