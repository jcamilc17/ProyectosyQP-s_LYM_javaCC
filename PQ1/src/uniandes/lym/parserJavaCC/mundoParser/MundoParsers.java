package uniandes.lym.parserJavaCC.mundoParser;

import java.util.*;

import uniandes.lym.parserJavaCC.myParserDR.ParserDR;
import uniandes.lym.parserJavaCC.parserCAML.ParserCAMLco;
import uniandes.lym.parserJavaCC.parserProlog.ParserProlog;
import uniandes.lym.parserJavaCC.parserGrammars.ParserG;
import uniandes.lym.parserJavaCC.newParserPost.NuevoParserPost;
import uniandes.lym.parserJavaCC.graphParser.GraphParser;
import uniandes.lym.parserJavaCC.myParser.ParserCAML;
import uniandes.lym.parserJavaCC.sExpParser.S_ExpParser;
import uniandes.lym.parserJavaCC.exprParser.ExprParser;
import uniandes.lym.parserJavaCC.stackParser.stackParser;
import uniandes.lym.parserJavaCC.petriParser.PetriParser;



public class MundoParsers {
	
	// Nombres de los Parsers
	private  ArrayList  <String> parsers  = new ArrayList <String> (); 
	
	// Parser que se está usando
	private int currentParser;
	
	
	public  MundoParsers () {
  	
		// Agreguen al final de esta lista los nombres del nuevo parser
		
	    parsers.add("ParserDR");
	    parsers.add("CAML -  interpreter");
	    parsers.add("CAML");
	    parsers.add("Expressions");
	    parsers.add("Parser Prolog");
	    parsers.add("Parser G");
	    parsers.add("Nuevo Parser POST");
	    // Added new
	    
	    parsers.add("Graph");
	    parsers.add("sExpr");
	    parsers.add("stack");
	    parsers.add("Petri");
		  
	    
	    currentParser =  0;

	    
	    
	}

	
	
	
	
	public GraphParser getGraphParser(){
		return new GraphParser(System.in);
	}

	public PetriParser getPetriParser(){
		return new PetriParser(System.in);
	}

	public ParserProlog getParserProlog(){
		return new ParserProlog(System.in);
	}
	
	public ParserCAML getParserCAML(){
		return new ParserCAML(System.in);
	}
	
	public ParserCAMLco getParserCAMLco(){
		return new ParserCAMLco(System.in);
	}
	
	public ParserDR getParserDR(){
		return new ParserDR();
	}
	
	public ExprParser getExprParser(){ 
		return new ExprParser(System.in);
	}
	public stackParser getStackParser(){ 
		return new stackParser(System.in);
	}
	public NuevoParserPost getNuevoParserPost(){
		return new NuevoParserPost(System.in);
	}
	public ParserG getGParser(){
		return new ParserG(System.in);
	}
	public S_ExpParser getSExpParser(){
		return new S_ExpParser(System.in);
	}
	
	
	
	public String getStringCurrentParser(){
		return parsers.get(currentParser);
	}
	
	public int getCurrentParser() {
		return currentParser;
	}
	
	public void setCurrentParser(int p) {
		currentParser = p;
	}
	
	public String getParser(int i) {
		return parsers.get(i);
	}
	
	public int sizeParsers() {
		return parsers.size();
	}
	
	public String  procesarCadena(String texto) {
		String resp = "";
		
		if(parsers.get(currentParser).equals("ParserDR")){
			ParserDR parserDR = getParserDR();
			parserDR.ReInit(new java.io.StringReader(texto));
			try {
		    	parserDR.Lexp(); 
		    	resp = new String("OK    \n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		}
		}
		else if(parsers.get(currentParser).equals("CAML")){
			ParserCAMLco parserCAML= getParserCAMLco();
			parserCAML.ReInit(new java.io.StringReader(texto));
			try {
		    	parserCAML.camlList(); 
		    	resp = new String("OK    \n"); 	 
		    	
			}catch (Exception e) {
		        	resp = new String ("Error de Sintaxis: "+e.getMessage());
		    } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		    }
		}
		else if(parsers.get(currentParser).equals("CAML -  interpreter")){
			ParserCAML parserCAML = getParserCAML();
			parserCAML.ReInit(new java.io.StringReader(texto));
			ArrayList <Integer>  myList = new ArrayList <Integer> ();
			try {
		    	 myList= parserCAML.camlList(); 
		    	resp = new String("OK    \n" + myList+ "\n");
			}catch (Exception e) {
		        	resp = new String ("Error de Sintaxis: "+e.getMessage());
		    } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		    }
		}
		else if(parsers.get(currentParser).equals("Expressions")){
			ExprParser nuevoParser = getExprParser();
			
			nuevoParser.ReInit(new java.io.StringReader(texto));
			try {
		    	int v = nuevoParser.one_line(); 
		    	resp = new String("OK   "+v+ "\n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		
		
		else if(parsers.get(currentParser).equals("stack")){
			stackParser nuevoParser = getStackParser();
			
			nuevoParser.ReInit(new java.io.StringReader(texto));
			try {
		    	int v = nuevoParser.one_line(); 
		    	resp = new String("OK   "+v+ "\n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		else if(parsers.get(currentParser).equals("Parser Prolog")){
			ParserProlog nuevoParser = getParserProlog();
			nuevoParser.ReInit(new java.io.StringReader(texto));
			try {
		    	nuevoParser.hr(); 
		    	resp = new String("OK    \n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		else if(parsers.get(currentParser).equals("Parser G")){
			ParserG nuevoParser = getGParser();
			nuevoParser.ReInit(new java.io.StringReader(texto));
			try {
		    	nuevoParser.gram(); 
		    	resp = new String("OK    \n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		else if(parsers.get(currentParser).equals("Nuevo Parser POST")){
			NuevoParserPost nuevoParser = getNuevoParserPost();
			nuevoParser.ReInit(new java.io.StringReader(texto));
			try {
		    	int v = nuevoParser.one_line(); 
		    	resp = new String("OK   "+v+ "\n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		else if(parsers.get(currentParser).equals("sExpr")){
			S_ExpParser sp= getSExpParser();
			sp.ReInit(new java.io.StringReader(texto));
			try {
		    	sp.sExp(); 
		    	resp = new String("OK   \n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		else if(parsers.get(currentParser).equals("Graph")){
			GraphParser gp= getGraphParser();
			gp.ReInit(new java.io.StringReader(texto));
			try {
		    	gp.graph(); 
		    	resp = new String("OK   \n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
		else if(parsers.get(currentParser).equals("Petri")){
			PetriParser pp= getPetriParser();
			pp.ReInit(new java.io.StringReader(texto));
			try {
		    	pp.net(); 
		    	resp = new String("OK   \n");
		    }catch (Exception e) {
		        resp = new String ("Error de Sintaxis: "+e.getMessage());
		     } catch (Error e) {
		    	 resp = new String ("Error Lexico: "+e.getMessage());
		     }
		}
	
	
	
	
		
		
		return "\n SISTEMA " + parsers.get(currentParser) + ": " + resp + "\n";
	}

}
