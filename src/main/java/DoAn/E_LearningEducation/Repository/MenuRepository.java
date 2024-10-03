package DoAn.E_LearningEducation.Repository;

import DoAn.E_LearningEducation.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    boolean existsByTitle(String title);
    List<Menu> findAllByOrderByLevelAscPositionAsc();
}
