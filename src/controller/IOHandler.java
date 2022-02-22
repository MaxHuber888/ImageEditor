package controller;

/**
 * Interface representing a handler class
 * that manages importing and exporting files.
 */
public interface IOHandler<T> {

  /**
   * Imports the specified file as a T.
   * @param filename String, name of the file
   * @param filetype String, extension of the file
   * @return Generic, the file as a T
   */
  T importItem(String filename,String filetype);

  /**
   * Exports the given item with the given name as the given file type.
   * @param filename String, name of the file once exported
   * @param filetype String, extension of the file once exported
   * @param input Generic, the item to be exported
   */
  void exportItem(String filename,String filetype,T input);

  /**
   * Exports the given text to a file with the given name.
   * @param filename String, the name of the file once exported
   * @param text String, the text in the file
   */
  void exportText(String filename,String text);

  /**
   * Imports the specified text file as a Readable.
   * @param filename String, name of the specified file
   * @return Readable, a readable object containing the text of the file
   */
  Readable importText(String filename);

}
