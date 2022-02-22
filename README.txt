IMAGE PROCESSING MODEL

Functionality:
Our program's functionality includes support for applying color transformations such as greyscale
and sepia to a given imageImpl, as well as applying filters such as blurring and sharpening. Images can
be created programmatically, such as a checkerboard or rainbow, or imported in the JPG, PNG, or PPM
imageImpl format. Images may be exported in PNG, JPEG, or PPM format. It is also possible to run text
files containing commands, which is what allows us to import multiple images at once. The program
can also generate such script files from multiple images, allowing the user to export multi-layered
images and then re-import them. The model can be controlled with a controller that prints output to
an Appendable (given at its instantiation) or to a GUI, which also serves as that controller's input.

Design:
Our design represents images using the ImageImpl class, which has a List of Pixels, an integer width,
and an integer height. A pixel has an integer x and integer y value that specify its coordinates,
as well as a red, green and blue integer value to specify its color. A pixel is represented in the
Pixel class.

The IEffect interface represents the various filters and color transformations that may be applied
to a given imageImpl and will return a new imageImpl with the effect applied. The function object
approach is utilized to allow for each effect to be represented by a class, which each implement
an apply method that will apply the desired effect on the imageImpl. The BlurFilter class extends
the AbstractFilter class and its apply method will blur a given ImageImpl. The SharpenFilter class
extends the AbstractFilter class and its apply method will sharpen a given ImageImpl. The Greyscale
class implements the IEffect interface and its apply method will greyscale an ImageImpl. The Sepia
class implements the IEffect interface and its apply method will turn the ImageImpl into a
sepia-toned imageImpl.

The ImageEditorModel class represents a model that supports importing, exporting, and
manipulating images. The model supports the importing and exporting of PPM, JPG, and PNG files, as
well as the exporting of multiple images at once. The model implements an applyEffect method that
will apply the given processing effect on the specified ImageImpl. The main change
we made to our model was changing its field of a single Image to two fields: a List of Images,
 to represent a stack of layers, and a List of Boolean values to represent the visibilities
of those layers. This change had to be made to allow the model to manipulate multiple images
at the same time, and to be able to export and import multi-layered images.

The Main class contains a main method that serves as a program execution start point. This is where
the controller may be instantiated and set to run on some readable input. This is also what runs
when the program is started using a jar.

The IController interface acts as the Controller for the ImageEditorModel. It reads Readable
input and executes any valid commands that it finds. The Controller passes actions to the model,
and acts as a connection between the user and the model. The IController interface has been
implemented in two different classes: ImageAppendableController and ImageViewController. The
ImageAppendableController sends its output to an Appendable given at instantiation, while the
ImageViewController creates a GUI using the ImageViewImpl and ViewListener classes. This allows it
to send output and receives input through UI elements that the user can observe and interact with.

The IOHandlerImpl class implements the IOHandler interface and acts as a tool for the controller to
read and create imageImpl and text files.

The Extra Credit effects implement the IEffect interface, so they have an apply method that works with our prexisting code. Because they require more input than regular effects, the way they are currently implemented they are applied using pre-programmed parameters (by the controller and view), although the effects individually have methods that take in all the necessary parameters. This made it very easy to implement them, because we could treat them like every other effect, at the cost of losing control over the effect specific parameters when applying them from a view, model, or controller. If we had more time, we could have added more fields to the view for user input and added custom methods to the model, viewlistener, and controller to handle the new effects.

CHANGES WE WANTED TO MAKE BUT DID NOT GET TO:
- adding a Pixel interface
- making Images responsible for their own visibility, or even making
a new layer class to handle that
- making the view allow for a more intuitive way to use the Extra Credit effects


Image Authorization:
Images used in the project are owned by us and we authorize the use of them in this project.
