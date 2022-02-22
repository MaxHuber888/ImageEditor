package controller;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.IEditorModel;
import model.ImageImpl;
import model.effects.BlurFilter;
import model.effects.Downscale;
import model.effects.Greyscale;
import model.effects.IEffect;
import model.effects.Mosaic;
import model.effects.Sepia;
import model.effects.SharpenFilter;

/**
 * Class representing a controller for an ImageImpl Editor. Supports user input commands that
 * execute various functions such as loading in an image, applying image processing effect, saving
 * images, and removing layers.
 */
public class ImageAppendableController implements IController<ImageImpl> {

  private final IEditorModel<ImageImpl> model;
  private final Appendable ap;
  private final IOHandler<ImageImpl> handler;

  /**
   * General Constructor.
   * @param model IEditorModel, the model that is being controlled
   * @param ap Appendable, where the output will appear
   * @throws IllegalArgumentException if model or appendable is null
   */
  public ImageAppendableController(IEditorModel<ImageImpl> model, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("null model or appendable");
    }
    this.model = model;
    this.handler = new IOHandlerImpl();
    this.ap = ap;
  }

  @Override
  public void run(Readable input) {
    if (input == null) {
      throw new IllegalArgumentException("null input");
    }
    Scanner master = new Scanner(input);
    while (master.hasNext()) {
      String in = master.next();
      switch (in) {
        case "file":
          if (master.hasNext()) {
            file(master.next());
          }
          break;
        case "quit":
          appendCatchIO("Program quitting..");
          return;
        case "load":
          if (master.hasNext()) {
            String filenameLoad = master.next();
            if (master.hasNext()) {
              String filetypeLoad = master.next();
              if (master.hasNextBoolean()) {
                load(filenameLoad, filetypeLoad, master.nextBoolean());
              }
            }
          }
          break;
        case "save":
          if (master.hasNext()) {
            String filenameSave = master.next();
            if (master.hasNext()) {
              String filetypeSave = master.next();
              save(filenameSave, filetypeSave);
            }
          }
          break;
        case "blur":
          applyEffect(new BlurFilter());
          break;
        case "sharpen":
          applyEffect(new SharpenFilter());
          break;
        case "greyscale":
          applyEffect(new Greyscale());
          break;
        case "sepia":
          applyEffect(new Sepia());
          break;
        case "mosaic":
          applyEffect(new Mosaic());
          break;
        case "downscale":
          applyEffect(new Downscale());
          break;
        case "remove":
          remove();
          break;
        case "invisible":
          if (master.hasNextInt()) {
            invisible(master.nextInt());
          }
          break;
        case "visible":
          if (master.hasNextInt()) {
            visible(master.nextInt());
          }
          break;
        case "toTop":
          if (master.hasNextInt()) {
            toTop(master.nextInt());
          }
          break;
        case "exportAll":
          if (master.hasNext()) {
            exportAll(master.next());
          }
          break;
        default:
          //if not a valid command, do nothing
      }
    }
  }

  @Override
  public void updateOutput() {
    if (model.getLayerCount() != 0) {
      appendCatchIO("CURRENT LAYER: " + (model.getTopVisibleLayerIndex() + 1) + " of "
          + model.getLayerCount());
    } else {
      appendCatchIO("CURRENT LAYER: 0 of " + model.getLayerCount());
    }
  }

  /**
   * Writes the given string to the appendable.
   * @param output String, appended to the appendable
   * @throws IllegalArgumentException if appendable fails (throws IOException)
   */
  private void appendCatchIO(String output) throws IllegalArgumentException {
    try {
      ap.append(output + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed writing to appendable.");
    }
  }

  @Override
  public void file(String filename) {
    try {
      Readable script = handler.importText(filename);
      run(script);
      appendCatchIO("Script " + filename + ".txt loaded");
    } catch (Exception e) {
      appendCatchIO("Script load unsuccessful.");
    }
    updateOutput();
  }

  @Override
  public void save(String filename, String filetype) {
    try {
      ImageImpl img = model.exportTopLayer();
      handler.exportItem(filename,filetype,img);
      appendCatchIO("Saved " + filename + "." + filetype);
    } catch (Exception e) {
      appendCatchIO("Save unsuccessful.");
    }
    updateOutput();
  }

  @Override
  public void load(String filename, String filetype, Boolean visibility) {
    try {
      ImageImpl img = handler.importItem(filename,filetype);
      model.importItem(img);
      model.setVisibility(model.getLayerCount() - 1,visibility);
      appendCatchIO("Loaded " + filename + "." + filetype);
    } catch (Exception e) {
      appendCatchIO("Load unsuccessful.");
    }
    updateOutput();
  }

  @Override
  public void applyEffect(IEffect<ImageImpl> effect) {
    try {
      model.applyEffect(effect, model.getTopVisibleLayerIndex());
      appendCatchIO("Effect applied.");
    } catch (Exception e) {
      appendCatchIO("Effect could not be applied.");
    }
    updateOutput();
  }

  @Override
  public void remove() {
    try {
      model.removeLayer(model.getTopVisibleLayerIndex());
      appendCatchIO("Layer removed.");
    } catch (Exception e) {
      appendCatchIO("Layer could not be removed.");
    }
    updateOutput();
  }

  @Override
  public void toTop(int index) {
    try {
      model.moveToTop(index);
      appendCatchIO("Layer " + (index + 1) + " moved to top.");
    } catch (Exception e) {
      appendCatchIO("Layer could not be moved.");
    }
    updateOutput();
  }

  @Override
  public void invisible(int index) {
    try {
      model.setVisibility(index, false);
      appendCatchIO("Layer " + (index + 1) + " made invisible.");
    } catch (Exception e) {
      appendCatchIO("Layer could not be made invisible.");
    }
    updateOutput();
  }

  @Override
  public void visible(int index) {
    try {
      model.setVisibility(index, true);
      appendCatchIO("Layer " + (index + 1) + " made visible.");
    } catch (Exception e) {
      appendCatchIO("Layer could not be made visible.");
    }
    updateOutput();
  }

  @Override
  public void exportAll(String filename) {
    try {
      String text = "";
      List<ImageImpl> layers = model.exportAll();
      for (int i = 0; i < layers.size(); i++) {
        text = text + "load " + filename + Integer.toString(i) + " ppm "
            + model.getVisibilities().get(i) + "\n";
        handler.exportItem(filename + Integer.toString(i), "ppm", layers.get(i));
      }
      handler.exportText(filename, text);
      appendCatchIO("Exported all layers.");
    } catch (Exception e) {
      appendCatchIO("Could not export all layers.");
    }
    updateOutput();
  }

}
