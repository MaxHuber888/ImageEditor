package model;


import controller.ImageAppendableController;
import controller.ImageViewController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * This class contains the main function which runs on execution.
 */
public class Main {
  /**
   * Acts as program execution start point.
   * @param args The given arguments.
   */
  public static void main(String[] args) throws IOException {
    ImageEditorModel model = new ImageEditorModel();
    Appendable out = System.out;
    if (args.length == 0) {
      ImageViewController vcInteract = new ImageViewController(model);
      return;
    }
    switch (args[0]) {
      case "-text":
        ImageAppendableController acInteract = new ImageAppendableController(model, out);
        acInteract.run(new InputStreamReader(System.in));
        break;
      case "-script":
        ImageAppendableController acScript = new ImageAppendableController(model, out);
        acScript.run(new StringReader("file " + args[1]));
        acScript.run(new StringReader("quit"));
        break;
      case "":
      case "-interactive":
        ImageViewController vcInteract = new ImageViewController(model);
        break;
      default:
        throw new IllegalArgumentException("INVALID COMMAND");
    }
  }
}

