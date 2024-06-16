import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        StringTokenizer st;
        Map<String, Set<String>> map = new HashMap<>();
        for(String rep: report) {
            st = new StringTokenizer(rep);
            String reporter = st.nextToken();
            String defendant = st.nextToken();
            map.putIfAbsent(defendant, new HashSet());
            map.get(defendant).add(reporter);
        }
        for(Map.Entry<String, Set<String>> entry: map.entrySet()) {
            if(entry.getValue().size() >= k) {
                for(String name: entry.getValue()) {
                    answer[findIndex(name, id_list)]++;
                }
            }
        }
        
        return answer;
    }
    
    public int findIndex(String name, String[] id_list) {
        for(int i=0; i<id_list.length; i++) {
            if(name.equals(id_list[i])) return i;
        }
        return -1;
    }
}