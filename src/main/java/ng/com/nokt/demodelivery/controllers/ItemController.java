package ng.com.nokt.demodelivery.controllers;

import ng.com.nokt.demodelivery.entites.Item;
import ng.com.nokt.demodelivery.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/add-item-form")
    public String showItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item_form"; // Map to Thymeleaf template name
    }

    @PostMapping("/create-item")
    public String createItem(@ModelAttribute Item item, Model model) {
        Item savedItem =itemService.createItem(item);
        return "success"; // Redirect to success page
    }
}
/*package ng.com.nokt.demodelivery.controllers;

import ng.com.nokt.demodelivery.entites.Item;
import ng.com.nokt.demodelivery.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/add-item-form")
    public String showItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item_form"; // Map to Thymeleaf template name
    }

    @PostMapping("/create-item")
    public String createItem(@ModelAttribute Item item, Model model) {
        Item savedItem =itemService.createItem(item);
        return "success"; // Redirect to success page
    }
}
*/
