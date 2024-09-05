import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Long> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(today, ".");
        long todayValue = Long.parseLong(st.nextToken()) * 28* 12 + Long.parseLong(st.nextToken())*28 + Long.parseLong(st.nextToken());
        System.out.println(todayValue);
        for(String term: terms) {
            st = new StringTokenizer(term);
            map.put(st.nextToken(), Long.parseLong(st.nextToken()));
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<privacies.length; i++) {
            st = new StringTokenizer(privacies[i]);
            String privacyDay = st.nextToken();
            String term = st.nextToken();
            st = new StringTokenizer(privacyDay, ".");
            
            long privacyValue = Long.parseLong(st.nextToken()) * 28* 12 + Long.parseLong(st.nextToken())*28 + Long.parseLong(st.nextToken()) + map.get(term)*28 -1;
            System.out.println(privacyValue);
            if(todayValue > privacyValue) {
                list.add(i+1);
            }
        }
        
        
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) answer[i] = list.get(i);
        return answer;
    }
}