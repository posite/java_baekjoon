import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static int[] ans;
    private static ArrayList<Integer>[] points;
    private static int count = 1;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            ans = new int[n + 1];
            points = new ArrayList[n + 1];
            for (int i = 1; i < n + 1; i++) {
                points[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                points[a].add(b);
                points[b].add(a);
            }
            for (int i = 1; i < n + 1; i++) {
                points[i].sort(Comparator.reverseOrder());
            }
            dfs(start);
            for (int i = 1; i <= n; i++) {
                bw.write(Integer.toString(ans[i]));
                bw.newLine();
            }
            bw.flush();
            br.close();
            bw.close();
        } catch (IOException e) {

        }
    }

    public static void dfs(int start) {
        ans[start] = count;
        for (int i = 0; i < points[start].size(); i++) {
            if (ans[points[start].get(i)] == 0) {
                count++;
                dfs(points[start].get(i));
            }
        }
    }
}