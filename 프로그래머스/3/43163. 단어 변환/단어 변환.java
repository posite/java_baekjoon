import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean containTarget = false;
        for(String word: words) {
            if(word.equals(target)) containTarget = true;
        }
        int strLength = begin.length();
        boolean[] visited = new boolean[words.length];
        if(!containTarget) return 0;
        Deque<Entry> queue = new ArrayDeque<>();
        queue.add(new Entry(0, begin));
        while(!queue.isEmpty()) {
            Entry current = queue.remove();
            if(current.word.equals(target)) return current.count;
            for(int i=0; i<words.length; i++) {
                String word = words[i];
                if(word.equals(current.word)) continue;
                int diffCount = 0;
                for(int j=0; j<strLength; j++) {
                    if(word.charAt(j) != current.word.charAt(j)) diffCount++;
                }
                if(diffCount == 1 && !visited[i]) {
                    queue.add(new Entry(current.count+1, word));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
    
    public class Entry {
        int count;
        String word;
        
        public Entry(int count, String word) {
            this.count = count;
            this.word = word;
        }
    }
}