package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.MenuCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateMenuRequest;
import DoAn.E_LearningEducation.Dto.response.MenuResponse;
import DoAn.E_LearningEducation.Mapper.MenuMapper;
import DoAn.E_LearningEducation.Model.Menu;
import DoAn.E_LearningEducation.Repository.MenuRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuService {
    MenuMapper menuMapper;
    MenuRepository menuRepository;

    public Menu createMenu(MenuCreationRequest request) {

        Menu menu = menuMapper.toMenu(request);
        if (menuRepository.existsByTitle(menu.getTitle())) {
            throw new RuntimeException("Title da ton tai");
        }
        Menu savedMenu = menuRepository.save(menu);
        System.out.println("Saved Menu: " + savedMenu); // In ra menu đã lưu
        return savedMenu;
    }


    public List<Menu> getAllMenus(){
        List<Menu> getAllLists = menuRepository.findAllByOrderByLevelAscPositionAsc();
        List<Menu> result = new ArrayList<>();
        Map<Integer,Menu> parentMap = new HashMap<>();

        for(Menu menu : getAllLists) {
            if (menu.getLevel() == 1 && menu.getActive() == 1) {
                parentMap.put(menu.getMenuID(), menu);
                result.add(menu);
            }else if(menu.getLevel() == 2
                    && parentMap.containsKey(menu.getParentID())
                    && menu.getActive() == 1){

                Menu parentMenu = parentMap.get(menu.getParentID());
                if(parentMenu.getSubMenus() == null){
                    parentMenu.setSubMenus(new ArrayList<>());
                }
                parentMenu.getSubMenus().add(menu);
            }
        }
        return result;
    }
    public void deleteMenus(int menuID){
        menuRepository.deleteById(menuID);
    }
    public MenuResponse updateMenu(int menuID, UpdateMenuRequest request){

        Menu menu = menuRepository.findById(menuID).orElseThrow(()->
                new RuntimeException("Menu not exist"));

        menuMapper.updateMenu(menu, request);
        return menuMapper.toMenuResponse(menuRepository.save(menu));
    }

    public List<Menu> getMenuByAdmin(){
        return menuRepository.findAll();
    }
    public Menu getMenuByID(int ID){
        return menuRepository.findById(ID).orElseThrow(()->
                new RuntimeException("MenuID not exist"));
    }

}
