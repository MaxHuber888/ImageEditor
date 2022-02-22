package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a Sharpen image effect.
 */
public class SharpenFilter extends AbstractFilter {

  @Override
  public ImageImpl apply(ImageImpl input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("null imageImpl");
    }
    List<Pixel> sharpenPixels = new ArrayList<>();
    for (int i = 0; i < input.getNumPixels(); i++) {
      Pixel pixel = input.getPixels().get(i);
      sharpenPixels.add(helper(input,pixel,1,.25,.25,-.125));
    }
    return new ImageImpl(sharpenPixels, input.getWidth(), input.getHeight());
  }
}
