import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] points = new ArrayList[200001];
        for (int i = 0; i < 200001; i++) {
            points[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[x + 100000].add(y);
        }
        for (int i = 0; i < 200001; i++) {
            if (!points[i].isEmpty()) {
                Collections.sort(points[i]);
                for (int y : points[i]) {
                    sb.append(i - 100000 + " " + y);
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}