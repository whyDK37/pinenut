/*
 * A Graphic Interface ( A prototype interface )
 */
import java.io.*;

public interface IGraphic extends Cloneable, Serializable {
    public String getName() ;
    public void setName(String gName);
}