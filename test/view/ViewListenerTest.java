package view;

import static org.junit.Assert.assertEquals;

import controller.MockController;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the ViewListener class, as well
 * as its relationship to the View and Controller
 * (represented here as MockView and MockController).
 */
public class ViewListenerTest {
  ViewListener vl;
  MockView view;
  MockController mc;

  @Before
  public void setUp() throws Exception {
    view = new MockView();
    mc = new MockController();
    vl = new ViewListener(mc,view);
    view.filename = "name";
    view.filetype = "type";
    view.output = "OUT";
    view.effect = "blur";
    view.index = 0;
    view.currentLayer = "";
  }

  @Test
  public void actionPerformedSaveButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"SAVE");
    vl.actionPerformed(action);
    assertEquals("save name type",mc.command);
  }

  @Test
  public void actionPerformedLoadButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"LOAD");
    vl.actionPerformed(action);
    assertEquals("load name type true",mc.command);
  }

  @Test
  public void actionPerformedApplyButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"APPLY");
    vl.actionPerformed(action);
    assertEquals("run blur",mc.command);
  }

  @Test
  public void actionPerformedRemoveButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"REMOVE");
    vl.actionPerformed(action);
    assertEquals("remove",mc.command);
  }

  @Test
  public void actionPerformedInvisibleButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"INVISIBLE");
    vl.actionPerformed(action);
    assertEquals("invisible 0",mc.command);
  }

  @Test
  public void actionPerformedVisibleButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"VISIBLE");
    vl.actionPerformed(action);
    assertEquals("visible 0",mc.command);
  }

  @Test
  public void actionPerformedTopButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"TOP");
    vl.actionPerformed(action);
    assertEquals("toTop 0",mc.command);
  }

  @Test
  public void actionPerformedExportAllButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"EXPORTALL");
    vl.actionPerformed(action);
    assertEquals("export name",mc.command);
  }

  @Test
  public void actionPerformedFileButtonPressed() {
    ActionEvent action = new ActionEvent(view,0,"FILE");
    vl.actionPerformed(action);
    assertEquals("file name",mc.command);
  }

}