public class PascalVal
{
	// assumes valid input
	public static int findPascalVal(int row, int col) {
		if (row < 2)
			return 1;
		if (col == 1 || col == row-1)
			return row;
		int[][] memo = new int[row+1][row+1];
		return findPascalValHelper(row, col, memo);
	}
	private static int findPascalValHelper(int row, int col, int[][] memo) {
		if (col == 0 || col == row)
			return 1;
		else if (memo[row][col] != 0)
			return memo[row][col];

		return memo[row][col] = findPascalValHelper(row-1, col-1, memo) + findPascalValHelper(row-1, col, memo);
	}

	public static void main(String[] args) {
		System.out.println(findPascalVal(6, 6));
	}
}