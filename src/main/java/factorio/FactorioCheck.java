package factorio;

import util.Helper;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class FactorioCheck {

    public String hostname;
    public String serverData;
    private int serverPort = 34197;
    private String hexStrUDPDiscover = "0200000012091bc2a8ee7297";
    private String hexStrUDPIdentify = "2401800100000000a8ee72972966213b691077780e78584461726b56696b696e675878000000751da6cb33123c3c010462617365001209514e12d600";
    private DatagramSocket socket;
    private boolean running = false;
    private byte[] buf = new byte[65535];
    private DatagramPacket packet = null;

    public FactorioCheck(String targetAdress){
        hostname = targetAdress;
        requestServerInfo();
    }

    public void requestServerInfo(){
        try{
            socket = new DatagramSocket(55555);

            Thread t = new Thread(){
                public void run(){
                    running = true;
                    runPair(hexStrUDPDiscover);
                    runPair(hexStrUDPIdentify);
                    System.out.println("Thread exited");
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
            String strRtn = new String(buf);
            System.out.println(strRtn + ":::" + buf.length + ":::" + Arrays.toString(buf));
            rtn = new String(buf);
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return rtn;
    }
}
