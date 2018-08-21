package java8;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

/**
 * https://www.jianshu.com/p/c623b8b2aec8
 */
public class Currying {
  public static void main(String[] args) {
    //
    Function<Integer, Function<Integer, Function<Integer, Integer>>> currying = x -> y -> z -> (x + y) * z;

    Function<Integer, Function<Integer, Integer>> apply4 = currying.apply(4);
    System.out.println(apply4.apply(5).apply(6)); //54

    //
    IntFunction<IntFunction<IntUnaryOperator>> f = x -> y -> z -> (x + y) * z;
    System.out.println(f.apply(4).apply(5).applyAsInt(6)); //54

    //
    TriFunction<Integer,Integer,Integer, Integer> triFunction = (x,y,z) -> (x+y)*z;
    System.out.println(triFunction.apply(4,5,6)); //54

  }

  @FunctionalInterface
  public interface TriFunction<U, T, S, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @return the function result
     */
    R apply(T t, U u, S s);
  }

}
