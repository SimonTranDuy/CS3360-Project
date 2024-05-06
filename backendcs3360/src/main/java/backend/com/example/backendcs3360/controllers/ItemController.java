package backend.com.example.backendcs3360.controllers;

import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.models.Accessories;
import backend.com.example.backendcs3360.models.Clothes;
import backend.com.example.backendcs3360.models.ResponseObject;
import backend.com.example.backendcs3360.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ItemController {
    private ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllProducts(){
        List<ItemDTO> productList = itemService.getAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Query product successfully", productList)
        );
    }

//    @PostMapping("/post-accessories")
//    public ResponseEntity<ResponseObject> postNewAccessories(@RequestBody Accessories item){
//        List<ItemDTO> productList = itemService.getAllItems();
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("success", "Query product successfully", productList)
//        );
//    }
    @PostMapping("/post-clothes")
    public ResponseEntity<ResponseObject> postNewClothes(@RequestBody Clothes item){
        itemService.insertNewClothes(item);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert new product successfully", null)
        );
    }
}