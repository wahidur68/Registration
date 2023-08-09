package resAndLogin;

public class UserExist extends Exception
{

	String message;
	public UserExist(String message)
	{
		this.message=message;
	}
	public String toString()
	{
		return message;
	}
}
