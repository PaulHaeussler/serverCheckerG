package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Helper {


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String formatInfo(ArrayList<GameServer> serverList){
        String result = "";
        for(GameServer gs : serverList){
            result += gs.serverName + "," + gs.RCONtext + ":" + gs.port + ":" + gs.isOnline + ";";
        }
        return result;
    }

    public static void waitForThreads(ArrayList<Thread> threads){
        long start = System.currentTimeMillis();
        boolean allFinished = false;
        while(!allFinished){
            allFinished = true;
            for(Thread t : threads){
                if(t.isAlive()) allFinished = false;
            }
            long elapsedTime = (new Date()).getTime() - start;
            //System.out.println("Waiting since " + elapsedTime/1000f + " seconds");
            if(elapsedTime/1000f > 3) {
                System.out.println("Timed out");
                new Thread(() -> {
                    for(Thread t : threads){
                        if(t.isAlive()) try{t.stop();} catch (Exception e){};
                    }
                }).start();
                break;
            }
        }
    }
}
