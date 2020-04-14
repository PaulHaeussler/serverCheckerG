package general;

import util.GameServer;
import util.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class IsAliveChecker {

    public ArrayList<Thread> threads = new ArrayList<>();
    private int localStartPortUDP = 6500;

    public void checkServer(ArrayList<GameServer> serverList){

        for(GameServer gs : serverList){
            if(gs.portType == GameServer.PortType.TCP){
                Thread t = new Thread(() -> {
                    boolean isListening = IsAliveTCP.serverListening(gs.domain, gs.port);
                    gs.isOnline = isListening;
                    System.out.println(gs.port + "::" + isListening);
                });
                threads.add(t);
                t.start();
            } else if(gs.portType == GameServer.PortType.UDP){
                Thread t = new Thread(() -> {
                    boolean isListening = IsAliveUDP.serverListening(gs.domain, gs.port, localStartPortUDP);
                    localStartPortUDP++;
                    gs.isOnline = isListening;
                    System.out.println(gs.port + "::" + isListening);
                });
                threads.add(t);
                t.start();
            } else {
                System.out.println("Server had no porttype assigned, this shouldnt be able to happen");
            }
        }

        Helper.waitForThreads(threads);
    }
}
