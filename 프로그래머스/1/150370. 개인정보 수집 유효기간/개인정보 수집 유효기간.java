import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> totals = new ArrayList();
        StringTokenizer st = new StringTokenizer(today, ".");
        int todayYear = Integer.parseInt(st.nextToken());
        int todayMonth = Integer.parseInt(st.nextToken());
        int todayDay = Integer.parseInt(st.nextToken());
        //System.out.println("today " + todayYear + " " + todayMonth + " " + todayDay);
        Map<String, Integer> map = new HashMap<>();
        for(String term: terms) {
            st = new StringTokenizer(term);
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        for(int i=0; i<privacies.length; i++) {
            String privacy = privacies[i];
            st = new StringTokenizer(privacy);
            String start = st.nextToken();
            String termType = st.nextToken();
            //System.out.println("start " + start);
            st = new StringTokenizer(start, ".");
            int startYear = Integer.parseInt(st.nextToken());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken())-1;
            //System.out.println((i+1) + " " + startYear + " " + startMonth + " " + startDay);
            int resultMonth = startMonth + map.get(termType);
            //System.out.println(map.get(termType));
            if(resultMonth % 12 == 0) {
                startYear += (resultMonth /12) -1;
                startMonth = 12;
            } else {
                startYear += resultMonth / 12;
                startMonth = resultMonth % 12;
            }
            //System.out.println((i+1) + " " + startYear + " " + startMonth + " " + startDay);
            if(todayYear > startYear) {
                totals.add(i+1);
                continue;
            }
            if(startYear > todayYear) continue;
            if(todayMonth > startMonth) {
                totals.add(i+1);
                continue;
            }
            if(startMonth > todayMonth) continue;
            if(todayDay > startDay) {
                totals.add(i+1);
                continue;
            }
        }
        //System.out.println(totals);
        int[] answer = new int[totals.size()];
        for(int i=0; i<totals.size(); i++) {
            answer[i] = totals.get(i);
        }
        return answer;
    }
}