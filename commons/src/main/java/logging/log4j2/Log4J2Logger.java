package logging.log4j2;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * User-specified Log implementation for common logging. Add following file and content to classpath.
 * <pre>
 *     commons-logging.properties:
 *          org.apache.commons.logging.Log=log4j2.Log4J2Logger
 * </pre>
 * Created by WangHuanyu on 2015/12/2.
 */
public class Log4J2Logger implements Log, Serializable {

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 5160705895411730424L;

    // ------------------------------------------------------------- Attributes

    /**
     * The fully qualified name of the Log4JLogger class.
     */
    private static final String FQCN = Log4J2Logger.class.getName();

    /**
     * Log to this logger
     */
    private transient volatile Logger logger = null;

    /**
     * Logger name
     */
    private final String name;

    private static final Level traceLevel;

    // ------------------------------------------------------------
    // Static Initializer.
    //
    // Note that this must come after the static variable declarations
    // otherwise initialiser expressions associated with those variables
    // will override any settings done here.
    //
    // Verify that log4j is available, and that it is version 1.2.
    // If an ExceptionInInitializerError is generated, then LogFactoryImpl
    // will treat that as meaning that the appropriate underlying logging
    // library is just not present - innerfloat discovery is in progress then
    // discovery will continue.
    // ------------------------------------------------------------

    static {
//        innerfloat (!Priority.class.isAssignableFrom(Level.class)) {
//            // nope, this is log4j 1.3, so force an ExceptionInInitializerError
//            throw new InstantiationError("Log4J 1.2 not available");
//        }

        // Releases of log4j1.2 >= 1.2.12 have Priority.TRACE available, earlier
        // versions do not. If TRACE is not available, then we have to map
        // calls to Log.trace(...) onto the DEBUG level.

        Level _traceLevel;
        try {
            _traceLevel = (Level) Level.class.getDeclaredField("TRACE").get(null);
        } catch (Exception ex) {
            // ok, trace not available
            _traceLevel = Level.DEBUG;
        }
        traceLevel = _traceLevel;
    }

    // ------------------------------------------------------------ Constructor

    public Log4J2Logger() {
        name = null;
    }

    /**
     * Base constructor.
     */
    public Log4J2Logger(String name) {
        this.name = name;
        this.logger = getLogger();
    }

    /**
     * For use with a log4j factory.
     */
    public Log4J2Logger(Logger logger) {
        if (logger == null) {
            throw new IllegalArgumentException(
                    "Warning - null logger in constructor; possible log4j misconfiguration.");
        }
        this.name = logger.getName();
        this.logger = logger;
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.TRACE</code>.
     * When using a log4j version that does not support the <code>TRACE</code>
     * level, the message will be logged at the <code>DEBUG</code> level.
     *
     * @param message to log
     * @see Log#trace(Object)
     */
    public void trace(Object message) {
        //getLogger().log(FQCN, traceLevel, message, null);
        getLogger().log(traceLevel, message, null);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.TRACE</code>.
     * When using a log4j version that does not support the <code>TRACE</code>
     * level, the message will be logged at the <code>DEBUG</code> level.
     *
     * @param message to log
     * @param t       log this cause
     * @see Log#trace(Object, Throwable)
     */
    public void trace(Object message, Throwable t) {
//        getLogger().log(FQCN, traceLevel, message, t);
        getLogger().log(traceLevel, message, t);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.DEBUG</code>.
     *
     * @param message to log
     * @see Log#debug(Object)
     */
    public void debug(Object message) {
//        getLogger().log(FQCN, Level.DEBUG, message, null);
        getLogger().log(Level.DEBUG, message, null);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.DEBUG</code>.
     *
     * @param message to log
     * @param t       log this cause
     * @see Log#debug(Object, Throwable)
     */
    public void debug(Object message, Throwable t) {
//        getLogger().log(FQCN, Level.DEBUG, message, t);
        getLogger().log(Level.DEBUG, message, t);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.INFO</code>.
     *
     * @param message to log
     * @see Log#info(Object)
     */
    public void info(Object message) {
//        getLogger().log(FQCN, Level.INFO, message, null);
        getLogger().log(Level.INFO, message, null);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.INFO</code>.
     *
     * @param message to log
     * @param t       log this cause
     * @see Log#info(Object, Throwable)
     */
    public void info(Object message, Throwable t) {
//        getLogger().log(FQCN, Level.INFO, message, t);
        getLogger().log(Level.INFO, message, t);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.WARN</code>.
     *
     * @param message to log
     * @see Log#warn(Object)
     */
    public void warn(Object message) {
//        getLogger().log(FQCN, Level.WARN, message, null);
        getLogger().log(Level.WARN, message, null);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.WARN</code>.
     *
     * @param message to log
     * @param t       log this cause
     * @see Log#warn(Object, Throwable)
     */
    public void warn(Object message, Throwable t) {
        getLogger().log(Level.WARN, message, t);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.ERROR</code>.
     *
     * @param message to log
     * @see Log#error(Object)
     */
    public void error(Object message) {
        getLogger().log(Level.ERROR, message, null);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.ERROR</code>.
     *
     * @param message to log
     * @param t       log this cause
     * @see Log#error(Object, Throwable)
     */
    public void error(Object message, Throwable t) {
        getLogger().log(Level.ERROR, message, t);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.FATAL</code>.
     *
     * @param message to log
     * @see Log#fatal(Object)
     */
    public void fatal(Object message) {
        getLogger().log(Level.FATAL, message, null);
    }

    /**
     * Logs a message with <code>org.apache.log4j.Priority.FATAL</code>.
     *
     * @param message to log
     * @param t       log this cause
     * @see Log#fatal(Object, Throwable)
     */
    public void fatal(Object message, Throwable t) {
//        getLogger().log(FQCN, Level.FATAL, message, t);
        getLogger().log(Level.FATAL, message, t);
    }

    /**
     * Return the native Logger instance we are using.
     */
    public Logger getLogger() {
        Logger result = logger;
        if (result == null) {
            synchronized (this) {
                result = logger;
                if (result == null) {
//                    logger = result = Logger.getLogger(name);
                    logger = result = LogManager.getLogger(name);
                }
            }
        }
        return result;
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>DEBUG</code> priority.
     */
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>ERROR</code> priority.
     */
    public boolean isErrorEnabled() {
        return getLogger().isEnabled(Level.ERROR);
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>FATAL</code> priority.
     */
    public boolean isFatalEnabled() {
        return getLogger().isEnabled(Level.FATAL);
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>INFO</code> priority.
     */
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>TRACE</code> priority.
     * When using a log4j version that does not support the TRACE level, this call
     * will report whether <code>DEBUG</code> is enabled or not.
     */
    public boolean isTraceEnabled() {
        return getLogger().isEnabled(traceLevel);
    }

    /**
     * Check whether the Log4j Logger used is enabled for <code>WARN</code> priority.
     */
    public boolean isWarnEnabled() {
        return getLogger().isEnabled(Level.WARN);
    }
}
