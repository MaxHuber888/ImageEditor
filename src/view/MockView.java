package view;

import model.Image;

/**
 * Class acts as a View for testing the ViewListener.
 */
public class MockView implements ImageView {
  public Image img;
  public String output;
  public String currentLayer;

  public String filename;
  public String filetype;
  public String effect;
  public int index;

  @Override
  public void updateView(Image newImage) {
    img = newImage;
  }

  @Override
  public String getFilename() {
    return filename;
  }

  @Override
  public String getFiletype() {
    return filetype;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public String getEffect() {
    return effect;
  }

  @Override
  public void showOutput(String output) {
    this.output = output;
  }

  @Override
  public void showCurrentLayerIndex(int index, int total) {
    currentLayer = index + " out of " + total;
  }
}
