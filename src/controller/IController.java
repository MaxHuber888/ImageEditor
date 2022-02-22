package controller;

import model.effects.IEffect;

/**
 * Interface representing a controller class.
 */
public interface IController<T> {

  /**
   * Interprets the input and runs appropriate methods.
   * @param input Readable, should be interpretable by controller
   */
  void run(Readable input);

  /**
   * Runs the given text file as input for the run() method.
   * @param filename String, name of the text file
   */
  void file(String filename);

  /**
   * Saves the top visible layer with the given file name and file type.
   * @param filename String, name of the file once saved
   * @param filetype String, extension of the file once saved
   */
  void save(String filename, String filetype);

  /**
   * Loads the given file into the model.
   * @param filename String, name of the file to be loaded
   * @param filetype String, extension of the file to be loaded
   * @param visibility Boolean, is the file visible when loaded?
   */
  void load(String filename, String filetype, Boolean visibility);

  /**
   * Applies the given effect to the top visible layer of the model.
   * @param effect IEffect, applied to the top visible layer
   */
  void applyEffect(IEffect<T> effect);

  /**
   * Removes the top layer of the model.
   */
  void remove();

  /**
   * Moves the layer of given index to the top of the model's stack.
   * @param index Integer, index of the layer to move up
   */
  void toTop(int index);

  /**
   * Makes the layer of given index invisible in the model.
   * Does nothing if index is 0.
   * @param index Integer, the index of the layer to make invisible
   */
  void invisible(int index);

  /**
   * Makes the layer of given index visible in the model.
   * @param index Integer, the index of the layer to make visible
   */
  void visible(int index);

  /**
   * Initiates a multi-image export from the model.
   * @param filename String, name used for the images and the script file
   */
  void exportAll(String filename);

  /**
   * Updates the output (View, Appendable, etc.).
   */
  void updateOutput();
}
