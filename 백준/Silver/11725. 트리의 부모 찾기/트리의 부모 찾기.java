import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int total = Integer.parseInt(br.readLine());
            int[] parent = new int[total + 1];
            boolean[] check = new boolean[total + 1];

            StringTokenizer str;
            ArrayList<Integer>[] adj = new ArrayList[total + 1];
            for (int i = 1; i <= total; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < total - 1; i++) {
                str = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(str.nextToken());
                int b = Integer.parseInt(str.nextToken());
                adj[a].add(b);
                adj[b].add(a);
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            check[1] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    if (check[next]) {
                        continue;
                    }

                    check[next] = true;
                    q.add(next);
                    parent[next] = cur;
                }
            }
            for (int i = 2; i <= total; i++) {
                System.out.println(parent[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
