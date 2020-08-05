import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class CS6086Balluri {
	public static void main(String[] args)
	{ 
		CS6086Balluri ob=new  CS6086Balluri();
		System.out.println("Name:vineetha Alluri");
		System.out.println("course id:CS608");
		System.out.println(java.time.LocalDate.now());
		System.out.println("\n");
		try {
			for(int i=0;i<9;i+=2)
			{
			String line = Files.readAllLines(Paths.get("infixData6B.txt")).get(i);
			System.out.println("the given infix expression is:" +line);
			float a=ob.assign(i,'a');
			float b=ob.assign(i,'b');
			float c=ob.assign(i,'c');
			float d=ob.assign(i, 'd');
			System.out.println("a="+a+"\tb="+b+"\tc= "+c+"\td=" +d);
			float n=ob.evaluate(line,i);
			System.out.println("Value after evaluating the infix expression:" +n);
			System.out.println("\n");
			
			}
		    
		}
		catch(Exception e)
		{
			System.out.println("Throws FileNotFound Exception");
		}
	}

		

	public float evaluate(String line,int i)throws Exception
	{
		Stack<Character> operator=new Stack<Character>();
		Stack<Float> operand=new Stack<Float>();
		float result=0.0f;
		float final_value=0.0f;
		float opr1=0.0f,opr2=0.0f;
		for(int p=0;p<line.length();++p)
		{
			char c=line.charAt(p);
			
			
		if(Character.isLetterOrDigit(c) )
		{
			float value=assign(i,c);
			operand.push(value);
			
		}
		
     	else if(c=='(')
		{
			operator.push(c);
			
		}
	
		else if(c==')') 
		{
			
			while(operator.peek()!='(')
			{
			 	
	     	 opr1=operand.pop();
		     opr2=operand.pop();
		     char op=operator.pop();
		     result=calc(op,opr1,opr2);
		     operand.push(result);
		     }
		operator.pop();	 
      }
		else if(operator.isEmpty()==true)
		{
			operator.push(c);
			
	      }
		else if(operator.isEmpty()==false && priority(c)>priority(operator.peek()))
		{
			operator.push(c);
				
		}
	
		
		else 
		{     
			  
			  opr1=operand.pop();
		      opr2=operand.pop();
		      char op=operator.pop();
		      result=calc(op,opr1,opr2);
		      operand.push(result);
		      p--;
		}
		}
		
		if(operator.empty()==true)
		{
			 final_value=operand.pop();
		}
		while(operator.empty()==false)
		{   
			opr1=operand.pop();
		    opr2=operand.pop();
		    char op=operator.pop();
		    final_value=calc(op,opr1,opr2);
		    operand.push(final_value);
		      }
		return final_value;
	}
	
	
	public int priority(char c)
	{
		
		switch(c) 
		{
		case '^':
			return 3;
		case '*':
		case '/':	
			return 2;
		case '+':
		case '-':
			return 1;
		}
		return 0;
	}
	float calc(char op,float opr1,float opr2)
	{
		switch(op)
		{
		case '+':
			 return opr2+opr1;
		case '-':
		     return opr2-opr1;
		case '/':
			 return opr2/opr1;
		case '*':		 
		     return opr2*opr1;
		
		}return 0.00f;
	}
	public float assign(int i,char c)throws IOException
	{
		int k=0;
		int j=i+1;	
		String line1 = Files.readAllLines(Paths.get("infixData6B.txt")).get(j);
		Scanner sc1=new Scanner(line1);
		float[] ar=new float[5];
		while(sc1.hasNextFloat())
		{
			ar[k]=sc1.nextFloat();
			k++;
		}
		sc1.close();

	    switch(c)
	    {
	    
	    case 'a':
	    	
	    	return ar[0];
	    case 'b':
	    	
	    	return ar[1];
	    	
	    case 'c':
	    	return ar[2];
	    case 'd':	
	    	return ar[3];
	    }
	    
	    return ar[4];
	}
	
}
