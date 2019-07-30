package com.smarthome.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandPromptCommands {

	public String sendOnCommand(String ip) throws Exception {
        System.out.println(StaticData.CLEVER_HOME_BULB_PATH);
		ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd " + StaticData.CLEVER_HOME_BULB_PATH + "\" && node cli.js turnon \"" + ip +"\"");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line="";
        String result="";
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            result+=line;
        }
        return result;
    }
	
	public String sendOffCommand(String ip) throws Exception {
        System.out.println(StaticData.CLEVER_HOME_BULB_PATH);
		ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd " + StaticData.CLEVER_HOME_BULB_PATH + "\" && node cli.js turnoff \"" + ip +"\"");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line="";
        String result="";
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            result+=line;
        }
        return result;
    }
	public String sendSetColorCommand(String ip,String value) throws Exception {
		double brightnessColor = Integer.valueOf(value)*2.55;
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd " + StaticData.CLEVER_HOME_BULB_PATH + "\" && node cli.js setcolor \"" + ip +"\" " + String.valueOf(brightnessColor) + " 0" + " 0");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line="";
        String result="";
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            result+=line;
        }
        return result;
    }
}
