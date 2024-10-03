package DoAn.E_LearningEducation.Controller;


import DoAn.E_LearningEducation.Dto.request.MenuCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateMenuRequest;
import DoAn.E_LearningEducation.Model.Menu;
import DoAn.E_LearningEducation.Service.MenuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuController {

    MenuService menuService;
    @GetMapping("/menus")
    List<Menu> getAllmenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/getAllMenuByAdmin")
    List<Menu> getAllMenuByAdmin(){
        return menuService.getMenuByAdmin();
    }

    @PostMapping("/create_menu")
    ResponseEntity<String> createMenu(@RequestBody MenuCreationRequest request){
        menuService.createMenu(request);
        return ResponseEntity.ok("Menu creation successfully!");
    }


    @GetMapping("/update_menus")
    Menu queryMenuByID(@RequestParam("id") int ID){
        return menuService.getMenuByID(ID);
    }
    @PutMapping("/update_menuById")
    ResponseEntity<String> updateMenuByID(@RequestParam("id") int ID, @RequestBody UpdateMenuRequest request){
        menuService.updateMenu(ID, request);
        return ResponseEntity.ok("Menu update successfully!");
    }
    @DeleteMapping("/delete_menu")
    ResponseEntity<String> deleteMenu(@RequestParam("id") int Id){
        menuService.deleteMenus(Id);
        return ResponseEntity.ok("Menu delete successfully!");
    }

}
