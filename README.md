# STAS
Simple Text Automation System

**STAS** is a language to optimize the writing of templates.
You can write a template, set some variables, and let the compiler build the text, here is an example:

  
    #This is a comment
    #To define variables, you simply write them!
    #They can contain anything
    Name john;
    Age 32;
    #This '-' character tells the parser to start writing the output
    -
    Hello! My name is {Name F} and I'm {Age} years old!
    
    #'{}' are used to put variables. The 'F' next to 'Name' is a "tag"
    #Tags:
    #A = All Uppercase
    #a = All Lowecase
    #F = First Uppercase
    #f = First Lowecase
  
  
###Progress:
- [x] Make a parser 
- [ ] Read external files (Working on this)
- [ ] Make a GUI
- [ ] Implement more features


###Syntax:

**STAS** sytax is simple and intuitive, here are the guidelines:

    #This is a comment
    #Multiline comments aren't possible (For now)
    
    Name john;    #That's a variable, make sure there is a ';' at the end
    Age 32;       #Variables can contain anything, the data will be displayed raw
    -             #This tells the parser to start the actual file
    Hello!
    My name is {Name F},   #This is a tagged variable (See below)
    I'm {Age} years old!   #This is another variable
    
  **Variables on Text**
  
  The structure of a variable is this:
  
  ('[]' = Requirement)
  
  ('--' = Optional)
  
  
  {[VAR] -TAG-}
  -------------
  {Name F}
  
  Tags can be:   (Var is "john")
  
  **A**: Outputs the variable in Uppercase (JOHN)
  
  **a**: Outputs the variable in Lowercase (john)
  
  **F**: Outputs the first char of the variable in uppercase (John)
  
  **f**: Outputs the first char of the variable in lowecase (john)
  
  
  


*Note: This is a learning project, there are better solutions out there*


