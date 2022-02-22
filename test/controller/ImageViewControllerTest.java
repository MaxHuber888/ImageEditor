package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import model.ImageEditorModel;
import model.ImageEditorModelTest;
import model.ImageImpl;
import model.Pixel;
import model.effects.BlurFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing ImageViewController.
 */
public class ImageViewControllerTest {
  ImageEditorModel model;
  ImageImpl img;
  ImageImpl img2;
  ImageImpl img3;
  ImageViewController controller;
  IOHandler<ImageImpl> handler;

  @Before
  public void setUp() {
    model = new ImageEditorModel();
    img = new ImageImpl(100);
    img2 = new ImageImpl(100, 10);
    img3 = new ImageImpl(30, 3);
    model.importItem(img);
    model.importItem(img2);
    model.importItem(img3);
    controller = new ImageViewController(model);
    handler = new IOHandlerImpl();
  }

  /**
   * Returns whether the two Images are the same (have the same width, height, and list of pixels).
   *
   * @param i1 ImageImpl, the first image
   * @param i2 ImageImpl, the second image
   * @return Boolean, are the two images the same?
   */
  public static boolean sameImage(ImageImpl i1, ImageImpl i2) {
    if (i1.getHeight() != i2.getHeight() || i1.getWidth() != i2.getWidth()
        || i1.getNumPixels() != i2.getNumPixels()) {
      return false;
    }
    int matching = 0;
    for (Pixel x : i1.getPixels()) {
      for (Pixel y : i2.getPixels()) {
        if (samePixels(x, y)) {
          matching++;
        }
      }
    }
    return matching == i1.getNumPixels();
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


  @Test
  public void run() {
    controller.run(new StringReader("toTop 0"));
    assertTrue(ImageEditorModelTest.sameImage(img, model.exportTopLayer()));
  }

  //updateOutput was tested extensively using the view GUI. No tests
  // can be written for it because the controller does not give external
  // access to its own view.

  @Test
  public void file() {
    handler.exportText("test","toTop 0");
    assertTrue(ImageEditorModelTest.sameImage(img3, model.exportTopLayer()));
    controller.file("test");
    assertTrue(ImageEditorModelTest.sameImage(img, model.exportTopLayer()));
  }

  @Test
  public void savePPMLoadPPM() {
    controller.save("testsave2","ppm");
    controller.load("testsave2","ppm",true);
    assertTrue(ImageEditorModelTest.sameImage(img3, model.exportTopLayer()));
  }

  @Test
  public void savePNGLoadPNG() {
    controller.save("testsave2","png");
    model.removeLayer(1);
    controller.load("testsave2","png",true);
    assertTrue(sameImage(img3,model.exportTopLayer()));
  }

  @Test
  public void saveJPGLoadJPG() {
    controller.save("testsave2","jpg");
    controller.load("testsave2","jpg",true);
    //can't perform sameImage because JPG compresses image
    assertEquals(img3.getHeight(),model.exportTopLayer().getHeight());
    assertEquals(img3.getWidth(),model.exportTopLayer().getWidth());
    assertEquals(img3.getNumPixels(),model.exportTopLayer().getNumPixels());
  }

  @Test
  public void applyEffect() {
    controller.applyEffect(new BlurFilter());
    BlurFilter blur = new BlurFilter();
    assertTrue(ImageEditorModelTest.sameImage(blur.apply(img3), model.exportTopLayer() ));
  }

  @Test
  public void remove() {
    controller.remove();
    assertTrue(ImageEditorModelTest.sameImage(img2, model.exportTopLayer()));
  }

  @Test
  public void toTop() {
    controller.toTop(0);
    assertTrue(ImageEditorModelTest.sameImage(img, model.exportTopLayer()));
  }

  @Test
  public void invisible() {
    assertEquals(2,model.getTopVisibleLayerIndex());
    controller.invisible(2);
    assertEquals(1,model.getTopVisibleLayerIndex());
    controller.invisible(1);
    assertEquals(0,model.getTopVisibleLayerIndex());
  }

  @Test
  public void visible() {
    assertEquals(2,model.getTopVisibleLayerIndex());
    controller.invisible(2);
    assertEquals(1,model.getTopVisibleLayerIndex());
    controller.visible(2);
    assertEquals(2,model.getTopVisibleLayerIndex());
  }

  @Test
  public void exportAll() {
    controller.exportAll("testExport");
    controller.file("testExport");
    assertEquals(6,model.getLayerCount());
  }
}