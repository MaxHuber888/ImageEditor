package model.effects;

import java.util.ArrayList;
import java.util.List;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a model.effects.Sepia Tone image effect. Applies a sepia color
 * transformation to the given image. Computes the result of the transformation using
 * matrix multiplication on the pixels and channels. Generates a new image that is a
 * copy of the original image with the sepia effect applied.
 */
public class Sepia implements IEffect<ImageImpl> {

  @Override
  public ImageImpl apply(ImageImpl imageImpl) throws IllegalArgumentException {
    if (imageImpl == null) {
      throw new IllegalArgumentException("null imageImpl");
    }
    List<Pixel> sepiaPixels = new ArrayList<Pixel>();
    for (int i = 0; i < imageImpl.getNumPixels(); i++) {
      Pixel pixel = imageImpl.getPixels().get(i);
      sepiaPixels.add(new Pixel(pixel.getX(), pixel.getY(),
          (int) (0.393 * pixel.getR() + 0.769 * pixel.getG() + 0.189 * pixel.getB()),
          (int) (0.349 * pixel.getR() + 0.686 * pixel.getG() + 0.168 * pixel.getB()),
          (int) (0.272 * pixel.getR() + 0.534 * pixel.getG() + 0.131 * pixel.getB())));
    }
    ImageImpl sepiaImageImpl = new ImageImpl(sepiaPixels, imageImpl.getWidth(),
        imageImpl.getHeight());
    return sepiaImageImpl;
  }
}
