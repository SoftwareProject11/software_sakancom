package code.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class Login {

    private static Logger logger = Logger.getLogger(Login.class.getName());
    public boolean isLoggedin;
    private int ownerID;
    private String ownerName;

    public Login() {
        this.isLoggedin = false;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }



    public boolean isLoggedIn() {
        return isLoggedin;
    }

    public void login(){
        isLoggedin=true;
    }

    public void logout() {
        this.isLoggedin = false;
    }
    public void logInCheck(String username, String password, String user_choice) {
        boolean flag1=false, flag2=false;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom","root","memesa32002@");
            Statement stmt=con.createStatement();
            ResultSet result=stmt.executeQuery("select * from admin");
            while (result.next()){
                if(result.getString("username").equals(username) && result.getString("password").equals(password) && user_choice.equals("1"))
                {
                    login();
                    logger.info("logged in successfully");
                    flag1=true;
                    break;
                }
            }

            if(flag1==false){
                ResultSet resTenant=stmt.executeQuery("select * from tenant");
                while (resTenant.next()){
                    if(resTenant.getString("username").equals(username) && resTenant.getString("password").equals(password) && user_choice.equals("2"))
                    {
                        login();
                        logger.info("logged in successfully");
                        flag2=true;
                        break;
                    }
                }
            }

            if(flag1==false && flag2==false){
                ResultSet resOwner=stmt.executeQuery("select * from owner");
                while (resOwner.next()){
                    if(resOwner.getString("username").equals(username) && resOwner.getString("password").equals(password) && user_choice.equals("3"))
                    {
                        ownerID=resOwner.getInt("idowner");
                        ownerName=resOwner.getString("name");
                        login();
                        logger.info("logged in successfully");
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void reasonFalseLogin() {
        logger.info("The username or password is not correct");
    }
}
