package com.pluralsight.controllers;

import com.pluralsight.dao.VehicleDao;
import com.pluralsight.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleDao vehicleDao;


    @GetMapping("/api/vehicles")
    public List<Vehicle> getProduct() {
        return vehicleDao.getAllVehicles();
    }

}
