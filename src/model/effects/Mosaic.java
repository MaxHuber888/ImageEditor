package model.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.ColorType;
import model.ImageImpl;
import model.Pixel;

/**
 * Class representing a Mosaic image effect.
 */
public class Mosaic implements IEffect<ImageImpl> {

  /**
   * Applies the mosaic effect to the given ImageImpl with the given number of seeds.
   * @param image ImageImpl, the image to have the effect applied to
   * @param numSeeds Integer, the number of seeds for the mosiac effect
   * @return ImageImpl, the image with the effect applied
   */
  public ImageImpl applySpecific(ImageImpl image, int numSeeds) {
    if (image == null) {
      throw new IllegalArgumentException("image can't be null");
    }
    if (numSeeds < 0 || numSeeds > image.getNumPixels()) {
      throw new IllegalArgumentException("invalid number of seeds");
    }
    if (numSeeds == 0) {
      List<Pixel> pixels = new ArrayList<>(image.getPixels());
      return new ImageImpl(pixels, image.getWidth(), image.getHeight());
    }
    // pixels in original image
    List<Pixel> pixels = image.getPixels();
    // copy of pixels
    List<Pixel> copyPixels = new ArrayList<>(pixels);
    // random seeds
    List<Pixel> seeds = getRandomPixels(copyPixels, numSeeds);
    // map of pixels and their seeds
    Map<Pixel, Pixel> map = new HashMap<>();
    // list of seeds
    List<Pixel> newSeeds = new ArrayList<>();
    // list of clusters
    List<List<Pixel>> clusters = new ArrayList<>();
    // new list of pixels
    List<Pixel> newPixels = new ArrayList<>();
    // new image
    ImageImpl mosaicImage = new ImageImpl(newPixels, image.getWidth(), image.getHeight());
    // puts each pixel and their closest seed into map
    for (Pixel pixel : pixels) {
      map.put(pixel, closestSeed(pixel, seeds));
    }
    // adds each unique seed to list of seeds
    for (Map.Entry<Pixel, Pixel> entry : map.entrySet()) {
      if (!newSeeds.contains(entry.getValue())) {
        newSeeds.add(entry.getValue());
      }
    }
    // creates list of clusters
    for (int i = 0; i < newSeeds.size(); i++) {
      clusters.add(new ArrayList<>());
    }
    // adds pixels to corresponding clusters
    double num = 0;
    for (Map.Entry<Pixel, Pixel> set : map.entrySet()) {
      for (int i = 0; i < newSeeds.size(); i++) {
        if (newSeeds.get(i) == set.getValue()) {
          clusters.get(i).add(set.getKey());
        }
      }
    }
    // computes color values for each cluster and creates new pixels
    for (List<Pixel> list : clusters) {
      for (Pixel pix : list) {
        int clusterColorRed = averageColor(list, ColorType.RED);
        int clusterColorGreen = averageColor(list, ColorType.GREEN);
        int clusterColorBlue = averageColor(list, ColorType.BLUE);
        newPixels.add(new Pixel(pix.getX(), pix.getY(), clusterColorRed, clusterColorGreen,
            clusterColorBlue));
      }
    }
    return mosaicImage;
  }

  /**
   * Returns the average color for the specified color for the given list of pixels.
   * @param cluster List of Pixels, the pixels of which to find the average color
   * @param color ColorType, the color for which to find the average
   * @return Integer, the average color value for specified color
   */
  private int averageColor(List<Pixel> cluster, ColorType color) {
    double value = 0;
    switch (color) {
      case RED:
        for (Pixel pix : cluster) {
          value += pix.getR();
        }
        break;
      case GREEN:
        for (Pixel pix : cluster) {
          value += pix.getG();
        }
        break;
      case BLUE:
        for (Pixel pix : cluster) {
          value += pix.getB();
        }
        break;
      default:
        throw new IllegalArgumentException("invalid color type.");
    }
    return (int) (value / cluster.size());
  }

  /**
   * Finds the closest seed to the given pixel.
   * @param pixel Pixel, the pixel being analyzed
   * @param seeds List of Pixels, the list of seeds
   * @return Pixel, the closest seed to the given pixel
   */
  private Pixel closestSeed(Pixel pixel, List<Pixel> seeds) {
    List<Double> list = new ArrayList<>();
    Pixel pix = null;
    for (Pixel seed : seeds) {
      list.add(distanceBetween(pixel, seed));
    }
    double closest = Collections.min(list);
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == closest) {
        pix = seeds.get(i);
      }
    }
    return pix;
  }

  /**
   * Returns the distance between the two given pixels.
   * @param pixel Pixel, the first pixel
   * @param seed Pixel, the second pixel
   * @return Double, the distance between the two pixels
   */
  private double distanceBetween(Pixel pixel, Pixel seed) {
    double x1 = pixel.getX();
    double y1 = pixel.getY();
    double x2 = seed.getX();
    double y2 = seed.getY();
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }

  /**
   * Returns a list of random pixels with the given length.
   * @param pixels List of Pixels, the pixels of the image
   * @param numPixels Integer, the number of random pixels to return
   * @return List of Pixels, the randomly selected pixels
   */
  private List<Pixel> getRandomPixels(List<Pixel> pixels, int numPixels) {
    Random rand = new Random();
    List<Pixel> list = new ArrayList<>();
    for (int i = 0; i < numPixels; i++) {
      int index = rand.nextInt(pixels.size());
      list.add(pixels.get(index));
      pixels.remove(index);
    }
    return list;
  }

  @Override
  public ImageImpl apply(ImageImpl input) throws IllegalArgumentException {
    return applySpecific(input, input.getNumPixels() / 10);
  }
}

