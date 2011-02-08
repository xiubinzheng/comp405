package exceptions;

import java.lang.Exception;

public class MyTimeException extends Exception
{
	public MyTimeException(String what)
	{
		super(what);
	}
	
	public MyTimeException(String what, Exception e)
	{
		super(what, e);
	}
}