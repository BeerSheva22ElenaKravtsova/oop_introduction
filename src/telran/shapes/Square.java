package telran.shapes;

public class Square extends Rectangle {

	public Square(int size) {
		super(size, size);
	}

	private void setSize(int size) {
		super.setWidth(size);
		super.setHeight(size);
	}

	@Override//функционально ничего не делает - показывает программисту что метод перезаписан
	public void setWidth(int width) {
		setSize(width);
	}

	@Override
	public void setHeight(int height) {
		setSize(height);
	}
}
