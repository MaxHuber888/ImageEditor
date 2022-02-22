package view;

import controller.IController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import model.ImageImpl;

/**
 * Class that serves as ActionListener for View and sends commands to controller.
 */
public class ViewListener implements ActionListener {
  private final IController<ImageImpl> controller;
  private final ImageView view;

  /**
   * General Constructor for ViewListener.
   * @param controller IController, the controller to which commands are sent
   */
  public ViewListener(IController<ImageImpl> controller,ImageView view) {
    this.controller = controller;
    this.view = view;
  }

  /**
   * Sends a valid command to the controller.
   * @param command String, must be readable by controller
   */
  private void sendCommand(String command) {
    Readable input = new StringReader(command);
    this.controller.run(input);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "SAVE":
        this.controller.save(view.getFilename(), view.getFiletype());
        break;
      case "LOAD":
        this.controller.load(view.getFilename(), view.getFiletype(), true);
        break;
      case "APPLY":
        sendCommand(view.getEffect());
        break;
      case "REMOVE":
        this.controller.remove();
        break;
      case "INVISIBLE":
        this.controller.invisible(view.getIndex());
        break;
      case "VISIBLE":
        this.controller.visible(view.getIndex());
        break;
      case "TOP":
        this.controller.toTop(view.getIndex());
        break;
      case "EXPORTALL":
        this.controller.exportAll(view.getFilename());
        break;
      case "FILE":
        this.controller.file(view.getFilename());
        break;
      default:
        //if action event not recognized, do nothing
    }
  }

}
