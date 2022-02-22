package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a Blur image effect.
 */
public class BlurFilter extends AbstractFilter {

  @Override
  public ImageImpl apply(ImageImpl input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("null imageImpl");
    }
    List<Pixel> blurPixels = new ArrayList<>();
    for (int i = 0; i < input.getNumPixels(); i++) {
      Pixel pixel = input.getPixels().get(i);
      blurPixels.add(helper(input,pixel,.25,.0625,.125,0));
    }
    return new ImageImpl(blurPixels, input.getWidth(), input.getHeight());
  }
}
