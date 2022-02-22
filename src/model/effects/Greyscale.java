package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a model.effects.Greyscale Tone image effect. Applies a greyscale color
 * transformation to the given image. Computes the result of the transformation using matrix
 * multiplication on the pixels and channels. Generates a new image that is a copy of the
 * original image with the greyscale effect applied.
 */
public class Greyscale implements IEffect<ImageImpl> {

  @Override
  public ImageImpl apply(ImageImpl imageImpl) throws IllegalArgumentException {
    if (imageImpl == null) {
      throw new IllegalArgumentException("null imageImpl");
    }
    List<Pixel> greyScalePixels = new ArrayList<>();
    for (int i = 0; i < imageImpl.getNumPixels(); i++) {
      Pixel pixel = imageImpl.getPixels().get(i);
      greyScalePixels.add(new Pixel(pixel.getX(), pixel.getY(),
          (int) (0.2126 * pixel.getR() + 0.7152 * pixel.getG() + 0.0722 * pixel.getB()),
          (int) (0.2126 * pixel.getR() + 0.7152 * pixel.getG() + 0.0722 * pixel.getB()),
          (int) (0.2126 * pixel.getR() + 0.7152 * pixel.getG() + 0.0722 * pixel.getB())));
    }
    ImageImpl greyScaleImageImpl = new ImageImpl(greyScalePixels, imageImpl.getWidth(),
        imageImpl.getHeight());
    return greyScaleImageImpl;
  }
}
