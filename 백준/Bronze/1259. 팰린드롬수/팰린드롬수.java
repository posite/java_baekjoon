import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        ArrayList<String> al = new ArrayList<>();
        while (true) {
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            if (word.equals("0")) {
                break;
            } else {
                al.add(word);
            }
        }
        for (String word : al) {
            boolean isP = true;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                    bw.write("no");
                    isP = false;
                    break;
                }
            }
            if (isP) {
                bw.write("yes");
            }
            bw.newLine();
        }
        bw.flush();
    }
}