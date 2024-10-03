package DoAn.E_LearningEducation.Mapper;

import DoAn.E_LearningEducation.Dto.request.MenuCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateMenuRequest;
import DoAn.E_LearningEducation.Dto.response.MenuResponse;
import DoAn.E_LearningEducation.Model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    Menu toMenu(MenuCreationRequest request);
    MenuResponse toMenuResponse(Menu menu);
    void updateMenu(@MappingTarget Menu menu, UpdateMenuRequest request);
}
