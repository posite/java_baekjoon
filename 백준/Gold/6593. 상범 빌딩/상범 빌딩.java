import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] dl = {1, -1, 0, 0, 0, 0};
        int[] dn = {0, 0, 1, -1, 0, 0};
        int[] dm = {0, 0, 0, 0, 1, -1};
        while (true) {
            int l, n, m;
            Place start = new Place(0, 0, 0);
            Place exit = new Place(0, 0, 0);
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (l == 0 && n == 0 && m == 0) {
                break;
            }
            char[][][] board = new char[l][n][m];
            int[][][] counts = new int[l][n][m];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        counts[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < n; j++) {
                    st = new StringTokenizer(br.readLine());
                    String token = st.nextToken();
                    for (int k = 0; k < m; k++) {
                        board[i][j][k] = token.charAt(k);
                        if (token.charAt(k) == 'S') {
                            start = new Place(i, j, k);
                        }
                        if (token.charAt(k) == 'E') {
                            exit = new Place(i, j, k);
                        }
                    }
                }
                st = new StringTokenizer(br.readLine());
            }


            Queue<Place> q = new LinkedList<>();
            q.add(start);
            counts[start.l][start.n][start.m] = 0;
            while (!q.isEmpty()) {
                Place temp = q.poll();
                for (int i = 0; i < dl.length; i++) {
                    int nl = temp.l + dl[i];
                    int nn = temp.n + dn[i];
                    int nm = temp.m + dm[i];
                    if (nl >= 0 && nl < l && nn >= 0 && nn < n && nm >= 0 && nm < m && board[nl][nn][nm] != '#') {
                        if (counts[nl][nn][nm] > counts[temp.l][temp.n][temp.m] + 1) {
                            counts[nl][nn][nm] = counts[temp.l][temp.n][temp.m] + 1;
                            q.add(new Place(nl, nn, nm));
                        }
                    }
                }
            }
            int times = counts[exit.l][exit.n][exit.m];
            if (times == Integer.MAX_VALUE) {
                bw.write("Trapped!");
            } else {
                bw.write("Escaped in " + times + " minute(s).");
            }
            bw.newLine();
            bw.flush();
        }

    }
}

class Place {
    int l, n, m;

    public Place(int l, int n, int m) {
        this.l = l;
        this.n = n;
        this.m = m;
    }
}