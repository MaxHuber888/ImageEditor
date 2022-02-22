package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing model.Pixel class.
 */
public class PixelTest {

  Pixel p1;
  Pixel p2;
  Pixel p3;
  Pixel p4;

  @Before
  public void setUp() throws Exception {
    p1 = new Pixel(0, 0, 30, 250, 4);
    p2 = new Pixel(0, 1, 5, 450, 20);
    p3 = new Pixel(1, 0, -30, 30, -30);
    p4 = new Pixel(1, 1, 600, 60, 700);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelInvalidX() {
    Pixel pixel = new Pixel(-1, 2, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelInvalidY() {
    Pixel pixel = new Pixel(1, -2, 5, 5, 5);
  }

  @Test
  public void getRGB() {
    assertEquals(p1.getR() * 65536 + 256 * p1.getG() + p1.getB(), p1.getRGB());
    assertEquals(p2.getR() * 65536 + 256 * 255 + p2.getB(), p2.getRGB());
    assertEquals(256 * p3.getG(), p3.getRGB());
    assertEquals(255 * 65536 + 256 * p4.getG() + 255, p4.getRGB());
  }

  @Test
  public void clampColors() {
    Pixel p1 = new Pixel(0, 0, 30, 250, 4);
    Pixel p2 = new Pixel(0, 1, 5, 450, 20);
    Pixel p3 = new Pixel(1, 0, -30, 30, -30);
    Pixel p4 = new Pixel(1, 1, 600, 60, 700);

    p1.clampColors();
    p2.clampColors();
    p3.clampColors();
    p4.clampColors();

    assertEquals(30, p1.getR());
    assertEquals(250, p1.getG());
    assertEquals(4, p1.getB());

    assertEquals(5, p2.getR());
    assertEquals(255, p2.getG());
    assertEquals(20, p2.getB());

    assertEquals(0, p3.getR());
    assertEquals(30, p3.getG());
    assertEquals(0, p3.getB());

    assertEquals(255, p4.getR());
    assertEquals(60, p4.getG());
    assertEquals(255, p4.getB());
  }
}