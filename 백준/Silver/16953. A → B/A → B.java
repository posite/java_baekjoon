import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        Queue<Long> q = new LinkedList<>();
        q.add(a * 2);
        q.add(a * 10 + 1);

        int count = 0;
        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                long current = q.poll();
                if (current > b) {
                    continue;
                }
                if (current == b) {
                    bw.write(Integer.toString(count + 1));
                    bw.flush();
                    br.close();
                    bw.close();
                    return;
                }
                q.add(current * 2);
                q.add(current * 10 + 1);
            }
        }
        bw.write("-1");
        br.close();
        bw.close();
    }
}