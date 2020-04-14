package util;

import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

import javax.sound.sampled.Port;
import java.net.InetAddress;

public class GameServer {

    public String domain;
    public PortType portType;
    public int port;
    public String serverName;
    public ServerType serverType;

    public boolean isRCONenabled = false;
    public int RCONport;
    public String RCONpassword;

    public boolean isOnline = false;
    public String RCONtext = "";

    public int maxPlayers = -1;
    public int currPlayers = -1;
    public String serverDesc = "";
    public String serverVersion = "";

    public enum PortType{
        TCP,
        UDP
    }

    public enum ServerType{
        Minecraft,
        Avorion,
        Terraria,
        Factorio
    }

    public GameServer(String ServerName, ServerType SType, String Domain, PortType PType, int Port){
        domain = Domain;
        portType = PType;
        serverType = SType;
        port = Port;
        serverName = ServerName;
    }

    public GameServer(String ServerName, ServerType SType, String Domain, PortType PType, int Port, int RPort, String RPassword){
        domain = Domain;
        portType = PType;
        serverType = SType;
        port = Port;
        serverName = ServerName;
        isRCONenabled = true;
        RCONport = RPort;
        RCONpassword = RPassword;
    }

    public void cleanMCReturn(String mcReturn) {
        serverDesc = mcReturn.split("\\{\"text\":\"")[1].split("\"}")[0];
        String[] players = mcReturn.split("\"players\":\\{")[1].split("}")[0].split(",");
        maxPlayers = Integer.parseInt(players[0].split("\"max\":")[1]);
        currPlayers = Integer.parseInt(players[1].split("\"online\":")[1]);
        serverVersion = mcReturn.split("\"version\":\\{\"name\":\"")[1].split("\",")[0];
    }
}

