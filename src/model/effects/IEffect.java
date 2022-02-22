package model.effects;

/**
 * Interface representing an effect that can be applied to a generic type.
 */
public interface IEffect<T> {

  /**
   * Returns a new image with an image processing effect applied to the given image.
   *
   * @param input Generic, the specified input to process
   * @return Generic, the new input after the effect has been applied
   * @throws IllegalArgumentException if given input is null
   */
  T apply(T input) throws IllegalArgumentException;
}
