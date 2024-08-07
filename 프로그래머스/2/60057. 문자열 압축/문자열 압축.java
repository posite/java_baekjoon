import java.util.*;

class Solution {
    public int solution(String s) {
        if(s.length() < 2) {
            return s.length();
        }
        int answer = Integer.MAX_VALUE;
        int length = s.length();
        
        Deque<String> stack = new ArrayDeque<>();
        
        for(int i=1; i<=length/2; i++) {
            int currentLength = 0;
            int currentIndex = 0;
            
            while(length > currentIndex) {
                String currentString = "";
                if(currentIndex + i > length) currentString = s.substring(currentIndex);
                else currentString = s.substring(currentIndex, currentIndex + i);
                currentIndex += i;
                
                if(stack.isEmpty() || stack.peek().equals(currentString)) {
                    stack.push(currentString);
                    continue;
                }
                
                String beforeString = stack.peek();
                int beforeCount = stack.size();
                stack.clear();
                if(beforeCount > 1) currentLength += (Integer.toString(beforeCount).length() + i); 
                else currentLength += i;
                
                stack.push(currentString);
            }
            
            if(!stack.isEmpty()) {
                String lastString = stack.peek();
                int lastCount = stack.size();
                if(lastCount > 1) currentLength += (Integer.toString(lastCount).length() + lastString.length()); 
                else currentLength += lastString.length();
            }
            answer = Math.min(currentLength, answer);
            stack.clear();
        }
        return answer;
    }
}