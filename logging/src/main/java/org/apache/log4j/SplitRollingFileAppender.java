//package org.apache.log4j;
//
//import org.apache.log4j.helpers.LogLog;
//import org.apache.log4j.spi.LoggingEvent;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// *
// * 可按子系统分割日志文件，以loggername的前缀进行分割。日志文件名格式：splitprefix+runtimeprefix+filename
// * <p>在RollingFileAppender的配置信息基础上增加"splitString"属性，
// * 根据category分割文件写日志，日志文件使用splitprefix-filename。
// * 如果没有找到匹配的分割信息，日志文件使用filename。
// *
// * </p>
// * <p><pre>
// *     用法：
// *      1，在appender配置中，class填写org.apache.log4j.SplitRollingFileAppender
// *      2，添加splitString参数，值格式为：包名前缀:分割日志前缀。
// *      3，其他属性的填写与RollingFileAppender属性一致。
// * </pre></p>
// *
// * @author Wang Huanyu
// * @version 0.1 2015-7-4
// */
//public class SplitRollingFileAppender extends RollingFileAppender {
//
//    /**
//     * java -Dorg.apache.log4j.splitRollingFileAppenderPrefix=logfile_runtime_prefix
//     */
//    public static final String RT_FILE_PREFIX_ID = "org.apache.log4j.splitRollingFileAppenderPrefix";
//
//    private String rtFilePrefix = null;
//    /**
//     * 分割属性，定义格式：报名:日志文件前缀;
//     */
//    String splitString;
//    private Map<String,String> split = new HashMap<String,String>();
//    /**
//     * split匹配缓存,key:category prefix,value:split prefix
//     */
//    private Map<String,String> splitCache = new HashMap<String,String>();
//
//    private Map<String,RollingFileAppender> splitAppenderCache = new HashMap<String,RollingFileAppender>();
//
//    boolean splited = false;
//
//    /**
//     The default constructor simply calls its {@link
//    FileAppender#FileAppender parents constructor}.  */
//    public
//    SplitRollingFileAppender() {
//        super();
//    }
//
//    /**
//     Instantiate a RollingFileAppender and open the file designated by
//     <code>filename</code>. The opened filename will become the ouput
//     destination for this appender.
//
//     <p>If the <code>append</code> parameter is true, the file will be
//     appended to. Otherwise, the file desginated by
//     <code>filename</code> will be truncated before being opened.
//     */
//    public
//    SplitRollingFileAppender(Layout layout, String filename, boolean append)
//            throws IOException {
//        super(layout, filename, append);
//    }
//
//    /**
//     Instantiate a FileAppender and open the file designated by
//     <code>filename</code>. The opened filename will become the output
//     destination for this appender.
//
//     <p>The file will be appended to.  */
//    public
//    SplitRollingFileAppender(Layout layout, String filename) throws IOException {
//        super(layout, filename);
//    }
//    @Override
//    protected void subAppend(LoggingEvent event) {
//        //加入分割日志写入逻辑
//        String loggername = event.getLoggerName();
//
//        RollingFileAppender rollingFileAppender = discoverSplitAppender(loggername);
//        if(rollingFileAppender != null){
//            rollingFileAppender.subAppend(event);
//            return;
//        }
//
//        super.subAppend(event);
//    }
//
//    /**
//     *
//     * @param loggername 日志分类，一般为日志使用Logger的类名。
//     * @return
//     */
//    private RollingFileAppender discoverSplitAppender(final String loggername) {
//        //获取分割日志文件前缀
////        从缓存中获取
//        String splitStr = splitCache.get(loggername);
//        if(splitStr == null || "".equals(splitStr)){
//            //从split中匹配分割文件前缀，并以loggername为key缓存。
//            Set<Map.Entry<String, String>> entries = split.entrySet();
//            for (Map.Entry<String,String> entry : entries){
//                String key = entry.getKey();
//                if(loggername.startsWith(key)){
//                    splitStr = entry.getValue();
//                    splitCache.put(loggername,splitStr);
//                    break;
//                }
//            }
//        }
//
//        //
//        RollingFileAppender rollingFileAppender = null;
//        if(splitStr != null && !"".equals(splitStr)){
//            rollingFileAppender = splitAppenderCache.get(splitStr);
//            if(rollingFileAppender == null){
//                rollingFileAppender = newInstance(splitStr);
//                splitAppenderCache.put(splitStr,rollingFileAppender);
//            }
//        }
//        return rollingFileAppender;
//    }
//
//    private RollingFileAppender newInstance(String splitStr) {
//        RollingFileAppender rollingFileAppender = new RollingFileAppender();
//        rollingFileAppender.setMaxBackupIndex(this.getMaxBackupIndex());
//        rollingFileAppender.setAppend(this.getAppend());
//        rollingFileAppender.setBufferedIO(this.getBufferedIO());
//        rollingFileAppender.setBufferSize(this.getBufferSize());
//        rollingFileAppender.setEncoding(this.getEncoding());
//        rollingFileAppender.setLayout(this.getLayout());
//        rollingFileAppender.maxFileSize=this.maxFileSize;
//        rollingFileAppender.setErrorHandler(this.getErrorHandler());
//        rollingFileAppender.setThreshold(this.getThreshold());
////        rollingFileAppender.setQWForFiles();
////        rollingFileAppender.setWriter();
//        rollingFileAppender.setFile(appendFilePrefix(this.getFile(),splitStr));
//        rollingFileAppender.activateOptions();
//        return rollingFileAppender;
//    }
//
//    private String appendFilePrefix(String filepath, String prefix) {
//        File file = new File(filepath);
//        String parent = file.getParent();
//        String name = file.getName();
//        return parent + File.separator + prefix + name;
//    }
//
//    private void loadSplistString() {
//        String[] splitgroup = this.splitString.split(";");
//        for (int i = 0; i < splitgroup.length; i++) {
//            String[] splitinfo = splitgroup[i].split(":");
//            if(splitinfo.length != 2){
//                // fixme throw exception
//            }
//            split.put(splitinfo[0],splitinfo[0]);
//        }
//    }
//
//    private String getConfigurationValue(String property) {
//        LogLog.debug("Trying to get configuration for item " + property);
//
//        try {
//            String value = getSystemProperty(property, null);
//            if (value != null) {
//                LogLog.debug("Found system property [" + value + "] for " + property);
//                return value;
//            }
//
//            LogLog.debug("No system property found for property " + property);
//        } catch (SecurityException e) {
//            LogLog.debug("Security prevented reading system property " + property);
//        }
//        LogLog.debug("No configuration defined for item " + property);
//        return null;
//    }
//
//    private static String getSystemProperty(final String key, final String def)
//            throws SecurityException {
//        return (String) AccessController.doPrivileged(
//                new PrivilegedAction() {
//                    public Object run() {
//                        return System.getProperty(key, def);
//                    }
//                });
//    }
//
//    public void setSplitString(String splitString) {
//        this.splitString = splitString;
//        // load and format split string
//        loadSplistString();
//        splited=true;
//    }
//
//    public void setFile(String file) {
//        //预留，接收jvm参数
//        String filename = file;
//
//        String rtFilePrefix = this.getConfigurationValue(RT_FILE_PREFIX_ID);
//        if(rtFilePrefix != null){
//            filename = appendFilePrefix(filename,rtFilePrefix);
//        }
//        fileName = filename;
//    }
//}
