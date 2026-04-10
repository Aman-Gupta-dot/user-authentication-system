package User_authentication;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/mydb";
    private static final String username="Aman";
    private static final String password="qwertyas";


    public static void main(String[] args) {
        int count=0;
        ArrayList<User> us=new ArrayList<User>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        try{
            Connection conn=DriverManager.getConnection(url,username,password);
            Statement statement=conn.createStatement();
            String query="select * from info";
            ResultSet rs=statement.executeQuery(query);

            while(rs.next())
            {
                count++;
                String u=rs.getString("username");
                String p=rs.getString("password");
                User e=new User(u,p);
                us.add(e);

                System.out.println("username:"+u);
                System.out.println("password:"+p);

            }
            LoginForm l=new LoginForm(us,conn);




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
