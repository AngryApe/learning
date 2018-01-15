//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdk.concurrency.sparkle;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

public final class OSUtility {
    private static final String OS_NAME_CONSTANT = "os.name";
    private static final String LINE_SEPARATOR_CONSTANT = "line.separator";
    private static final String FILE_SEPARATOR_CONSTANT = "file.separator";
    private static final String CURRENT_PATH = "user.dir";
    private static final String OS_WINDOWS = "WINDOWS";
    private static final String OS_LINUX = "LINUX";
    private static final String OS_MAC = "MAC OS";
    private static Properties properties = System.getProperties();
    public static String OS_NAME = _getProperty("os.name");
    private static String LINE_SEPARATOR = _getProperty("line.separator");
    private static String FILE_SEPARATOR = _getProperty("file.separator");

    public OSUtility() {
    }

    public static final int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static final int getCurrentProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        int processID = Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
        return processID;
    }

    public static final String getCurrentPath() {
        String currentPath = _getProperty("user.dir");
        return currentPath;
    }

    public static final String getStandardPath() {
        File directory = new File("");

        try {
            String standardPath = directory.getCanonicalPath();
            return standardPath;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static final String getAbsolutionPath() {
        File directory = new File("");

        try {
            String absolutionPath = directory.getAbsolutePath();
            return absolutionPath;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static final String getSystemOSName() {
        Properties properties = System.getProperties();
        String osName = _getProperty("os.name");
        return osName;
    }

    public static final String getStringIP() throws SocketException, UnknownHostException {
        String osName = getSystemOSName();
        String currentIP = "0.0.0.0";
        String tmpHostName = null;

        try {
            String osNameTmp = osName.toUpperCase();
            InetAddress iNet = null;
            if (osNameTmp.startsWith("WINDOWS")) {
                iNet = _getWindowsLocalAddress();
            } else if (osNameTmp.startsWith("LINUX")) {
                iNet = _getUnixLocalAddress();
            } else if (osNameTmp.startsWith("MAC OS")) {
                iNet = _getMacLocalAddress();
            }

            if (iNet == null) {
                throw new UnknownHostException("local IP is unknown!");
            } else {
                tmpHostName = iNet.getHostName();
                currentIP = InetAddress.getByName(tmpHostName).getHostAddress();
                return currentIP;
            }
        } catch (SocketException var5) {
            throw new SocketException("error to get host IP " + var5.getMessage());
        } catch (UnknownHostException var6) {
            throw new UnknownHostException("unknown error when get host IP");
        }
    }

    public static final long getLongIP() throws SocketException, UnknownHostException {
        String stringIP = getStringIP();
        if (stringIP == null) {
            return -1L;
        } else {
            byte[] intIP = null;
            if (stringIP.contains(":")) {
                intIP = _ipv6_2_Int(stringIP);
            } else {
                intIP = _ipv4_2_Int(stringIP);
            }

            BigInteger bigInteger = new BigInteger(intIP);
            long lngIP = bigInteger.longValue();
            return lngIP;
        }
    }

    private static final String _getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    private static final InetAddress _getWindowsLocalAddress() throws UnknownHostException {
        InetAddress iNet = InetAddress.getLocalHost();
        return iNet;
    }

    private static final InetAddress _getUnixLocalAddress() throws SocketException {
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

        InetAddress iNet;
        for(iNet = null; netInterfaces.hasMoreElements(); iNet = null) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            iNet = (InetAddress)ni.getInetAddresses().nextElement();
            if (!iNet.isSiteLocalAddress() && !iNet.isLoopbackAddress() && iNet.getHostAddress().indexOf(":") == -1) {
                return iNet;
            }
        }

        return iNet;
    }

    private static final InetAddress _getMacLocalAddress() throws SocketException {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address;
        } catch (Exception var1) {
            return null;
        }
    }

    private static final byte[] _ipv6_2_Int(String stringIP) {
        byte[] ret = new byte[17];
        ret[0] = 0;
        int ib = 16;
        boolean compositedFlag = false;
        String tmpStringIP = stringIP;
        if (stringIP.startsWith(":")) {
            tmpStringIP = stringIP.substring(1);
        }

        String[] groups = tmpStringIP.split(":");

        for(int ig = groups.length - 1; ig > -1; --ig) {
            if (groups[ig].contains(".")) {
                ret = _ipv4_2_Int(groups[ig]);
                compositedFlag = true;
            } else {
                int temp;
                if (groups[ig].equals("")) {
                    for(temp = 9 - (groups.length + (compositedFlag ? 1 : 0)); temp-- > 0; ret[ib--] = 0) {
                        ret[ib--] = 0;
                    }
                } else {
                    temp = Integer.parseInt(groups[ig], 16);
                    ret[ib--] = (byte)temp;
                    ret[ib--] = (byte)(temp >> 8);
                }
            }
        }

        return ret;
    }

    private static final byte[] _ipv4_2_Int(String stringIP) {
        int pos1 = stringIP.indexOf(".");
        int pos2 = stringIP.indexOf(".", pos1 + 1);
        int pos3 = stringIP.indexOf(".", pos2 + 1);
        byte[] ret = new byte[]{0, (byte)Integer.parseInt(stringIP.substring(0, pos1)), (byte)Integer.parseInt(stringIP.substring(pos1 + 1, pos2)), (byte)Integer.parseInt(stringIP.substring(pos2 + 1, pos3)), (byte)Integer.parseInt(stringIP.substring(pos3 + 1))};
        return ret;
    }
}
