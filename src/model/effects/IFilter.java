package model.effects;

import model.ImageImpl;
import model.Pixel;

/**
 * Interface representing a subset of Image filters that involve a filter.
 */
public interface IFilter extends IEffect<ImageImpl> {
  Pixel helper(ImageImpl img,Pixel centerPixel,double center,
      double innerCorner,double inner,double outer);
}
