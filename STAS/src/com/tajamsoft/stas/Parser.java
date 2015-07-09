package com.tajamsoft.stas;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

enum ReadStatus
{
	Getting,
	Parsing,
}

enum Argument
{
	Mayus,
	Minus,
	FirstMayus,
	FirstMinus,
}

public class Parser {
	
	public Map<String, String> vars = new HashMap<String, String>();
	public ReadStatus status;
	
	public int cp = 0;
	public char actualChar;
	public String newVar;
	public String newVarValue;
	public boolean definingValue = false;
	public boolean definingName = false;
	
	public String outLine;
	public String getVarName;
	public boolean parsingVar;
	public boolean parsingArgument;
	public Argument arg;
	
	public Parser()
	{
		status = ReadStatus.Getting;
	}
	
	public void parse(String line)
	{
		definingValue = false;
		definingName = false;
		newVar = "";
		newVarValue = "";
		
		getVarName = "";
		parsingVar = false;
		parsingArgument = false;
		outLine = "";
		cp = 0;
		
		arg = null;
		
		
		while(cp < line.length())
		{
			actualChar = line.charAt(cp);
			System.out.println(actualChar);
			if(status == ReadStatus.Getting)
			{
				if(actualChar == '#')
				{
					print("Comment!");
					break;
				}
				else if(actualChar == ';')
				{
					print("Line jump!");
					break;
				}
				else if(actualChar == '-' && !definingValue && !definingName)
				{
					status = ReadStatus.Parsing;
					print("Getting to parse!");
					break;
				}
				else 
				{
					if(!definingValue)
					{
						if(actualChar == ' ')
						{
							definingName = false;
							print("Starting to define value");
							definingValue = true;
						}
						else
						{
							definingName = true;
							print("Defining Name!");
							newVar += actualChar;
						}
					}
					else
					{
						print("Defining value!");
						newVarValue += actualChar;
					}
				}
			}
			else
			{
				if(actualChar == '{')
				{
					parsingVar = true;
					print("Starting to parse var!");
				}
				else if(actualChar == '}')
				{
					print("Finishing var parse!");
					parsingVar = false;
					parsingArgument = false;
					//Output the parsed variable:
					if(vars.get(getVarName) != null)
					{
						print("Found var!");
						if(arg != null)
						{
							if(arg == Argument.Mayus)
							{
								outLine += vars.get(getVarName).toUpperCase();
							}
							else if(arg == Argument.Minus)
							{
								outLine += vars.get(getVarName).toLowerCase();
							}
							else if(arg == Argument.FirstMayus)
							{
								String tmpOut = vars.get(getVarName);
								
								outLine += tmpOut.substring(0, 1).toUpperCase() + tmpOut.substring(1);;
							}
							else if(arg == Argument.FirstMinus)
							{
								String tmpOut = vars.get(getVarName);
								outLine += tmpOut.substring(0, 1).toLowerCase() + tmpOut.substring(1);;
							}
							else
							{
								outLine += vars.get(getVarName);
							}
							
						}
						else
						{
							outLine += vars.get(getVarName);
						}
						getVarName = "";
					}
					else
					{
						print("Unable to find var!");
						outLine += "[ERROR PARSING VAR: " + getVarName + "]";
					}
				}
				else
				{
					if(parsingVar && !parsingArgument)
					{
						if(actualChar == ' ')
						{
							parsingArgument = true;
						}
						else
						{
							getVarName += actualChar;
						}
					}
					else if(parsingArgument)
					{
						if(actualChar == 'A')
						{
							arg = Argument.Mayus;
							print("Found MAYUS argument!");
						}
						else if(actualChar == 'a')
						{
							arg = Argument.Minus;
							print("Found MINUS argument!");
						}
						else if(actualChar == 'F')
						{
							arg = Argument.FirstMayus;
							print("Found FIRST_MAYUS argument!");
						}
						else if(actualChar == 'f')
						{
							arg = Argument.FirstMinus;
							print("Found FIRST_MINUS argument!");
						}
						
						parsingArgument = false;
					}
					else
					{
						outLine += actualChar;
					}
				}
			}
			print(outLine);
			cp++;
		}
		print("-------------------------------------------");
		if(newVar != "" && newVarValue != "")
		{
			if(!vars.containsKey(newVar))
			{
				vars.put(newVar, newVarValue);
			}
			else
			{
				print("Can't redefine variable, ignoring!");
				return;
			}
			print("Defined var name: " + newVar);
			print("Defined var data: " + newVarValue);
		}
		
		
	}

	
	//Makes my life easier:
	private static void print(Object obj) {
		System.out.println(obj);
	}
	
}
