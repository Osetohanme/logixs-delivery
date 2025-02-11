package ng.com.nokt.demodelivery.controllers;

import ng.com.nokt.demodelivery.entites.Item;
import ng.com.nokt.demodelivery.entites.Vehicle;
import ng.com.nokt.demodelivery.services.ItemService;
import ng.com.nokt.demodelivery.services.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final ItemService itemService;
    private final VehicleService vehicleService;

    public VehicleController(ItemService itemService, VehicleService vehicleService) {
        this.itemService = itemService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/add-vehicle-form")
    public String showVehicleForm(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Item> items = itemService.getAllItems();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("items", items);
        return "vehicle_form"; // Name of the Thymeleaf template
    }

    @PostMapping("/create-vehicle")
    public String createVehicle(@ModelAttribute Vehicle vehicle, Model model) {
        Vehicle newVehicle = (Vehicle) vehicleService.createVehicle(vehicle);
        model.addAttribute("vehicle", newVehicle);
        model.addAttribute("successMessage","Vehicle created successfully");
        return "success"; // Redirect after successful submission
    }

    @PostMapping("/create-item")
    public String createItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "/success";
    }

    @GetMapping("/assign-item")
    public String showAssignItemPage(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Item> items = itemService.getAllItems();

        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("items", itemService.getAllItems());
        return "assign_item";
    }

    @PostMapping("/assign-item")
    public String assignItemToVehicle(@RequestParam String vehiclePlate, @RequestParam Long itemId, Model model) {
        System.out.println("🚀 Assigning item to vehicle: Vehicle Plate = " + vehiclePlate + ", Item ID = " + itemId);

        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(vehiclePlate);
        Item item = itemService.getItemById(itemId);

        if (vehicle == null || item == null) {
            System.out.println("❌ Vehicle or item not found!");
            model.addAttribute("errorMessage", "Vehicle or item does not exist");
            return showAssignItemPage(model);
        }

        // Calculate the remaining weight
        float currentWeight = (float) vehicle.getItems().stream().mapToDouble(Item::getWeight).sum();
        float newWeight = currentWeight + item.getWeight();

        if (newWeight <= vehicle.getCarryingWeight()) {
            vehicle.getItems().add(item);
            vehicleService.saveVehicle(vehicle);

            // Generate a unique code for the assignment
            String uniqueCode = "V" + vehicle.getId() + "I" + item.getId() + System.currentTimeMillis();

            System.out.println("✅ Item assigned successfully! Code: " + uniqueCode);

            model.addAttribute("successMessage", "Item assigned successfully! Code: " + uniqueCode);
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("item", item);
        } else {
            System.out.println("❌ Vehicle cannot carry this much weight!");
            model.addAttribute("errorMessage", "Error: Vehicle cannot carry this much weight!");
        }

        return showAssignItemPage(model);
    }
}
