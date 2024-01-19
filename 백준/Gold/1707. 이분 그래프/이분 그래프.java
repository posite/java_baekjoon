import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int total, n, m;
    private static int[] check;
    private static ArrayList<Integer>[] al;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            total = Integer.parseInt(br.readLine());
            for (int i = 0; i < total; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
                al = new ArrayList[n + 1];
                check = new int[n + 1];
                for (int j = 0; j <= n; j++) {
                    al[j] = new ArrayList<Integer>();
                }
                for (int j = 0; j < m; j++) {
                    StringTokenizer s = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(s.nextToken());
                    int b = Integer.parseInt(s.nextToken());
                    al[a].add(b);
                    al[b].add(a);
                }
                bfs();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (check[i] == 0) {
                q.add(i);
                check[i] = 1;
            }
            while (!q.isEmpty()) {
                int temp = q.poll();
                for (int j = 0; j < al[temp].size(); j++) {
                    if (check[al[temp].get(j)] == 0) {
                        q.add(al[temp].get(j));
                    }

                    if (check[al[temp].get(j)] == check[temp]) {
                        System.out.println("NO");
                        return;
                    }

                    if (check[temp] == 1 && check[al[temp].get(j)] == 0) {
                        check[al[temp].get(j)] = 2;
                    } else if (check[temp] == 2 && check[al[temp].get(j)] == 0) {
                        check[al[temp].get(j)] = 1;
                    }
                }
            }
        }
        System.out.println("YES");
    }
}

