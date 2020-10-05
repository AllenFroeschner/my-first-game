package arls;

public class Delay2 {

	public void wait(int milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
		}
		catch (Exception e)
		{
			//ignoring exception at the moment
		}
	}
	
	
}
