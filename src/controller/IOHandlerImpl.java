package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a handler that imports and exports images.
 */
public class IOHandlerImpl implements IOHandler<ImageImpl> {

  @Override
  public ImageImpl importItem(String filename, String filetype) throws IllegalArgumentException {
    if (filename == null || filetype == null || filetype.equals("")
        || filename.equals("")) {
      throw new IllegalArgumentException("Invalid filename or filetype.");
    }
    switch (filetype) {
      case "ppm":
        return readPPM("res/" + filename + ".ppm");
      case "jpg":
        try {
          BufferedImage img = ImageIO.read(new File("res/" + filename + ".jpg"));
          return bufferedToImage(img);
        } catch (IOException e) {
          throw new IllegalArgumentException("Import failed");
        }
      case "png":
        try {
          BufferedImage img = ImageIO.read(new File("res/" + filename + ".png"));
          return bufferedToImage(img);
        } catch (IOException e) {
          throw new IllegalArgumentException("Import failed");
        }
      default:
        throw new IllegalArgumentException("filetype not supported or does not exist");
    }
  }

  @Override
  public void exportItem(String filename, String filetype, ImageImpl imageImpl) throws
      IllegalArgumentException {
    //create BufferedImage for exporting
    BufferedImage export = new BufferedImage(imageImpl.getWidth(), imageImpl.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    //adds all pixel information to the BufferedImage
    for (Pixel x : imageImpl.getPixels()) {
      export.setRGB(x.getX(), x.getY(), x.getRGB());
    }
    //exports BufferedImage as given filetype
    switch (filetype) {
      case "png":
        // Save as PNG
        File file = new File("res/" + filename + ".png");
        try {
          ImageIO.write(export, "png", file);
        } catch (IOException io) {
          throw new IllegalArgumentException("export failed.");
        }
        break;
      case "jpg":
        // Save as JPEG
        file = new File("res/" + filename + ".jpg");
        try {
          ImageIO.write(export, "jpg", file);
        } catch (IOException io) {
          throw new IllegalArgumentException("export failed.");
        }
        break;
      case "ppm":
        // Save as PPM
        writePPM("res/" + filename + ".ppm", imageImpl);
        break;
      default:
        throw new IllegalArgumentException("filetype not supported or does not exist");
    }
  }

  @Override
  public void exportText(String filename, String text) throws IllegalArgumentException {
    PrintWriter out = null;
    try {
      out = new PrintWriter("res/" + filename + ".txt");
      out.println(text);
      out.close();
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Export failed");
    }
  }

  @Override
  public Readable importText(String filename) throws IllegalArgumentException {
    try {
      File file = new File("res/" + filename + ".txt");
      return new FileReader(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Import failed");
    }
  }

  /**
   * Creates an ImageImpl object from a BufferedImage.
   *
   * @param img BufferedImage, the image to read data from
   * @return ImageImpl, created using the data of the BufferedImage
   */
  private ImageImpl bufferedToImage(BufferedImage img) {
    int height = img.getHeight();
    int width = img.getWidth();
    List<Pixel> list = new ArrayList<>();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = img.getRGB(x, y);
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >>  8) & 0xff;
        int b = (rgb) & 0xff;
        list.add(new Pixel(x, y, r, g, b));
      }
    }
    return new ImageImpl(list, width, height);
  }

  /**
   * Read an image file in the PPM format and return an ImageImpl instance.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if file is not found or the ppm file is invalid.
   */
  private ImageImpl readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    //reads starting values of ppm
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    //create list of pixels for image
    List<Pixel> pixels = new ArrayList<Pixel>();
    //creates pixels in list
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels.add(new Pixel(j, i, r, g, b));
      }
    }
    //create model.ImageImpl instance with imported image data and return
    ImageImpl result = new ImageImpl(pixels, width, height);
    return result;
  }

  /**
   * Creates a new PPM file containing the data of the imageImpl.
   *
   * @param filename String, the name of the file to be written to
   * @param imageImpl    ImageImpl, the imageImpl to convert
   * @throws IllegalArgumentException if the file is not found.
   */
  private void writePPM(String filename, ImageImpl imageImpl) throws IllegalArgumentException {
    int width = imageImpl.getWidth();
    int height = imageImpl.getHeight();
    StringBuilder ppm = new StringBuilder();
    ppm.append("P3\n").append(width).append(" ").append(height).append("\n255\n");
    for (Pixel pixel : imageImpl.getPixels()) {
      int r = pixel.getR();
      int g = pixel.getG();
      int b = pixel.getB();
      ppm.append(r).append(" ").append(g).append(" ").append(b).append("\n");
    }
    try {
      FileOutputStream fos = new FileOutputStream(filename);
      fos.write(new String(ppm).getBytes(StandardCharsets.UTF_8));
      fos.close();
    } catch (IOException io) {
      throw new IllegalArgumentException("file not found.");
    }
  }
}
