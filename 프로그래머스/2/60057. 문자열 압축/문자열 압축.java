import java.util.*;

class Solution {
    public int solution(String s) {
        int length = s.length();
        if(length < 2) return length;
        int answer = length;
        for(int i=1; i<=length/2; i++) {
            Deque<String> stack = new ArrayDeque<>();
            int currentIndex = 0;
            int currentLength = 0;
            while(length > currentIndex) {
                String currentString = "";
                if(length > currentIndex + i) currentString = s.substring(currentIndex, currentIndex + i);
                else currentString = s.substring(currentIndex);
                currentIndex += i;
                if(stack.isEmpty() || currentString.equals(stack.peek())) {
                    stack.push(currentString);
                    continue;
                }
                
                int size = stack.size();
                String beforeString = stack.pop();
                stack.clear();
                stack.add(currentString);
                if(size > 1) currentLength += (beforeString.length() + Integer.toString(size).length());
                else currentLength += beforeString.length();
                
            }
            if(!stack.isEmpty()) {
                int size = stack.size();
                String beforeString = stack.pop();
                stack.clear();
                if(size > 1) currentLength += (beforeString.length() + Integer.toString(size).length());
                else currentLength += beforeString.length();
            }
            answer = Math.min(answer, currentLength);
        }
        return answer;
    }
}