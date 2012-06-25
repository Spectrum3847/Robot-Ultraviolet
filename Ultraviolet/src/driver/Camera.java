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
public class Camera {
    
    
    private static String url;
    private static SocketConnection clientSocket;
    public static InputStream is;
    public static OutputStream os;
    public static byte[] receiveData = new byte[HW.BUFFER_SIZE];
    

    
    public Camera(){
        this(HW.CAMERA_IP,HW.CAMERA_PORT);
    }
    
    public Camera(String ip, String port){
        try
        {
            url = "socket://" + ip + ":" + port;
            clientSocket = (SocketConnection)Connector.open(url);
            is = clientSocket.openInputStream();
            os = clientSocket.openOutputStream();
            System.out.println(clientSocket.getAddress() + ":" + clientSocket.getPort());
        }catch(IOException e){System.out.println("Failed to connect to camera");}
        
    }
    
    public static String receiveRaw()
    {
        try
        {
            os.write(42);
            is.read(receiveData);
        }catch(Exception e){}
        char carray[] = new char[receiveData.length];
        String s = "";
        for(int i = 0; i < receiveData.length; i++)
        {
            carray[i] += receiveData[i];
            s += carray[i];
        }
        
        return s;
    }
    
    public static int[] receiveData()
    {
        return parseCamData(tokenizeNewLine(safeChar(receiveRaw())));
    }
    
    public static String[] tokenizeNewLine(String str)
    {
        if(str != null)
        {
            StringTokenizer tokened = new StringTokenizer(str, "\n");
            String final_str[] = new String[tokened.countTokens()];
            int i = 0;
            while(tokened.hasMoreTokens())
            {
                final_str[i] = tokened.nextToken();
                i++;
            }
            return final_str;
        }
        return null;
    }
    
    private static int[] parseCamData(String[] sarray)
    {
  
        if(sarray != null)
        {
            int farray[] = new int[sarray.length];
            for(int i = 0; i < sarray.length; i++)
            {
                try{
                farray[i] = Integer.parseInt(sarray[i]);
                }catch(NumberFormatException e){}
            }
            
            return farray;
        }
        return null;
    }
    
    public static String safeChar(String input)
    {
        char[] allowed = "-0123456789.\n".toCharArray();
        char[] charArray = input.toString().toCharArray();
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < charArray.length; i++)
        {
            for (int j = 0; j < allowed.length; j++)
            {
                if(charArray[i]==allowed[j]) result.append(allowed[j]);
            }
        }
        
        return result.toString();
    }
    
    public static String printData(float[] data)
    {
        String s = "";
        
        for(int i=0;i < data.length;i++)
        {
            s+=data[i] + " ";
        }
        
        return s;
    }
    
    public static String arraytoString(String[] array){
        String s = "";
        for(int i = 0; i < array.length;i++){
            s+= array[i] + " ";
            
        }
        return s;
    }
    
}
