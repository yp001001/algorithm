package dfs;

public class Test02 {

    //===================================单词搜索================================

    boolean[][] marked;

    public boolean exist(char[][] board, String word) {
        int row = board.length, column = board[0].length;
        marked = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int path) {
        if (path >= word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(path) || marked[i][j]) {
            return false;
        }
        marked[i][j] = true;
        boolean flag = dfs(board, word, i + 1, j, path + 1) ||
                dfs(board, word, i - 1, j, path + 1) ||
                dfs(board, word, i, j + 1, path + 1) ||
                dfs(board, word, i, j - 1, path + 1);
        marked[i][j] = false;
        return flag;
    }


}
