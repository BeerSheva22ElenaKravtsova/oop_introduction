package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;

class ShapeTests {

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

	@Test
	@Disabled
	void squareTest() {
		Square square = new Square(5);
		assertEquals(5, square.getWidth());
		assertEquals(5, square.getHeight());
		displayStrings(square.presentation(10));
		square.setHeight(8);
		assertEquals(8, square.getWidth());
	}

	@Test
	@Disabled
	void SquareLeftTriangleTest() {
		SquareLeftTriangle squareTriangle = new SquareLeftTriangle(5);
		displayStrings(squareTriangle.presentation(10));
	}

	@Test
	@Disabled
	void SquareRightTriangleTest() {
		SquareRightTriangle squareTriangle = new SquareRightTriangle(10);
		displayStrings(squareTriangle.presentation(10));
	}
	
	@Test
	void canvasTest() {
		Shape rectangle = new Rectangle(5, 7);
		Shape square = new Square(9);
		Shape squareLeftTriangle = new SquareLeftTriangle(5);
		Shape squareRightTriangle = new SquareRightTriangle(7);
		Shape [] shapes = new Shape[] {rectangle, square, squareLeftTriangle, squareRightTriangle};
		Canvas canvas = new Canvas(15, 15, shapes);
		displayStrings(canvas.presentation(10));
		canvas.setDirection("column");
		displayStrings(canvas.presentation(10));
	}
}
