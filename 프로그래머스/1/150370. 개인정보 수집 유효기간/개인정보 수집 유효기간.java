import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answers = new ArrayList();
        StringTokenizer st = new StringTokenizer(today, ".");
        int todayYear = Integer.parseInt(st.nextToken());
        int todayMonth = Integer.parseInt(st.nextToken());
        int todayDay = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        for(String term: terms) {
            st = new StringTokenizer(term);
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        for(int i=0; i<privacies.length; i++) {
            String privacy = privacies[i];
            st = new StringTokenizer(privacy);
            String start = st.nextToken();
            String term = st.nextToken();
            st = new StringTokenizer(start, ".");
            int startYear = Integer.parseInt(st.nextToken());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken()) -1;
            int resultMonth = startMonth + map.get(term);
            if(resultMonth % 12 == 0) {
                startYear += (resultMonth / 12 ) -1;
                startMonth = 12;
            } else {
                startYear += resultMonth / 12;
                startMonth = resultMonth % 12;
            }
            if(todayYear > startYear) {
                answers.add(i+1);
                continue;
            }
            if(startYear > todayYear) continue;
            if(todayMonth > startMonth) {
                answers.add(i+1);
                continue;
            }
            if(startMonth > todayMonth) continue;
            if(todayDay > startDay) {
                answers.add(i+1);
                continue;
            }
            if(startDay > todayDay) continue;
        }
        int[] answer = new int[answers.size()];
        for(int i=0; i<answers.size(); i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }
}