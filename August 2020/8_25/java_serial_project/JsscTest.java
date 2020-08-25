// package oop;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import jssc.SerialPort;
import jssc.SerialPortException;

public class JsscTest{
	
	public static String stringToHex(String s) {
		String result = "";
		
		for (int i=0; i<s.length(); i++) {
			result += String.format("%02X ", (int) s.charAt(i));
		}
		
		return result;
	}


	// 0x add
	public static String stringToHex0x(String s) {
		String result = "";
	
		for (int i=0; i<s.length(); i++) {
			result += String.format("0x%02X ", (int) s.charAt(i));
		}
	
		return result;
	}

	
	public static String getCheckSum(String str_trans) {
		String args = "";

		args = stringToHex(str_trans);
		System.out.println("args : " + args);
		int args1 = Integer.parseInt(args.substring(0,2), 16);
		int args2 = Integer.parseInt(args.substring(3,5), 16);
		int args3 = Integer.parseInt(args.substring(6,8), 16);
		
//			System.out.println("args1 : " + args1);
//			System.out.println("args2 : " + args2);
//			System.out.println("args3 : " + args3);
		args = Integer.toString(args1 + args2 + args3);
//			System.out.println(args);
		
		int check = Integer.parseInt(args);
		args = Integer.toHexString(check); 
		
		
		return args;
	}
	
	public static char checkSum(String nmeaStr) { 
		char check = 0; // iterate over the string, XOR each byte with the total sum: 
		for (int i=0; i<nmeaStr.length(); i++) { 
			check = (char) (check ^ nmeaStr.charAt(i)); 
		}
		
		return check; 
	}

		
	public static void main(String[] args) throws UnsupportedEncodingException{
		SerialPort serialPort = new SerialPort("/dev/ttyUSB1");
		
	    try {
	        serialPort.openPort();//Open serial port
	        serialPort.setParams(SerialPort.BAUDRATE_9600, 
	                             SerialPort.DATABITS_8,
	                             SerialPort.STOPBITS_1,
	                             SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
			
	        String check_sum = getCheckSum("Hi?");
	        System.out.println("check_sum : " + check_sum);
	        System.out.println("Tx: " + "2448693F" + check_sum);
	        //System.out.println(("$Hi?" + check_sum).getBytes());
	        byte[] tx = (/*"$Hi?"*/ "2448693FF0" /*+ check_sum*/).getBytes("UTF-8");
	        serialPort.writeBytes(tx);//Write data to port
	        
//		    try {
		    	Thread.sleep(1000);
//		    } catch (Exception e) {}
		    
	        byte[] buffer = serialPort.readBytes(10); //Read 10 bytes from serial port
	        System.out.println("Rx: " + buffer);
	        
	        serialPort.closePort(); //Close serial port
	    } catch (SerialPortException e) {
	        e.printStackTrace();
	    } 
	    

	}
}
