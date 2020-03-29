package factorio;

import util.Helper;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FactorioServerlistCheck {

    private String hostname;
    private InetAddress ip;

    public FactorioServerlistCheck(String servername){
        hostname = servername;
        try{
            ip = InetAddress.getByName(hostname);
            System.out.println(ip);

            int port = 443;
            SocketFactory basicSocketFactory = SocketFactory.getDefault();
            Socket s = basicSocketFactory.createSocket(hostname,port);
// s is a TCP socket
            SSLSocketFactory tlsSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            s = tlsSocketFactory.createSocket(s, hostname, port, true);
// s is now a TLS socket over TCP




            OutputStream out = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //dos.write(Helper.hexStringToByteArray("1800f2031142696c626f736a6f75726e616c2e636f6d63dd01"));
            //dos.write(Helper.hexStringToByteArray("0100"));


            Socket finalS = s;
            new Thread(){
                public void run() {
                    try{
                        Thread.sleep(10000);
                        finalS.shutdownInput();
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


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
