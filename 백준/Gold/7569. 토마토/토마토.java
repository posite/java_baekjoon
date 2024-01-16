import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int layer = Integer.parseInt(st.nextToken());
            int[][][] arrs = new int[layer][n][m];


            ArrayList<MyPoint> points = new ArrayList<>();
            for (int k = 0; k < layer; k++) {
                int[][] arr = new int[n][m];
                for (int i = 0; i < n; i++) {
                    String[] inputs = br.readLine().split(" ");
                    for (int j = 0; j < m; j++) {
                        int tomato = Integer.parseInt(inputs[j]);
                        arr[i][j] = tomato;
                        if (tomato == 1) {
                            points.add(new MyPoint(i, j, k));
                        }
                    }
                }
                arrs[k] = arr;
            }
            int[] mx = {-1, 1, 0, 0};
            int[] my = {0, 0, 1, -1};
            int[] mz = {-1, 0, 1};
            int[][][] ans = new int[layer][n][m];
            Queue<MyPoint> q = new LinkedList<>();
            for (MyPoint p : points) {
                ans[p.z][p.x][p.y] = 0;

                q.add(p);
            }

            while (!q.isEmpty()) {
                MyPoint temp = q.poll();
                int currentX = temp.x;
                int currentY = temp.y;
                int currentZ = temp.z;
                for (int j = 0; j < 4; j++) {
                    int nx = currentX + mx[j];
                    int ny = currentY + my[j];
                    int nz;
                    for (int l = 0; l < 3; l++) {
                        nz = currentZ + mz[l];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= layer) {
                            continue;
                        }
                        if (mz[l] == 0) {
                            if (arrs[nz][nx][ny] == 0) {
                                arrs[nz][nx][ny] = 1;
                                q.add(new MyPoint(nx, ny, nz));
                                ans[nz][nx][ny] = ans[currentZ][currentX][currentY] + 1;
                            }
                        } else {
                            if (arrs[nz][currentX][currentY] == 0) {
                                arrs[nz][currentX][currentY] = 1;
                                q.add(new MyPoint(currentX, currentY, nz));
                                ans[nz][currentX][currentY] = ans[currentZ][currentX][currentY] + 1;
                            }
                        }
                    }
                }
            }

            int max = 0;
            for (int k = 0; k < layer; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (arrs[k][i][j] == 0) {
                            bw.write("-1");
                            bw.flush();
                            bw.close();
                            br.close();
                            return;
                        }
                        if (ans[k][i][j] > max) {
                            max = ans[k][i][j];
                        }
                    }
                }
            }
            bw.write(Integer.toString(max));
            bw.flush();
            bw.close();
            br.close();
        } catch (IOException e) {

        }
    }
}

class MyPoint {
    int x;
    int y;
    int z;

    public MyPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}