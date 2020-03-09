package general;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;

public class IsAliveChecker {

    public HashMap<Integer, Boolean> results = new HashMap<>();
    public ArrayList<Thread> threads = new ArrayList<>();
    private int localStartPortUDP = 6500;

    public void checkServer(String domain, ArrayList<Integer> portsTCP, ArrayList<Integer> portsUDP){


        for(Integer p : portsTCP) {
            results.put(p, false);
            Thread t = new Thread(() -> {
                boolean isListening = IsAliveTCP.serverListening(domain, p);
                results.put(p, isListening);
                System.out.println(p + "::" + isListening);
            });
            threads.add(t);
            t.start();
        }

        for(Integer p : portsUDP) {
            results.put(p, false);
            Thread t = new Thread(() -> {
                boolean isListening = IsAliveUDP.serverListening(domain, p, localStartPortUDP);
                localStartPortUDP++;
                results.put(p, isListening);
                System.out.println(p + "::" + isListening);
            });
            threads.add(t);
            t.start();
        }
        long start = System.currentTimeMillis();

        //wait for threads
        boolean allFinished = false;
        while(!allFinished){
            allFinished = true;
            for(Thread t : threads){
                if(t.isAlive()) allFinished = false;
            }
            long elapsedTime = (new Date()).getTime() - start;
            System.out.println("Waiting since " + elapsedTime/1000f + " seconds");
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
