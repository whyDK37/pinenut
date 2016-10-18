package slf4j;

import org.junit.Test;
import org.perf4j.StopWatch;  
import org.perf4j.slf4j.Slf4JStopWatch;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
   
public class LoggerTest {  
   
    private static final Logger log = LoggerFactory.getLogger(LoggerTest.class);  
    private static final Logger classInfoLog = LoggerFactory.getLogger("CLASS_INFO");  
    private static final Logger noClassInfoLog = LoggerFactory.getLogger("NO_CLASS_INFO");  
   
    private static final int REPETITIONS = 15;//15;
    private static final int COUNT = 100000;  
   
    @Test  
    public void testClassInfo() throws Exception {  
        for (int test = 0; test < REPETITIONS; ++test)  
            testClassInfo(COUNT);  
    }  
   
    private void testClassInfo(final int count) {  
        StopWatch watch = new Slf4JStopWatch("Class info");  
        for (int i = 0; i < count; ++i)  
            classInfoLog.info("Example message");  
        printResults(count, watch);  
    }  
   
    @Test  
    public void testNoClassInfo() throws Exception {  
        for (int test = 0; test < REPETITIONS; ++test)  
            testNoClassInfo(COUNT);
    }  
   
    private void testNoClassInfo(final int count) {  
        StopWatch watch = new Slf4JStopWatch("No class info");  
        for (int i = 0; i < count; ++i)  
            noClassInfoLog.info("Example message");  
        printResults(count, watch);  
    }  
   
    private void printResults(int count, StopWatch watch) {  
        log.info("Test {} took {}ms (avg. {} ns/log)", new Object[]{  
                watch.getTag(),  
                watch.getElapsedTime(),  
                watch.getElapsedTime() * 1000 * 1000 / count});  
    }  
   
}  