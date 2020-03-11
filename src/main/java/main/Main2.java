package main;

import general.IsAliveChecker;
import server.ServerHandler;
import util.Helper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main2 {

    private static ServerHandler sh;
    private static TimerTask tt;
    private static Timer t;

    public static void main(String[] args){
        IsAliveChecker c = new IsAliveChecker();
        ArrayList<Integer> tcpPorts = new ArrayList<>();
        ArrayList<Integer> udpPorts = new ArrayList<>();
        String domain = "bilbosjournal.com";
        tcpPorts.add(7777);
        tcpPorts.add(25565);
        tcpPorts.add(25566);
        tcpPorts.add(27000);
        udpPorts.add(34197);
        c.checkServer(domain, tcpPorts, udpPorts);
        System.out.println(c.results);

        try{ sh = new ServerHandler(Helper.formatInfo(c.results)); } catch (Exception e) { e.printStackTrace(); }

        tt = new TimerTask() {
            @Override
            public void run() {
                c.checkServer(domain, tcpPorts, udpPorts);
                sh.updateServerInfo(Helper.formatInfo(c.results));
                System.out.println(c.results);
            }
        };

        t = new Timer();
        t.scheduleAtFixedRate(tt, 30000, 30000);
        while(true);
    }
}