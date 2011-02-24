package exceptions;

import java.lang.Exception;

@SuppressWarnings("serial")
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