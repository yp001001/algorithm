package everyday;

import java.util.HashSet;
import java.util.Set;

public class Test66 {

    public void setZeroes(int[][] matrix) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> columnSet = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    columnSet.add(j);
                    break;
                }
            }
        }
        for (int row : rowSet) {
            for (int i = 0; i < matrix[row].length; i++) {
                matrix[row][i] = 0;
            }
        }
        for (int column : columnSet) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][column] = 0;
            }
        }
    }

}
