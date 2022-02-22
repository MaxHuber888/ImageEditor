package model.effects;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import model.ColorType;
import model.ImageImpl;
import model.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing sepia effect.
 */
public class SepiaTest {

  List<Pixel> list;
  ImageImpl img;
  Sepia sepia;
  Greyscale greyscale;
  BlurFilter blur;
  SharpenFilter sharpen;

  @Before
  public void setUp() throws Exception {
    list = new ArrayList<>();
    list.add(new Pixel(0, 0, 255, 255, 0));
    list.add(new Pixel(1, 0, 0, 255, 255));
    list.add(new Pixel(0, 1, 200, 200, 200));
    list.add(new Pixel(1, 1, 50, 255, 50));
    img = new ImageImpl(list, 10, 10);
    blur = new BlurFilter();
    sepia = new Sepia();
    greyscale = new Greyscale();
    sharpen = new SharpenFilter();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaNullImage() {
    ImageImpl sepiaImageImpl = sepia.apply(null);
  }

  @Test
  public void applySepiaOnce() {
    ImageImpl sepiaImageImpl = sepia.apply(img);

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(205, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(244, sepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(217, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(169, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(240, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(187, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(225, sepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(200, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(156, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void applySepiaTwice() {
    ImageImpl onceSepiaImageImpl = sepia.apply(img);
    ImageImpl sepiaImageImpl = sepia.apply(onceSepiaImageImpl);

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(232, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(204, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(222, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(241, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(188, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testBlurOnSepia() {
    // apply sepia to image
    ImageImpl sepiaImageImpl = sepia.apply(img);

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(205, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(244, sepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(217, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(169, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(240, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(187, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(225, sepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(200, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(156, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // apply blur to sepia image
    ImageImpl blurOnSepiaImageImpl = blur.apply(sepiaImageImpl);

    assertEquals(139, blurOnSepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(132, blurOnSepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(104, blurOnSepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(136, blurOnSepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(125, blurOnSepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(98, blurOnSepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(138, blurOnSepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(129, blurOnSepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(101, blurOnSepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(133, blurOnSepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(122, blurOnSepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(95, blurOnSepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testGreyScaleOnSepia() {
    // apply sepia to image
    ImageImpl sepiaImageImpl = sepia.apply(img);

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(205, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(244, sepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(217, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(169, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(240, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(187, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(225, sepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(200, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(156, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // apply greyscale to sepia image
    ImageImpl greyOnSepiaImageImpl = greyscale.apply(sepiaImageImpl);

    assertEquals(251, greyOnSepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(251, greyOnSepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(251, greyOnSepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(219, greyOnSepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(219, greyOnSepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(219, greyOnSepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(239, greyOnSepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(239, greyOnSepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(239, greyOnSepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(202, greyOnSepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(202, greyOnSepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(202, greyOnSepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testSharpenOnSepia() {
    // apply sepia to image
    ImageImpl sepiaImageImpl = sepia.apply(img);

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(205, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(244, sepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(217, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(169, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(240, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(187, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(225, sepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(200, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(156, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // apply sharpen to sepia image
    ImageImpl sharpenOnSepiaImageImpl = sharpen.apply(sepiaImageImpl);

    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(255, sharpenOnSepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }
}