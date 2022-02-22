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
 * Class for testing greyscale effect.
 */
public class GreyscaleTest {

  List<Pixel> list;
  ImageImpl img;
  BlurFilter blur;
  Sepia sepia;
  Greyscale greyscale;
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
  public void testGreyScaleNullImage() {
    ImageImpl grey = greyscale.apply(null);
  }

  @Test
  public void applyGreyscaleOnce() {
    ImageImpl greyscaleImageImpl = greyscale.apply(img);

    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void applyGreyscaleTwice() {
    ImageImpl onceGreyscaleImageImpl = greyscale.apply(img);
    ImageImpl greyscaleImageImpl = greyscale.apply(onceGreyscaleImageImpl);

    assertEquals(235, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(235, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(235, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(195, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(195, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(195, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testSepiaOnGreyScale() {
    // greyscale applied to image
    ImageImpl greyscaleImageImpl = greyscale.apply(img);
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // sepia applied to greyscale image
    ImageImpl sepiaImageImpl = sepia.apply(greyscaleImageImpl);
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(221, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(240, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(187, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(240, sepiaImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(187, sepiaImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(255, sepiaImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(235, sepiaImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(183, sepiaImageImpl.getColorAt(ColorType.BLUE, 1, 1));

  }

  @Test
  public void testBlurOnGreyScale() {
    // greyscale applied to image
    ImageImpl greyscaleImageImpl = greyscale.apply(img);
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // blur applied to greyscale image
    ImageImpl blurOnGreyImageImpl = blur.apply(greyscaleImageImpl);
    assertEquals(121, blurOnGreyImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(121, blurOnGreyImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(121, blurOnGreyImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(116, blurOnGreyImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(116, blurOnGreyImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(116, blurOnGreyImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(116, blurOnGreyImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(116, blurOnGreyImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(116, blurOnGreyImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(113, blurOnGreyImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(113, blurOnGreyImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(113, blurOnGreyImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }

  @Test
  public void testSharpenOnGreyScale() {
    // greyscale applied to image
    ImageImpl greyscaleImageImpl = greyscale.apply(img);
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(236, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(200, greyscaleImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(196, greyscaleImageImpl.getColorAt(ColorType.BLUE, 1, 1));

    // sharpen applied to greyscale image
    ImageImpl sharpOnGreyImageImpl = blur.apply(greyscaleImageImpl);
    assertEquals(121, sharpOnGreyImageImpl.getColorAt(ColorType.RED, 0, 0));
    assertEquals(121, sharpOnGreyImageImpl.getColorAt(ColorType.GREEN, 0, 0));
    assertEquals(121, sharpOnGreyImageImpl.getColorAt(ColorType.BLUE, 0, 0));

    assertEquals(116, sharpOnGreyImageImpl.getColorAt(ColorType.RED, 1, 0));
    assertEquals(116, sharpOnGreyImageImpl.getColorAt(ColorType.GREEN, 1, 0));
    assertEquals(116, sharpOnGreyImageImpl.getColorAt(ColorType.BLUE, 1, 0));

    assertEquals(116, sharpOnGreyImageImpl.getColorAt(ColorType.RED, 0, 1));
    assertEquals(116, sharpOnGreyImageImpl.getColorAt(ColorType.GREEN, 0, 1));
    assertEquals(116, sharpOnGreyImageImpl.getColorAt(ColorType.BLUE, 0, 1));

    assertEquals(113, sharpOnGreyImageImpl.getColorAt(ColorType.RED, 1, 1));
    assertEquals(113, sharpOnGreyImageImpl.getColorAt(ColorType.GREEN, 1, 1));
    assertEquals(113, sharpOnGreyImageImpl.getColorAt(ColorType.BLUE, 1, 1));
  }
}