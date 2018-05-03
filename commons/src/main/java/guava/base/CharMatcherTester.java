package guava.base;

import com.google.common.base.CharMatcher;

public class CharMatcherTester {
    public static void main(String args[]){
        CharMatcherTester tester = new CharMatcherTester();
        tester.testCharMatcher();
    }

    private void testCharMatcher(){
        System.out.println(CharMatcher.DIGIT.retainFrom("mahesh123")); // only the digits
        System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom("     Mahesh     Parashar ", ' '));
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(CharMatcher.JAVA_DIGIT.replaceFrom("mahesh123", "*")); // star out all digits
        System.out.println(CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom("mahesh123"));
        // eliminate all characters that aren't digits or lowercase
    }
}