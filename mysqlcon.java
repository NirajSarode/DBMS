import java.sql.*;  
import java.util.*;
class MysqlCon{  
public static void main(String args[]){  

try{  
Scanner sc = new Scanner(System.in);

Class.forName("com.mysql.jdbc.Driver");  

Connection con=DriverManager.getConnection(  
"jdbc:mysql://10.10.15.63/te3432db","te3432","te3432");  

System.out.println("1. Insert into database.");
System.out.println("2. Update from database.");
System.out.println("3. Delete from database.");
System.out.println("4. Retrieve from database.");
System.out.println("5. Exit.");

PreparedStatement stmt=null;

int age,marks;
String name,str;
String sex;
int yes=1;
while(yes==1){
	int ch = sc.nextInt();
switch(ch)
{
	case 1:
		str = "insert into student values (?,?,?,?);";
		stmt = con.prepareStatement(str);
		System.out.println("Enter name:");
		name=sc.next();
		System.out.println("Enter age:");
		age=sc.nextInt();
		System.out.println("Enter sex:");
		sex=sc.next();
		System.out.println("Enter marks:");
		marks=sc.nextInt();
		stmt.setString(1, name);
		stmt.setInt(2,age);
		stmt.setString(3,sex);
		stmt.setInt(4,marks);
		stmt.execute();

		System.out.println("Inserted Successfully.");

		break;
	case 2:
		str = "update student SET age=?,sex=?,marks=? where name=?;";

		stmt = con.prepareStatement(str);
		System.out.println("Enter name to update:");
		name=sc.next();
		System.out.println("Enter updated age:");
		age=sc.nextInt();
		System.out.println("Enter updated sex:");
		sex=sc.next();
		System.out.println("Enter updated marks:");
		marks=sc.nextInt();
		
		stmt.setString(4,name);
		stmt.setInt(1,age);
		stmt.setString(2,sex);
		stmt.setInt(3,marks);

		stmt.execute();

		System.out.println("Updated Successfully.");

		break;
	case 3:
		str = "delete from student where name=?;";
		stmt = con.prepareStatement(str);
		System.out.println("Enter name to delete:");
		name=sc.next();
		
		stmt.setString(1,name);

		stmt.execute();

		System.out.println("Deleted Successfully.");

		break;
	case 4:
		str = "select * from student;";
		stmt = con.prepareStatement(str);

		ResultSet rs=stmt.executeQuery();

		while(rs.next())  
		System.out.println(rs.getString(1)+"  "+rs.getInt(2)+"  "+rs.getString(3)+"  "+rs.getInt(4)); 

		System.out.println("Operation Successful.");
		break;
	case 5:
		
		
	default:
		yes=0;
}

}

	con.close();  
}
catch(Exception e){ System.out.println(e);}  
}  
}  
