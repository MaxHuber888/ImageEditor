package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;
import model.ImageImpl;
import model.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing IOHandler.
 */
public class IOHandlerImplTest {

  IOHandler<ImageImpl> handler;
  ImageImpl img;

  @Before
  public void setUp() throws Exception {
    handler = new IOHandlerImpl();
    img = new ImageImpl(70); //70x70 rainbow square
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
  public void exportImageAndImportImagePPM() {
    handler.exportItem("test", "ppm", img);
    ImageImpl img2 = handler.importItem("test", "ppm");
    assertTrue(sameImage(img, img2));
  }

  @Test
  public void exportImageAndImportImagePNG() {
    handler.exportItem("test", "png", img);
    ImageImpl img2 = handler.importItem("test", "png");
    assertTrue(sameImage(img, img2));
  }


  @Test
  public void exportImageAndImportImageJPG() {
    handler.exportItem("test", "jpg", img);
    ImageImpl img2 = handler.importItem("test", "jpg");
    //due to JPG compression, the imported image will not be a perfect match
    assertEquals(img.toString(),img2.toString());
  }

  @Test
  public void exportTextAndImportText() {
    handler.exportText("test", "HELLO WORLD");
    Readable r = handler.importText("test");
    Scanner sc = new Scanner(r);
    StringBuffer sb = new StringBuffer();
    while (sc.hasNext()) {
      sb.append(sc.nextLine());
    }
    assertEquals("HELLO WORLD", sb.toString());
  }
}