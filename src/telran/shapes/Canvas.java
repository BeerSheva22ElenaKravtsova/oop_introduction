package telran.shapes;

import java.util.Arrays;

public class Canvas extends Shape {
	public String direction = "row";
	public int margin = 5;
	private Shape[] shapes;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		switch (direction) {
		case "row":
			this.direction = direction;
			break;
		case "column":
			this.direction = direction;
			break;
		default:
			break;
		}
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	private Shape[] setEqualSizeForPrinting(Shape[] shapes) {
		for (int i = 0; i < shapes.length; i++) {
			if (direction == "row") {
				shapes[i].setHeight(height);
			} else {
				shapes[i].setWidth(width);
			}
		}
		return shapes;
	}

	@Override
	public String[] presentation(int offset) {
		setEqualSizeForPrinting(shapes);
		String[] res = shapes[0].presentation(offset);
		for (int j = 1; j < shapes.length; j++) {
			if (shapes[j] instanceof Canvas) {
				((Canvas) shapes[j]).setDirection(direction);
			}
			if (direction == "row") {
				shapeAdditionForRow(res, shapes[j].presentation(margin));
			} else {
				res = new String[getHeight()];
				shapeAdditionForColumn(res, shapes[j].presentation(margin), offset);
			}
		}
		return res;
	}

	private String[] shapeAdditionForRow(String[] res, String[] figure) {
		for (int j = 0; j < res.length; j++) {
			res[j] += figure[j];
		}
		return res;
	}

	private String[] shapeAdditionForColumn(String[] res, String[] presentation, int offset) {
		String[] marginColumn = getMarginForColumn();
		System.arraycopy(shapes[0].presentation(offset), 0, res, 0, shapes[0].presentation(offset).length);
		int distance = shapes[0].presentation(offset).length;
		for (int j = 1; j < shapes.length; j++) {
			System.arraycopy(marginColumn, 0, res, distance, marginColumn.length);
			distance += marginColumn.length;
			System.arraycopy(shapes[j].presentation(offset), 0, res, distance, shapes[j].presentation(offset).length);
			distance += shapes[j].presentation(offset).length;
		}
		return res;
	}

	@Override
	public int getHeight() {
		return direction == "row"? height : getHeightOfColumn();
	}

	public int getHeightOfColumn() {
		int totalHeightOfColumn = shapes[0].getHeight();
		for (int i = 1; i < shapes.length; i++) {
			totalHeightOfColumn += margin + shapes[i].getHeight();
		}
		return totalHeightOfColumn;
	}

	public String[] getMarginForColumn() {
		String[] marginRow = new String[margin];
		for (int i = 0; i < marginRow.length; i++) {
			marginRow[i] = " ".repeat(width);
		}
		return marginRow;
	}
}