package jdbcAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class Main
{
	public static Scanner scanner = new Scanner (System.in);
	public static Connection connection;
	public static Statement statement;
	public static  Scanner input = new Scanner(System.in);
	public static ResultSet rs;
	  

	public static void registerEmployee(){

		try{
			
			System.out.print("ID: ");
			int newID = input.nextInt();
			input.nextLine();
			System.out.print("Profession: ");
			String e_position = input.nextLine();  
			
			System.out.print("Name: ");
			String ep_name = input.nextLine();
		      
			System.out.print("11 Digit Phone Number: ");
			String ep_phone = input.nextLine();
		      
			System.out.print("Years of experience: ");
			int ep_years_experience = input.nextInt();
		      
			System.out.print("Criminal History? y/n ");
			boolean ep_criminal_history;
			String check = input.next();
			if((check.equals("y"))){
				ep_criminal_history = true;
			}else{
				ep_criminal_history = false;
			} 
			
			String estatus = "unemployed";
			statement.executeUpdate("insert into EProfile values(" + newID + ",'" + ep_name + "', '" + ep_phone + "', " + ep_years_experience + "," + null + ",'" + ep_criminal_history + "', '" + estatus + "');");
			statement.executeUpdate("insert into Employee values(" + newID + ",'" + e_position + "'," + null + ");");
			
			System.out.print("\nThank you for registering as an Employee ");
		}catch(SQLException err){
			err.printStackTrace();
		}	
	}
	
	
	public static void payCut(){
		try{
			System.out.println("Enter employee ID: ");
			int e_ID = input.nextInt();
			System.out.println("How much would you like to decrease this employees salary?");
			int cut = input.nextInt();
			
			statement.executeUpdate("update employee set e_pay = (e_pay - " + cut + ")where e_ID = " + e_ID + ";");
			
			System.out.println("\nEmployee #"+ e_ID+ " salary has been decreased by "+ cut);
			
			rs = statement.executeQuery("SELECT e_pay as pay" + " FROM employee" + 
			  	       " where e_ID = " + e_ID + ";");
				       		
			  			while (rs.next()){
			  					System.out.println("Employee #"+ e_ID+ " pay/hr is now set to: $"+rs.getString("pay")); 

			  			}
			
		}catch(SQLException err){
			err.printStackTrace();
		}
	}


	public static void findWorker(){
	  	  
	  	  try{
	  		  
	  		 ResultSet rs1 = statement.executeQuery("select distinct j_position from Jobs;");
			  System.out.println("Choose from our selection of Jobs available \n");
			  while (rs1.next()){
					System.out.println(rs1.getString("j_position")); 
			}
	  		  
	  		  
	  		  
			  System.out.print("\nWhat type of worker are you looking for: ");
		      String worker_type = input.nextLine();

		       rs = statement.executeQuery("SELECT ep_name as Available" + " FROM Employee,EProfile" + 
	  	       " WHERE Employee.e_position = '" + worker_type + "' AND e_ID = ep_ID;");
		       		
	  			while (rs.next()){
	  					System.out.println("\n"+rs.getString("Available")); 

	  			}
	  			
	  		
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	public static void payRaise(){
		
		 try{
	  		 
			
			  System.out.print("Enter the Employees ID: ");
		      int worker_id = input.nextInt();
		      
		      System.out.print("Enter how much would you like to increase the payscale by: ");
		      int raise = input.nextInt();


			  String sql = "UPDATE Employee"
							+" SET e_pay = e_pay +"+raise
							+" WHERE e_ID = "+worker_id+";";
				statement.executeUpdate(sql);
				
			  System.out.println("\nEmployee #"+ worker_id+ " payscale has been raised");
			  
			  rs = statement.executeQuery("SELECT e_pay as pay" + " FROM employee" + 
			  	       " where e_ID = " + worker_id + ";");
				       		
			  			while (rs.next()){
			  					System.out.println("\nEmployee #"+ worker_id+ " pay/hr is now set to: "+rs.getString("pay")); 

			  			}
			  	
	  	  }
		 catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	public static void rateWorker(){
		 try{
	  		  
	 
			  System.out.print("Enter the Employees ID: ");
		      int worker_id = input.nextInt();
		      
		      System.out.print("On a scale of 1-10, how would you rate your employee: ");
		      int ep_rating = input.nextInt();


			  String sql = "UPDATE EProfile"
							+" SET ep_rating = (ep_rating +"+ ep_rating
							+")/2 WHERE ep_ID = "+worker_id+";";
			  
			  
				statement.executeUpdate(sql);  
				 rs = statement.executeQuery("SELECT ep_rating as rating" + " FROM EProfile" + 
				  	       " where ep_ID = " + worker_id + ";");
					       		
				  		
				
			
				
				
				
			  System.out.println("\nYour rating for Employee #"+ worker_id+ " has been applied");
				while (rs.next()){
  					System.out.println("Employee #"+ worker_id+ " rating is now set to: "+rs.getString("rating")); 

  			}
 
			  System.out.println("Thank you ");
			  

				
	  	  }
		 catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	

	public static void registerBossComp(){
		
		
	 	  System.out.print("Enter the company you belong to: ");
	  	  String c_name = input.nextLine();
	      
	      System.out.print("Enter your Boss ID: ");
	      int b_ID = input.nextInt();
	      input.nextLine();
	      
	  	  System.out.print("Enter your full name: ");
	  	  String b_name = input.nextLine();   

	  	  System.out.print("Enter the main company phone: ");
	  	  int c_phone = input.nextInt();
	  	  
	  	  System.out.print("Enter your Phone ext: ");
	  	  int b_phone_ext = input.nextInt();
	   	  input.nextLine();
	  	  
	  	  System.out.print("Whats the address of this company: ");
	  	  String l_address = input.nextLine();
	  		  	   
	  	  System.out.print("Enter your salary: ");
	  	  int b_salary = input.nextInt();
	  	  input.nextLine();
	  	
	  	try{
	  	  

			statement.executeUpdate("insert into Boss values("+b_ID+","+b_phone_ext+",'"+b_name+"',"+b_salary+");");
			statement.executeUpdate("insert into Company values("+b_ID+",'"+c_name+"',"+c_phone+");");
			statement.executeUpdate("insert into Location values('"+l_address+"',"+b_ID+");");
		
		  System.out.print("New Boss and Company info has been created. ");
		
			
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }	
	}
	

	public static void findAddress(){
		  try{
			  
			  
			  ResultSet rs1 = statement.executeQuery("select distinct c_name from Company;");
			  System.out.println("Choose from our selection of Companies \n");
			  while (rs1.next()){
					System.out.println(rs1.getString("c_name")); 
			}
			  
	  		 
			
			  System.out.println("\nEnter the full name of the company you're inquiring about: ");
		      String c_name = input.nextLine();
		      
		      rs = statement.executeQuery("select l_address as Location from Location, Company where c_name = '" +c_name+"' and c_ID = l_id;");
		     
	  			while (rs.next()){
	  					System.out.println(c_name + " location has been found at "+rs.getString("Location")); 
	  					
	  				

	  			}
	  			
	  		
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	public static void findSalary(){
		try{
			
			  ResultSet rs1 = statement.executeQuery("select distinct e_position from Employee;");
			  System.out.println("Choose from our selection of positions to inquire wage info\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("e_position")); 
			}
			
	  	   	  System.out.print("\nEnter the full position name of what are seeking to inquire: ");
		      String e_position = input.nextLine();
		      
		      rs = statement.executeQuery(" select e_pay from Employee where e_position = '"+e_position+"'");
		      
	  		
	  			while (rs.next()){
	  				
	  				if(rs.getString("e_pay") != null)
	  					System.out.println(e_position+ "'s are making "+rs.getString("e_pay")+"/hr"); 

	  			}
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	
	public static void findEmpbyRate(){
		try{
			
			  ResultSet rs1 = statement.executeQuery("select distinct e_position from Employee;");
			  System.out.println("Choose from our selection of workers you are seeking\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("e_position")); 
			}
	  		  
	  		  System.out.print("\nEnter the full name for the type of worker you will be needing: ");
		      String e_position = input.nextLine();
			
	  	   	  System.out.print("\nEnter a number(1-10) for the quality of worker you are seeking: ");
		      String ep_rating = input.nextLine();
		      
		      rs = statement.executeQuery(" select ep_name from Employee, EProfile where e_position = '"+e_position+"' and ep_rating >= "+ep_rating+" and ep_ID = e_ID");
		      
		    
	  			while (rs.next()){
	  					System.out.println(rs.getString("ep_name")+ " is an "+ e_position+ " with a rating over "+ep_rating); 

	  			}
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}

	
	
	public static void updatejobSpecs(){
		
		try{
			
			  System.out.print("Enter your Boss ID: ");
		      int b_ID = input.nextInt();
		      input.nextLine();
		      
		      System.out.println("Enter any details for the agreement, type 'none' if there is no agreement: ");
		      String r_agreement = input.nextLine();
		      
		      System.out.println("Enter the number of years experience you are now looking for: ");
		      int r_years_experience = input.nextInt();
		      
		      System.out.println("Enter the new wage you would like to set for the position: ");
		      int j_pay = input.nextInt();
		      
		    // ResultSet rs = statement.executeQuery(" select e_pay from Employee where e_position = '"+e_position+"'");
		      
		      String sql = "UPDATE Requirements"
						+" SET r_agreement = '"+r_agreement+"', r_years_experience = "+r_years_experience+" "
						+ "WHERE r_ID = "+b_ID+";";
		      
		      statement.executeUpdate(sql);

			  sql = "UPDATE Jobs"
							+" SET j_pay = "+j_pay
							+" WHERE j_ID = "+b_ID+";";
				
			  statement.executeUpdate(sql);
				
			  System.out.println("\nBoss #"+ b_ID+ " position posting has been updated");
				
	  	  }
		 catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	
	public static void findIdealEmployeepayrange(){
		try{
			
			  ResultSet rs1 = statement.executeQuery("select distinct e_position from Employee;");
			  System.out.println("Choose from our selection of worker types\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("e_position")); 
			}
	  		  
	  		  System.out.println("\nEnter the full worker type of name for the type of worker you are seeking: ");
		      String e_position = input.nextLine();
			
		      System.out.print("\nYou will now enter a range starting from lower bound for how much you are willing to pay");
	  	   	  System.out.println("\nEnter a lower bound for the payment/hour you are willing to pay: ");
		      int e_pay1 = input.nextInt();
		      
		      System.out.println("\nEnter the higher bound for the payment/hour you are willing to pay: ");
		      int e_pay2 = input.nextInt();
 
		      rs = statement.executeQuery("select ep_name from Employee, EProfile where e_position = '"+e_position+"' and e_ID = ep_ID and e_pay >= "+e_pay1+" "
		      		+ "and e_pay <="+ e_pay2);
		      
		    
	  			while (rs.next()){
	  					System.out.println(rs.getString("ep_name")); 

	  			}
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	

	public static void findjobsTypeAvailable(){
		try{
			
			  ResultSet rs1 = statement.executeQuery("select distinct c_name from Company;");
			  System.out.println("Choose your desired place of work from this list of Companies\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("c_name")); 
			}
	  	 
	  		  System.out.println("\nEnter the full Company name of where you would like to work: ");
		      String c_name = input.nextLine();
			
		      
		       rs1 = statement.executeQuery("select j_position from Jobs;");
			  System.out.println("Choose a position you would like to inquire about\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("j_position")); 
			}
		      
		      System.out.println("\nEnter the full position title of where you are interested in working: ");
		      String e_position = input.nextLine();
 
		      
		    //  ResultSet rs = statement.executeQuery("select ep_name from Employee, EProfile where e_position = '"+e_position+"' and e_ID = ep_ID and e_pay >= "+e_pay1+" "
			  //    		+ "and e_pay <="+ e_pay2);
			      
		      
		      rs = statement.executeQuery("select count(j_position) from Jobs, Company where c_ID= j_ID and c_name = '"+c_name+"' and j_position = '"+e_position+"'");
		      
		      
		      
		    
	  			while (rs.next()){
	  					System.out.println("There are currently "+rs.getString("count(j_position)")+" " +e_position+ " positions available"); 
	  			}
		      
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	
	public static void changeCompLoc(){
		try{
			System.out.println("Enter name of company that is moving location: ");
			String c_name = input.next();
			input.nextLine();
			
			System.out.println("Enter the address of the new location");
			String newLoc = input.nextLine();
			
			rs = statement.executeQuery("select c_ID from Company where c_name = '" + c_name + "';");
			int ID = 0;
			while(rs.next()){
				ID = rs.getInt("c_ID");
			}
			
			statement.executeUpdate("update Location set l_address = '" + newLoc + "' where l_ID = " + ID + ";");
			System.out.println("Company location has been changed.");
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	public static void employeeNumber(){
		try{
			System.out.println("Enter the ID# of the employee you would like to contact: ");
			int ID = input.nextInt();
			rs = statement.executeQuery("select ep_phone from EProfile where ep_ID = " + ID + ";");
			System.out.print("You can reach this employee at ");
			while(rs.next()){
				System.out.println(rs.getString("ep_phone"));
			}
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	public static void bossNumber(){
		try{
			String ph_num;
			System.out.println("Enter the ID# of your employer: ");
			int ID = input.nextInt();
			rs = statement.executeQuery("select c_phone from Company, Boss where c_ID = " + ID + ";");
			ph_num = rs.getString("c_phone");
			
			System.out.print("You can reach your boss at " + ph_num + " with extension# ");
			rs = statement.executeQuery("select b_phone_ext from Boss where b_ID = " + ID + ";");
			while(rs.next()){
				System.out.println(rs.getString("b_phone_ext") + ".");
			}
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	public static void postNewJob(){
		try{
			  System.out.print("Whats the title of this job: ");
		  	  String j_position = input.nextLine();
		  	  
		  	  System.out.print("Enter the ID# to be associated with this job: ");
		  	  int ID = input.nextInt();
		  	  
			  System.out.print("What does this job pay per hour? ");
		  	  int j_pay = input.nextInt();
		  	  
		   	  System.out.print("How many years of experience are you looking for: ");
		  	  int r_years_experience = input.nextInt();
		  	  
		  	  System.out.print("Enter the days required to work: ");
		  	  String s_days = input.nextLine();
		  	  input.nextLine();
		  	  
		  	  System.out.print("Enter the start time as hh:mm:ss ");
		  	  String s_start = input.nextLine();
		  	  
		  	  System.out.print("Enter the end time as hh:mm:ss ");
		  	  String s_end = input.nextLine();
		  	  
		  	  
			statement.executeUpdate("insert into Requirements values("+ID+",'"+null+"',"+r_years_experience+");");
			statement.executeUpdate("insert into Jobs values('"+j_position+"',"+j_pay+","+ID+");");
			statement.executeUpdate("insert into Schedule values('"+ s_start + "', '" + s_end + "', '" + s_days + "', " + ID + ")");
			
			System.out.println("Job has been posted.");
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	
	public static void hire(){
		try{
			System.out.println("Enter the ID# of the employee you would like to hire: ");
			int ID = input.nextInt();
			rs = statement.executeQuery("select e_position from Employee where e_ID = " + ID + ";");
			String position = rs.getString("e_position");
			rs = statement.executeQuery("select j_pay from Jobs where j_position = '" + position + "';");
			//int j_pay = rs.getInt("j_pay");
			String estatus = "employed";
			//statement.executeUpdate("update Employee set e_pay = " + j_pay + " where e_ID = " + ID + ";");
			statement.executeUpdate("update EProfile set ep_employment_status = '" + estatus + "' where ep_ID = " + ID + ";");
			System.out.println("Employee has been hired.");
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	public static void fire(){
		try{
			System.out.println("Enter the ID# of the employee you would like to fire: ");
			int ID = input.nextInt();
			
			String estatus = "unemployed";
			statement.executeUpdate("update Employee set e_pay = 0 where e_ID = " + ID + ";");
			statement.executeUpdate("update EProfile set ep_employment_status = '" + estatus + "' where ep_ID = " + ID + ";");
			System.out.println("Employee #"+ID+" has been fired.");
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	public static void deleteJob(){
		try{
			System.out.println("Please enter the ID# of the job you would like to delete: ");
			int ID = input.nextInt();
			statement.executeUpdate("delete from Jobs where j_ID = " + ID + ";");
			statement.executeUpdate("delete from Schedule where s_ID = " + ID + ";");
			statement.executeUpdate("delete from Requirements where r_ID = " + ID + ";");
			System.out.println("Job posting #"+ID+" has been deleted. ");
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	
	public static void findEmpbyExp(){
		try{
			  ResultSet rs1 = statement.executeQuery("select distinct e_position from Employee;");
			  System.out.println("Choose from our selection of workers you are seeking\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("e_position")); 
			}
	  		  
	  		  System.out.print("\nEnter the full name for the type of worker you will be needing: ");
		      String e_position = input.nextLine();
		      
		      System.out.println("What is the minimum number of years of experience are you looking for? ");
		      int years = input.nextInt();
		      
		      int count = 0;
		      rs = statement.executeQuery("select e_ID from employee where e_position = '" + e_position + "';");
		      //get size of array
		      while(rs.next()){
		    	count++;
		    	//System.out.println(rs.getInt("e_ID"));
		      }
		      //make array
		      int[] ID = new int[count];
		      int i = 0;
		      rs = statement.executeQuery("select e_ID from employee where e_position = '" + e_position + "';");
		      while(rs.next()){
			    	ID[i] = rs.getInt("e_ID");
			    	i++;
			  }
		      
		      System.out.println("Available employees meeting this requirement: ");
		      
		      for(int j = 0; j < count; j++){
		    	  rs = statement.executeQuery("select ep_ID, ep_name from EProfile where ep_ID = " + ID[j] + " and ep_years_experience >= " + years + " and ep_employment_status = 'unemployed';");
		    	  while(rs.next()){
				    	  System.out.println("ID: " + rs.getInt("ep_ID") + " Name: " + rs.getString("ep_name") + "\n");
				  }
		      }
		
		     // while(rs.next()){
		    	//  System.out.println("ID: " + rs.getInt("ep_ID") + " Name: " + rs.getString("ep_name") + "\n");
		      //}
		      
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	
	
	
	public static void findjobOppurtunities(){
		try{
			
			  ResultSet rs1 = statement.executeQuery("select distinct j_position from Jobs ;");
			  System.out.println("Choose from our selection of worker types\n");
			  while (rs1.next()){
					System.out.println(rs1.getString("j_position")); 
			}
	  		  
	  		  System.out.println("\nEnter a keyword associated with the type of position you are looking for ");
		      String e_position = input.nextLine();
			
		     
		      rs = statement.executeQuery("	select distinct j_position, j_pay as payPerhour,  c_name as Company, l_address as location, c_phone as phoneNumber "
		      		+ "from Jobs join Company join Location "
		      		+ "where j_position like '%"+ e_position+"%' and j_ID = c_ID and l_id = j_id "
		      		+ "order by j_pay");
		      
		    
	  			while (rs.next()){
	  					System.out.println("Title: " + rs.getString("j_position") +" \n"+
	  							"Wage: " + rs.getString("payPerhour") + "/hour \n" +
	  							"Company: " + rs.getString("Company") +" \n"+
	  							"Location: " + rs.getString("location") + " \n"+
	  							"Phone Number: " +rs.getString("phoneNumber")+ "\n \n"); 

	  			}
	  	  }catch(SQLException err){
	  		  err.printStackTrace();
	  	  }
	}
	
	public static void checkSched(){
		try{
			int ID;
			String job;
			System.out.println("Enter employee ID: ");
			ID = input.nextInt();
			
			rs = statement.executeQuery("select e_position from Employee where e_ID = " + ID + ";");
			job = rs.getString("e_position");
			rs = statement.executeQuery("select s_start, s_end, s_workdays from (Jobs join Schedule on Jobs.j_ID = Schedule.s_ID)where j_position = '" + job + "';");

			while(rs.next()){
				System.out.println("Start time: " + rs.getString("s_start") + "\n");
				System.out.println("End time: " + rs.getString("s_end") + "\n");
				System.out.println("Days Needed: " + rs.getString("s_workdays") + "\n");
			}
			
		}catch(SQLException err){
			err.printStackTrace();
		}
	}
	


	
	
	public static void printEmployeeActions(){
		System.out.println("\nAvailable Employee Actions: \npress");
		
		System.out.println("1  - Register\n"  +
						   "2  - Find an Employer\n"  +
						   "3  - Find Typical Job Salaries \n"  +
						   "4  - Find Job types available\n"  +
						   "5  - Find a Companies Address\n" +
						   "6  - Search positions based on keyword\n" +
						   "7  - View Employee Schedule\n"+
						   "8  - Exit to Main Menu\n" );
		
		System.out.println("Choose your action:  ");
	}
	
	public static void printBossActions(){
		System.out.println("\nAvailable Boss Actions: \npress");
		System.out.println("1  - Register\n"  +
						   "2  - Post a Job\n"  +
						   "3  - Update your job\n"  +
						   "4  - Find type of worker\n"  +
						   "5  - Find worker based on their rating\n"  +
						   "6  - Find worker based on amount you are willing to pay\n"  +
						   "7  - Find worker based on their experience\n"  +
						   "8  - Cut Employee pay\n"  +
						   "9  - Give an Employee a pay raise\n"  +
						   "10 - Rate an Employee\n"  +
						   "11 - Change the company location\n"  +
						   "12 - Find an Employee phone number\n"  +
						   "13 - Hire an Employee\n"  +
						   "14 - Fire an Employee\n"  +
						   "15 - Delete a previous Job Posting\n"  +
						   "16 - Exit to main menu\n");
		System.out.println("Choose your action:  ");
	}
	
	public static void printMainMenu(){
		System.out.println("\nAvailable Actions: \npress");
		
		System.out.println("1  - For the Boss Portal\n"  +
						   "2  - For the Employee Portal\n"  +
						   "3  - Exit\n" );
		
		System.out.println("Choose your action:  ");
	}


  public static void main(String[] args) 
  {
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:ContractFinder.db");
      statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
    
     // registerEmployee();//Employee
     // payCut();//Boss
      //changeCompLoc();//Boss
      //employeeNumber();//Boss
       //bossNumber();//Employee
       //findWorker();//Boss side
     // payRaise();//Boss side
      //rateWorker();//Boss side    

      //registerBossComp();//BossSide
      //postNewJob();//Boss side

       // findAddress();//Employee side
        //findSalary();//employee
       // findEmpbyRate();//Boss side
       // updatejobSpecs(); //Boss side
       // findIdealEmployeepayrange();//Boss side
       // findjobsTypeAvailable();//Employee
      // hire();//Boss
      //fire();//Boss
       //deleteJob();//Boss
      //  findEmpbyExp();//Boss
  //    findjobOppurtunities();//employee
      
      boolean quit = false;
      
        while(quit != true){
          printMainMenu();
    	  int action = scanner.nextInt();
    	  scanner.nextLine();
    	  
    	  switch(action){
    	  
    	  case 1:
    		    boolean quit1 = false;
    		      
    	        while(quit1 != true){
    	          printBossActions();
    	    	  int start = scanner.nextInt();
    	    	  scanner.nextLine();
    	    	  
    	    	  switch(start){
    	    	  
    	    	  case 1:
    	    		  System.out.println("Register as a Boss: ");
    	    		  registerBossComp();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 2:
    	    		  System.out.println("Post a Job: ");
    	    		  postNewJob();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 3:
    	    		 // System.out.println("Insert : ");
    	    		  updatejobSpecs();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 4:
    	    		  //System.out.println("Insert Data: ");
    	    		  findWorker();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 5:
    	    		  //System.out.println("Insert Data: ");
    	    		  findEmpbyRate();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 6:
    	    		 // System.out.println("Insert Data: ");
    	    		  findIdealEmployeepayrange();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 7:
    	    		 // System.out.println("Insert Data: ");
    	    		  findEmpbyExp();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 8:
    	    		  //System.out.println("Insert Data: ");
    	    		  payCut();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 9:
    	    		  //System.out.println("Insert Data: ");
    	    		  payRaise();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 10:
    	    		  //System.out.println("Insert Data: ");
    	    		  rateWorker();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 11:
    	    		  //System.out.println("Insert Data: ");
    	    		  changeCompLoc();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 12:
    	    		  //System.out.println("Insert Data: ");
    	    		  employeeNumber();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 13:
    	    		 // System.out.println("Insert Data: ");
    	    		  hire();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 14:
    	    		  //System.out.println("Insert Data: ");
    	    		  fire();
    	    		  
    	    		  quit1 = false;
    	    		  break;
    	    	  case 15:
    	    		  //System.out.println("Insert Data: ");
    	    		  deleteJob();
    	    		  quit1 = false;
    	    		  break;
    	    		  
    	    	  case 16: 
    	    		  
    	    		  System.out.println("Exited");
    	    		  quit1 = true;
    	    		  break;
    	    	  }
    	    	//  break;
    	    	 
    	      }
    	        break;
    	        
    	  case 2:
    		  boolean quit2 = false;
		      
  	        while(quit2 != true){
  	        	printEmployeeActions();
  	    	  int start = scanner.nextInt();
  	    	  scanner.nextLine();
  	    	  
  	    	  switch(start){
  	    	  
  	    	  case 1:
  	    		 // System.out.println("Update Warehouse: ");
  	    		  registerEmployee();
  	    		  
  	    		  quit2 = false;
  	    		  break;
  	    	  case 2:
  	    		  //System.out.println("Insert Data: ");
  	    	   	  bossNumber();
  	    		  
  	    		  quit2 = false;
  	    		  break;
  	    	  case 3:
	    		  //System.out.println("Insert Data: ");
	    		  findSalary();
	    		  
	    		  quit2 = false;
	    		  break;
  	    	 case 4:
	    		  //System.out.println("Insert Data: ");
	    		  findjobsTypeAvailable();
	    		  
	    		  quit2 = false;
	    		  break;
  	    	case  5:
	    		 // System.out.println("Insert Data: ");
	    		  findAddress();
	    		  
	    		  quit2 = false;
	    		  break;
	    	case  6:
	    		 // System.out.println("Insert Data: ");
	    		 findjobOppurtunities();
	    		  
	    		  quit2 = false;
	    		  break;
	    		  
	    		  
	         case 7: 
 	    		  quit2 = false;
 	    		  checkSched();	    		  
	    		  
	         case 8: 
  	    		  quit2 = true;
  	    		  System.out.println("Exited");

  	    	  }
  	    	  
  	      }
  	        break;
  	        
    	  case 3:
    		  System.out.println("Application has been terminated");
    		  quit = true;
    		  break;
		      
    	  }    	  
      }

    }
    catch(SQLException e)
    {
      // if the error message is "out of memory", 
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
        
          //System.out.println("Connection Disconnected");
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
        
      }
      
    }
    
  }
}