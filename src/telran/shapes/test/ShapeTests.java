package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.Rectangle;
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
	void squareTest() {
		Square square = new Square(5);
		assertEquals(5, square.getWidth());
		assertEquals(5, square.getHeight());
		displayStrings(square.presentation(10));
		square.setHeight(8);
		assertEquals(8, square.getWidth());
	}

	@Test
	void SquareLeftTriangleTest() {
		SquareLeftTriangle squareTriangle = new SquareLeftTriangle(5);
		displayStrings(squareTriangle.presentation(10));
	}

	@Test
	void SquareRightTriangleTest() {
		SquareRightTriangle squareTriangle = new SquareRightTriangle(10);
		displayStrings(squareTriangle.presentation(10));
	}
}
