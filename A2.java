package mysql_pro;

import java.util.Scanner;
import java.sql.*;



public class A2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/";
		String driver="com.mysql.jdbc.Driver";
		String dbname="prac";
		String uname="root";
		String pword="password";
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url+dbname, uname, pword);
			Statement st=con.createStatement();
			String table_name=null;
			System.out.println("The tables in database are:");
			ResultSet rs = st.executeQuery("show tables;");
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			/*String sql = "create table preksha(name varchar(20),roll_no int(5));";
			int s = st.executeUpdate(sql);*/
			
			String sql = "Insert into preksha(name,roll_no) Values(?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "preksha");
			stmt.setInt(2, 1);
			stmt.execute();
			
			sql = "Update preksha set name = ? where name = ?";
			PreparedStatement stmt1 = con.prepareStatement(sql);
			stmt1.setString(1, "nayan");
			stmt1.setString(2, "Pratiksha");
			stmt1.execute();
			sql="select * from preksha;";
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println("Name "+rs.getString(1)+"Roll_no "+rs.getInt(2));
			}
			sql = "delete from preksha where name=?";
			PreparedStatement stmt2 = con.prepareStatement(sql);
			stmt2.setString(1, "Nayan");
			stmt2.execute();
			sql="select * from preksha;";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println("Name"+rs.getString(1)+"Roll"+rs.getString(2));
			}
			
			
			
		}
		catch(Exception e)
		{
			
		}
		

	}

}
