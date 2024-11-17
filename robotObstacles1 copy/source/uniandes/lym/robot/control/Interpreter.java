package uniandes.lym.robot.control;



import uniandes.lym.robot.kernel.*;

import uniandes.lym.robot.view.Console;

/**
 * Receives commands and relays them to the Robot. 
 */

public class Interpreter   {
	
	/**
	 * Robot's world
	 */
	private RobotWorldDec world;   
	
	private Console callingConsole;
	
	
	public Interpreter()
	  {
	  }


    /**
	 * Creates a new interpreter for a given world
	 * @param world 
	 */


	public Interpreter(RobotWorld world, Console c)
      {
		this.world =  (RobotWorldDec) world;
		this.callingConsole = c;
		c.printOutput("Enter a string of commands: \n");
	  }
	
	
	/**
	 * sets a the world
	 * @param m 
	 */

	public void setWorld(RobotWorld m) 
	{
		world = (RobotWorldDec) m;
		
	}
	/**
	 * sets a the console
	 * @param c 
	 */

	public void setConsole(Console c) 
	{
		callingConsole = c;

		c.printOutput("Enter a string of commands: \n");
		
	}

	
	
	/**
	 *  Processes a sequence of commands. commands muust be   followed by a ";"
	 *  A command can be:
	 *    M:  moves forward
	 *    R:  turns right
	 *    C: puts 1 chip
	 *    B: puts 1 balloon
	 *    c: picks 1 balloon
	 *    b: picks 1 chip
	 *    P: pops 1 balloon
	 *    G(x,y):  x and y are digits; goes to position x,y
	 *    J(d)  : d is a digit;  jumps  d squares forward
	 * @param input: string of commands followed by semicolons to be interpreted. (i.e.   R;R;G(3,3);B;C;M; )
	 * There may be spaces or newlines after the ;'s
	 */
	
	public boolean process(String input) throws Error
     {   
		
		callingConsole.printOutput("Starting processing: \n ");
		int pos,n,x, y;
		boolean ok = true;
		boolean skipchar = false;
		n = input.length();
	    
	    pos  = 0;
	    try	    {
	    while (pos < n &&  ok) {
	    	x=-1;
	    	y=-1;
	    
	    	switch (input.charAt(pos)) {
	    		case 'M': world.moveForward(1,false); callingConsole.printOutput(new String("move \n"));break;
	    		case 'R': world.turnRight(); callingConsole.printOutput("turnRight \n");break;
	    		case 'C': world.putChips(1); callingConsole.printOutput("putChip \n");break;
	    		case 'B': world.putBalloons(1); callingConsole.printOutput("putBalloon \n");break;
	    		case 'c': world.pickChips(1); callingConsole.printOutput("getChip \n");break;
	    		case 'b': world.grabBalloons(1); callingConsole.printOutput("getBalloon \n");break;
	    		case 'P': world.popBalloons(1); callingConsole.printOutput("popBalloon \n");break;
	    		case 'G': 
	    			x =-1;
	    			y= -1;
	    			acceptChar(input,++pos, '(');
	    			x=acceptDigit(input,++pos);
	    			acceptChar(input,++pos, ',');
	    			y=acceptDigit(input,++pos);
	    			acceptChar(input,++pos, ')');
	    			world.setPostion(x, y);
  				  	callingConsole.printOutput("Go("+x+ ","+ y +  ")\n");
	    			break;
	    		case 'J': 
	    			x =-1;
	    			acceptChar(input,++pos, '(');
	    			x= acceptDigit(input,++pos);
	    		    acceptChar(input,++pos, ')');
					world.moveForward(x,true);
					callingConsole.printOutput("J("+x+  ")\n");
					break;
	    		case '\n':
	    		case ' ': skipchar=true;break;
	    		default:
	    				throw new Error("Unrecognized input:  "+ print(input.charAt(pos))+"\n");
	    				 
	    	 }
	
	    	  if (ok) {
	    		if (skipchar) { 
	    			pos++;
	    			skipchar = false;
	    		}
	    		else { 
	    			acceptChar(input,++pos, ';');
	    			if (ok) 
	    			{
	    				pos++;
	    				try {
	    			        Thread.sleep(1000);
	    			    } catch (InterruptedException e) {
	    			        System.err.format("IOException: %s%n", e);
	    			    }
	    			}
	    		 
	    		}
	    		
	    	 }
	    }
	    
	    }
	 catch (Error e ){
		 callingConsole.printOutput("Error!!!  "+e.getMessage()+ "\n");
		 return false;
	 
 }
	    
	    
	   return ok;
	    }

 
 int acceptDigit(String input, int pos) 
 {
	 if (pos>= input.length()) {
		 throw new Error("expected a digit; found end of input \n ");
	 } 
	 else if  (! Character.isDigit(input.charAt(pos))) {
		throw new Error("expected a digit found: "+ print(input.charAt(pos))+ "\n" );
			}
	else { 
				return input.charAt(pos)-'0';
			}
	 
	 
 }

 boolean acceptChar(String input, int pos, char x) throws Error
 {
	 
	 
	 if (pos>= input.length()) {
		throw new Error("expected " +  print(x) + " found end of input \n");
	     
	 } 
	 if  (input.charAt(pos) != x ) {
		 throw new Error ("expected " +  print(x) + " found: "+ print(input.charAt(pos))+ "\n" );
	 }
	 
	 return true; 
 }
 
 
 
 String print(char c) {
  switch (c) {
  case ' ':  return "<SPACE>";
  case '\n': return "<NEWLINE>";
  case '\t': return "<TAB>";
  default : return ""+c;
	}
	 
 }
}
	    