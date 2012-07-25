/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import com.sun.squawk.util.StringTokenizer;
import framework.HW;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 *
 * @author David
 */
public class CamData {
    
    private static final boolean DEBUG = true;
    private static String url;
    private static SocketConnection clientSocket;
    public static InputStream is;
    public static OutputStream os;
    public static byte[] receiveData = new byte[HW.BUFFER_SIZE];
    private static boolean connected = false;

    public CamData() {
        this(HW.CAMERA_IP, HW.CAMERA_PORT);
    }

    public CamData(String ip, String port) {
        try {
            url = "socket://" + ip + ":" + port;
            clientSocket = (SocketConnection) Connector.open(url);
            is = clientSocket.openInputStream();
            os = clientSocket.openOutputStream();
            System.out.println(clientSocket.getAddress() + ":" + clientSocket.getPort());
            connected = true;
        } catch (IOException e) {
            System.out.println("Failed to connect to camera");
            connected = false;
        }

    }

    public static void disconnect() {
        try {
            clientSocket.close();
            os.close();
            is.close();
            connected = false;
        } catch (IOException ex) {
            System.out.println("Disconnect Failed");
            connected = true;
        }
    }

    public static String receiveRaw() {
        if (connected) {
            try {
                os.write(42);
                is.read(receiveData);
            } catch (Exception e) {
                System.out.println("Failed to receive raw data");
            }
            char carray[] = new char[receiveData.length];
            String s = "";
            for (int i = 0; i < receiveData.length; i++) {
                carray[i] += receiveData[i];
                s += carray[i];
            }
            if (DEBUG){
                System.out.println("Cam Data = " + s);
            }
            return s;
        }
        if (DEBUG){
            System.out.println("Camera not connected = no data");
        }
        return "0";
    }

    public static int[] receiveData() {
        return parseCamData(receiveRaw());
    }

    public static String[] tokenizeNewLine(String str) {

        if (str != null) {
            StringTokenizer tokened = new StringTokenizer(str, "\n");
            String final_str[] = new String[tokened.countTokens()];
            int i = 0;
            while (tokened.hasMoreTokens()) {
                final_str[i] = tokened.nextToken();
                i++;
            }
            return final_str;
        }
        return null;
    }

    public static int[] parseCamData(String input) {
        if (input != null) {
            int farray[] = new int[8];
            farray[0] = Integer.parseInt(String.valueOf(input.toCharArray(), 0, 3));
            farray[1] = Integer.parseInt(String.valueOf(input.toCharArray(), 3, 3));
            farray[2] = Integer.parseInt(String.valueOf(input.toCharArray(), 6, 3));
            farray[3] = Integer.parseInt(String.valueOf(input.toCharArray(), 9, 3));
            farray[4] = Integer.parseInt(String.valueOf(input.toCharArray(), 12, 3));
            farray[5] = Integer.parseInt(String.valueOf(input.toCharArray(), 15, 3));
            farray[6] = Integer.parseInt(String.valueOf(input.toCharArray(), 18, 3));
            farray[7] = Integer.parseInt(String.valueOf(input.toCharArray(), 21, 3));
            return farray;
        }
        return null;
    }

    private static int[] parseCamData(String[] sarray) {

        if (sarray != null) {
            int farray[] = new int[sarray.length];
            for (int i = 0; i < sarray.length; i++) {
                try {
                    farray[i] = Integer.parseInt(sarray[i]);
                } catch (NumberFormatException e) {
                }
            }

            return farray;
        }
        return null;
    }

    public static String safeChar(String input) {
        char[] allowed = "-0123456789.\n".toCharArray();
        char[] charArray = input.toString().toCharArray();
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < allowed.length; j++) {
                if (charArray[i] == allowed[j]) {
                    result.append(allowed[j]);
                }
            }
        }

        return result.toString();
    }

    public static String printData(float[] data) {
        String s = "";

        for (int i = 0; i < data.length; i++) {
            s += data[i] + " ";
        }

        return s;
    }

    public static String arraytoString(String[] array) {
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s += array[i] + " ";

        }
        return s;
    }
    
    public static boolean isConnected(){
        return connected;
    }
}
