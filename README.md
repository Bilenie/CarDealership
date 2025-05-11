# Car Dealership Console Application

A simple Java console application that allows users to manage a car dealership's inventory. The application supports adding, removing, viewing, and filtering vehicles by different criteria, as well as persisting data to and from a CSV file.

---

## 🚀 Features

### User Interactions

1. Search vehicles by price range
2. Search vehicles by make/model
3. Search vehicles by year range
4. Search vehicles by color
5. Search vehicles by mileage/odometer range
6. Search vehicles by type
7. View all vehicles
8. Add a vehicle to inventory
9. Remove a vehicle from inventory
10. Exit application

---

## 🧱 Project Structure

### `Vehicle.java`

* Fields: `vin`, `year`, `make`, `model`, `type`, `color`, `odometer`, `price`
* `toString()` override
* Getters and setters

### `Dealership.java`

* Fields: `name`, `address`, `phone`, `ArrayList<Vehicle> inventory`
* Methods:

  * `addVehicle(Vehicle vehicle)`
  * `removeVehicle(String vin)`
  * `getAllVehicles()`
  * `Search/find/filter` methods 

### `DealershipFileManager.java`

* Methods:

  * `getDealership()`: reads from `inventory.csv`, loads dealership info + vehicles
  * `saveDealership(Dealership dealership)`: saves dealership info + vehicles back to file

### `UserInterface.java`

* Menu system and input handling
* Loads dealership using file manager
* Handles user choices and calls appropriate processing methods

### `Program.java`

* Main entry point
* Calls `UserInterface.display()`

---

## 💾 File Format: inventory.csv

```
Dealership Name|Address|Phone
vin|year|make|model|type|color|odometer|price
...
```

---

## 📋 Example Usage

```
Welcome to Bilenie Auto!
1. Search By Price
7. View All Vehicles
...
8. Add Vehicle
9. Remove Vehicle
99. Exit
Enter your choice: 1

--- Available Vehicles ---
10112|1993|Ford|Explorer|SUV|Red|525123|995.00
...
```

---

## ✅ Final Checklist

* [x] Full feature set implemented
* [x] Data persists via CSV
* [x] User input validation
* [x] Code refactored for readability

---

## 🛠 Requirements

* Java 8+
* IntelliJ

---

## 🧠 Credits

Thanks to my instructor, tutor center support, my peers, lots of Googling, and AI assistance for helping me complete this project. Their support helped me figure out how to approach building the screens, handle file operations, and debug issues along the way.

---

## 📁 Repository Setup

```bash
git clone [https://github.com/your-username/CarDealership.git](https://github.com/Bilenie/CarDealership.git)
cd /Pluralsight/workshop/CarDealership

```

---

## 💡 Tips for Improvement

* Add unit tests for Dealership logic and test that all classes function  properly.

---

## 📌 License

N/A
