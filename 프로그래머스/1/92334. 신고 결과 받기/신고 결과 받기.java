import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        Map<String, Set<String>> reportMap = new HashMap<>();
        Map<String, Integer> idMap = new HashMap<>();
        for(int i=0; i<id_list.length; i++) {
            idMap.put(id_list[i], i);
            reportMap.put(id_list[i], new HashSet<>());
        }
        for(String rep: report) {
            st = new StringTokenizer(rep);
            String reporter = st.nextToken();
            String user = st.nextToken();
            reportMap.get(user).add(reporter);
        }
        
        int[] answer = new int[id_list.length];
        for(Set<String> reporters: reportMap.values()) {
            if(reporters.size() >= k) {
                for(String reporter: reporters) answer[idMap.get(reporter)]++;
            }
        }
        
        return answer;
    }
}