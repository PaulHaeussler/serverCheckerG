package general;

import net.kronos.rkon.core.Rcon;
import util.GameServer;

import java.net.InetAddress;
import java.util.ArrayList;

public class RCONChecker {

    public void checkRCONServers(ArrayList<GameServer> serverList){
        try{
            //Rcon rcon = new Rcon(InetAddress.getByName("bilbosjournal.com").getHostAddress(), 7878, "mitsuyaCider11".getBytes());
            //System.out.println(rcon.command("help"));

            for(GameServer gs : serverList){
                if(gs.serverType == GameServer.ServerType.Minecraft){

                } else if(gs.serverType == GameServer.ServerType.Factorio){

                } else if(gs.serverType == GameServer.ServerType.Avorion){

                } else if(gs.serverType == GameServer.ServerType.Terraria){

                } else {
                    System.out.println("No procedure implemented for RCON for server type " + gs.serverType);
                }



            }

        }
        catch (Exception e) { e.printStackTrace(); }
    }

}
