/**
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author George Baryannis
 * Produces as many relations per trajectory as the value of degree, on average
 * This version also produces different encodings
 */
public class CSV2ASPConverter {
	
	private static final String csvFile = "C:/Sampled_Trajectory_region.csv";		//Trajectories data path
	private static final String cvsSplitBy = ",";
	private static final String pred_name = "element";		//name for trajectory predicates
	private static final String var_name = "";				//name for trajectory predicate variables
	private static final int size = 10;						//number of trajectories
	private static final int degree = 1;					//number of known relations per trajectory
	private static final boolean tc10 = true;				//=false for tc6
	private static final String encoding = "ctsa2";			//possible values: coi7, ctsa, ctsa2, gen
	private static String aspFile = "C:/";		//ASP export path			
	
	//private static int sum = 0;
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if(tc10){
			aspFile += "10-";
		}
		else{
			aspFile += "6-";
		}
		aspFile += "TC" + encoding + "-" + size + "-D" + degree + ".asp";
		
        BufferedReader br = null;
        BufferedWriter bw = null;
		FileWriter fw = null;
        String line = "";
        
        
        int[][] trajectories = new int[size][];
        
        int[] check = new int[size];
        for(int i=0;i<size;i++){
        	check[i] = degree;
        }
        
        int count = 0;

        try {

        	fw = new FileWriter(aspFile);
			bw = new BufferedWriter(fw);
            br = new BufferedReader(new FileReader(csvFile));
            
            if(tc10){
            	switch(encoding){
            	case "gen":
            		bw.write("{true(X,R,Y) : relation(R)} = 1 :- element(X); element(Y); X != Y.\n");
            		bw.write("true(X,eq,X) :- element(X).\n");
            		bw.write(":- true(X,R1,Y); true(Y,R2,Z); not true(X,Rout,Z) : table(R1,R2,Rout).\n");
            		bw.write(":- possible(X,_,Y); not true(X,R,Y) : possible(X,R,Y).\n");
            		break;            		
            	case "coi7":
            		bw.write("{ s(X,Z) ; f(X,Z) ; ex(X,Z); ex(Z,X) ; alt(X,Z) ; ret(X,Z) ; rev(X,Z) ;  i(X,Z) ; eq(X,Z) ; dis(X,Z) }=1 :- traj(X), traj(Z), X!=Z.\n");
            		bw.write("eq(X,X) :- traj(X).\n");
            		break;
            	case "ctsa":
            		bw.write("{s(X,Y); f(X,Y); ex(X,Y); exi(X,Y); alt(X,Y); ret(X,Y); rev(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X<Y.\n");
            		bw.write("eq(X,X) :- traj(X).\n");
            		break;
            	case "ctsa2":
            		bw.write("{s(X,Y); f(X,Y); ex(X,Y); exi(X,Y); alt(X,Y); ret(X,Y); rev(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X<Y, #count{R : fact(R,X,Y)} = 0.\n");
            		bw.write("eq(X,X) :- traj(X).\n");
            		break;
	            }
	            
	            if(encoding.startsWith("gen")){
	            	bw.write("relation(eq; rev; alt; ret; s; f; ex; exi; i; dis).\n");

	            	bw.write("table(eq, eq, (eq)).\n");
	            	bw.write("table(eq, rev, (rev)).\n");
	            	bw.write("table(eq, alt, (alt)).\n");
	            	bw.write("table(eq, ret, (ret)).\n");
	            	bw.write("table(eq, s, (s)).\n");
	            	bw.write("table(eq, f, (f)).\n");
	            	bw.write("table(eq, ex, (ex)).\n");
	            	bw.write("table(eq, exi, (exi)).\n");
	            	bw.write("table(eq, i, (i)).\n");
	            	bw.write("table(eq, dis, (dis)).\n");

	            	bw.write("table(rev, eq, (rev)).\n");
	            	bw.write("table(rev, rev, (eq)).\n");
	            	bw.write("table(rev, alt, (ret)).\n");
	            	bw.write("table(rev, ret, (alt)).\n");
	            	bw.write("table(rev, s, (exi)).\n");
	            	bw.write("table(rev, f, (ex)).\n");
	            	bw.write("table(rev, ex, (f)).\n");
	            	bw.write("table(rev, exi, (s)).\n");
	            	bw.write("table(rev, i, (i)).\n");
	            	bw.write("table(rev, dis, (dis)).\n");

	            	bw.write("table(alt, eq, (alt)).\n");
	            	bw.write("table(alt, rev, (ret)).\n");
	            	bw.write("table(alt, alt, (eq;alt)).\n");
	            	bw.write("table(alt, ret, (rev;ret)).\n");
	            	bw.write("table(alt, s, (s)).\n");
	            	bw.write("table(alt, f, (f)).\n");
	            	bw.write("table(alt, ex, (ex)).\n");
	            	bw.write("table(alt, exi, (exi)).\n");
	            	bw.write("table(alt, i, (i;dis)).\n");
	            	bw.write("table(alt, dis, (i;dis)).\n");

	            	bw.write("table(ret, eq, (ret)).\n");
	            	bw.write("table(ret, rev, (alt)).\n");
	            	bw.write("table(ret, alt, (rev;ret)).\n");
	            	bw.write("table(ret, ret, (eq;alt)).\n");
	            	bw.write("table(ret, s, (exi)).\n");
	            	bw.write("table(ret, f, (ex)).\n");
	            	bw.write("table(ret, ex, (f)).\n");
	            	bw.write("table(ret, exi, (s)).\n");
	            	bw.write("table(ret, i, (i;dis)).\n");
	            	bw.write("table(ret, dis, (i;dis)).\n");

	            	bw.write("table(s, eq, (s)).\n");
	            	bw.write("table(s, rev, (ex)).\n");
	            	bw.write("table(s, alt, (s)).\n");
	            	bw.write("table(s, ret, (ex)).\n");
	            	bw.write("table(s, s, (eq;alt;s)).\n");
	            	bw.write("table(s, f, (exi;i;dis)).\n");
	            	bw.write("table(s, ex, (rev;ret;ex)).\n");
	            	bw.write("table(s, exi, (f;i;dis)).\n");
	            	bw.write("table(s, i, (f;exi;i;dis)).\n");
	            	bw.write("table(s, dis, (f;exi;i;dis)).\n");

	            	bw.write("table(f, eq, (f)).\n");
	            	bw.write("table(f, rev, (exi)).\n");
	            	bw.write("table(f, alt, (f)).\n");
	            	bw.write("table(f, ret, (exi)).\n");
	            	bw.write("table(f, s, (ex;i;dis)).\n");
	            	bw.write("table(f, f, (eq;alt;f)).\n");
	            	bw.write("table(f, ex, (s;i;dis)).\n");
	            	bw.write("table(f, exi, (rev;ret;exi)).\n");
	            	bw.write("table(f, i, (s;ex;i;dis)).\n");
	            	bw.write("table(f, dis, (s;ex;i;dis)).\n");
	            	
	            	bw.write("table(ex, eq, (ex)).\n");
	            	bw.write("table(ex, rev, (s)).\n");
	            	bw.write("table(ex, alt, (ex)).\n");
	            	bw.write("table(ex, ret, (s)).\n");
	            	bw.write("table(ex, s, (f;i;dis)).\n");
	            	bw.write("table(ex, f, (rev;ret;ex)).\n");
	            	bw.write("table(ex, ex, (exi;i;dis)).\n");
	            	bw.write("table(ex, exi, (eq;alt;s)).\n");
	            	bw.write("table(ex, i, (f;exi;i;dis)).\n");
	            	bw.write("table(ex, dis, (f;exi;i;dis)).\n");

	            	bw.write("table(exi, eq, (exi)).\n");
	            	bw.write("table(exi, rev, (f)).\n");
	            	bw.write("table(exi, alt, (exi)).\n");
	            	bw.write("table(exi, ret, (f)).\n");
	            	bw.write("table(exi, s, (rev;ret;exi)).\n");
	            	bw.write("table(exi, f, (s;i;dis)).\n");
	            	bw.write("table(exi, ex, (eq;alt;f)).\n");
	            	bw.write("table(exi, exi, (ex;i;dis)).\n");
	            	bw.write("table(exi, i, (s;ex;i;dis)).\n");
	            	bw.write("table(exi, dis, (s;ex;i;dis)).\n");

	            	bw.write("table(i, eq, (i)).\n");
	            	bw.write("table(i, rev, (i)).\n");
	            	bw.write("table(i, alt, (i;dis)).\n");
	            	bw.write("table(i, ret, (i;dis)).\n");
	            	bw.write("table(i, s, (f;ex;i;dis)).\n");
	            	bw.write("table(i, f, (s;exi;i;dis)).\n");
	            	bw.write("table(i, ex, (s;exi;i;dis)).\n");
	            	bw.write("table(i, exi, (f;ex;i;dis)).\n");
	            	bw.write("table(i, i, (eq;rev;alt;ret;s;f;ex;exi;i;dis)).\n");
	            	bw.write("table(i, dis, (alt;ret;s;f;ex;exi;i;dis)).\n");

	            	bw.write("table(dis, eq, (dis)).\n");
	            	bw.write("table(dis, rev, (dis)).\n");
	            	bw.write("table(dis, alt, (i;dis)).\n");
	            	bw.write("table(dis, ret, (i;dis)).\n");
	            	bw.write("table(dis, s, (f;ex;i;dis)).\n");
	            	bw.write("table(dis, f, (s;exi;i;dis)).\n");
	            	bw.write("table(dis, ex, (s;exi;i;dis)).\n");
	            	bw.write("table(dis, exi, (f;ex;i;dis)).\n");
	            	bw.write("table(dis, i, (alt;ret;s;f;ex;exi;i;dis)).\n");
	            	bw.write("table(dis, dis, (eq;rev;alt;ret;s;f;ex;exi;i;dis)).\n");

	            }
	            else if(encoding.equals("coi7")){
	            	bw.write("eq(X,Z) :- eq(X,Y), eq(Y,Z).\n");
	            	bw.write("rev(X,Z) :- eq(X,Y), rev(Y,Z).\n");
	            	bw.write("alt(X,Z) :- eq(X,Y), alt(Y,Z).\n");
	            	bw.write("ret(X,Z) :- eq(X,Y), ret(Y,Z).\n");
	            	bw.write("s(X,Z) :- eq(X,Y), s(Y,Z).\n");
	            	bw.write("f(X,Z) :- eq(X,Y), f(Y,Z).\n");
	            	bw.write("ex(X,Z) :- eq(X,Y), ex(Y,Z).\n");
	            	bw.write("ex(Z,X) :- eq(X,Y), ex(Z,Y).\n");
	            	bw.write("i(X,Z) :- eq(X,Y), i(Y,Z).\n");
	            	bw.write("dis(X,Z) :- eq(X,Y), dis(Y,Z).\n");
	
	            	bw.write("rev(X,Z) :- rev(X,Y), eq(Y,Z).\n");
	            	bw.write("eq(X,Z) :- rev(X,Y), rev(Y,Z).\n");
	            	bw.write("ret(X,Z) :- rev(X,Y), alt(Y,Z).\n");
	            	bw.write("alt(X,Z) :- rev(X,Y), ret(Y,Z).\n");
	            	bw.write("ex(Z,X) :- rev(X,Y), s(Y,Z).\n");
	            	bw.write("ex(X,Z) :- rev(X,Y), f(Y,Z).\n");
	            	bw.write("f(X,Z) :- rev(X,Y), ex(Y,Z).\n");
	            	bw.write("s(X,Z) :- rev(X,Y), ex(Z,Y).\n");
	            	bw.write("i(X,Z) :- rev(X,Y), i(Y,Z).\n");
	            	bw.write("dis(X,Z) :- rev(X,Y), dis(Y,Z).\n");
	
	            	bw.write("alt(X,Z) :- alt(X,Y), eq(Y,Z).\n");
	            	bw.write("ret(X,Z) :- alt(X,Y), rev(Y,Z).\n");
	            	bw.write("eq(X,Z) | alt(X,Z)  :- alt(X,Y), alt(Y,Z).\n");
	            	bw.write("rev(X,Z) | ret(X,Z)  :- alt(X,Y), ret(Y,Z).\n");
	            	bw.write("s(X,Z) :- alt(X,Y), s(Y,Z).\n");
	            	bw.write("f(X,Z) :- alt(X,Y), f(Y,Z).\n");
	            	bw.write("ex(X,Z) :- alt(X,Y), ex(Y,Z).\n");
	            	bw.write("ex(Z,X) :- alt(X,Y), ex(Z,Y).\n");
	            	bw.write("i(X,Z) | dis(X,Z) :- alt(X,Y), i(Y,Z).\n");
	            	bw.write("i(X,Z) | dis(X,Z) :- alt(X,Y), dis(Y,Z).\n");
	
	            	bw.write("ret(X,Z) :- ret(X,Y), eq(Y,Z).\n");
	            	bw.write("alt(X,Z) :- ret(X,Y), rev(Y,Z).\n");
	            	bw.write("rev(X,Z) | ret(X,Z)  :- ret(X,Y), alt(Y,Z).\n");
	            	bw.write("eq(X,Z) | alt(X,Z)  :- ret(X,Y), ret(Y,Z).\n");
	            	bw.write("ex(Z,X) :- ret(X,Y), s(Y,Z).\n");
	            	bw.write("ex(X,Z) :- ret(X,Y), f(Y,Z).\n");
	            	bw.write("f(X,Z) :- ret(X,Y), ex(Y,Z).\n");
	            	bw.write("s(X,Z) :- ret(X,Y), ex(Z,Y).\n");
	            	bw.write("i(X,Z) | dis(X,Z) :- ret(X,Y), i(Y,Z).\n");
	            	bw.write("i(X,Z) | dis(X,Z) :- ret(X,Y), dis(Y,Z).\n");
	
	            	bw.write("s(X,Z) :- s(X,Y), eq(Y,Z).\n");
	            	bw.write("ex(X,Z) :- s(X,Y), rev(Y,Z).\n");
	            	bw.write("s(X,Z) :- s(X,Y), alt(Y,Z).\n");
	            	bw.write("ex(X,Z) :- s(X,Y), ret(Y,Z).\n");
	            	bw.write("eq(X,Z) | alt(X,Z) | s(X,Z) :- s(X,Y), s(Y,Z).\n");
	            	bw.write("exi(Z,X) | i(X,Z) | dis(X,Z) :- s(X,Y), f(Y,Z).\n");
	            	bw.write("rev(X,Z) | ret(X,Z) | ex(X,Z):- s(X,Y), ex(Y,Z).\n");
	            	bw.write("f(X,Z) | i(X,Z) | dis(X,Z) :- s(X,Y), ex(Z,Y).\n");
	            	bw.write("f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- s(X,Y), i(Y,Z).\n");
	            	bw.write("f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- s(X,Y), dis(Y,Z).\n");
	
	            	bw.write("f(X,Z) :- f(X,Y), eq(Y,Z).\n");
	            	bw.write("ex(Z,X) :- f(X,Y), rev(Y,Z).\n");
	            	bw.write("f(X,Z) :- f(X,Y), alt(Y,Z).\n");
	            	bw.write("ex(Z,Y) :- f(X,Y), ret(Y,Z).\n");
	            	bw.write("ex(X,Z) | i(X,Z) | dis(X,Z) :- f(X,Y), s(Y,Z).\n");
	            	bw.write("eq(X,Z) | alt(X,Z) | f(X,Z) :- f(X,Y), f(Y,Z).\n");
	            	bw.write("s(X,Z) | i(X,Z) | dis(X,Z):- f(X,Y), ex(Y,Z).\n");
	            	bw.write("rev(X,Z) | ret(X,Z) | ex(Z,X) :- f(X,Y), ex(Z,Y).\n");
	            	bw.write("s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- f(X,Y), i(Y,Z).\n");
	            	bw.write("s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- f(X,Y), dis(Y,Z).\n");
	
	            	bw.write("ex(X,Z) :- ex(X,Y), eq(Y,Z).\n");
	            	bw.write("s(X,Z) :- ex(X,Y), rev(Y,Z).\n");
	            	bw.write("ex(X,Z) :- ex(X,Y), alt(Y,Z).\n");
	            	bw.write("s(X,Z) :- ex(X,Y), ret(Y,Z).\n");
	            	bw.write("f(X,Z) | i(X,Z) | dis(X,Z) :- ex(X,Y), s(Y,Z).\n");
	            	bw.write("rev(X,Z) | ret(X,Z) | ex(X,Z) :- ex(X,Y), f(Y,Z).\n");
	            	bw.write("ex(Z,X) | i(X,Z) | dis(X,Z):- ex(X,Y), ex(Y,Z).\n");
	            	bw.write("eq(X,Z) | alt(X,Z) | s(X,Z) :- ex(X,Y), ex(Z,Y).\n");
	            	bw.write("f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- ex(X,Y), i(Y,Z).\n");
	            	bw.write("f(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z) :- ex(X,Y), dis(Y,Z).\n");
	
	            	bw.write("ex(Z,X) :- ex(Y,X), eq(Y,Z).\n");
	            	bw.write("f(X,Z) :- ex(Y,X), rev(Y,Z).\n");
	            	bw.write("ex(Z,X) :- ex(Y,X), alt(Y,Z).\n");
	            	bw.write("f(X,Z) :- ex(Y,X), ret(Y,Z).\n");
	            	bw.write("rev(X,Z) | ret(X,Z) | ex(Z,X) :- ex(Y,X), s(Y,Z).\n");
	            	bw.write("s(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), f(Y,Z).\n");
	            	bw.write("eq(X,Z) | alt(X,Z) | f(X,Z):- ex(Y,X), ex(Y,Z).\n");
	            	bw.write("ex(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), ex(Z,Y).\n");
	            	bw.write("s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), i(Y,Z).\n");
	            	bw.write("s(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- ex(Y,X), dis(Y,Z).\n");
	
	            	bw.write("i(X,Z) :- i(X,Y), eq(Y,Z).\n");
	            	bw.write("i(X,Z) :- i(X,Y), rev(Y,Z).\n");
	            	bw.write("i(X,Z)| dis(X,Z) :- i(X,Y), alt(Y,Z).\n");
	            	bw.write("i(X,Z)| dis(X,Z) :- i(X,Y), ret(Y,Z).\n");
	            	bw.write("f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- i(X,Y), s(Y,Z).\n");
	            	bw.write("s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- i(X,Y), f(Y,Z).\n");
	            	bw.write("s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- i(X,Y), ex(Y,Z).\n");
	            	bw.write("f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z):- i(X,Y), ex(Z,Y).\n");
	            	bw.write(":- i(X,Y), dis(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z), not f(X,Z), not ex(X,Z), not ex(Z,X), not i(X,Z), not dis(X,Z).\n");
	
	            	bw.write("dis(X,Z) :- dis(X,Y), eq(Y,Z).\n");
	            	bw.write("dis(X,Z) :- dis(X,Y), rev(Y,Z).\n");
	            	bw.write("i(X,Z)| dis(X,Z) :- dis(X,Y), alt(Y,Z).\n");
	            	bw.write("i(X,Z)| dis(X,Z) :- dis(X,Y), ret(Y,Z).\n");
	            	bw.write("f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z) :- dis(X,Y), s(Y,Z).\n");
	            	bw.write("s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- dis(X,Y), f(Y,Z).\n");
	            	bw.write("s(X,Z) | ex(Z,X) | i(X,Z) | dis(X,Z):- dis(X,Y), ex(Y,Z).\n");
	            	bw.write("f(X,Z) | ex(X,Z) | i(X,Z) | dis(X,Z):- dis(X,Y), ex(Z,Y).\n");
	            	bw.write(":- dis(X,Y), i(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z), not f(X,Z), not ex(X,Z), not ex(Z,X), not i(X,Z), not dis(X,Z).\n");
	            	
	            	bw.write(":- s(X,Z), f(X,Z).\n");
	            	bw.write(":- s(X,Z), alt(X,Z).\n");
	            	bw.write(":- s(X,Z), i(X,Z).\n");
	            	bw.write(":- s(X,Z), eq(X,Z).\n");
	            	bw.write(":- s(X,Z), dis(X,Z).\n");
	            	bw.write(":- s(X,Z), ex(X,Z).\n");
	            	bw.write(":- s(X,Z), ex(Z,X).\n");
	            	bw.write(":- s(X,Z), rev(X,Z).\n");
	            	bw.write(":- s(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- f(X,Z), alt(X,Z).\n");
	            	bw.write(":- f(X,Z), i(X,Z).\n");
	            	bw.write(":- f(X,Z), eq(X,Z).\n");
	            	bw.write(":- f(X,Z), dis(X,Z).\n");
	            	bw.write(":- f(X,Z), ex(X,Z).\n");
	            	bw.write(":- f(X,Z), ex(Z,X).\n");
	            	bw.write(":- f(X,Z), rev(X,Z).\n");
	            	bw.write(":- f(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- alt(X,Z), i(X,Z).\n");
	            	bw.write(":- alt(X,Z), eq(X,Z).\n");
	            	bw.write(":- alt(X,Z), dis(X,Z).\n");
	            	bw.write(":- alt(X,Z), ex(X,Z).\n");
	            	bw.write(":- alt(X,Z), ex(Z,X).\n");
	            	bw.write(":- alt(X,Z), rev(X,Z).\n");
	            	bw.write(":- alt(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- i(X,Z), eq(X,Z).\n");
	            	bw.write(":- i(X,Z), dis(X,Z).\n");
	            	bw.write(":- i(X,Z), ex(X,Z).\n");
	            	bw.write(":- i(X,Z), ex(Z,X).\n");
	            	bw.write(":- i(X,Z), rev(X,Z).\n");
	            	bw.write(":- i(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- eq(X,Z), dis(X,Z).\n");
	            	bw.write(":- eq(X,Z), ex(X,Z).\n");
	            	bw.write(":- eq(X,Z), ex(Z,X).\n");
	            	bw.write(":- eq(X,Z), rev(X,Z).\n");
	            	bw.write(":- eq(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- dis(X,Z), ex(X,Z).\n");
	            	bw.write(":- dis(X,Z), ex(Z,X).\n");
	            	bw.write(":- dis(X,Z), rev(X,Z).\n");
	            	bw.write(":- dis(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- ex(X,Z), ex(Z,X).\n");
	            	bw.write(":- ex(X,Z), rev(X,Z).\n");
	            	bw.write(":- ex(X,Z), ret(X,Z).\n");
	
	            	bw.write(":- ex(Z,X), rev(X,Z).\n");
	            	bw.write(":- ex(Z,X), ret(X,Z).\n");
	
	            	bw.write(":- rev(X,Z), ret(X,Z).\n");
	            }
	            else{
	            	bw.write(":- eq(X,Y), eq(Y,Z), not eq(X,Z).\n");
	            	bw.write(":- eq(X,Y), rev(Y,Z),not rev(X,Z) .\n");
	            	bw.write(":- eq(X,Y), alt(Y,Z), not alt(X,Z) .\n");
	            	bw.write(":- eq(X,Y), ret(Y,Z), not ret(X,Z) .\n");
	            	bw.write(":- eq(X,Y), s(Y,Z), not s(X,Z).\n");
	            	bw.write(":- eq(X,Y), f(Y,Z), not f(X,Z).\n");
	            	bw.write(":- eq(X,Y), ex(Y,Z), not ex(X,Z) .\n");
	            	bw.write(":- eq(X,Y), exi(Y,Z), not exi(X,Z).\n");
	            	bw.write(":- eq(X,Y), i(Y,Z), not i(X,Z).\n");
	            	bw.write(":- eq(X,Y), dis(Y,Z), not dis(X,Z).\n");

	            	bw.write(":- rev(X,Y), eq(Y,Z), not rev(X,Z).\n");
	            	bw.write(":- rev(X,Y), rev(Y,Z), not eq(X,Z).\n");
	            	bw.write(":- rev(X,Y), alt(Y,Z), not ret(X,Z).\n");
	            	bw.write(":- rev(X,Y), ret(Y,Z), not alt(X,Z) .\n");
	            	bw.write(":- rev(X,Y), s(Y,Z), not exi(X,Z).\n");
	            	bw.write(":- rev(X,Y), f(Y,Z), not ex(X,Z).\n");
	            	bw.write(":- rev(X,Y), ex(Y,Z), not f(X,Z).\n");
	            	bw.write(":- rev(X,Y), exi(Y,Z), not s(X,Z).\n");
	            	bw.write(":- rev(X,Y), i(Y,Z), not i(X,Z).\n");
	            	bw.write(":- rev(X,Y), dis(Y,Z), not dis(X,Z).\n");

	            	bw.write(":- alt(X,Y), eq(Y,Z), not alt(X,Z) .\n");
	            	bw.write(":- alt(X,Y), rev(Y,Z), not ret(X,Z) .\n");
	            	bw.write(":- alt(X,Y), alt(Y,Z), not eq(X,Z) , not alt(X,Z)  .\n");
	            	bw.write(":- alt(X,Y), ret(Y,Z), not rev(X,Z) , not ret(X,Z)  .\n");
	            	bw.write(":- alt(X,Y), s(Y,Z), not s(X,Z) .\n");
	            	bw.write(":- alt(X,Y), f(Y,Z), not f(X,Z) .\n");
	            	bw.write(":- alt(X,Y), ex(Y,Z), not ex(X,Z) .\n");
	            	bw.write(":- alt(X,Y), exi(Y,Z), not exi(X,Z) .\n");
	            	bw.write(":- alt(X,Y), i(Y,Z), not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- alt(X,Y), dis(Y,Z), not i(X,Z) , not dis(X,Z) .\n");

	            	bw.write(":- ret(X,Y), eq(Y,Z), not ret(X,Z) .\n");
	            	bw.write(":- ret(X,Y), rev(Y,Z), not alt(X,Z) .\n");
	            	bw.write(":- ret(X,Y), alt(Y,Z), not rev(X,Z) , not ret(X,Z)  .\n");
	            	bw.write(":- ret(X,Y), ret(Y,Z), not eq(X,Z) , not alt(X,Z)  .\n");
	            	bw.write(":- ret(X,Y), s(Y,Z), not exi(X,Z) .\n");
	            	bw.write(":- ret(X,Y), f(Y,Z), not ex(X,Z) .\n");
	            	bw.write(":- ret(X,Y), ex(Y,Z), not f(X,Z) .\n");
	            	bw.write(":- ret(X,Y), exi(Y,Z), not s(X,Z) .\n");
	            	bw.write(":- ret(X,Y), i(Y,Z), not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- ret(X,Y), dis(Y,Z), not i(X,Z) , not dis(X,Z) .\n");

	            	bw.write(":- s(X,Y), eq(Y,Z), not s(X,Z) .\n");
	            	bw.write(":- s(X,Y), rev(Y,Z), not ex(X,Z) .\n");
	            	bw.write(":- s(X,Y), alt(Y,Z), not s(X,Z) .\n");
	            	bw.write(":- s(X,Y), ret(Y,Z), not ex(X,Z) .\n");
	            	bw.write(":- s(X,Y), s(Y,Z), not eq(X,Z) , not alt(X,Z) , not s(X,Z) .\n");
	            	bw.write(":- s(X,Y), f(Y,Z), not exi(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- s(X,Y), ex(Y,Z), not rev(X,Z) , not ret(X,Z) , not ex(X,Z).\n");
	            	bw.write(":- s(X,Y), exi(Y,Z), not f(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- s(X,Y), i(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- s(X,Y), dis(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	         
	            	bw.write(":- f(X,Y), eq(Y,Z), not f(X,Z) .\n");
	            	bw.write(":- f(X,Y), rev(Y,Z), not exi(X,Z) .\n");
	            	bw.write(":- f(X,Y), alt(Y,Z), not f(X,Z) .\n");
	            	bw.write(":- f(X,Y), ret(Y,Z), not exi(X,Z) .\n");
	            	bw.write(":- f(X,Y), s(Y,Z), not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- f(X,Y), f(Y,Z), not eq(X,Z) , not alt(X,Z) , not f(X,Z) .\n");
	            	bw.write(":- f(X,Y), ex(Y,Z), not s(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- f(X,Y), exi(Y,Z), not rev(X,Z) , not ret(X,Z) , not exi(X,Z) .\n");
	            	bw.write(":- f(X,Y), i(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- f(X,Y), dis(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");

	            	bw.write(":- ex(X,Y), eq(Y,Z), not ex(X,Z).\n");
	            	bw.write(":- ex(X,Y), rev(Y,Z), not s(X,Z) .\n");
	            	bw.write(":- ex(X,Y), alt(Y,Z), not ex(X,Z) .\n");
	            	bw.write(":- ex(X,Y), ret(Y,Z), not s(X,Z) .\n");
	            	bw.write(":- ex(X,Y), s(Y,Z), not f(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- ex(X,Y), f(Y,Z), not rev(X,Z) , not ret(X,Z) , not ex(X,Z) .\n");
	            	bw.write(":- ex(X,Y), ex(Y,Z), not exi(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- ex(X,Y), exi(Y,Z), not eq(X,Z) , not alt(X,Z) , not s(X,Z) .\n");
	            	bw.write(":- ex(X,Y), i(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- ex(X,Y), dis(Y,Z), not f(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z) .\n");

	            	bw.write(":- exi(X,Y), eq(Y,Z), not exi(X,Z) .\n");
	            	bw.write(":- exi(X,Y), rev(Y,Z), not f(X,Z) .\n");
	            	bw.write(":- exi(X,Y), alt(Y,Z), not exi(X,Z) .\n");
	            	bw.write(":- exi(X,Y), ret(Y,Z), not f(X,Z) .\n");
	            	bw.write(":- exi(X,Y), s(Y,Z), not rev(X,Z) , not ret(X,Z) , not exi(X,Z) .\n");
	            	bw.write(":- exi(X,Y), f(Y,Z), not s(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- exi(X,Y), ex(Y,Z), not eq(X,Z) , not alt(X,Z) , not f(X,Z).\n");
	            	bw.write(":- exi(X,Y), exi(Y,Z), not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- exi(X,Y), i(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- exi(X,Y), dis(Y,Z), not s(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");

	            	bw.write(":- i(X,Y), eq(Y,Z), not i(X,Z) .\n");
	            	bw.write(":- i(X,Y), rev(Y,Z), not i(X,Z) .\n");
	            	bw.write(":- i(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z) .\n");
	            	bw.write(":- i(X,Y), ret(Y,Z), not i(X,Z), not dis(X,Z) .\n");
	            	bw.write(":- i(X,Y), s(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- i(X,Y), f(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), ex(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), exi(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), dis(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z) , not f(X,Z), not ex(X,Z) , not exi(X,Z), not i(X,Z) , not dis(X,Z)  .\n");

	            	bw.write(":- dis(X,Y), eq(Y,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), rev(Y,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z) .\n");
	            	bw.write(":- dis(X,Y), ret(Y,Z), not i(X,Z), not dis(X,Z) .\n");
	            	bw.write(":- dis(X,Y), s(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z) .\n");
	            	bw.write(":- dis(X,Y), f(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), ex(Y,Z), not s(X,Z) , not exi(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), exi(Y,Z), not f(X,Z) , not ex(X,Z) , not i(X,Z) , not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), i(Y,Z), not alt(X,Z), not ret(X,Z), not s(X,Z) , not f(X,Z), not ex(X,Z) , not exi(X,Z), not i(X,Z) , not dis(X,Z)  .\n");
	            
	            }
	            if(encoding.equalsIgnoreCase("ctsa2")){
	            	bw.write("eq(X,Y) :- fact(eq,X,Y).\n");
	            	bw.write("alt(X,Y) :- fact(alt,X,Y).\n");
	            	bw.write("i(X,Y) :- fact(i,X,Y).\n");
	            	bw.write("s(X,Y) :- fact(s,X,Y).\n");
	            	bw.write("f(X,Y) :- fact(f,X,Y).\n");
	            	bw.write("dis(X,Y) :- fact(dis,X,Y).\n");
	            	bw.write("ex(X,Y) :- fact(ex,X,Y).\n");
	            	bw.write("exi(X,Y) :- fact(exi,X,Y).\n");
	            	bw.write("rev(X,Y) :- fact(rev,X,Y).\n");
	            	bw.write("ret(X,Y) :- fact(ret,X,Y).\n");
	            }
            }
            else{
            	switch(encoding){
            	case "gen":
            		bw.write("{true(X,R,Y) : relation(R)} = 1 :- element(X); element(Y); X != Y.\n");
            		bw.write("true(X,eq,X) :- element(X).\n");
            		bw.write(":- true(X,R1,Y); true(Y,R2,Z); not true(X,Rout,Z) : table(R1,R2,Rout).\n");
            		bw.write(":- possible(X,_,Y); not true(X,R,Y) : possible(X,R,Y).\n");
            		break;
            	case "coi7":
            		bw.write("{s(X,Y); f(X,Y); alt(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X!=Y.\n");
            		bw.write("eq(X,X) :- traj(X).\n");
            		break;
            	case "ctsa":
            		bw.write("{s(X,Y); f(X,Y); alt(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X<Y.\n");
            		bw.write("eq(X,X) :- traj(X).\n");
            		break;
            	case "ctsa2":
            		bw.write("{s(X,Y); f(X,Y); alt(X,Y); i(X,Y); eq(X,Y); dis(X,Y)}=1 :- traj(X), traj(Y), X<Y, #count{R : fact(R,X,Y)} = 0.\n");
            		bw.write("eq(X,X) :- traj(X).\n");
            		break;
	            }
            	if(encoding.startsWith("gen")){
	            	bw.write("relation(eq; alt; s; f; i; dis).\n");

            		bw.write("table(eq, eq, (eq)).\n");
            		bw.write("table(eq, alt, (alt)).\n");
            		bw.write("table(eq, s, (s)).\n");
            		bw.write("table(eq, f, (f)).\n");
            		bw.write("table(eq, i, (i)).\n");
            		bw.write("table(eq, dis, (dis)).\n");

            		bw.write("table(alt, eq, (alt)).\n");
            		bw.write("table(alt, alt, (eq;alt)).\n");
            		bw.write("table(alt, s, (s)).\n");
            		bw.write("table(alt, f, (f)).\n");
            		bw.write("table(alt, i, (i;dis)).\n");
            		bw.write("table(alt, dis, (i;dis)).\n");

            		bw.write("table(s, eq, (s)).\n");
            		bw.write("table(s, alt, (s)).\n");
            		bw.write("table(s, s, (eq;alt;s)).\n");
            		bw.write("table(s, f, (i;dis)).\n");
            		bw.write("table(s, i, (f;i;dis)).\n");
            		bw.write("table(s, dis, (f;i;dis)).\n");

            		bw.write("table(f, eq, (f)).\n");
            		bw.write("table(f, alt, (f)).\n");
            		bw.write("table(f, s, (i;dis)).\n");
            		bw.write("table(f, f, (eq;alt;f)).\n");
            		bw.write("table(f, i, (s;i;dis)).\n");
            		bw.write("table(f, dis, (s;i;dis)).\n");

            		bw.write("table(i, eq, (i)).\n");
            		bw.write("table(i, alt, (i;dis)).\n");
            		bw.write("table(i, s, (f;i;dis)).\n");
            		bw.write("table(i, f, (s;i;dis)).\n");
            		bw.write("table(i, i, (eq;alt;s;f;i;dis)).\n");
            		bw.write("table(i, dis, (alt;s;f;i;dis)).\n");

            		bw.write("table(dis, eq, (dis)).\n");
            		bw.write("table(dis, alt, (i;dis)).\n");
            		bw.write("table(dis, s, (f;i;dis)).\n");
            		bw.write("table(dis, f, (s;i;dis)).\n");
            		bw.write("table(dis, i, (alt;s;f;i;dis)).\n");
            		bw.write("table(dis, dis, (eq;alt;s;f;i;dis)).\n");
	            }
	            else if(encoding.equals("coi7")){
	            	bw.write("eq(Z,X) :- eq(Y,X), eq(Z,Y).\n");
	            	bw.write("alt(Z,X) :- eq(Y,X), alt(Z,Y).\n");
	            	bw.write("s(X,Z) :- eq(Y,X), s(Y,Z).\n");
	            	bw.write("f(X,Z) :- eq(Y,X), f(Y,Z).\n");
	            	bw.write("i(X,Z) :- eq(Y,X), i(Y,Z).\n");
	            	bw.write("dis(Z,X) :- eq(Y,X), dis(Z,Y).\n");
	            	bw.write("alt(Z,X) :- alt(Y,X), eq(Z,Y).\n");
	            	bw.write("eq(Z,X) | alt(Z,X) :- alt(Y,X), alt(Z,Y).\n");
	            	bw.write("s(X,Z) :- alt(Y,X), s(Y,Z).\n");
	            	bw.write("f(X,Z) :- alt(Y,X), f(Y,Z).\n");
	            	bw.write("i(X,Z) | dis(Z,X) :- alt(Y,X), i(Y,Z).\n");
	            	bw.write("i(X,Z) | dis(Z,X) :- alt(Y,X), dis(Z,Y).\n");
	            	bw.write("s(X,Z) :- s(X,Y), eq(Z,Y).\n");
	            	bw.write("s(X,Z) :- s(X,Y), alt(Z,Y).\n");
	            	bw.write("eq(Z,X) | alt(Z,X) | s(X,Z) :- s(X,Y), s(Y,Z).\n");
	            	bw.write("i(X,Z) | dis(Z,X) :- s(X,Y), f(Y,Z).\n");
	            	bw.write("f(X,Z) | i(X,Z) | dis(Z,X) :- s(X,Y), i(Y,Z).\n");
	            	bw.write("f(X,Z) | i(X,Z) | dis(Z,X) :- s(X,Y), dis(Z,Y).\n");
	            	bw.write("f(X,Z) :- f(X,Y), eq(Z,Y).\n");
	            	bw.write("f(X,Z) :- f(X,Y), alt(Z,Y).\n");
	            	bw.write("i(X,Z) | dis(Z,X) :- f(X,Y), s(Y,Z).\n");
	            	bw.write("eq(Z,X) | alt(Z,X) | f(X,Z) :- f(X,Y), f(Y,Z).\n");
	            	bw.write("s(X,Z) | i(X,Z) | dis(Z,X) :- f(X,Y), i(Y,Z).\n");
	            	bw.write("s(X,Z) | i(X,Z) | dis(Z,X) :- f(X,Y), dis(Z,Y).\n");
	            	bw.write("i(X,Z) :- i(X,Y), eq(Z,Y).\n");
	            	bw.write("i(X,Z) | dis(Z,X) :- i(X,Y), alt(Z,Y).\n");
	            	bw.write("f(X,Z) | i(X,Z) | dis(Z,X) :- i(X,Y), s(Y,Z).\n");
	            	bw.write("s(X,Z) | i(X,Z) | dis(Z,X) :- i(X,Y), f(Y,Z).\n");
	            	bw.write("alt(Z,X) | s(X,Z) | f(X,Z) | i(X,Z) | dis(Z,X) :- i(X,Y), dis(Z,Y).\n");
	            	bw.write("dis(Z,X) :- dis(Y,X), eq(Z,Y).\n");
	            	bw.write("i(X,Z) | dis(Z,X) :- dis(Y,X), alt(Z,Y).\n");
	            	bw.write("f(X,Z) | i(X,Z) | dis(Z,X) :- dis(Y,X), s(Y,Z).\n");
	            	bw.write("s(X,Z) | i(X,Z) | dis(Z,X) :- dis(Y,X), f(Y,Z).\n");
	            	bw.write("alt(Z,X) | s(X,Z) | f(X,Z) | i(X,Z) | dis(Z,X) :- dis(Y,X), i(Y,Z).\n");
	            	bw.write(":- s(X,Z), f(X,Z).\n");
	            	bw.write(":- s(X,Z), alt(Z,X).\n");
	            	bw.write(":- s(X,Z), i(X,Z).\n");
	            	bw.write(":- s(X,Z), eq(Z,X).\n");
	            	bw.write(":- s(X,Z), dis(Z,X).\n");
	            	bw.write(":- f(X,Z), s(X,Z).\n");
	            	bw.write(":- f(X,Z), alt(Z,X).\n");
	            	bw.write(":- f(X,Z), i(X,Z).\n");
	            	bw.write(":- f(X,Z), eq(Z,X).\n");
	            	bw.write(":- f(X,Z), dis(Z,X).\n");
	            	bw.write(":- alt(Z,X), s(X,Z).\n");
	            	bw.write(":- alt(Z,X), f(X,Z).\n");
	            	bw.write(":- alt(Z,X), i(X,Z).\n");
	            	bw.write(":- alt(Z,X), eq(Z,X).\n");
	            	bw.write(":- alt(Z,X), dis(Z,X).\n");
	            	bw.write(":- i(X,Z), s(X,Z).\n");
	            	bw.write(":- i(X,Z), f(X,Z).\n");
	            	bw.write(":- i(X,Z), alt(Z,X).\n");
	            	bw.write(":- i(X,Z), eq(Z,X).\n");
	            	bw.write(":- i(X,Z), dis(Z,X).\n");
	            	bw.write(":- eq(Z,X), s(X,Z).\n");
	            	bw.write(":- eq(Z,X), f(X,Z).\n");
	            	bw.write(":- eq(Z,X), alt(Z,X).\n");
	            	bw.write(":- eq(Z,X), i(X,Z).\n");
	            	bw.write(":- eq(Z,X), dis(Z,X).\n");
	            	bw.write(":- dis(Z,X), s(X,Z).\n");
	            	bw.write(":- dis(Z,X), f(X,Z).\n");
	            	bw.write(":- dis(Z,X), alt(Z,X).\n");
	            	bw.write(":- dis(Z,X), i(X,Z).\n");
	            	bw.write(":- dis(Z,X), eq(Z,X).\n");
	            }
	            else{
	            	bw.write(":- eq(X,Y), eq(Y,Z), not eq(X,Z).\n");
	            	bw.write(":- eq(X,Y), alt(Y,Z), not alt(X,Z).\n");
	            	bw.write(":- eq(X,Y), s(Y,Z), not s(X,Z).\n");
	            	bw.write(":- eq(X,Y), f(Y,Z), not f(X,Z).\n");
	            	bw.write(":- eq(X,Y), i(Y,Z), not i(X,Z).\n");
	            	bw.write(":- eq(X,Y), dis(Y,Z), not dis(X,Z).\n");
	            	bw.write(":- alt(X,Y), eq(Y,Z), not alt(X,Z).\n");
	            	bw.write(":- alt(X,Y), alt(Y,Z), not eq(X,Z), not alt(X,Z).\n");
	            	bw.write(":- alt(X,Y), s(Y,Z), not s(X,Z).\n");
	            	bw.write(":- alt(X,Y), f(Y,Z), not f(X,Z).\n");
	            	bw.write(":- alt(X,Y), i(Y,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- alt(X,Y), dis(Y,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- s(X,Y), eq(Y,Z), not s(X,Z).\n");
	            	bw.write(":- s(X,Y), alt(Y,Z), not s(X,Z).\n");
	            	bw.write(":- s(X,Y), s(Y,Z), not eq(X,Z), not alt(X,Z), not s(X,Z).\n");
	            	bw.write(":- s(X,Y), f(Y,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- s(X,Y), i(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- s(X,Y), dis(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- f(X,Y), eq(Y,Z), not f(X,Z).\n");
	            	bw.write(":- f(X,Y), alt(Y,Z), not f(X,Z).\n");
	            	bw.write(":- f(X,Y), s(Y,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- f(X,Y), f(Y,Z), not eq(X,Z), not alt(X,Z), not f(X,Z).\n");
	            	bw.write(":- f(X,Y), i(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- f(X,Y), dis(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), eq(Y,Z), not i(X,Z).\n");
	            	bw.write(":- i(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), s(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), f(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- i(X,Y), dis(Y,Z), not alt(X,Z), not s(X,Z), not f(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), eq(Y,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), alt(Y,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), s(Y,Z), not f(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), f(Y,Z), not s(X,Z), not i(X,Z), not dis(X,Z).\n");
	            	bw.write(":- dis(X,Y), i(Y,Z), not alt(X,Z), not s(X,Z), not f(X,Z), not i(X,Z), not dis(X,Z).\n");
	            }
	            if(encoding.equalsIgnoreCase("ctsa2")){
	            	bw.write("eq(X,Y) :- fact(eq,X,Y).\n");
	            	bw.write("alt(X,Y) :- fact(alt,X,Y).\n");
	            	bw.write("i(X,Y) :- fact(i,X,Y).\n");
	            	bw.write("s(X,Y) :- fact(s,X,Y).\n");
	            	bw.write("f(X,Y) :- fact(f,X,Y).\n");
	            	bw.write("dis(X,Y) :- fact(dis,X,Y).\n");
	            }
            }
            
            
            
            int line_no = 1;
            if(encoding.startsWith("gen")){
            		bw.write(pred_name + "(");// + var_name + line_no + ").\n");
            	}
            while ((line = br.readLine()) != null) {
            	//System.out.println(line);
            	if(line_no==1){
            		line = line.substring(3);		//remove weird BOM characters
            	}
            	
            	//System.out.println(line_no + " " + line + "\n");
                //System.out.println(line_no);
            	
            	// use comma as separator
            	ArrayList<String> temp = new ArrayList<String>(Arrays.asList(line.split(cvsSplitBy)));
            	
            	if(tc10){
            		if(temp.get(0).equals(temp.get(temp.size()-1))) {			//TC-10 does not allow trajectories starting and finishing at the same region
            			continue;
            		}
            	}
            	if(encoding.startsWith("gen")){
            		if(line_no==1) {
            			bw.write(var_name + line_no);
            		}
            		else {
            			bw.write("; " + var_name + line_no);
            		}
            	}
            	else {
            		bw.write(pred_name + "(" + var_name + line_no + ").\n");
            	}
            	
            	
            	ListIterator<String> tempIterator = temp.listIterator();
            	//int index = 0;
            	String prev = tempIterator.next();
        		while (tempIterator.hasNext()) {
        			String cur = tempIterator.next();
        			if(cur.equals(prev)){
        				tempIterator.remove();
        			}
        			prev = cur;
        		}
        		
        		/*sum += temp.size();
        		
        		System.out.println(line_no + " " + sum/line_no);*/
        		
        		trajectories[line_no-1] = temp.stream().mapToInt(i -> Integer.parseInt(i)).toArray();
                //trajectories[line_no-1] = Arrays.stream(line.split(cvsSplitBy)).mapToInt(i -> Integer.parseInt(i)).toArray();
                //Arrays.stream(line.split("cvsSplitBy")).distinct().collect(Collectors.joining("-"));
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
        		/*int length = trajectories[line_no-1].length;
                System.out.print(line_no + " ");
                for(int i=0;i<length;i++){
                	System.out.print(trajectories[line_no-1][i] + " ");
                }
                System.out.print("\n");*/
                if(line_no ==size){
                	break;
                }
                line_no++;
            }
            
            if(encoding.startsWith("gen")){
            		bw.write(").\n");
            }
            
            /*for(int i=0;i<size;i++){
            	int length = trajectories[i].length;
            	System.out.print(i+1);
            	for(int k=0;k<length;k++){
            		System.out.print(" " + trajectories[i][k]);
            	}
            	System.out.print("\n");
            }*/
            
            for(int i=0;i<size;i++){
            	if(check[i]==0){
            		continue;
            	}
            	for (int j=i+1;j<size;j++){
            		if(check[i]==0){
            			break;
            		}
            		if(check[j]==0){
            			continue;
            		}
            		int length_i = trajectories[i].length;
            		int length_j = trajectories[j].length;
            		if(trajectories[i][0]==trajectories[j][0]){		//Start regions are equal
            			if(trajectories[i][length_i-1]==trajectories[j][length_j-1]){	//Finish regions are equal
            				if(length_i!=length_j){										//Unequal length: Alternative
            					if(encoding.equals("ctsa2")){
            						bw.write("fact(alt," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
            					}
            					else if(encoding.startsWith("gen")){
            						bw.write("possible(" + var_name + (i+1) + ", alt, " + var_name + (j+1) + ").\n");
            					}            					
            					else{
            						bw.write("alt(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
            					}
            					count++;
            				}
            				else{														//Equal length
            					int isAlt=0;
            					for(int k=1;k<length_i;k++){
            						if(trajectories[i][k]!=trajectories[j][k]){			//Found different region: Alternative
            							if(encoding.equals("ctsa2")){
                    						bw.write("fact(alt," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                    					}
            							else if(encoding.startsWith("gen")){
            								bw.write("possible(" + var_name + (i+1) + ", alt, " + var_name + (j+1) + ").\n");
            							}             							
                    					else{
                    						bw.write("alt(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                    					}
            							count++;
            							isAlt=1;
            							break;
            						}
            					}
            					if(isAlt==0){											//No different region: Equal
            						if(encoding.equals("ctsa2")){
                						bw.write("fact(eq," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                					}
            						else if(encoding.startsWith("gen")){
            							bw.write("possible(" + var_name + (i+1) + ", eq, " + var_name + (j+1) + ").\n");
            						} 
                					else{
                						bw.write("eq(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                					}
            						count++;
            					}
            				}
            			}
            			else{														//Finish regions not equal: Start
            				if(encoding.equals("ctsa2")){
        						bw.write("fact(s," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        					}
            				else if(encoding.startsWith("gen")){
            					bw.write("possible(" + var_name + (i+1) + ", s, " + var_name + (j+1) + ").\n");
            				}             				
        					else{
        						bw.write("s(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        					}
            				count++;
            			}
            		}
            		else{											//Start regions not equal
            			if(trajectories[i][length_i-1]==trajectories[j][length_j-1]){	//Finish regions are equal: Finish
            				if(encoding.equals("ctsa2")){
        						bw.write("fact(f," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        					}
            				else if(encoding.startsWith("gen")){
            					bw.write("possible(" + var_name + (i+1) + ", f, " + var_name + (j+1) + ").\n");
            				}             				
        					else{
        						bw.write("f(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        					}
            				count++;
            			}
        				else{														//Finish regions not equal
        					int isRev = 0, isTC10 = 0;
        					if(tc10){									//Checking for additional TC-10 relations
        						if(trajectories[i][0]==trajectories[j][length_j-1]){	//Start_1 = End_2
        							if(trajectories[i][length_i-1]==trajectories[j][0]){		//End_1 = Start_2
        								isTC10 = 1;
        								if(length_i!=length_j){										//Unequal lengths: Return
        									if(encoding.equals("ctsa2")){
        	            						bw.write("fact(ret," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        	            					}
        									else if(encoding.startsWith("gen")){
        										bw.write("possible(" + var_name + (i+1) + ", ret, " + var_name + (j+1) + ").\n");
        									}         									
        	            					else{
        	            						bw.write("ret(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        	            					}
        									count++;
        								}
        								else{
        									isRev=1;
        	        						for(int k=1;k<length_i-1;k++){								
        	            						if(trajectories[i][k]!=trajectories[j][length_i-1-k]){			
        	            							isRev=0;
        	            							break;
        	            						}
        	            					}
        	        						if(isRev==1){												//Reverse
        	        							if(encoding.equals("ctsa2")){
        	                						bw.write("fact(rev," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        	                					}
        	        							else if(encoding.startsWith("gen")){
        	        								bw.write("possible(" + var_name + (i+1) + ", rev, " + var_name + (j+1) + ").\n");
        	        							}         	        							
        	                					else{
        	                						bw.write("rev(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        	                					}
        	        							count++;
        	        						}
        	        						else{														//Return
        	        							if(encoding.equals("ctsa2")){
        	                						bw.write("fact(ret," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        	                					}
        	        							else if(encoding.startsWith("gen")){
        	        								bw.write("possible(" + var_name + (i+1) + ", ret, " + var_name + (j+1) + ").\n");
        	        							}         	        							
        	                					else{
        	                						bw.write("ret(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
        	                					}
        	        							count++;
        	        						}
        								}
        							}
        							else{														//End_1 != Start_2: Extends
        								if(encoding.equals("ctsa2")){
                    						bw.write("fact(ex," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                    					}
        								else if(encoding.startsWith("gen")){
        									bw.write("possible(" + var_name + (i+1) + ", ex, " + var_name + (j+1) + ").\n");
        								}         								
                    					else{
                    						bw.write("ex(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                    					}
        								count++;
        								isTC10 = 1;
        							}
        						}
        						else{													//Start_1 != End_2
        							if(trajectories[i][length_i-1]==trajectories[j][0]){	//End_1 = Start_2: ExtendedBy
        								if(encoding.equals("ctsa2")){
                    						bw.write("fact(exi," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                    					}
        								else if(encoding.equals("coi7")){
                    						bw.write("ex(" + var_name + (j+1) + "," + var_name + (i+1) + ").\n");
                    					}
        								else if(encoding.startsWith("gen")){
        									bw.write("possible(" + var_name + (i+1) + ", exi, " + var_name + (j+1) + ").\n");
        								}         								
                    					else{
                    						bw.write("exi(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                    					}        								
        								count++;
        								isTC10 = 1;
        							}
        						}
        					}
        					if(isTC10==0){
        						int isI=0;
            					//int shorter = Math.min(length_i,length_j);
            					outerloop:
        						for(int k=1;k<length_i;k++){
            						for(int l=1;l<length_j;l++){
            							if(trajectories[i][k]==trajectories[j][l]){			//Found equal region: Intersect
            								if(encoding.equals("ctsa2")){
                        						bw.write("fact(i," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                        					}
            								else if(encoding.startsWith("gen")){
            									bw.write("possible(" + var_name + (i+1) + ", i, " + var_name + (j+1) + ").\n");
            								}             								
                        					else{
                        						bw.write("i(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                        					}
            								count++;
            								isI=1;
            								break outerloop;
            							}
            						}
            					}
            					if(isI==0){											//No equal region: Disjoint
            						if(encoding.equals("ctsa2")){
                						bw.write("fact(dis," + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                					}
            						else if(encoding.startsWith("gen")){
            							bw.write("possible(" + var_name + (i+1) + ", dis, " + var_name + (j+1) + ").\n");
            						}             						
                					else{
                						bw.write("dis(" + var_name + (i+1) + "," + var_name + (j+1) + ").\n");
                					}
            						count++;
            					}
        					}
        				}
            		}
            		check[i]--;
            		check[j]--;
            	}
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        System.out.println(count + " relations");

    }

}
