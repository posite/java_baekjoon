import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new int[100001];
        Arrays.fill(check, -1);
        bfs();
        System.out.println(check[m]);
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(n, 0));
        while (!q.isEmpty()) {
            Point temp = q.poll();
            if (temp.x == m) {
                if (check[m] == -1 || check[m] > temp.y) {
                    check[m] = temp.y;
                }
                continue;
            }
            int plus = temp.x + 1;
            int minus = temp.x - 1;
            int teleport = temp.x * 2;
            if (teleport <= 100000 && teleport >= 2) {
                if (check[teleport] == -1 || check[teleport] > temp.y) {
                    check[teleport] = temp.y;
                    q.add(new Point(teleport, temp.y));
                }

            }
            if (plus <= m) {
                if (check[plus] == -1 || check[plus] > temp.y + 1) {
                    check[plus] = temp.y + 1;
                    q.add(new Point(plus, temp.y + 1));
                }
            }
            if (minus >= 0) {
                if (check[minus] == -1 || check[minus] > temp.y + 1) {
                    check[minus] = temp.y + 1;
                    q.add(new Point(minus, temp.y + 1));
                }
            }

        }
    }
}