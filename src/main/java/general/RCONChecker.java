package general;

import minecraft.MinecraftCheck;
import net.kronos.rkon.core.Rcon;
import util.GameServer;
import util.Helper;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class RCONChecker implements Runnable {

    public ArrayList<Thread> threads = new ArrayList<>();

    private GameServer gs;

    public void checkRCONServers(ArrayList<GameServer> serverList){


                for (GameServer gameServer : serverList) {
                    if(!gameServer.isRCONenabled) continue;
                    Runnable r = new RCONChecker(gameServer);
                    Thread t = new Thread(r);
                    threads.add(t);
                    t.start();
                }
                Helper.waitForThreads(threads);

    }

    public RCONChecker(){

    }

    public RCONChecker(GameServer gameServer){
        gs = gameServer;
    }

    public void run() {
        try {
            if(gs.serverType == GameServer.ServerType.Minecraft){
                MinecraftCheck mc = new MinecraftCheck(gs.domain);
                dispatchMCCheck(mc);
            } else {
                Rcon rcon = new Rcon(InetAddress.getByName(gs.domain).getHostAddress(), gs.RCONport, gs.RCONpassword.getBytes());
                if (gs.serverType == GameServer.ServerType.Factorio) {
                    gs.RCONtext = rcon.command("help");
                } else if (gs.serverType == GameServer.ServerType.Avorion) {
                    gs.RCONtext = rcon.command("help");
                } else if (gs.serverType == GameServer.ServerType.Terraria) {
                    gs.RCONtext = rcon.command("help");
                } else {
                    System.out.println("No procedure implemented for RCON for server type " + gs.serverType);
                }
                System.out.println(gs.RCONtext);
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    private void dispatchMCCheck(MinecraftCheck mc){
        mc.requestServerInfo();
        gs.RCONtext = mc.serverInfo;
        gs.cleanMCReturn(mc.serverInfo);
    }
}
