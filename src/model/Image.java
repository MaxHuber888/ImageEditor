package model;

import java.util.List;

/**
 * Interface representing an image.
 */
public interface Image {

  /**
   * Returns the number of pixels in the image.
   *
   * @return Integer, the size of the list of pixels
   */
  int getNumPixels();

  /**
   * Returns the width of the image.
   *
   * @return Integer, the width of the image
   */
  int getWidth();

  /**
   * Returns the height of the image.
   *
   * @return Integer, the height of the image.
   */
  int getHeight();

  /**
   * Returns the list of pixels in the image.
   *
   * @return List of Pixels, the pixels in the image.
   */
  List<Pixel> getPixels();
}
