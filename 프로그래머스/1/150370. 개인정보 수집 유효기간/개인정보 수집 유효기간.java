import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answers = new ArrayList();
        StringTokenizer st = new StringTokenizer(today, ".");
        long todayValue = Long.parseLong(st.nextToken()) * 12 * 28 + Long.parseLong(st.nextToken()) * 28 + Long.parseLong(st.nextToken());
    
        Map<String, Long> map = new HashMap<>();
        for(String term: terms) {
            st = new StringTokenizer(term);
            map.put(st.nextToken(), Long.parseLong(st.nextToken())*28);
        }
        
        for(int i=0; i<privacies.length; i++) {
            String privacy = privacies[i];
            st = new StringTokenizer(privacy);
            String start = st.nextToken();
            String termType = st.nextToken();
            
            st = new StringTokenizer(start, ".");
            long startValue = Long.parseLong(st.nextToken()) * 12 * 28 + Long.parseLong(st.nextToken()) * 28 + Long.parseLong(st.nextToken()) + map.get(termType) -1;
            
            if(todayValue > startValue) {
                answers.add(i+1);
            }
        }
        
        int[] answer = new int[answers.size()];
        for(int i=0; i<answers.size(); i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }
}