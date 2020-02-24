package main;

import factorio.FactorioServerlistCheck;
import minecraft.MinecraftCheck;
import server.ServerHandler;

public class Main {

    public static MinecraftCheck mc;
    public static ServerHandler sh;

    public static void main(String[] args){
        callChecks();
        try{ sh = new ServerHandler(); } catch (Exception e) { e.printStackTrace(); }
    }

    public static void callChecks(){
        mc = new MinecraftCheck("bilbosjournal.com");
    }
}
