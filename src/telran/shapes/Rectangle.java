package telran.shapes;

public class Rectangle {

	public static final String SYMBOL = "*";
	private int width;
	private int height;
	protected static String symbol = SYMBOL;	
	public static void setSymbol(String symbol) {
		Rectangle.symbol = symbol;
	}
	
	public static String getSymbol() {
		return symbol;
	}

	public Rectangle(int width, int height) {
		this.width = width; // on the left is what is indicated above (object field), on the right (what is passed in the constructor)
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String[] presentation(int offset) {
		String[] res = new String[height];
		String line = getLine(offset);
		res[0] = line;
		int lastline = height-1;
		res[lastline] = line;
		for(int i = 1; i < lastline; i ++) {
			res[i] = getMiddleLine(offset);
		}
		return res;
	}

	protected String getMiddleLine(int offset) {
		return getOffset(offset) + symbol + getOffset(width-2) + symbol;
	}

	protected String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(width);
	}

	protected String getOffset(int offset) {
		return " ".repeat(offset);
	}
}
