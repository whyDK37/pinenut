//package jdk.util;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//
//class ObjectPoolClient {
//    private static WeakObjectPool objectPool = new WeakObjectPool();
//
//    public static void main(String args[]) throws Exception {
//        BufferedReader reader = new BufferedReader(new FileReader("d:/a.txt"));
//        List<String[]> parsedLines = new ArrayList<String[]>();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] elements = line.split(",");
//            for (int i = 0; i < elements.length; i++) {
//                // replace the string read from the file with the pool instance
//                elements[i] = (String) objectPool.replace(elements[i]);
//            }
//            parsedLines.add(elements);
//        }
//        reader.close();
//
//        // Cool, we saved a lot of memory by reusing the repeated strings!
//        doSomethingInteresting(parsedLines);
//        // Now, we get rid of the references and soon the garbage collector
//        // will reclaim the memory
//        parsedLines = null;
//        doMoreInterestingStuff();
//    }
//}