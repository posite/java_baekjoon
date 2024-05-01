import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[8];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        boolean ascending = false;
        boolean descending = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    descending = true;
                } else if (numbers[i] < numbers[j]) {
                    ascending = true;
                }
            }
        }
        if (ascending && descending) {
            bw.write("mixed");
        } else if (ascending) {
            bw.write("ascending");
        } else {
            bw.write("descending");
        }
        bw.flush();
    }
}