package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the ImageImpl class.
 */
public class ImageImplTest {

  ImageImpl img;

  @Before
  public void setUp() throws Exception {
    img = new ImageImpl(100, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalidPixels() {
    ImageImpl imageImpl = new ImageImpl(null, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalidWidth() {
    ImageImpl imageImpl = new ImageImpl(null, 0, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageInvalidHeight() {
    ImageImpl imageImpl = new ImageImpl(null, 10, 0);
  }

  @Test
  public void testToString() {
    assertEquals("Width: 100 Height: 100 Pixel Count: 10000", img.toString());
  }

  @Test
  public void testGetNumPixels() {
    assertEquals(img.getPixels().size(), img.getNumPixels());
  }

  @Test
  public void testGetColorAt() {
    assertEquals(0, img.getColorAt(ColorType.RED, 0, 0));
    assertEquals(0, img.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(0, img.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(255, img.getColorAt(ColorType.RED, 15, 0));
    assertEquals(255, img.getColorAt(ColorType.GREEN, 15, 0));
    assertEquals(255, img.getColorAt(ColorType.BLUE, 15, 0));

    assertEquals(255, img.getColorAt(ColorType.RED, 0, 15));
    assertEquals(255, img.getColorAt(ColorType.GREEN, 0, 15));
    assertEquals(255, img.getColorAt(ColorType.BLUE, 0, 15));

    assertEquals(0, img.getColorAt(ColorType.RED, 15, 15));
    assertEquals(0, img.getColorAt(ColorType.GREEN, 15, 15));
    assertEquals(0, img.getColorAt(ColorType.BLUE, 15, 15));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckerboardInvalidDimension() {
    ImageImpl checkerboard = new ImageImpl(0, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckerboardInvalidSquaresPerLine() {
    ImageImpl checkerboard = new ImageImpl(5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRainbowInvalidDimension() {
    ImageImpl checkerboard = new ImageImpl(0);
  }

  @Test
  public void testProgrammaticImages() {
    ImageImpl checkerboard = new ImageImpl(10, 10);
    assertEquals(0, checkerboard.getColorAt(ColorType.RED, 0, 0));
    assertEquals(0, checkerboard.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(0, checkerboard.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(255, checkerboard.getColorAt(ColorType.RED, 1, 0));
    assertEquals(255, checkerboard.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(255, checkerboard.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, checkerboard.getColorAt(ColorType.RED, 0, 1));
    assertEquals(255, checkerboard.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(255, checkerboard.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(0, checkerboard.getColorAt(ColorType.RED, 1, 1));
    assertEquals(0, checkerboard.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(0, checkerboard.getColorAt(ColorType.BLUE, 1, 1));

    ImageImpl rainbow = new ImageImpl(7);
    assertEquals(255, rainbow.getColorAt(ColorType.RED, 0, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(255, rainbow.getColorAt(ColorType.RED, 1, 0));
    assertEquals(130, rainbow.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, rainbow.getColorAt(ColorType.RED, 2, 0));
    assertEquals(255, rainbow.getColorAt(ColorType.GREEN, 2, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.BLUE, 2, 0));

    assertEquals(0, rainbow.getColorAt(ColorType.RED, 3, 0));
    assertEquals(255, rainbow.getColorAt(ColorType.GREEN, 3, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.BLUE, 3, 0));

    assertEquals(0, rainbow.getColorAt(ColorType.RED, 4, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.GREEN, 4, 0));
    assertEquals(255, rainbow.getColorAt(ColorType.BLUE, 4, 0));

    assertEquals(75, rainbow.getColorAt(ColorType.RED, 5, 0));
    assertEquals(0, rainbow.getColorAt(ColorType.GREEN, 5, 0));
    assertEquals(130, rainbow.getColorAt(ColorType.BLUE, 5, 0));

    assertEquals(238, rainbow.getColorAt(ColorType.RED, 6, 0));
    assertEquals(130, rainbow.getColorAt(ColorType.GREEN, 6, 0));
    assertEquals(238, rainbow.getColorAt(ColorType.BLUE, 6, 0));
  }

}