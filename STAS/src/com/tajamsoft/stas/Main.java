package com.tajamsoft.stas;

public class Main {

	public static void main(String[] args) throws Exception 
	{
		Parser parser = new Parser();
		parser.parse("#Demo");
		parser.parse("#This program will test STAS");
		parser.parse("#Variables:");
		parser.parse("Name jonh;   #Set name to jonh");
		parser.parse("Age 23;      #Set age to 23");
		parser.parse("-");
		parser.parse("My name is {Name F}, and I'm {Age} years old!");
	}

}
