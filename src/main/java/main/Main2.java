package main;

import general.IsAliveChecker;
import general.RCONChecker;
import net.kronos.rkon.core.Rcon;
import server.ServerHandler;
import util.GameServer;
import util.Helper;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main2 {

    private static ServerHandler sh;
    private static TimerTask tt;
    private static Timer t;

    public static String domain = "bilbosjournal.com";
    public static ArrayList<GameServer> serverList = new ArrayList<>();

    public static void main(String[] args){

        IsAliveChecker c = new IsAliveChecker();
        RCONChecker rc = new RCONChecker();

        serverList.add(new GameServer("Avorion", GameServer.ServerType.Avorion, domain, GameServer.PortType.UDP, 27000));
        serverList.add(new GameServer("Factorio", GameServer.ServerType.Factorio, domain, GameServer.PortType.UDP, 34197));
        serverList.add(new GameServer("Terraria", GameServer.ServerType.Terraria, domain, GameServer.PortType.TCP, 7777));
        serverList.add(new GameServer("Minecraft", GameServer.ServerType.Minecraft, domain, GameServer.PortType.TCP, 25565));
        serverList.add(new GameServer("Minecraft Modded", GameServer.ServerType.Minecraft, domain, GameServer.PortType.TCP, 25566));
        serverList.add(new GameServer("TerrariaRCONTCP", GameServer.ServerType.Terraria, domain, GameServer.PortType.TCP, 7878));
        serverList.add(new GameServer("TerrariaRCONUDP", GameServer.ServerType.Terraria, domain, GameServer.PortType.UDP, 7878));
        serverList.add(new GameServer("MinecraftRCONTCP", GameServer.ServerType.Terraria, domain, GameServer.PortType.TCP, 25575));
        serverList.add(new GameServer("MinecraftRCONUDP", GameServer.ServerType.Terraria, domain, GameServer.PortType.UDP, 25575));
        serverList.add(new GameServer("AvorionRCONTCP", GameServer.ServerType.Terraria, domain, GameServer.PortType.TCP, 27015));
        serverList.add(new GameServer("AvorionRCONUDP", GameServer.ServerType.Terraria, domain, GameServer.PortType.UDP, 27015));
        serverList.add(new GameServer("FactorioRCONTCP", GameServer.ServerType.Terraria, domain, GameServer.PortType.TCP, 34198));
        serverList.add(new GameServer("FactorioRCONUDP", GameServer.ServerType.Terraria, domain, GameServer.PortType.UDP, 34198));


        c.checkServer(serverList);
        rc.checkRCONServers(serverList);

        try{ sh = new ServerHandler(Helper.formatInfo(serverList)); } catch (Exception e) { e.printStackTrace(); }

        tt = new TimerTask() {
            @Override
            public void run() {
                c.checkServer(serverList);
                rc.checkRCONServers(serverList);
                sh.updateServerInfo(Helper.formatInfo(serverList));
            }
        };

        t = new Timer();
        t.scheduleAtFixedRate(tt, 30000, 30000);
        while(true);
    }
}
