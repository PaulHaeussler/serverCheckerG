package general;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class IsAliveUDP {

    public static boolean serverListening(String host, int port, int localPort)
    {
        DatagramSocket s = null;
        try
        {
            s = new DatagramSocket(localPort);
            s.connect(InetAddress.getByName(host), port);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if(s != null)
                try {s.close();}
                catch(Exception e){}
        }
    }
}
