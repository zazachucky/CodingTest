import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j]  = str.charAt(j)-48;
            }
        }

        bfs(map, N, M);
    }

    static void bfs(int[][] map, int N, int M) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        Pos start = new Pos(0,0,1);
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        int result = 1;
        while (!queue.isEmpty()) {
            Pos curr = queue.poll();
            if(curr.x == N-1 && curr.y == M-1) {
                result = Math.max(result, curr.cnt);
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >=M)
                    continue;
                if(map[nx][ny] != 0 && !visited[nx][ny]) {
                    // 메모리 초과를 방지하기 위해 큐에 넣기 전에 방문 체크를 한다.
                    // 중복으로 큐에 들어가는 것을 방지.
                    visited[nx][ny] = true;
                    queue.offer(new Pos(nx, ny, curr.cnt + 1));
                }
            }
        }
        System.out.println(result);
    }

    static class Pos {
        int x, y, cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
