import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        int count = k;
        for(int i=0; i<number.length(); i++) {
            char current = number.charAt(i);
            if(stack.isEmpty()) {
                stack.push(current);
                continue;
            }
            while(count > 0 && !stack.isEmpty()) {
                if(current > stack.peek()) {
                    count--;
                    stack.pop();
                } else break;
            }
            stack.push(current);
            //System.out.println(stack);
        }
        while(stack.size() != number.length()-k) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<number.length()-k; i++) sb.insert(0, stack.pop());
        return sb.toString();
    }
}