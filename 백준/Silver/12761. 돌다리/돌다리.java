import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a, b, n, m;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] counts = new int[100001];
        Arrays.fill(counts, Integer.MAX_VALUE);
        int[] dx = {-1, 1, a, -a, b, -b};
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        counts[n] = 0;
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int x : dx) {
                int nx = temp + x;
                if (nx >= 0 && nx < counts.length) {
                    if (counts[nx] > counts[temp] + 1) {
                        counts[nx] = counts[temp] + 1;
                        q.add(nx);
                    }

                }
                if (x == a || x == b) {
                    int mx = temp * x;
                    if (mx >= 0 && mx < counts.length) {
                        if (counts[mx] > counts[temp] + 1) {
                            counts[mx] = counts[temp] + 1;
                            q.add(mx);
                        }
                    }
                }
            }
        }
        bw.write(Integer.toString(counts[m]));
        bw.flush();
    }

}