package factorio;

import util.Helper;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class FactorioCheck {

    public String hostname;
    public String serverData = "";
    private int serverPort = 27003;
    private String hexStrUDPDiscover = "0200000012091bc2a8ee7297";
    private String hexStrUDPIdentify = "2401800100000000a8ee72972966213b691077780e78584461726b56696b696e675878000000751da6cb33123c3c010462617365001209514e12d600";
    private String av = "813dd32200000000000000001000000000000000060000000000000023dbf97e6ae4594000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    private DatagramSocket socket;
    private boolean running = false;
    private byte[] buf = new byte[65535];
    private DatagramPacket packet = null;

    public FactorioCheck(String targetAdress){
        hostname = targetAdress;
        try {System.out.println(InetAddress.getByName(hostname)); } catch (UnknownHostException e) { e.printStackTrace(); }
        requestServerInfo();
    }

    public void requestServerInfo(){
        try{
            socket = new DatagramSocket(55555);

            Thread t = new Thread(){
                public void run(){
                    running = true;
                    serverData += runPair(av);
                    //serverData += runPair(hexStrUDPDiscover) + ":::";
                    //serverData += runPair(hexStrUDPIdentify);
                    System.out.println("Thread exited");
                    System.out.println(serverData);
                }
            };
            t.start();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public String runPair(String StringToSend){
        String rtn = "";
        try{
            buf = Helper.hexStringToByteArray(StringToSend);
            packet = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName(hostname), serverPort);
            socket.send(packet);
            buf = new byte[65535];
            packet = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName(hostname), serverPort);
            socket.receive(packet);
            buf = packet.getData();
            String strRtn = readByteArr();
            System.out.println(strRtn);
            rtn = strRtn;
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return rtn;
    }

    private String readByteArr(){
        String rtn = bytesToHex(buf);
        rtn = rtn.replace("0000000000000000000000000000", "");
        return rtn;
    }

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
