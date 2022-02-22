package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an ImageImpl. Each image has a list of pixels, a width and a height.
 * Images may be generated programmatically, such as a checkerboard or rainbow. The width,
 * height, and pixel count of the image may be printed to a string. Values may be observed
 * with various getters and setters. Pixels may be clamped.
 */
public class ImageImpl implements Image {

  private final List<Pixel> pixels;
  private final int width;
  private final int height;

  /**
   * Constructor for an image.
   *
   * @param pixels List, the list of pixels that make up the image.
   * @param width  Integer, the width of the image.
   * @param height Integer, the height of the image.
   * @throws IllegalArgumentException if a parameter is invalid.
   */
  public ImageImpl(List<Pixel> pixels, int width, int height) throws IllegalArgumentException {
    if (pixels == null || width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.pixels = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * Constructor for programmatic rainbow image.
   *
   * @param dimension Integer, the integer value of the desired dimension
   * @throws IllegalArgumentException if the parameter is invalid.
   */
  public ImageImpl(int dimension) throws IllegalArgumentException {
    if (dimension <= 0) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    this.width = dimension;
    this.height = dimension;
    this.pixels = makeRainbow();
  }

  /**
   * Constructor for programmatic checkerboard image.
   *
   * @param dimension      Integer, the integer value of the desired dimension
   * @param squaresPerLine Integer, the desired number of squares per row
   * @throws IllegalArgumentException if the parameters are invalid
   */
  public ImageImpl(int dimension, int squaresPerLine) throws IllegalArgumentException {
    if (dimension <= 0 || squaresPerLine <= 0) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.width = dimension;
    this.height = dimension;
    this.pixels = makeCheckerBoard(squaresPerLine);
  }

  /**
   * Creates a list of pixels that represents a checkerboard and fits the dimensions of the image.
   *
   * @param squares Integer, the number of squares per row
   * @return List of Pixels, forms a checkerboard pattern
   * @throws IllegalArgumentException if given an invalid number of squares
   */
  private List<Pixel> makeCheckerBoard(int squares) throws IllegalArgumentException {
    if (squares <= 0) {
      throw new IllegalArgumentException("invalid number of squares");
    }
    List<Pixel> pixels = new ArrayList<>();
    int squareSize = width / squares;
    for (int i = 0; i < width * height; i++) {
      if ((i % width) / squareSize % 2 == 0
          && (i / width) / squareSize % 2 == 0) { //odd square, odd row
        pixels.add(new Pixel(i % width, i / width, 0, 0, 0));
      } else if ((i % width) / squareSize % 2 != 0
          && (i / width) / squareSize % 2 == 0) { //even square, odd row
        pixels.add(new Pixel(i % width, i / width, 255, 255, 255));
      } else if ((i % width) / squareSize % 2 == 0
          && (i / width) / squareSize % 2 != 0) { //odd square, even row
        pixels.add(new Pixel(i % width, i / width, 255, 255, 255));
      } else if ((i % width) / squareSize % 2 != 0
          && (i / width) / squareSize % 2 != 0) { //even square, even row
        pixels.add(new Pixel(i % width, i / width, 0, 0, 0));
      } else {
        pixels.add(new Pixel(i % width, i / width, 0, 0, 0));
      }
    }
    return pixels;
  }

  /**
   * Creates a list of pixels that represents a rainbow and fits the dimensions of the image.
   *
   * @return List of Pixels, forms a rainbow
   */
  private List<Pixel> makeRainbow() {
    List<Pixel> pixels = new ArrayList<>();
    for (int i = 0; i < width * height; i++) {
      if (i % width < width / 7) {
        pixels.add(new Pixel(i % width, i / width, 255, 0, 0));
      } else if (i % width < width * 2 / 7) {
        //orange
        pixels.add(new Pixel(i % width, i / width, 255, 130, 0));
      } else if (i % width < width * 3 / 7) {
        //yellow
        pixels.add(new Pixel(i % width, i / width, 255, 255, 0));
      } else if (i % width < width * 4 / 7) {
        //green
        pixels.add(new Pixel(i % width, i / width, 0, 255, 0));
      } else if (i % width < width * 5 / 7) {
        //blue
        pixels.add(new Pixel(i % width, i / width, 0, 0, 255));
      } else if (i % width < width * 6 / 7) {
        //indigo
        pixels.add(new Pixel(i % width, i / width, 75, 0, 130));
      } else if (i % width < width) {
        //violet
        pixels.add(new Pixel(i % width, i / width, 238, 130, 238));
      } else {
        pixels.add(new Pixel(i % width, i / width, 0, 0, 0));
      }
    }
    return pixels;
  }

  @Override
  public String toString() {
    return "Width: " + width + " Height: " + height + " Pixel Count: " + pixels.size();
  }

  @Override
  public int getNumPixels() {
    return pixels.size();
  }


  @Override
  public int getWidth() {
    return width;
  }


  @Override
  public int getHeight() {
    return height;
  }


  @Override
  public List<Pixel> getPixels() {
    return pixels;
  }


  /**
   * Retrieves pixel at specified coordinates.
   *
   * @param x x coordinate of pixel
   * @param y y coordinate of pixel
   * @return the specified pixel
   */
  public Pixel getPixelAt(int x, int y) {
    Pixel pix = null;
    for (Pixel pixel : pixels) {
      if (pixel.getX() == x && pixel.getY() == y) {
        pix = pixel;
      }
    }
    return pix;
  }


  /**
   * Returns the value of the specified color of the pixel at the given x,y coordinates.
   *
   * @param color String, the specified color value to return
   * @param x     Integer, the x coordinate of the specified pixel
   * @param y     Integer, the y coordinate of the specified pixel
   * @return Integer, the specified color value of the specified pixel
   * @throws IllegalArgumentException if the given color is invalid
   */
  public int getColorAt(ColorType color, int x, int y) throws IllegalArgumentException {
    Pixel p = getPixelAt(x,y);
    if (p == null) {
      throw new IllegalArgumentException("Pixel not contained in Image");
    }
    return p.getColor(color);
  }


}
