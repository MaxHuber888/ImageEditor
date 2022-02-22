package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import model.effects.Greyscale;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the ImageEditorModel class.
 */
public class ImageEditorModelTest {

  ImageEditorModel model;
  ImageImpl img;
  ImageImpl img2;
  ImageImpl img3;

  /**
   * Returns whether the two Images are the same (have the same width, height, and list of pixels).
   *
   * @param i1 ImageImpl, the first image
   * @param i2 ImageImpl, the second image
   * @return Boolean, are the two images the same?
   */
  public static boolean sameImage(ImageImpl i1, ImageImpl i2) {
    if (i1.getHeight() != i2.getHeight() || i1.getWidth() != i2.getWidth()) {
      return false;
    }
    for (int i = 0; i < i1.getNumPixels(); i++) {
      if (!samePixels(i1.getPixels().get(i), i2.getPixels().get(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns whether two Pixels are the same (same x,y, and rgb values).
   *
   * @param p1 Pixel, the first pixel
   * @param p2 Pixel, the second pixel
   * @return Boolean, are the two pixels the same?
   */
  private static boolean samePixels(Pixel p1, Pixel p2) {
    return p1.getX() == p2.getX() && p1.getY() == p2.getY() && p1.getRGB() == p2.getRGB();
  }

  @Before
  public void setUp() throws Exception {
    model = new ImageEditorModel();
    img = new ImageImpl(100);
    img2 = new ImageImpl(100, 10);
    img3 = new ImageImpl(100, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelNullLayers() {
    ImageEditorModel model = new ImageEditorModel(null);
  }

  @Test
  public void testApplyEffect() {
    model.importItem(img);
    Greyscale grey = new Greyscale();
    model.applyEffect(grey, 0);
    assertTrue(sameImage(model.exportTopLayer(), grey.apply(img)));
    model.importItem(img2);
    model.applyEffect(grey, 1);
    assertTrue(sameImage(model.exportTopLayer(), grey.apply(img2)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testApplyEffectInvalid() {
    model.importItem(img);
    Greyscale grey = new Greyscale();
    model.applyEffect(grey, -1);
    assertTrue(sameImage(model.exportTopLayer(), grey.apply(img)));
  }

  @Test
  public void importItem() {
    model.importItem(img);
    assertTrue(sameImage(img, model.exportTopLayer()));
  }

  @Test (expected = IllegalArgumentException.class)
  public void importItemNullImage() {
    img = null;
    model.importItem(img);
  }

  @Test
  public void exportItem() {
    model.importItem(img);
    assertTrue(sameImage(img,model.exportItem(0)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void exportItemInvalidIndex() {
    model.importItem(img);
    assertTrue(sameImage(img,model.exportItem(-1)));
  }

  @Test
  public void testGetTopVisibleLayerIndex() {
    model.importItem(img);
    model.importItem(img2);
    assertEquals(1, model.getTopVisibleLayerIndex());
    model.setVisibility(1,false);
    assertEquals(0, model.getTopVisibleLayerIndex());
  }

  @Test
  public void testGetLayerCount() {
    model.importItem(img);
    model.importItem(img2);
    assertEquals(2, model.getLayerCount());
    model.removeLayer(0);
    assertEquals(1,model.getLayerCount());
  }

  @Test
  public void testExportTopLayer() {
    model.importItem(img);
    model.importItem(img2);
    assertTrue(sameImage(img2, model.exportTopLayer()));
    model.setVisibility(1,false);
    assertTrue(sameImage(img, model.exportTopLayer()));
    model.removeLayer(0);
    assertTrue(sameImage(img2, model.exportTopLayer()));
  }

  @Test
  public void testRemoveLayer() {
    model.importItem(img);
    model.importItem(img2);
    assertEquals(2, model.getLayerCount());
    model.removeLayer(0);
    assertEquals(1, model.getLayerCount());
    model.removeLayer(0);
    assertEquals(0, model.getLayerCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidIndex() {
    model.importItem(img);
    model.importItem(img2);
    model.removeLayer(-1);
  }

  @Test
  public void testSetVisibility() {
    model.importItem(img);
    model.importItem(img2);
    model.importItem(img3);

    assertEquals(true, model.getVisibilities().get(0));
    assertEquals(true, model.getVisibilities().get(1));
    assertEquals(true, model.getVisibilities().get(2));
    model.setVisibility(1, false);
    assertEquals(true, model.getVisibilities().get(0));
    assertEquals(false, model.getVisibilities().get(1));
    assertEquals(true, model.getVisibilities().get(2));
    model.setVisibility(2, false);
    assertEquals(true, model.getVisibilities().get(0));
    assertEquals(false, model.getVisibilities().get(1));
    assertEquals(false, model.getVisibilities().get(2));
    model.setVisibility(1, true);
    assertEquals(true, model.getVisibilities().get(0));
    assertEquals(true, model.getVisibilities().get(1));
    assertEquals(false, model.getVisibilities().get(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetVisibilityInvalidIndex() {
    model.setVisibility(-1, true);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetVisibilityBottomLayer() {
    model.importItem(img);
    model.importItem(img2);
    model.setVisibility(0, false);
  }

  @Test
  public void testExportAll() {
    model.importItem(img);
    model.importItem(img2);
    model.importItem(img3);
    List<ImageImpl> list = model.exportAll();
    assertTrue(sameImage(img,list.get(0)));
    assertTrue(sameImage(img2,list.get(1)));
    assertTrue(sameImage(img3,list.get(2)));
  }

  @Test
  public void testGetVisibilities() {
    model.importItem(img);
    model.importItem(img2);
    assertEquals(Arrays.asList(true, true), model.getVisibilities());
    model.setVisibility(1, false);
    assertEquals(Arrays.asList(true, false), model.getVisibilities());
  }

  @Test
  public void testMoveToTop() {
    model.importItem(img);
    model.importItem(img2);
    assertTrue(sameImage(img2, model.exportTopLayer()));
    model.moveToTop(0);
    assertTrue(sameImage(img, model.exportTopLayer()));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToTopInvalidIndex() {
    model.importItem(img);
    model.importItem(img2);
    assertTrue(sameImage(img2, model.exportTopLayer()));
    model.moveToTop(-1);
  }
}