package jdk.util;

/**
 * 列举一些hash 算法，比较他们的优缺点
 * 一般的说，Hash函数可以简单的划分为如下几类：
 * 1. 加法Hash；
 * 2. 位运算Hash；
 * 3. 乘法Hash；
 * 4. 除法Hash；
 * 5. 查表Hash；
 * 6. 混合Hash；
 * 下面详细的介绍以上各种方式在实际中的运用。
 * Created by why on 3/1/2017.
 */
public class HashAlgorithm {

    /**
     * 一 加法Hash
     * 所谓的加法Hash就是把输入元素一个一个的加起来构成最后的结果。标准的加法Hash的构造如下：
     * 这里的prime是任意的质数，看得出，结果的值域为[0,prime-1]。
     *
     * @param key
     * @param prime
     * @return
     */
    static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }


    /**
     * 二 位运算Hash
     * 这类型Hash函数通过利用各种位运算（常见的是移位和异或）来充分的混合输入元素。比如，标准的旋转Hash的构造如下：
     * <p>
     * 先移位，然后再进行各种位运算是这种类型Hash函数的主要特点。比如，以上的那段计算hash的代码还可以有如下几种变形：
     * 1.     hash = (hash<<5)^(hash>>27)^key.charAt(i);
     * 2.     hash += key.charAt(i);
     * hash += (hash << 10);
     * hash ^= (hash >> 6);
     * 3.     if((i&1) == 0)
     * {
     * hash ^= (hash<<7) ^ key.charAt(i) ^ (hash>>3);
     * }
     * else
     * {
     * hash ^= ~((hash<<11) ^ key.charAt(i) ^ (hash >>5));
     * }
     * 4.     hash += (hash<<5) + key.charAt(i);
     * 5.     hash = key.charAt(i) + (hash<<6) + (hash>>16) – hash;
     * 6.     hash ^= ((hash<<5) + key.charAt(i) + (hash>>2));
     *
     * @param key
     * @param prime
     * @return
     */
    static int rotatingHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i)
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        return (hash % prime);
    }
}
