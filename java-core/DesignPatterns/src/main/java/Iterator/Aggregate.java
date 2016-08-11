/**
 *  The interface to create concrete iterator
 *  When create iterator, we can use Factory Method pattern
 */
public interface Aggregate  {
    public Iterator CreateIterator();
}