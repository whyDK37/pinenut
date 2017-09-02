package Mediator;
/**
 *  An abstract Mediator
 */
public interface Mediator  {
    void Register(Colleague c, String type);
    void Changed(String type);
}