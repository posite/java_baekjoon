import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int cardMax = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] board = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<ArrayList<Integer>> perm = new ArrayList<>();
        permutation(perm, new ArrayList<>(), board, 3, m);
        bw.write(Integer.toString(cardMax));
        bw.flush();
    }

    private static void permutation(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> now, int[] arr, int size, int max) {
        if (now.size() == size) {
            int sum = now.get(0) + now.get(1) + now.get(2);
            if (now.get(0) + now.get(1) + now.get(2) <= max) {
                list.add(now);
                if (sum > cardMax) {
                    cardMax = sum;
                }
            }
            return;
        }
        for (int num : arr) {
            if (!now.contains(num)) {
                now.add(num);
                permutation(list, now, arr, size, max);
                now.remove(now.size() - 1);
            }
        }
    }
}