package telran.shapes;

public abstract class Shape {
	protected int width;
	protected int height;
	public static final String SYMBOL = "*";
	protected static String symbol = SYMBOL;

	public Shape(int width, int height) {
		this.width = width; // on the left is what is indicated above (object field), on the right (what is
		// passed in the constructor)
		this.height = height;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Shape.symbol = symbol;
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

	abstract public String[] presentation(int offset);
}