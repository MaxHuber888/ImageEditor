package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ColorType;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing an image downscale operation. Downscales an image based on the desired width
 * and height, and returns a new Image with these new dimensions.
 */
public class Downscale implements IEffect<ImageImpl> {

  /**
   * Applies a downscale effect to the given image with the given desired width and height.
   * @param image ImageImpl, the image to apply the effect to
   * @param desiredWidth Integer, the desired width
   * @param desiredHeight Integer, the desired height
   * @return ImageImpl, the image with the applied effect
   * @throws IllegalArgumentException when image is null or desired dimensions are invalid
   */
  public ImageImpl applySpecific(ImageImpl image, int desiredWidth, int desiredHeight)
      throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("null image");
    }
    if (desiredWidth < 0 || desiredHeight < 0 || desiredWidth > image.getWidth()
        || desiredHeight > image
        .getHeight()) {
      throw new IllegalArgumentException("invalid width and/or height");
    }
    List<Pixel> pixels = new ArrayList<>();
    ImageImpl newImage = new ImageImpl(pixels, desiredWidth, desiredHeight);
    for (int x = 0; x < desiredWidth; x++) {
      for (int y = 0; y < desiredHeight; y++) {
        pixels.add(helper(image, x, y, desiredWidth, desiredHeight));
      }
    }
    return newImage;
  }

  /**
   * Creates a new pixel for the specified mapped location based on the desired new width and
   * height. newX and newY are the coordinates of the mapped location in the downsized image,
   * desiredWidth and desiredHeight are the desired dimensions.
   * @param image ImageImpl, the image being affected
   * @param newX Integer, the x value of the pixel
   * @param newY Integer, the y value of the pixel
   * @param desiredWidth Integer, the desired width
   * @param desiredHeight Integer, the desired height
   * @return Pixel, the new pixel for the downsized image
   */
  private Pixel helper(ImageImpl image, int newX, int newY, int desiredWidth, int desiredHeight) {
    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();

    double scaledX = ((double) newX / (double) desiredWidth) * originalWidth;
    double scaledY = ((double) newY / (double) desiredHeight) * originalHeight;

    Pixel a = image.getPixelAt((int) scaledX, (int) scaledY);
    Pixel b = image.getPixelAt((int) Math.ceil(scaledX), (int) scaledY);
    Pixel c = image.getPixelAt((int) scaledX, (int) Math.ceil(scaledY));
    Pixel d = image.getPixelAt((int) Math.ceil(scaledX), (int) Math.ceil(scaledY));

    int newR = mathHelper(image, a, b, c, d, ColorType.RED, scaledX, scaledY);
    int newG = mathHelper(image, a, b, c, d, ColorType.GREEN, scaledX, scaledY);
    int newB = mathHelper(image, a, b, c, d, ColorType.BLUE, scaledX, scaledY);

    return new Pixel(newX, newY, newR, newG, newB);

  }

  /**
   * Calculates the value of the specified color based on the four integer pixel locations around
   * the given floating point location. a, b, c, and d are the four integer pixel locations, and x
   * and y are the coordinates of the floating point location.
   * @param image ImageImpl, the image being affected
   * @param a Pixel, the first of the four original pixels
   * @param b Pixel, the second of the four original pixels
   * @param c Pixel, the third of the four original pixels
   * @param d Pixel, the fourth of the four original pixels
   * @param color ColorType, the color type to find a new value for
   * @param x Double, x of the point
   * @param y Double, y of the point
   * @return Integer, the new color value for the specified color
   */
  private int mathHelper(ImageImpl image, Pixel a, Pixel b, Pixel c, Pixel d, ColorType color,
      double x, double y) {
    double m;
    double n;
    int cP;
    if (x == Math.floor(x) || y == Math.floor(y)) {
      switch (color) {
        case RED:
          cP = image.getColorAt(ColorType.RED, (int) x, (int) y);
          break;
        case GREEN:
          cP = image.getColorAt(ColorType.GREEN, (int) x, (int) y);
          break;
        case BLUE:
          cP = image.getColorAt(ColorType.BLUE, (int) x, (int) y);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + color);
      }
    } else {
      switch (color) {
        case RED:
          m = b.getR() * (x - (int) x) + a.getR() * ((int) Math.ceil(x) - x);
          n = d.getR() * (x - (int) x) + c.getR() * ((int) Math.ceil(x) - x);

          cP = (int) (n * (y - (int) y) + m * ((int) Math.ceil(y) - y));
          break;
        case GREEN:
          m = b.getG() * (x - (int) x) + a.getG() * ((int) Math.ceil(x) - x);
          n = d.getG() * (x - (int) x) + c.getG() * ((int) Math.ceil(x) - x);

          cP = (int) (n * (y - (int) y) + m * ((int) Math.ceil(y) - y));
          break;
        case BLUE:
          m = b.getB() * (x - (int) x) + a.getB() * ((int) Math.ceil(x) - x);
          n = d.getB() * (x - (int) x) + c.getB() * ((int) Math.ceil(x) - x);

          cP = (int) (n * (y - (int) y) + m * ((int) Math.ceil(y) - y));
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + color);
      }
    }
    return cP;
  }

  @Override
  public ImageImpl apply(ImageImpl input) throws IllegalArgumentException {
    return applySpecific(input,input.getWidth() / 2,input.getHeight() / 2);
  }
}
