package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ColorType;
import model.ImageImpl;
import model.Pixel;

/**
 * Abstract class representing a filter effect.
 */
abstract class AbstractFilter implements IFilter {

  @Override
  public Pixel helper(ImageImpl img, Pixel centerPixel, double center, double innerCorner,
      double inner, double outer) {
    if (img == null) {
      throw new IllegalArgumentException("null ImageImpl");
    }
    if (centerPixel == null) {
      throw new IllegalArgumentException("null centerPixel");
    }
    int x = centerPixel.getX();
    int y = centerPixel.getY();

    List<Pixel> innerCornerList = new ArrayList<>();
    innerCornerList.add(img.getPixelAt(x - 1, y - 1));
    innerCornerList.add(img.getPixelAt(x - 1, y + 1));
    innerCornerList.add(img.getPixelAt(x + 1, y - 1));
    innerCornerList.add(img.getPixelAt(x + 1, y + 1));

    List<Pixel> innerList = new ArrayList<>();
    innerList.add(img.getPixelAt(x - 1, y));
    innerList.add(img.getPixelAt(x + 1, y));
    innerList.add(img.getPixelAt(x, y - 1));
    innerList.add(img.getPixelAt(x, y + 1));

    List<Pixel> outerList = new ArrayList<>();
    outerList.add(img.getPixelAt(x - 2, y + 2));
    outerList.add(img.getPixelAt(x - 2, y + 1));
    outerList.add(img.getPixelAt(x - 2, y));
    outerList.add(img.getPixelAt(x - 2, y - 1));
    outerList.add(img.getPixelAt(x - 2, y - 2));
    outerList.add(img.getPixelAt(x - 1, y - 2));
    outerList.add(img.getPixelAt(x - 1, y + 2));
    outerList.add(img.getPixelAt(x, y - 2));
    outerList.add(img.getPixelAt(x, y + 2));
    outerList.add(img.getPixelAt(x + 1, y - 2));
    outerList.add(img.getPixelAt(x + 1, y + 2));
    outerList.add(img.getPixelAt(x + 2, y + 2));
    outerList.add(img.getPixelAt(x + 2, y + 1));
    outerList.add(img.getPixelAt(x + 2, y));
    outerList.add(img.getPixelAt(x + 2, y - 1));
    outerList.add(img.getPixelAt(x + 2, y - 2));

    int newRed = (int) (accumulateColor(innerList,ColorType.RED) * inner)
        + (int) (accumulateColor(innerCornerList,ColorType.RED) * innerCorner)
        + (int) (accumulateColor(outerList,ColorType.RED) * outer)
        + (int) (center * centerPixel.getColor(ColorType.RED));

    int newBlue = (int) (accumulateColor(innerList,ColorType.BLUE) * inner)
        + (int) (accumulateColor(innerCornerList,ColorType.BLUE) * innerCorner)
        + (int) (accumulateColor(outerList,ColorType.BLUE) * outer)
        + (int) (center * centerPixel.getColor(ColorType.BLUE));

    int newGreen = (int) (accumulateColor(innerList,ColorType.GREEN) * inner)
        + (int) (accumulateColor(innerCornerList,ColorType.GREEN) * innerCorner)
        + (int) (accumulateColor(outerList,ColorType.GREEN) * outer)
        + (int) (center * centerPixel.getColor(ColorType.GREEN));

    return new Pixel(x,y,newRed,newGreen,newBlue);
  }

  /**
   * Returns an accumulated color value for the given List
   * of Pixels for the specified color.
   * @param list List of Pixel, the pixels of which the color
   *             values will be accumulated. Some may be null.
   * @param color ColorType, specified color value that is accumulated
   * @return Integer, accumulated color value
   */
  protected int accumulateColor(List<Pixel> list,ColorType color) {
    int total = 0;
    for (Pixel x: list) {
      if (x != null) {
        total = total + x.getColor(color);
      }
    }
    return total;
  }

}
