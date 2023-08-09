package resAndLogin;
import java.util.*;
import java.sql.*;

public class UserAccount {
	static Scanner sc=new Scanner(System.in);
	//static boolean b=true;

	public static void main(String[] args) {
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","wahidur");
			PreparedStatement ps1=con.prepareStatement("insert into useraccount values(?,?,?,?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement("select * from useraccount where uname=? AND upwd=?");
			PreparedStatement ps3=con.prepareStatement("select * from useraccount where uname=? ");
			PreparedStatement ps4=con.prepareStatement("update useraccount set phno=? where uname=?");
			PreparedStatement ps5=con.prepareStatement("update useraccount set city=? where uname=?");
			
			PreparedStatement ps0=con.prepareStatement("select uname from useraccount");
			//where uname=? AND upwd=?
			
			while(true)
			{
				System.out.println("\t\t*****Enter your choice****** ");
				System.out.println("\t\t1.Registration"+"\n\t\t2.Login"+"\n\t\t3.logout");
				System.out.println("\t\tEnter your choice");
				
				switch(Integer.parseInt(sc.nextLine()))
				{
				case 1:
					System.out.println("********Registration*********");
					
					boolean x=false;
					
					do
					{
					System.out.println("Enter user Name :");
					String uname=sc.nextLine();
						
					ps1.setString(1, uname);
					
					
					ResultSet rs0=ps0.executeQuery();
					//ps1.executeQuery();
					
					int count=0;
					    x=false;
						while(rs0.next())
						{
							try {							
							        if(rs0.getString(1).equals(uname))
							         {
							        	count++;
							        	x=true;
							        	throw new UserExist("UserName already exist,choose different..");
							         }
							        
							}
							catch(UserExist e)
							{
								System.err.println(e.message);
							}
						}
						
					if(count==0)
					{
						System.out.println("Enter password :");
						String upwd=sc.nextLine();
						
						System.out.println("Enter user Fisrt Name :");
						String uf=sc.nextLine();
						
						System.out.println("tEnter user Last Name :");
						String ul=sc.nextLine();
						
						System.out.println("Enter user mail id :");
						String umail=sc.nextLine();
						
						System.out.println("Enter user phno :");
						long uph=Long.parseLong(sc.nextLine());
						
						System.out.println("Enter user city :");
						String ucity=sc.nextLine();
						
						ps1.setString(2, upwd);
						ps1.setString(3, uf);
						ps1.setString(4, ul);
						ps1.setString(5, umail);
						ps1.setLong(6, uph);
						ps1.setString(7, ucity);
						
						ps1.executeQuery();
						
						System.out.println("\t\tRegistration successfull ");
					}
					
					}while(x);
					
					break;
				case 2:
					System.out.println("\t\t********** WELCOME TO LOGIN PAGE **********");
					System.out.println("Enter your userName");
					String userName=sc.nextLine();
					System.out.println("Enter your password");
					String pwd=sc.nextLine();
					ps2.setString(1, userName);
					ps2.setString(2, pwd);
					ResultSet rs1=ps2.executeQuery();
					
					if(rs1.next())
					{
						
//                    while(rs1.next())
//                    {
//					if(rs1.getString(1).equals(userName) && rs1.getString(2).equals(pwd))
//					{
						System.out.println("Welcome "+rs1.getString(3));
						boolean c=true;
						do
						{
							System.out.println("\t\t*******Enter your choice********");
							System.out.println("\t\t1.View Profile"+"\t\t2.Upload personal details"+"\n\t\t3.Edit profile"+
							"\n\t\t4.Previous Menu"+"\n\t\t5.logout");
							switch(Integer.parseInt(sc.nextLine()))
							{
							case 1:
								ps3.setString(1, userName);
								ResultSet rs2=ps3.executeQuery();
								if(rs2.next())
								{
									System.out.println("\t\t****user profile*******");
									for(int i=1;i<=7;i++)
									{
										System.out.print(String.format("%-20s",rs2.getString(i)));
									}
									
								}
									
								break;
							case 2:
								System.out.println("Upload your Details Following Feilds");
								System.out.println("\t\t1.Upload Signature"+"\n\t\t2Upload photo"+
								"\n\t\t3.Upload Ressume");
								System.out.println("Enter choice");
								switch(Integer.parseInt(sc.nextLine()))
								{
								case 1:
									break;
								case 2:
									break;
								case 3:
									break;
									default:
										System.out.println("invalid option");
								}
								               
								break;
								
							case 3:
								
								boolean b=true;
								do {
									System.out.println("\t\t****Enter your choice****");
									System.out.println("\t\t1.update phone number"+"\n\t\t2.update current city"
									+"\n\t\t3.previous menu"+"\n\t\t4.logout");
									switch(Integer.parseInt(sc.nextLine()))
									{
									case 1:
										System.out.println("Existing phno : "+rs1.getString(6));
										System.out.println("Enter new phno number :");
										long uNph=Long.parseLong(sc.nextLine());
										ps4.setLong(1, uNph);
										ps4.setString(2, userName);
										int k=ps4.executeUpdate();
										if(k>0)System.out.println("update succesfully");
										break;
									case 2:
										
										System.out.println("Existing city : "+rs1.getString(7));
										System.out.println("Enter current city :");
										String  uC=sc.nextLine();
										ps5.setString(1, uC);
										ps5.setString(2,userName);
									     
										int k1=ps5.executeUpdate();
										if(k1>0)System.out.println("update succesfully");
										break;
									case 3:
										b=false;
										break;
									case 4:
										System.out.println("logout.....");
										System.exit(0);
										break;
										default:
											System.err.println("invalid choice");
									}
									
								}while(b);
								
								break;
								
								
							case 4:
								 c=false;
								 break;
							case 5:
								System.out.println("logout.....");
								System.exit(0);
								break;
								
								default:
									System.err.println("invalid input");
								
							}
							
						}while(c);
						
						
						
//					}
//					
//				}
				}
				else {
					System.err.println("Invalid userName or password!!!!");
				}
                 

					break;
					
				case 3:
					System.out.println("logout.....");
					System.exit(0);
					default:
						System.err.println("Invalid option you had choosen!!!!!!");
				}
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
