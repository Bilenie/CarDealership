package com.pluralsight;

public class Program {
    public static void main(String[] args) {

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
