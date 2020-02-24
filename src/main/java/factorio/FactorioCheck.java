package factorio;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FactorioCheck {

    public String hostname;
    public String serverData;
    private int serverPort = 34197;
    private String hexStrUDPDiscover = "220000001208a0c1365395f1";
    private DatagramSocket socket;

    public FactorioCheck(String targetAdress){
        hostname = targetAdress;
        requestServerInfo();
    }

    public void requestServerInfo(){
        try{
            socket = new DatagramSocket(serverPort, InetAddress.getByName(hostname));
            //TODO

        } catch (SocketException e){
            e.printStackTrace();
        } catch (UnknownHostException e){
            System.out.println("Host not found");
        }
    }
}
