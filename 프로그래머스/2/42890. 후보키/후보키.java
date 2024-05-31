import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        List<List<Integer>> list = new ArrayList();
        for(int i=1; i<=relation[0].length; i++) {
            combination(list, new ArrayList(), relation, 0, i);
        }
        return list.size();
    }
    
    public void combination(List<List<Integer>> list, ArrayList<Integer> now, String[][] relation, int start, int k) {
        if(now.size() == k) {
            String[] keys = new String[relation.length];
            for(int i=0; i<relation.length; i++) {
                StringBuilder key = new StringBuilder();
                for(int index: now) {
                    key.append(relation[i][index]);
                }
                keys[i] = key.toString();
            }
            if(isUnique(keys)) {
                list.add(new ArrayList<>(now));
            }
            return;
        }
        
        for(int i=start; i<relation[0].length; i++) {
            now.add(i);
            boolean isExist = false;
            for(List<Integer> comb: list) {
                if(now.containsAll(comb)) {
                    isExist = true;
                    break;
                }
            }
            if(!isExist) {
                combination(list, now, relation, i+1, k);
            }
            now.remove(now.size() - 1);
        }
    }
    
    
    public boolean isUnique(String[] keys) {
        ArrayList<String> list = new ArrayList();
        for(String key: keys) {
            if(list.contains(key)) {
                return false;
            }
            list.add(key);
        }
        return true;
    }
    
}