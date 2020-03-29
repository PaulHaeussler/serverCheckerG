package minecraft;

import util.Helper;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.ExportException;
import java.util.Date;

public class MinecraftCheck {

    private int serverPort = 25565;
    private String hostname;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String serverInfo = "";


    public MinecraftCheck(String targetAdress){
        hostname = targetAdress;
        requestServerInfo();
    }

    public void requestServerInfo(){
        try{
            socket = new Socket(hostname, serverPort);

            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            dos.write(Helper.hexStringToByteArray("1800f2031142696c626f736a6f75726e616c2e636f6d63dd01"));
            dos.write(Helper.hexStringToByteArray("0100"));


            new Thread(){
                public void run() {
                    try{
                        Thread.sleep(100);
                        socket.shutdownInput();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            String tmp = null;
            while(tmp == null){
                tmp = in.readLine();
                System.out.println(tmp);
            }


            tmp = tmp.replaceAll("[^\\p{Print}]", "");
            serverInfo = tmp.substring(tmp.indexOf("{")).trim();
            socket.shutdownOutput();
            socket.close();

        } catch (UnknownHostException e){
            System.out.println("Host " + hostname + " at port " + serverPort + " not found!");
        } catch (IOException e){
            System.out.println("Connection failed!");
        }

        System.out.println("Current serverdata: " + serverInfo);

    }


}
