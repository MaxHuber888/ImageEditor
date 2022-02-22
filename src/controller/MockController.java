package controller;

import java.util.Scanner;
import model.ImageImpl;
import model.effects.IEffect;

/**
 * Class acts as a controller for testing the ViewListener.
 */
public class MockController implements IController<ImageImpl> {
  public String command;

  @Override
  public void run(Readable input) {
    Scanner sc = new Scanner(input);
    String in = "";
    while (sc.hasNext()) {
      in = in + " " + sc.next();
    }
    command = "run" + in;
  }

  @Override
  public void file(String filename) {
    command = "file " + filename;
  }

  @Override
  public void save(String filename, String filetype) {
    command = "save " + filename + " " + filetype;
  }

  @Override
  public void load(String filename, String filetype, Boolean visibility) {
    command = "load " + filename + " " + filetype + " " + visibility;
  }

  @Override
  public void applyEffect(IEffect<ImageImpl> effect) {
    command = "apply " + effect;
  }

  @Override
  public void remove() {
    command = "remove";
  }

  @Override
  public void toTop(int index) {
    command = "toTop " + index;
  }

  @Override
  public void invisible(int index) {
    command = "invisible " + index;
  }

  @Override
  public void visible(int index) {
    command = "visible " + index;
  }

  @Override
  public void exportAll(String filename) {
    command = "export " + filename;
  }

  @Override
  public void updateOutput() {
    command = "updateOutput";
  }
}
