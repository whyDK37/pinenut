package java8;

import java.util.HashMap;
import java.util.Map;

/**
 * map是不支持流操作的。而更新后的map现在则支持多种实用的新方法，来完成常规的任务
 *
 * @author zhangxu
 */
public class MapTest {

    private Map<Integer, String> map = new HashMap<>();

    public MapTest() {
        initPut();
    }

    /**
     * 使用更新后的map进行putIfAbsent
     */
    private void initPut() {
        // putIfAbsent为Map接口中新增的一个默认方法
        /**
         * <code>
         default V putIfAbsent(K key, V value) {
         V v = get(key);
         if (v == null) {
         v = put(key, value);
         }

         return v;
         }
         </code>
         */
        // 如果map中有对应K映射的V且不为null则直接返回;否则执行put
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "value" + i);
        }

        // 放入了一个null元素
        map.putIfAbsent(10, null);
        // 替换null
        map.putIfAbsent(10, "value10");
        // 因为K-10有映射且不为null则忽略V-value11
        map.putIfAbsent(10, "value11");
    }

    /**
     * 使用更新后的map进行for-each
     */
    public void forEach() {
        // default void forEach(BiConsumer<? super K, ? super V> action)
        // Map接口中新增的默认方法

        // @FunctionalInterface public interface BiConsumer<T, U> {void accept(T
        // t, U u);
        map.forEach((id, val) -> System.out.println(val));
    }

    /**
     * 使用更新后的map进行compute——->重映射
     */
    public void compute() {
        // default V computeIfPresent(K key,BiFunction<? super K, ? super V, ?
        // extends V> remappingFunction)

        // Map接口中新增的默认方法

        // @FunctionalInterface public interface BiFunction<T, U, R> {R apply(T
        // t, U u);
        // --> V apply(K k,V v)

        // ifPresent会判断key对应的v是否是null，不会null才会compute->否则直接返回null

        // 解释:将K-3映射的value->compute->"value3" + 3 = value33
        map.computeIfPresent(3, (key, val) -> val + key);
        System.out.println(map.get(3));

        // 解释:这里将K-3映射的value进行重映射->null
        // 该方法源码实现会判断如果newValue为null则会执行remove(key)方法,将移除key
        map.computeIfPresent(9, (key, val) -> null);
        // 从上面的解释中得到，输出为false,因为已经被移除了
        System.out.println(map.containsKey(9));

        // default V computeIfAbsent(K key,Function<? super K, ? extends V>
        // mappingFunction)
        // 解释:代码实现上看，如果K-15映射的值为null,即不存在或者为null,则执行映射->所以本例来看(没有15的key)，该方法相当于插入一个新值
        map.computeIfAbsent(15, (key) -> "val" + key);
        System.out.println(map.containsKey(15));

        // 因为K-4映射的值存在，所以直接返回，即不会重映射,所以输出依然会是value4
        map.computeIfAbsent(4, key -> "bam");
        System.out.println(map.get(4));
    }

    /**
     * 使用更新后的map进行remove
     */
    public void remove() {
        // default boolean remove(Object key, Object value) {
        // Map接口中新增的默认方法

        // 其源码实现是
        // 1.当前key对应的值和传入的参数不一致时则直接返回，移除失败(用的是Objects.equals方法)
        // 2.当前key对应的值为null且!containsKey(key),移除失败(即当前map中根本不存在这个key_【因为有一种情况是有这个key但是key映射的值为null】)
        // ->否则执行移除

        /**
         * <code>
         *  default boolean remove(Object key, Object value) {
         Object curValue = get(key);
         if (!Objects.equals(curValue, value) ||
         (curValue == null && !containsKey(key))) {
         return false;
         }
         remove(key);
         return true;
         }
         * </code>
         */
        map.remove(3, "value4");
        System.out.println(map.get(3));

        // key和v匹配时则移除成功
        map.remove(3, "value33");
        System.out.println(map.get(3));
    }

    /**
     * getOrDefault是一个有用的方法
     */
    public void getOrDefault() {
        // default V getOrDefault(Object key, V defaultValue) {
        // Map接口中新增的默认方法

        /**
         * <code>
         * default V getOrDefault(Object key, V defaultValue) {
         V v;
         return (((v = get(key)) != null) || containsKey(key))
         ? v
         : defaultValue;
         }
         * </code>
         */

        // 源码实现:
        // 1.如果对应的key有value且不为null，则直接返回value;如果为null且包含该key，则返回null(总之即必须要有该key)
        // 2.如果没有该key，则用默认值
        String retV = map.getOrDefault("20", "not found");
        System.out.println(retV);

        // 加入一个null
        map.putIfAbsent(30, null);
        // 输出null
        System.out.println(map.get(30));
        // 输出null
        System.out.println(map.getOrDefault(30, "value30"));
    }

    /**
     * 合并
     */
    public void merge() {
        // default V merge(K key, V value,BiFunction<? super V, ? super V, ?
        // extends V> remappingFunction)

        // @FunctionalInterface public interface BiFunction<T, U, R> { R apply(T
        // t, U u);

        // merge为Map接口新增的默认方法

        /**
         * <code>
         default V merge(K key, V value,
         BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
         Objects.requireNonNull(remappingFunction);
         Objects.requireNonNull(value);
         V oldValue = get(key);
         V newValue = (oldValue == null) ? value :
         remappingFunction.apply(oldValue, value);
         if(newValue == null) {
         remove(key);
         } else {
         put(key, newValue);
         }
         return newValue;
         }
         * </code>
         */

        // 其源码实现:
        // 1.分别检查参数remappingFunction和value是否为null(调用Objects.requireNonNull).->为null则抛出空指针
        // 2.判断oldValue是否为null,如果为null则将传入的newValue赋值;如果oldValue不为null则执行merge函数
        // --->apply(oldValue, value)
        // 3.判断newValue->如果为null则执行移除；否则执行插入

        // k-9的值在执行compute方法的时候已经被移除了->所以oldValue为null->所以newValue为传入的参数value9->执行插入
        // 所以这里输出为value9
        String newValue1 = map.merge(9, "value9", (value, newValue) -> value.concat(newValue));
        System.out.println(newValue1);
        System.out.println(map.get(9));

        // k-9的值现在已经为value9了，所以执行merge函数->"value9".concat("concat")->newValue为"value9concat"
        // 执行插入，所以这里输出为value9concat
        String newValue2 = map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(newValue2);
        System.out.println(map.get(9));

        // k-8值存在为value8->执行merge函数->直接返回"NewMerge8"->newValue为"NewMerge8"
        // 执行put->所以这里输出"NewMerge8"
        map.merge(8, "merge", (value, newValue) -> "NewMerge8");
        System.out.println(map.get(8));
    }

    public static void main(String[] args) {
        MapTest example = new MapTest();

        //example.forEach();
        example.compute();
        //example.remove();
        example.getOrDefault();
        //example.merge();
    }
}
