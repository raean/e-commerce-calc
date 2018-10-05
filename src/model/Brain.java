package model;

public class Brain
{
	private static Brain instance = null;
	
	private Brain()
	{
		
	}
	
	public synchronized static Brain getInstance()
	{
		if (instance == null) instance = new Brain();
		return instance;
	}
	
	public String doAdd(String a, String b) throws Exception
	{
		int n1 = Integer.parseInt(a);
		int n2 = Integer.parseInt(b);
		return n1 + n2 + "";
		
	}
	

}
