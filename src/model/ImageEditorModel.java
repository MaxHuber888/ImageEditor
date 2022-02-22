package model;

import java.util.ArrayList;
import java.util.List;
import model.effects.IEffect;

/**
 * Class representing a model that handles multiple images. A model is represented by a list of
 * images which represent layers and a list of booleans which represent the visibilities of each
 * layer. Has various observer methods to retrieve information from the model.
 */
public class ImageEditorModel implements IEditorModel<ImageImpl> {

  private List<ImageImpl> layers;
  private List<Boolean> visibilities;

  /**
   * Basic Constructor (sets all visibilities to true).
   *
   * @param layers List of Images
   * @throws IllegalArgumentException if layers are null
   */
  public ImageEditorModel(List<ImageImpl> layers) throws IllegalArgumentException {
    if (layers == null) {
      throw new IllegalArgumentException("layers can't be null");
    }
    this.layers = layers;
    this.visibilities = new ArrayList<>();
    for (int i = 0; i < layers.size(); i++) {
      visibilities.add(true);
    }
  }

  /**
   * Default Constructor (no layers, no visibilities).
   */
  public ImageEditorModel() {
    this.layers = new ArrayList<>();
    this.visibilities = new ArrayList<>();
  }

  @Override
  public void applyEffect(IEffect<ImageImpl> effect, int index) throws IllegalArgumentException {
    if (index < 0 || index > layers.size() - 1) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    ImageImpl imageImpl = layers.get(index);
    imageImpl = effect.apply(imageImpl);
    layers.remove(index);
    layers.add(index, imageImpl);
  }

  @Override
  public void importItem(ImageImpl imageImpl) throws IllegalArgumentException {
    if (imageImpl == null) {
      throw new IllegalArgumentException("null image");
    }
    layers.add(imageImpl);
    visibilities.add(true);
  }

  @Override
  public ImageImpl exportItem(int index) throws IllegalArgumentException {
    if (index < 0 || index > layers.size() - 1) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    return layers.get(index);
  }

  @Override
  public int getTopVisibleLayerIndex() {
    for (int i = visibilities.size() - 1; i > -1; i--) {
      if (visibilities.get(i)) {
        return i;
      }
    }
    return 0;
  }

  @Override
  public int getLayerCount() {
    return layers.size();
  }

  @Override
  public ImageImpl exportTopLayer() throws IllegalArgumentException {
    for (int i = layers.size() - 1; i > -1; i--) {
      if (visibilities.get(i)) {
        return layers.get(i);
      }
    }
    throw new IllegalArgumentException("No visible layers.");
  }

  @Override
  public void removeLayer(int index) throws IllegalArgumentException {
    if (index < 0 || index >= layers.size()) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    layers.remove(index);
    visibilities.remove(index);
    if (visibilities.size() == 1) {
      visibilities.set(0,true);
    }
  }

  @Override
  public void setVisibility(int index, boolean b) throws IllegalArgumentException {
    if (index < 0 || index >= visibilities.size()) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    if (index != 0) {
      visibilities.set(index, b);
    } else {
      if (!b) {
        throw new IllegalArgumentException("Bottom layer can not be transparent.");
      }
    }
  }

  @Override
  public List<ImageImpl> exportAll() {
    List list = new ArrayList();
    list.addAll(layers);
    return list;
  }

  @Override
  public List<Boolean> getVisibilities() {
    return new ArrayList<>(visibilities);
  }

  @Override
  public void moveToTop(int index) throws IllegalArgumentException {
    if (index < 0 || index > this.layers.size() - 1) {
      throw new IllegalArgumentException("Index out of bounds");
    }
    ImageImpl layer = layers.get(index);
    Boolean vis = visibilities.get(index);
    removeLayer(index);
    importItem(layer);
    setVisibility(this.layers.size() - 1, vis);
  }

}
