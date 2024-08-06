import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
           
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> indexes = new HashMap<>();
        for(int i=0; i<id_list.length; i++) {
            indexes.put(id_list[i], i);
            map.put(id_list[i], new HashSet<>());
        }
        for(String rep: report) {
            StringTokenizer st = new StringTokenizer(rep);
            String reporter = st.nextToken();
            String opponent = st.nextToken();
            map.get(opponent).add(reporter);
        }
        
        for(Map.Entry<String, Set<String>> entry: map.entrySet()) {
            Set<String> value = entry.getValue();
            if(value.size() >= k) {
                for(String user: value) {
                    answer[indexes.get(user)]++;
                }
            }
        }
        
        return answer;
    }
    
    public int findUser(String[] id_list, String userId) {
        for(int i=0; i<id_list.length; i++) {
            if(id_list[i].equals(userId)) return i;
        }
        return -1;
    }
}