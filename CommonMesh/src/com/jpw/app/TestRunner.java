package com.jpw.app;

public class TestRunner
{

	public static void main( String[] args )
	{
		// TODO Auto-generated method stub
		System.out.println( "TestRunner is executing" );
		
		String color = "red";
		
		if (color.equals( "red" ))
		{
			System.out.println( "yes" );
		}
		else System.out.println ("no");
		
		
		System.out.println("help" );
		
		
		int number = 4;
		
		    int factorial = 1; //try 0, 7 )
		    
		    for(int i = number; i > 1; i-- )
		    {
		        factorial = i *  factorial;
		        System.out.println( "number = " + i + " fac = " + factorial);
		    }
		    
		    System.out.println( "factorial of " + number + " = " + factorial );
		}


	

}
