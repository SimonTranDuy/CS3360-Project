package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.ClothesDTO;
import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.models.Accessories;
import backend.com.example.backendcs3360.models.Clothes;
import backend.com.example.backendcs3360.models.Item;
import backend.com.example.backendcs3360.repositories.AccessoriesRepository;
import backend.com.example.backendcs3360.repositories.ClothesRepository;
import backend.com.example.backendcs3360.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private ClothesRepository clothesRepository;
    private AccessoriesRepository accessoriesRepository;

    public ItemService(ItemRepository itemRepository, ClothesRepository clothesRepository, AccessoriesRepository accessoriesRepository) {
        this.itemRepository = itemRepository;
        this.clothesRepository = clothesRepository;
        this.accessoriesRepository = accessoriesRepository;
    }

    @Autowired

    public List<ItemDTO> getAllItems(){
        List<ItemDTO> itemFromDB = itemRepository.findAll();
//        List<Item> itemToSend = itemFromDB.stream().map(Item::)
        return itemFromDB;
    }
    public ClothesDTO insertNewClothes(Clothes newClothes){
        ClothesDTO newDTO = newClothes.convertToDTO();
        return clothesRepository.save(newDTO);
    }
}
