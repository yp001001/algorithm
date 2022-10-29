package topic.package16;

// https://leetcode.cn/problems/surrounded-regions/
public class Solve {

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        for (int i = 0; i < board.length; i++) {
            infect(board, i, 0);
            infect(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board[0].length; i++) {
            infect(board, 0, i);
            infect(board, board.length - 1, i);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'A') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }

    }

    private void infect(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = 'A';
        infect(board, i + 1, j);
        infect(board, i - 1, j);
        infect(board, i, j + 1);
        infect(board, i, j - 1);
    }

}
