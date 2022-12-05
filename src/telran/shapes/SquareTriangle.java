package telran.shapes;

import java.util.Arrays;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public boolean isLeftDiagonal() {
		return isLeftDiagonal;
	}

	private char[][] getDiagonalLineAndColumn() {
		int size = getWidth();
		char symbolChar = symbol.charAt(0);
		char[][] diagonalLineAndColumn = new char[size][size];
		for (int i = 0; i < size; i++) {
			Arrays.fill(diagonalLineAndColumn[i], ' ');
			if (isLeftDiagonal) {
				diagonalLineAndColumn[i][size - 1] = symbolChar;
				diagonalLineAndColumn[i][size - i - 1] = symbolChar;
			} else {
				diagonalLineAndColumn[i][0] = symbolChar;
				diagonalLineAndColumn[i][i] = symbolChar;
			}
		}
		return diagonalLineAndColumn;
	}

	public String[] presentation(int offset) {
		int size = getWidth();
		String[] res = new String[size];
		int lastline = size - 1;
		res[lastline] = getLine(offset);
		char[][] getDiagonalLineAndColumn = getDiagonalLineAndColumn();
		for (int i = 0; i < lastline; i++) {
			res[i] = getOffset(offset) + new String(getDiagonalLineAndColumn[i]);
		}
		return res;
	}
}