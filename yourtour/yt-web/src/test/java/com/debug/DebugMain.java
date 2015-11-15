package com.debug;

import java.util.Scanner;


public class DebugMain {
	public static String HTTP="http://";
	public DebugMain() {
	}

	public static void main(String[] args) {
		Scanner scan = null;
		try{
			scan = new Scanner(System.in);
		    System.out.println("Please enter a tweet:");
		    String tweet = scan.nextLine().toLowerCase();
		    int a = tweet.length();
			if(a > 140){
				System.out.println("Excess Characters: "+(a-140));
				System.exit(0);
			}
			
			System.out.println("Length Correct");
			int num1 = 0, num2=0, num3=0;
			boolean b1=false, b2=false;
			char c = 0;
			int length = tweet.length();
			
			for(int index = 0; index < length; index++){
				c = tweet.charAt(index);
				
				if(c == '@'){ //Count the @ by the specified rule
					b1 = true;
					continue;
				}
				
				if(c == '#'){ //Count the # by the specified rule
					b2 = true;
					continue;
				}
				
				if(! (c == ' ' || c == '\t')){
					if(b1){
						num1 += 1;
						b1 = (c=='@');
					}
					
					if(b2){
						num2 += 1;
						b2 = (c=='#');
					}
					
					if(c == '/' && (index >= HTTP.length() - 1) && HTTP.equals(tweet.substring(index - HTTP.length() + 1, index + 1))){
						num3 += 1;
					}
				}else{
					b1 = false;
					b2 = false;
				}
			}
			
			System.out.println("num1=" + num1);
			System.out.println("num2=" + num2);
			System.out.println("num3=" + num3);
		}finally{
			if(scan != null){
				scan.close();
			}
		}
	}
}	
