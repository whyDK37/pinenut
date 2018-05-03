package guava.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

public class SplitterTester {
    public static void main(String args[]) {
        SplitterTester tester = new SplitterTester();
        tester.testSplitter();
    }

    private void testSplitter() {
        Iterable<String> split = Splitter.on(',')
                .trimResults(CharMatcher.whitespace())
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,  ,            jumps, over, the, lazy, little dog.");
        System.out.println(split);

        System.out.println(" Splitter 还支持使用正则表达式来描述分隔符。");
        System.out.println(Splitter.onPattern("\\s+").split("1 \t   2 3"));
        ;//["1", "2", "3"]

        System.out.println("Splitter 还支持根据长度来拆分字符串。");
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));//["1 2", " 3"]

        System.out.println("与 Joiner.MapJoiner 相对，Splitter.MapSplitter 用来拆分被拼接了的 Map 对象，返回 Map<String, String>。");
        System.out.println(Splitter.on("#").withKeyValueSeparator(":").split("1:2#3:4"));
        ;//{"1":"2", "3":"4"}
    }
}