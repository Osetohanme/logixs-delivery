package ng.com.nokt.demodelivery.services;

import ng.com.nokt.demodelivery.entites.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item item);
    Item getItemById(Long id);
    List<Item> getAllItems();
    void deleteItem(Long id);

    void saveItem(Item item);
}
