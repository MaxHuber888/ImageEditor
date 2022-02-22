package view;

import model.Image;

/**
 * Interface representing an ImageView class.
 */
public interface ImageView {

  /**
   * This method is responsible for updating the image window to show the given image.
   * @param newImage ImageImpl, the new image to be shown
   */
  void updateView(Image newImage);

  /**
   * Returns the filename inputted by the user.
   * @return String, the name of the file
   */
  String getFilename();

  /**
   * Returns the filetype inputted by the user.
   * @return String, the extension of the file
   */
  String getFiletype();

  /**
   * Returns the layer index inputted by the user.
   * @return Integer, the index of the specified layer
   */
  int getIndex();

  /**
   * Returns the effect specified by the user.
   * @return String, the name of the effect selected by the user
   */
  String getEffect();

  /**
   * The view displays the given output.
   * @param output String, the message to display
   */
  void showOutput(String output);

  /**
   * The view displays what layer is being viewed, as well as the total layer count.
   * @param index Integer, index of current layer
   * @param total Integer, total number of layers
   */
  void showCurrentLayerIndex(int index,int total);
}
