import java.sql.*;
import java.util.Scanner;

public class EmployeeManager {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{

        try
        {    int choice=0;
            Employee e = new Employee();
            do
            {
                System.out.println("Vui Long Chon Dich Vu \n 1- Add a new employee \n 2- Get employee by name \n 3- Get all employees \n 4- Edit employee details \n 5- Delete employee \n 6- Exit\n");
                Scanner sc = new Scanner(System.in);
                choice=sc.nextInt();
                switch(choice)
                {
                    case 1:
                        e.getEmployeeDetails();
                        e.insertStudent();
                        break;
                    case 2:
                        e.searchEmployee();
                        break;
                    case 3:
                        e.getAllEmployee();
                        break;
                    case 4:
                        e.updateEmployeeDetail();
                        break;
                    case 5:
                        e.deleteEmployee();
                        break;
                    case 6 :
                        break;
                    default:
                        System.out.println("Please check your selection");
                }
            }while(choice!=6);
            System.out.println("Thank you for using the services");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
class Employee
{
    private String empId;
    private String empNo;
    private String name;


    public void getEmployeeDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your emp.ID");
        empId = input.nextLine();
        System.out.println("Enter your emp.No");
        empNo = input.nextLine();
        System.out.println("Enter your Name");
        name = input.nextLine();
    }

    public void insertStudent() throws ClassNotFoundException, SQLException {
        //here we are going to work with a database
        //we need to open a database connection
        Connection connection = SQLServerConnection.getSQLServerConnection();
        String sql = "insert into employee values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, empId);
        preparedStatement.setString(2, empNo);
        preparedStatement.setString(3, name);
        int i = preparedStatement.executeUpdate();
        System.out.println("Add Successfully!\n");
        connection.close();
    }

    public void updateEmployeeDetail() throws ClassNotFoundException, SQLException {
        System.out.println("Where do you want to edit? ");
        System.out.println("1. Emp ID");
        System.out.println("2. Emp No");
        System.out.println("3. Name");
        System.out.println("4. Quit");
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                updateEmployeeID();
                break;
            case 2:
                updateEmployeeNO();
                break;
            case 3:
                updateEmployeeName();
                break;
            case 4:
                break;
            default:
                System.out.println("Please check your selection!");
        }
    }

    public void updateEmployeeID() throws ClassNotFoundException, SQLException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        System.out.println("Enter Your Name");
        Scanner input = new Scanner(System.in);
        String inputname=input.nextLine();
        System.out.println("Enter the new ID");
        String inputid=input.nextLine();
        String query = "update employee set empid = ? where name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, inputid);
        preparedStatement.setString(2, inputname);
        int i = preparedStatement.executeUpdate();
        if(i>0)
        {
            System.out.println("Edit successfully");
        }else
        {
            System.out.println("Edit Failed");
        }
        connection.close();
    }

    public void updateEmployeeNO() throws ClassNotFoundException, SQLException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        System.out.println("Enter Your Name");
        Scanner input = new Scanner(System.in);
        String inputname=input.nextLine();
        System.out.println("Enter the new emp No");
        String inputno=input.nextLine();
        String query = "update employee set empno = ? where name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, inputno);
        preparedStatement.setString(2, inputname);
        int i = preparedStatement.executeUpdate();
        if(i>0)
        {
            System.out.println("Edit successfully");
        }else
        {
            System.out.println("Edit Failed");
        }
        connection.close();
    }

    public void updateEmployeeName() throws ClassNotFoundException, SQLException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        System.out.println("Enter your ID:");
        Scanner input = new Scanner(System.in);
        String inputid=input.nextLine();
        System.out.println("Enter your new name:");
        String inputname=input.nextLine();
        String query = "update employee set name = ? where empid = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, inputname);
        preparedStatement.setString(2, inputid);
        int i = preparedStatement.executeUpdate();
        if(i>0)
        {
            System.out.println("Edit successfully");
        }else
        {
            System.out.println("Edit Failed");
        }
        connection.close();
    }


    public void deleteEmployee() throws ClassNotFoundException, SQLException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        System.out.println("Enter the name you want to delete");
        Scanner input = new Scanner(System.in);
        String inputname=input.nextLine();
        String sql = "delete from employee where name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, inputname);
        int i = preparedStatement.executeUpdate();
        if(i>0)
        {
            System.out.println("Deleted Successfully");
        }
        else
        {
            System.out.println("Name not found in the Database");
        }
        connection.close();
    }

    public void searchEmployee() throws ClassNotFoundException, SQLException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        System.out.println("Enter Your Name");
        Scanner input = new Scanner(System.in);
        String inputname=input.nextLine();
        String sql = "select * from employee where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, inputname);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()==false)
        {
            System.out.println("Name not found in the database");
        }
        else
        {
            System.out.println("Here is the information of the employee: ");
            System.out.println(rs.getInt(1)+" - "+rs.getString(2)+" - "+rs.getString(3));

        }
        connection.close();
    }

    public static void getAllEmployee() throws SQLException, ClassNotFoundException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        String query = "select * from employee";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            {
                int empId = resultSet.getInt(1);
                String empNo = resultSet.getString(2);
                String name = resultSet.getString(3);
                System.out.println("--------------------");
                System.out.println("empID: " + empId);
                System.out.println("empNO: " + empNo);
                System.out.println("Name: " + name);
            }

        }
        connection.close();
    }
}


