package model;

/**
 * Class representing a pixel. A pixel has a x coordinate, y coordinate, red value, green value, and
 * a blue value. May be observed by various getters and setters. RGB values may be clamped.
 */
public class Pixel {

  private final int x;
  private final int y;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor for a pixel.
   *
   * @param x Integer, the value of the x coordinate of the pixel
   * @param y Integer, the value of the y coordinate of the pixel
   * @param r Integer, the value of the red channel of the pixel
   * @param g Integer, the value of the green channel of the pixel
   * @param b Integer, the value of the blue channel of the pixel
   * @throws IllegalArgumentException if the x or y coordinate are invalid.
   */
  public Pixel(int x, int y, int r, int g, int b) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid parameter.");
    }
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
    this.clampColors();
  }

  /**
   * Returns the value of the pixel's x-coordinate.
   *
   * @return Integer, the value of the pixel's x-coordinate.
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the value of the pixel's y-coordinate.
   *
   * @return Integer, the value of the pixel's y-coordinate.
   */
  public int getY() {
    return y;
  }

  /**
   * Returns the value of the pixel's red channel value.
   *
   * @return Integer, the value of the pixel's red channel value.
   */
  public int getR() {
    return r;
  }

  /**
   * Returns the value of the pixel's green channel value.
   *
   * @return Integer, the value of the pixel's green channel value.
   */
  public int getG() {
    return g;
  }

  /**
   * Returns the value of the pixel's blue channel value.
   *
   * @return Integer, the value of the pixel's blue channel value.
   */
  public int getB() {
    return b;
  }


  /**
   * Returns the rgb values of the pixel as a single integer, using the formula: rgb = 65536r + 256g
   * + b.
   *
   * @return Integer, the rgb value of the pixel
   */
  public int getRGB() {
    clampColors();
    //returns the rgb as a single integer
    return (65536 * r) + (256 * g) + b;
  }

  /**
   * Returns a clamped version of the given integer. If the given integer is > 255, it is set to
   * 255. If it is < 0, it is set 0.
   *
   * @param x Integer, returned clamped
   * @return Intger, returns a clamped version of the given integer
   */
  private int clamp(int x) {
    if (x > 255) {
      return 255;
    }
    if (x < 0) {
      return 0;
    }
    return x;
  }

  /**
   * Clamps all the color values of the pixel.
   */
  public void clampColors() {
    //clamps all the rgb values
    r = clamp(r);
    g = clamp(g);
    b = clamp(b);
  }

  /**
   * Returns the specified color value.
   * @param color ColorType, either RED,GREEN,or BLUE
   * @return Integer, the specified color value
   */
  public int getColor(ColorType color) {
    switch (color) {
      case RED:
        return this.r;
      case GREEN:
        return this.g;
      case BLUE:
        return this.b;
      default:
        throw new IllegalArgumentException("Color not supported");
    }
  }
}
