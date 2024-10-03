package DoAn.E_LearningEducation.Repository;

import DoAn.E_LearningEducation.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    boolean existsByTitle(String title);
    List<Blog> findAllByOrderByBlogIDDesc();
    boolean existsByBlogID(int blogId);

}
