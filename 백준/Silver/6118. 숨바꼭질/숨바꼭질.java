import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static ArrayList<Integer>[] board;
    private static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new ArrayList[n + 1];
        distances = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            board[i] = new ArrayList<>();
            distances[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a].add(b);
            board[b].add(a);
        }
        distances[0] = 0;
        distances[1] = 0;

        bfs();
        int max = Arrays.stream(distances).max().getAsInt();
        int first = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            if (distances[i] == max) {
                count++;
                if (first > i) {
                    first = i;
                }
            }
        }
        bw.write(first + " " + max + " " + count);
        bw.flush();
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int i = 0; i < board[temp].size(); i++) {
                int dest = board[temp].get(i);
                if (distances[dest] > distances[temp] + 1) {
                    q.add(dest);
                    distances[dest] = distances[temp] + 1;
                }
            }
        }
    }
}
