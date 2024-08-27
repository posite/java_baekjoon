import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st = new StringTokenizer(today, ".");
        Map<String, Long> types = new HashMap<>();
        long todayValue = Long.parseLong(st.nextToken()) * 12 * 28 + Long.parseLong(st.nextToken()) * 28 + Long.parseLong(st.nextToken());
        for(String term: terms) {
            st = new StringTokenizer(term);
            types.put(st.nextToken(), Long.parseLong(st.nextToken()));
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<privacies.length; i++) {
            String privacy = privacies[i];
            st = new StringTokenizer(privacy);
            String day = st.nextToken();
            String type = st.nextToken();
            st = new StringTokenizer(day, ".");
            long privacyValue = Long.parseLong(st.nextToken()) * 12 * 28 + Long.parseLong(st.nextToken()) * 28 + Long.parseLong(st.nextToken()) + types.get(type) * 28;
            if(todayValue >= privacyValue) {
                list.add((i+1));
            }
        }
        
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) answer[i] = list.get(i);
        return answer;
    }
}