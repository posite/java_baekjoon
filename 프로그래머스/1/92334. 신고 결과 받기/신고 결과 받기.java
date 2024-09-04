import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        int[] answer = new int[id_list.length];
        Map<String, Integer> userIdMap = new HashMap<>();
        Map<String, Set<String>> reportMap = new HashMap<>();
        for(int i=0; i<id_list.length; i++) {
            userIdMap.put(id_list[i], i);
            reportMap.put(id_list[i], new HashSet<>());
        }
        for(String rep: report) {
            st = new StringTokenizer(rep);
            String reporter = st.nextToken();
            String user = st.nextToken();
            reportMap.get(user).add(reporter);
        }
        for(String id: id_list) {
            if(reportMap.get(id).size() >= k) {
                for(String rep: reportMap.get(id)) {
                    answer[userIdMap.get(rep)]++;
                }
            }
        }
    
        return answer;
    }
}