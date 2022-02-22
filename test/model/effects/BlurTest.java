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
 * Class for testing the BlurFilter class.
 */
public class BlurTest {

  List<Pixel> list;
  ImageImpl img;
  BlurFilter blur;
  Sepia sepia;
  Greyscale greyscale;
  SharpenFilter sharpen;

  @Before
  public void setUp() {
    list = new ArrayList<>();
    list.add(new Pixel(0, 0, 100, 100, 100));
    list.add(new Pixel(1, 0, 250, 250, 250));
    list.add(new Pixel(0, 1, 250, 250, 250));
    list.add(new Pixel(1, 1, 50, 50, 50));
    img = new ImageImpl(list, 10, 10);
    blur = new BlurFilter();
    sepia = new Sepia();
    greyscale = new Greyscale();
    sharpen = new SharpenFilter();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurNullImage() {
    ImageImpl blurImageImpl = blur.apply(null);
  }

  @Test
  public void testApplyBlurOnce() {
    ImageImpl blurImageImpl = blur.apply(img);

    //all color values should be way closer together,
    //since blurring works by averaging

    assertEquals(90, blurImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(80, blurImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testApplyBlurTwice() {
    ImageImpl onceBlurImageImpl = blur.apply(img);
    ImageImpl blurImageImpl = blur.apply(onceBlurImageImpl);

    assertEquals(50, blurImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(50, blurImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(50, blurImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(49, blurImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(49, blurImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(49, blurImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(49, blurImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(49, blurImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(49, blurImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(48, blurImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(48, blurImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(48, blurImageImpl.getColorAt(ColorType.BLUE, 1, 1));

  }

  @Test
  public void testSharpenOnBlur() {
    // blurred image
    ImageImpl blurImageImpl = blur.apply(img);

    assertEquals(90, blurImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(80, blurImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // sharpen applied to blurred image
    ImageImpl sharpenOnBlur = sharpen.apply(blurImageImpl);

    assertEquals(157, sharpenOnBlur.getColorAt(ColorType.RED, 0, 0));
    assertEquals(157, sharpenOnBlur.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(157, sharpenOnBlur.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(160, sharpenOnBlur.getColorAt(ColorType.RED, 1, 0));
    assertEquals(160, sharpenOnBlur.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(160, sharpenOnBlur.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(160, sharpenOnBlur.getColorAt(ColorType.RED, 0, 1));
    assertEquals(160, sharpenOnBlur.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(160, sharpenOnBlur.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(149, sharpenOnBlur.getColorAt(ColorType.RED, 1, 1));
    assertEquals(149, sharpenOnBlur.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(149, sharpenOnBlur.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testSepiaOnBlur() {
    // blurred image
    ImageImpl blurImageImpl = blur.apply(img);

    assertEquals(90, blurImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(80, blurImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // sepia applied to blurred image
    ImageImpl sepiaOnBlur = sepia.apply(blurImageImpl);

    assertEquals(121, sepiaOnBlur.getColorAt(ColorType.RED, 0, 0));
    assertEquals(108, sepiaOnBlur.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(84, sepiaOnBlur.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(128, sepiaOnBlur.getColorAt(ColorType.RED, 1, 0));
    assertEquals(114, sepiaOnBlur.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(89, sepiaOnBlur.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(128, sepiaOnBlur.getColorAt(ColorType.RED, 0, 1));
    assertEquals(114, sepiaOnBlur.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(89, sepiaOnBlur.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(108, sepiaOnBlur.getColorAt(ColorType.RED, 1, 1));
    assertEquals(96, sepiaOnBlur.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(74, sepiaOnBlur.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testGreyScaleOnBlur() {
    // blurred image
    ImageImpl blurImageImpl = blur.apply(img);

    assertEquals(90, blurImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(90, blurImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(95, blurImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(95, blurImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(80, blurImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(80, blurImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // greyscale applied to blurred image
    ImageImpl greyScaleOnBlur = greyscale.apply(blurImageImpl);

    assertEquals(90, greyScaleOnBlur.getColorAt(ColorType.RED, 0, 0));
    assertEquals(90, greyScaleOnBlur.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(90, greyScaleOnBlur.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(94, greyScaleOnBlur.getColorAt(ColorType.RED, 1, 0));
    assertEquals(94, greyScaleOnBlur.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(94, greyScaleOnBlur.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(94, greyScaleOnBlur.getColorAt(ColorType.RED, 0, 1));
    assertEquals(94, greyScaleOnBlur.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(94, greyScaleOnBlur.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(79, greyScaleOnBlur.getColorAt(ColorType.RED, 1, 1));
    assertEquals(79, greyScaleOnBlur.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(79, greyScaleOnBlur.getColorAt(ColorType.BLUE, 1, 1));
  }
}
