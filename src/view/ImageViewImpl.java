package view;

import controller.IController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.JTextComponent;
import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a View for an Image Editor.
 */
public class ImageViewImpl extends JFrame implements ImageView {
  private final ViewListener listener;
  private final JPanel controlPanel;
  private JTextArea output;
  private JTextComponent currentLayer;
  private JTextField filenameField;
  private JComboBox<String> filetypeCombo;
  private JComboBox<String> effectsCombo;
  private JSpinner indexSpinner;
  private final JLabel imageLabel;

  /**
   * General Constructor.
   * @param controller the controller that will receive input from this view
   */
  public ImageViewImpl(IController<ImageImpl> controller) {
    super();
    this.listener = new ViewListener(controller,this);
    setTitle("Image Editor");
    setSize(1200, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //MAIN PANEL
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainPanel.setBackground(Color.LIGHT_GRAY);
    add(mainPanel);

    //CONTROL PANEL
    controlPanel = new JPanel(new GridLayout(4, 0));
    //scroll bars around the control panel
    JScrollPane controlScrollPane = new JScrollPane(controlPanel);
    controlScrollPane.setPreferredSize(new Dimension(600, 600));
    controlPanel.setBackground(Color.blue);
    mainPanel.add(controlScrollPane);

    initInfoPanel();
    initSaveLoadPanel();
    initEffectRemovePanel();
    initIndexOpPanel();

    //IMAGE LABEL
    imageLabel = new JLabel(new ImageIcon(imageToBuffered(new ImageImpl(100,10))));
    JScrollPane imgScrollPane = new JScrollPane(imageLabel);
    imgScrollPane.setPreferredSize(new Dimension(600,600));
    mainPanel.add(imgScrollPane);
  }

  /**
   * Initializes the INFO panel.
   */
  private void initInfoPanel() {
    //INFO PANEL
    JPanel infoPanel = new JPanel(new GridLayout(2, 0));
    infoPanel.setBackground(Color.YELLOW);
    infoPanel.setBorder(BorderFactory.createTitledBorder("INFO"));
    controlPanel.add(infoPanel);

    //OUTPUT
    output = new JTextArea("OUTPUT");
    output.setEditable(false);
    infoPanel.add(output);

    //CURRENT LAYER
    currentLayer = new JTextArea("CURRENT LAYER INDEX: 0");
    currentLayer.setEditable(false);
    infoPanel.add(currentLayer);
  }

  /**
   * Initializes the SAVE/LOAD panel.
   */
  private void initSaveLoadPanel() {
    //SAVE/LOAD PANEL
    JPanel saveLoadPanel = new JPanel(new GridLayout(3, 2));
    saveLoadPanel.setBackground(Color.RED);
    saveLoadPanel.setBorder(BorderFactory.createTitledBorder("SAVE/LOAD"));
    controlPanel.add(saveLoadPanel);

    //NAME TEXT FIELD
    filenameField = new JTextField("FILENAME");
    saveLoadPanel.add(filenameField);

    //TYPE COMBO BOX
    String[] filetypes = {"jpg","png","ppm"};
    filetypeCombo = new JComboBox<>(filetypes);
    saveLoadPanel.add(filetypeCombo);

    //SAVE BUTTON
    JButton saveButton = new JButton("SAVE");
    saveLoadPanel.add(saveButton);
    saveButton.addActionListener(listener);
    saveButton.setActionCommand("SAVE");

    //LOAD BUTTON
    JButton loadButton = new JButton("LOAD");
    saveLoadPanel.add(loadButton);
    loadButton.addActionListener(listener);
    loadButton.setActionCommand("LOAD");

    //FILE BUTTON
    JButton fileButton = new JButton("RUN SCRIPT");
    saveLoadPanel.add(fileButton);
    fileButton.addActionListener(listener);
    fileButton.setActionCommand("FILE");

    //EXPORTALL BUTTON
    JButton exportAllButton = new JButton("EXPORT ALL LAYERS");
    saveLoadPanel.add(exportAllButton);
    exportAllButton.addActionListener(listener);
    exportAllButton.setActionCommand("EXPORTALL");
  }

  /**
   * Initializes the EFFECTS/REMOVE panel.
   */
  private void initEffectRemovePanel() {
    //EFFECTS/REMOVE PANEL
    JPanel effectsRemovePanel = new JPanel(new GridLayout(2, 2));
    effectsRemovePanel.setBorder(BorderFactory.createTitledBorder("APPLY TO CURRENT"));
    effectsRemovePanel.setBackground(Color.GREEN);
    controlPanel.add(effectsRemovePanel);

    //APPLY BUTTON
    JButton applyButton = new JButton("APPLY EFFECT");
    applyButton.addActionListener(listener);
    applyButton.setActionCommand("APPLY");
    effectsRemovePanel.add(applyButton);

    //EFFECTS COMBO
    String[] effects = {"blur","sharpen","sepia","greyscale","mosaic","downscale"};
    effectsCombo = new JComboBox<>(effects);
    effectsRemovePanel.add(effectsCombo);

    //REMOVE BUTTON
    JButton removeButton = new JButton("REMOVE LAYER");
    removeButton.addActionListener(listener);
    removeButton.setActionCommand("REMOVE");
    effectsRemovePanel.add(removeButton);
  }

  /**
   * Initializes the INDEX OPERATIONS panel.
   */
  private void initIndexOpPanel() {
    //INDEX OPERATIONS PANEL
    JPanel indexOperationsPanel = new JPanel(new GridLayout(3, 2));
    indexOperationsPanel.setBorder(BorderFactory.createTitledBorder("LAYER OPERATIONS"));
    indexOperationsPanel.setBackground(Color.CYAN);
    controlPanel.add(indexOperationsPanel);

    //INDEX FIELD
    indexSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    indexOperationsPanel.add(indexSpinner);

    //VIS BUTTON
    JButton visButton = new JButton("Visible");
    indexOperationsPanel.add(visButton);
    visButton.addActionListener(listener);
    visButton.setActionCommand("VISIBLE");

    //VIS BUTTON
    JButton invisButton = new JButton("Invisible");
    indexOperationsPanel.add(invisButton);
    invisButton.addActionListener(listener);
    invisButton.setActionCommand("INVISIBLE");

    //TOP BUTTON
    JButton topButton = new JButton("MOVE TO TOP");
    indexOperationsPanel.add(topButton);
    topButton.addActionListener(listener);
    topButton.setActionCommand("TOP");
  }

  @Override
  public void updateView(Image newImage) {
    BufferedImage img = imageToBuffered(newImage);
    imageLabel.setIcon(new ImageIcon(img));
  }

  @Override
  public String getFilename() {
    return filenameField.getText();
  }

  @Override
  public String getFiletype() {
    return (String) filetypeCombo.getSelectedItem();
  }

  @Override
  public int getIndex() {
    return (int) indexSpinner.getValue() - 1;
  }

  @Override
  public String getEffect() {
    return (String) effectsCombo.getSelectedItem();
  }

  @Override
  public void showOutput(String message) {
    output.setText(message);
  }

  @Override
  public void showCurrentLayerIndex(int index,int total) {
    currentLayer.setText("CURRENT LAYER INDEX: " + index + " out of " + total);
  }

  /**
   * Converts an ImageImpl to a BufferedImage.
   * @param imageImpl ImageImpl, the image to convert
   * @return BufferedImage, the given image as a BufferedImage
   */
  private BufferedImage imageToBuffered(Image imageImpl) {
    //create BufferedImage
    BufferedImage result = new BufferedImage(imageImpl.getWidth(), imageImpl.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    //adds all pixel information to the BufferedImage
    for (Pixel x : imageImpl.getPixels()) {
      result.setRGB(x.getX(), x.getY(), x.getRGB());
    }
    return result;
  }

}
