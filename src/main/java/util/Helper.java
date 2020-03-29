package util;

import java.util.HashMap;

public class Helper {

    private static HashMap<Integer, String> pairs = new HashMap<Integer, String>()
    {{
        put(7777, "Terraria");
        put(25565, "Minecraft");
        put(25566, "Minecraft Modded");
        put(27000, "Avorion");
        put(34197, "Factorio");
        put(34198, "FactorioRCON");
        put(25575, "MinecraftRCON");
        put(27015, "AvorionRCON");
    }};

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String formatInfo(HashMap<Integer, Boolean> info){
        String result = "";
        try{
            for(Integer key : pairs.keySet()){
                for(Integer ikey : pairs.keySet()){
                    System.out.println(info.toString() + "::" + pairs.toString());
                    if(key.equals(ikey)){
                        String name = pairs.get(key);
                        Boolean status = info.get(key);
                        String s = "false";
                        if(status != null) {
                            if (status) s = "true";
                        }
                        result += name + ":" + key + ":" + s + ";";
                        break;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
