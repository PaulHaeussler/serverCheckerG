package util;

import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

import javax.sound.sampled.Port;

public class GameServer {

    public String domain;
    public PortType portType;
    public int port;
    public String serverName;
    public ServerType serverType;

    public boolean isOnline = false;

    public int maxPlayers = -1;
    public int currPlayers = -1;

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
}

