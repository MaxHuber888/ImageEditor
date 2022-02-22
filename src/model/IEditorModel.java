package model;

import java.util.List;
import model.effects.IEffect;

/**
 * Interface representing a model that handles and applies effects to multiple things of type T.
 */
public interface IEditorModel<T> {

  /**
   * Applies the given effect to the specified item.
   *
   * @param effect IEffect, applied to the specified item
   * @param index  int, the index of the specified item
   * @throws IllegalArgumentException if index is out of bounds
   */
  void applyEffect(IEffect<T> effect, int index) throws IllegalArgumentException;

  /**
   * Imports the given item into the model's collection.
   *
   * @param input Generic, the item to be added to the model
   */
  void importItem(T input);

  /**
   * Returns the item at the given index.
   *
   * @param index int, the index of the specified image
   * @return Generic, returns the specified Item
   */
  T exportItem(int index);

  /**
   * Returns the index of the topmost visible layer.
   * @return
   */
  int getTopVisibleLayerIndex();

  /**
   * Returns the number of layers.
   * @return Integer, number of layers
   */
  int getLayerCount();

  /**
   * Sets the visibility of the specified layer. Does not affect the bottom layer.
   *
   * @param index Integer, index of the specified layer
   * @param b     Boolean, the visibility value to set for that layer
   * @throws IllegalArgumentException if index is invalid
   */
  void setVisibility(int index, boolean b) throws IllegalArgumentException;

  /**
   * Returns a shallow copy of all layers in a list.
   *
   * @return List of Generics, all layers
   */
  List<T> exportAll();

  /**
   * Getter for retrieving the list of visibilities.
   *
   * @return List of Boolean, the list of visibilities.
   */
  List<Boolean> getVisibilities();

  /**
   * Moves the specified layer to the top of the stack.
   *
   * @param index Integer, specifies which layer to move to the top
   */
  void moveToTop(int index);

  /**
   * Removes the specified layer.
   *
   * @param index Integer, index of the layer to remove
   * @throws IllegalArgumentException if the index is invalid
   */
  void removeLayer(int index) throws IllegalArgumentException;

  /**
   * Returns the topmost visible layer.
   *
   * @return ImageImpl, the top layer that has a visibility of true.
   * @throws IllegalArgumentException if all layers are invisible.
   */
  T exportTopLayer() throws IllegalArgumentException;

}
