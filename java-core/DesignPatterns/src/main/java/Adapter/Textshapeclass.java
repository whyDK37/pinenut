package Adapter;

/**
 * The Class Adapter in this sample
 */
public class Textshapeclass extends Text implements Shape {
  public Textshapeclass() {
  }

  public static void main(String[] args) {
    Textshapeclass myTextShapeClass = new Textshapeclass();
    myTextShapeClass.Draw();
    myTextShapeClass.Border();
    myTextShapeClass.SetContent("A test text !");
    System.out.println("The content in Text Shape is :" + myTextShapeClass.GetContent());
  }

  public void Draw() {
    System.out.println("Draw a shap ! Impelement Shape interface !");
  }

  public void Border() {
    System.out.println("Set the border of the shap ! Impelement Shape interface !");
  }
}