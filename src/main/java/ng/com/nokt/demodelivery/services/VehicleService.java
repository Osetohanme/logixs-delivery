package ng.com.nokt.demodelivery.services;


import ng.com.nokt.demodelivery.entites.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VehicleService {

    VehicleService createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Long id, Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleByPlateNumber(String plateNumber);
    void deleteVehicle(Long id);

    void saveVehicle(Vehicle vehicle);
}
