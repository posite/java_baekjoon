import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Long> typeMap = new HashMap<>();
        StringTokenizer st;
        for(String term: terms) {
            st = new StringTokenizer(term);
            typeMap.put(st.nextToken(), Long.parseLong(st.nextToken()));
        }
        st = new StringTokenizer(today, ".");
        long todayValue = Long.parseLong(st.nextToken()) * 28 * 12 + Long.parseLong(st.nextToken()) * 28 + Long.parseLong(st.nextToken());
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String date = st.nextToken();
            String type = st.nextToken();
            st = new StringTokenizer(date, ".");
            
            long dateValue = Long.parseLong(st.nextToken()) * 28 * 12 + Long.parseLong(st.nextToken()) * 28 + Long.parseLong(st.nextToken()) + typeMap.get(type) * 28;
            if(todayValue >= dateValue) {
                list.add(i+1);
            }
        }
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}