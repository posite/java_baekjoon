import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] connect;
    private static int n, m;
    private static boolean[] check;
    private static int[] result;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            result = new int[n + 1];
            check = new boolean[n + 1];
            connect = new ArrayList[n + 1];
            int max = 0;
            for (int j = 0; j <= n; j++) {
                connect[j] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                connect[x].add(y);
            }

            for (int i = 1; i < n + 1; i++) {
                check = new boolean[n + 1];
                bfs(i);

            }
            for (int i = 1; i < n + 1; i++) {
                if (max < result[i]) max = result[i];
            }

            for (int i = 1; i <= n; i++) {
                if (result[i] == max) {
                    System.out.print(i + " ");
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(start);
        check[start] = true;

        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int i : connect[temp]) {
                if (check[i]) continue;
                result[i]++;
                check[i] = true;
                q.add(i);
            }
        }
    }
}