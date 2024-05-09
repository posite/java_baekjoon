import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        List<List<Integer>> list = new ArrayList();
        for (int i = 1; i <= relation[0].length; i++) {
            findKey(list, new ArrayList(), relation, 0, i);
        }
        System.out.println(list);
        return list.size();
    }

    private static void findKey(List<List<Integer>> list, ArrayList<Integer> now, String[][] relation, int start, int size) {
        if (now.size() == size) {
            //System.out.println(now);
            String[] keys = new String[relation.length];
            for (int i = 0; i < relation.length; i++) {
                StringBuilder key = new StringBuilder();
                for (Integer integer : now) {
                    key.append(relation[i][integer]);
                }
                keys[i] = key.toString();
            }
            if (isUnique(keys)) {
                list.add(new ArrayList<>(now));
                //System.out.println(list);
                return;
            }
        }
        for (int i = start; i < relation[0].length; i++) {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(i);
            if (!list.contains(now)) {
                if (!list.contains(al)) {
                    now.add(i);
                    boolean isExist = false;
                    for (List<Integer> comb : list) {
                        if (now.containsAll(comb)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        findKey(list, now, relation, i + 1, size);
                    }
                    now.remove(now.size() - 1);
                }
            } else {
                return;
            }
        }
    }

    private static boolean isUnique(String[] keys) {
        ArrayList<String> list = new ArrayList<>();
        for (String key : keys) {
            if (!list.contains(key)) {
                list.add(key);
            } else {
                return false;
            }
        }
        return true;
    }
}