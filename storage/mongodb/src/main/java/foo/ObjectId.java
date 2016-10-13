package foo;

import java.io.Serializable;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.prefs.BackingStoreException;

/**
 * http://mp.weixin.qq.com/s?__biz=MzA5Nzc4OTA1Mw==&mid=2659598286&idx=1&sn=3172172ccea316b0ed83429ae718b54d&chksm=8be9eadcbc9e63caa10d708274b4fa34ceffa416ef4527e10e6b7a1a2d2f32cf8592d65bf728&mpshare=1&scene=1&srcid=1013MZH1OJuYcFl6iGc5XOG8#rd
 * <p>
 * 12-byte MongoDB ObjectId 的结构是：
 * <p>
 * a 4-byte value representing the seconds since the Unix epoch,
 * <p>
 * a 3-byte machine identifier,
 * <p>
 * a 2-byte process id, and
 * <p>
 * a 3-byte counter, starting with a random value.
 * <p>
 * Created by whydk on 2016/7/21.
 */
public class ObjectId implements Serializable {
    private static int datetoTimestampSeconds(final Date time) {
        return (int) (time.getTime() / 1000);
    }

    public static int getMachinePiece() throws SocketException {
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            sb.append(ni.toString());
            byte[] mac = ni.getHardwareAddress();
            if (mac != null) {
                ByteBuffer bb = ByteBuffer.wrap(mac);
                try {
                    sb.append(bb.getChar());
                    sb.append(bb.getChar());
                    sb.append(bb.getChar());
                } catch (BufferUnderflowException shortHardwardAddressException) {

                }
            }
        }
        int machinePiece = sb.toString().hashCode();
        return machinePiece;
    }

    public static short processId(){
        short processId;

        try {
            String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
            if(processName.contains("@")){
                processId = Short.parseShort(processName.substring(0,processName.indexOf("@")));
            }else{
                processId = (short)java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
            }
        } catch (Throwable e) {
            processId = (short)new SecureRandom().nextInt();
        }
        return processId;
    }

    private static final AtomicInteger NEXT_COUNT = new AtomicInteger(new SecureRandom().nextInt());

    public static void main(String[] args) throws SocketException {
        System.out.println(datetoTimestampSeconds(new Date()));
        System.out.println(getMachinePiece());
        System.out.println(processId());
    }
}
