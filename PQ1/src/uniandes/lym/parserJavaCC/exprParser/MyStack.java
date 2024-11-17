/*
 * Created on May 2, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package uniandes.lym.parserJavaCC.exprParser;


import javax.swing.DefaultListModel;


/**
 * @author mainUser
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MyStack extends  DefaultListModel <Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4636254164052752159L;
		
	   MyStack(){
			super();
		}
		
		public void push(Object o){
			super.add(0,(Integer) o);
		}
		
		public Integer peek() {
			return super.firstElement();
		}
		
		public Integer pop() {
			return	super.remove(0);
		}
		
		public void plus() {
			 Integer x, y;
			 
			
			x = pop();
			y = pop();
            push (new Integer(x+y));
			
		}

		public void minus() {
			Integer x, y;
			 
			
			x = (Integer) pop();
			y = (Integer) pop();
            push (new Integer(y-x));
			
		}

		public void times() {
			Integer x, y;
			 
			
			x = (Integer) pop();
			y = (Integer) pop();
            push (new Integer(y*x));
			
		}

		public void div() {
			Integer x, y;
			 
			
			x = (Integer) pop();
			y = (Integer) pop();
            push (new Integer(y/x));
			
		}
		
		public void chs() {
			Integer x;
			
			x = (Integer) pop();
		  push (new Integer(-x));
			
		}

		
}


