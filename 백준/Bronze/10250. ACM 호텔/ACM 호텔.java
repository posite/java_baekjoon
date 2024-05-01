import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            int floor;
            int room;
            if (number % height != 0) {
                floor = number % height;
                room = (number / height) + 1;
            } else {
                floor = height;
                room = (number / height);
            }
            if (room < 10) {
                bw.write(floor + "0" + room);
            } else {
                bw.write(floor + "" + room);
            }
            bw.newLine();
        }
        bw.flush();
    }
}