package com.pluralsight;

import com.pluralsight.dao.VehicleDao;
import com.pluralsight.userInterface.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Program {

    //connect to the special helper class for my connectivity
    public static BasicDataSource dataSource = new BasicDataSource();

    public static void main(String[] args) {
        //Making sure we passed in 2 argument from the CLI when we run the app.
        //page 45

        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>"
            );
            System.exit(1);//quite the program if we don't have 2 argument
        }

        //get the username and password from the command line args
        String username = args[0];
        String password = args[1];


        dataSource.setUrl("jdbc:mysql://localhost:3306/ CarDealershipdb");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // Pass dataSource to the DAO class
        VehicleDao.dataSource = dataSource;


        // build the actual dealership
        // Dealership myShop = new Dealership("Bilenie Cars", "123 Garland Texas", "123-456-7890");

        // build the actual car to put store it into our inventory in the dealership created
        // Vehicle myCar = new Vehicle(1234, 2025, "Toyota", "RAV4", "Suv", "Black", 9000, 45000);
        //myShop.addVehicle(myCar);

        //We instantiate an object userinterface to access the display method.
        UserInterface userInterface = new UserInterface();
        userInterface.display();

    }
}
