import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] coordinates = new ArrayList[200001];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            coordinates[y + 100000].add(x);
        }
        for (int i = 0; i < coordinates.length; i++) {
            if (!coordinates[i].isEmpty()) {
                Collections.sort(coordinates[i]);
                for (int x : coordinates[i]) {
                    bw.write(x + " " + (i - 100000));
                    bw.newLine();
                }
            }
        }
        bw.flush();

    }
}