package Prototype;
/*
 * A Graphic Interface ( A prototype interface )
 */

import java.io.Serializable;

public interface IGraphic extends Cloneable, Serializable {
    String getName() ;
    void setName(String gName);
}