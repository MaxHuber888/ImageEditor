package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ColorType;
import model.ImageImpl;
import model.Pixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for downscale effect.
 */
public class DownscaleTest {

  List<Pixel> list;
  ImageImpl img;
  BlurFilter blur;
  Sepia sepia;
  Greyscale greyscale;
  SharpenFilter sharpen;
  Downscale downscale;
  Pixel a;
  Pixel b;
  Pixel c;
  Pixel d;

  @Before
  public void setUp() {
    list = new ArrayList<>();
    a = new Pixel(0, 0, 100, 100, 100);
    b = new Pixel(1, 0, 250, 250, 250);
    c = new Pixel(0, 1, 250, 250, 250);
    d = new Pixel(1, 1, 50, 50, 50);

    list.add(new Pixel(0, 0, 8, 20, 42));
    list.add(new Pixel(0, 1, 7, 19, 41));
    list.add(new Pixel(0, 2, 11, 26, 45));
    list.add(new Pixel(0, 3, 14, 29, 48));
    list.add(new Pixel(0, 4, 25, 33, 52));
    list.add(new Pixel(0, 5, 17, 25, 44));
    list.add(new Pixel(0, 6, 11, 27, 43));
    list.add(new Pixel(0, 7, 12, 29, 47));
    list.add(new Pixel(0, 8, 15, 30, 51));
    list.add(new Pixel(0, 9, 7, 24, 44));
    list.add(new Pixel(1, 0, 17, 30, 49));
    list.add(new Pixel(1, 1, 15, 30, 49));
    list.add(new Pixel(1, 2, 11, 28, 46));
    list.add(new Pixel(1, 3, 11, 28, 44));
    list.add(new Pixel(1, 4, 18, 31, 48));
    list.add(new Pixel(1, 5, 16, 29, 46));
    list.add(new Pixel(1, 6, 15, 34, 49));
    list.add(new Pixel(1, 7, 15, 37, 51));
    list.add(new Pixel(1, 8, 24, 41, 59));
    list.add(new Pixel(1, 9, 38, 56, 76));
    list.add(new Pixel(2, 0, 18, 33, 52));
    list.add(new Pixel(2, 1, 22, 39, 55));
    list.add(new Pixel(2, 2, 19, 38, 53));
    list.add(new Pixel(2, 3, 18, 40, 53));
    list.add(new Pixel(2, 4, 15, 33, 47));
    list.add(new Pixel(2, 5, 10, 29, 43));
    list.add(new Pixel(2, 6, 9, 36, 47));
    list.add(new Pixel(2, 7, 14, 40, 53));
    list.add(new Pixel(2, 8, 24, 46, 60));
    list.add(new Pixel(2, 9, 23, 44, 61));
    list.add(new Pixel(3, 0, 27, 44, 62));
    list.add(new Pixel(3, 1, 33, 52, 69));
    list.add(new Pixel(3, 2, 32, 54, 68));
    list.add(new Pixel(3, 3, 42, 66, 78));
    list.add(new Pixel(3, 4, 32, 54, 67));
    list.add(new Pixel(3, 5, 18, 40, 53));
    list.add(new Pixel(3, 6, 11, 39, 50));
    list.add(new Pixel(3, 7, 15, 43, 54));
    list.add(new Pixel(3, 8, 16, 38, 52));
    list.add(new Pixel(3, 9, 16, 38, 52));
    list.add(new Pixel(4, 0, 36, 54, 76));
    list.add(new Pixel(4, 1, 36, 57, 78));
    list.add(new Pixel(4, 2, 36, 59, 77));
    list.add(new Pixel(4, 3, 51, 72, 89));
    list.add(new Pixel(4, 4, 48, 67, 82));
    list.add(new Pixel(4, 5, 35, 57, 70));
    list.add(new Pixel(4, 6, 24, 48, 60));
    list.add(new Pixel(4, 7, 22, 46, 58));
    list.add(new Pixel(4, 8, 25, 44, 58));
    list.add(new Pixel(4, 9, 19, 37, 51));
    list.add(new Pixel(5, 0, 32, 50, 74));
    list.add(new Pixel(5, 1, 33, 51, 75));
    list.add(new Pixel(5, 2, 34, 55, 76));
    list.add(new Pixel(5, 3, 39, 60, 79));
    list.add(new Pixel(5, 4, 41, 60, 77));
    list.add(new Pixel(5, 5, 42, 61, 76));
    list.add(new Pixel(5, 6, 37, 59, 72));
    list.add(new Pixel(5, 7, 37, 56, 70));
    list.add(new Pixel(5, 8, 51, 67, 82));
    list.add(new Pixel(5, 9, 86, 102, 117));
    list.add(new Pixel(6, 0, 35, 51, 76));
    list.add(new Pixel(6, 1, 34, 50, 75));
    list.add(new Pixel(6, 2, 41, 57, 80));
    list.add(new Pixel(6, 3, 37, 54, 74));
    list.add(new Pixel(6, 4, 37, 52, 71));
    list.add(new Pixel(6, 5, 40, 55, 74));
    list.add(new Pixel(6, 6, 42, 59, 75));
    list.add(new Pixel(6, 7, 47, 63, 79));
    list.add(new Pixel(6, 8, 57, 70, 87));
    list.add(new Pixel(6, 9, 72, 85, 102));
    list.add(new Pixel(7, 0, 46, 61, 84));
    list.add(new Pixel(7, 1, 38, 53, 76));
    list.add(new Pixel(7, 2, 47, 62, 85));
    list.add(new Pixel(7, 3, 39, 54, 75));
    list.add(new Pixel(7, 4, 38, 53, 72));
    list.add(new Pixel(7, 5, 36, 51, 70));
    list.add(new Pixel(7, 6, 33, 50, 66));
    list.add(new Pixel(7, 7, 36, 53, 69));
    list.add(new Pixel(7, 8, 39, 55, 71));
    list.add(new Pixel(7, 9, 39, 54, 73));
    list.add(new Pixel(8, 0, 48, 59, 81));
    list.add(new Pixel(8, 1, 46, 58, 80));
    list.add(new Pixel(8, 2, 44, 59, 80));
    list.add(new Pixel(8, 3, 42, 57, 76));
    list.add(new Pixel(8, 4, 34, 51, 69));
    list.add(new Pixel(8, 5, 36, 55, 72));
    list.add(new Pixel(8, 6, 29, 50, 67));
    list.add(new Pixel(8, 7, 28, 49, 66));
    list.add(new Pixel(8, 8, 29, 50, 67));
    list.add(new Pixel(8, 9, 32, 50, 72));
    list.add(new Pixel(9, 0, 45, 60, 81));
    list.add(new Pixel(9, 1, 47, 62, 83));
    list.add(new Pixel(9, 2, 42, 59, 79));
    list.add(new Pixel(9, 3, 38, 56, 76));
    list.add(new Pixel(9, 4, 26, 47, 64));
    list.add(new Pixel(9, 5, 26, 47, 64));
    list.add(new Pixel(9, 6, 28, 49, 66));
    list.add(new Pixel(9, 7, 34, 55, 72));
    list.add(new Pixel(9, 8, 28, 51, 67));
    list.add(new Pixel(9, 9, 30, 51, 70));
    img = new ImageImpl(list, 10, 10);
    blur = new BlurFilter();
    sepia = new Sepia();
    greyscale = new Greyscale();
    sharpen = new SharpenFilter();
    downscale = new Downscale();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownscaleNullImage() {
    downscale.applySpecific(null, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownscaleNegativeDimensions() {
    downscale.applySpecific(img, -2, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownscaleOversizedDimensions() {
    downscale.applySpecific(img, 11, 11);
  }

  @Test
  public void testDownscale5x5for10x10() {
    ImageImpl downscaleImage = downscale.applySpecific(img, 5, 5);
    assertEquals(8, downscaleImage.getColorAt(ColorType.RED, 0, 0));
    assertEquals(20, downscaleImage.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(42, downscaleImage.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(18, downscaleImage.getColorAt(ColorType.RED, 1, 0));
    assertEquals(33, downscaleImage.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(52, downscaleImage.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(11, downscaleImage.getColorAt(ColorType.RED, 0, 1));
    assertEquals(26, downscaleImage.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(45, downscaleImage.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(19, downscaleImage.getColorAt(ColorType.RED, 1, 1));
    assertEquals(38, downscaleImage.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(53, downscaleImage.getColorAt(ColorType.BLUE, 1, 1));

    assertEquals(5, downscaleImage.getWidth());
    assertEquals(5, downscaleImage.getHeight());
  }

  @Test
  public void testDownscaleApply() {
    ImageImpl downscaleImage = downscale.apply(img);
    assertEquals(8, downscaleImage.getColorAt(ColorType.RED, 0, 0));
    assertEquals(20, downscaleImage.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(42, downscaleImage.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(18, downscaleImage.getColorAt(ColorType.RED, 1, 0));
    assertEquals(33, downscaleImage.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(52, downscaleImage.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(11, downscaleImage.getColorAt(ColorType.RED, 0, 1));
    assertEquals(26, downscaleImage.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(45, downscaleImage.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(19, downscaleImage.getColorAt(ColorType.RED, 1, 1));
    assertEquals(38, downscaleImage.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(53, downscaleImage.getColorAt(ColorType.BLUE, 1, 1));

    assertEquals(5, downscaleImage.getWidth());
    assertEquals(5, downscaleImage.getHeight());
  }

  @Test
  public void testDownscale2x7for10x10() {
    ImageImpl downscaleImage = downscale.applySpecific(img, 2, 7);
    assertEquals(8, downscaleImage.getColorAt(ColorType.RED, 0, 0));
    assertEquals(20, downscaleImage.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(42, downscaleImage.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(32, downscaleImage.getColorAt(ColorType.RED, 1, 0));
    assertEquals(50, downscaleImage.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(74, downscaleImage.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(7, downscaleImage.getColorAt(ColorType.RED, 0, 1));
    assertEquals(19, downscaleImage.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(41, downscaleImage.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(33, downscaleImage.getColorAt(ColorType.RED, 1, 1));
    assertEquals(51, downscaleImage.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(75, downscaleImage.getColorAt(ColorType.BLUE, 1, 1));

    assertEquals(2, downscaleImage.getWidth());
    assertEquals(7, downscaleImage.getHeight());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownscale0x0for10x10() {
    ImageImpl downscaleImage = downscale.applySpecific(img, 0, 0);
  }

  @Test
  public void testDownscale7x2for10x10() {
    ImageImpl downscaleImage = downscale.applySpecific(img, 7, 2);
    assertEquals(8, downscaleImage.getColorAt(ColorType.RED, 0, 0));
    assertEquals(20, downscaleImage.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(42, downscaleImage.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(17, downscaleImage.getColorAt(ColorType.RED, 1, 0));
    assertEquals(30, downscaleImage.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(49, downscaleImage.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(17, downscaleImage.getColorAt(ColorType.RED, 0, 1));
    assertEquals(25, downscaleImage.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(44, downscaleImage.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(16, downscaleImage.getColorAt(ColorType.RED, 1, 1));
    assertEquals(29, downscaleImage.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(46, downscaleImage.getColorAt(ColorType.BLUE, 1, 1));

    assertEquals(7, downscaleImage.getWidth());
    assertEquals(2, downscaleImage.getHeight());

  }

  @Test
  public void testDownscale10x10for10x10() {
    ImageImpl downscaleImage = downscale.applySpecific(img, 10, 10);
    assertEquals(8, downscaleImage.getColorAt(ColorType.RED, 0, 0));
    assertEquals(20, downscaleImage.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(42, downscaleImage.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(17, downscaleImage.getColorAt(ColorType.RED, 1, 0));
    assertEquals(30, downscaleImage.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(49, downscaleImage.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(7, downscaleImage.getColorAt(ColorType.RED, 0, 1));
    assertEquals(19, downscaleImage.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(41, downscaleImage.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(15, downscaleImage.getColorAt(ColorType.RED, 1, 1));
    assertEquals(30, downscaleImage.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(49, downscaleImage.getColorAt(ColorType.BLUE, 1, 1));

    assertEquals(10, downscaleImage.getWidth());
    assertEquals(10, downscaleImage.getHeight());

  }

}
