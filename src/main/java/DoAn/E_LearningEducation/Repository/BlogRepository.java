package DoAn.E_LearningEducation.Repository;

import DoAn.E_LearningEducation.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    @Query("SELECT b FROM Blog b INNER JOIN b.category c INNER JOIN b.user u ORDER BY b.blogID DESC")
    List<Blog> findAllBlogsWithCategoryAndUser();
    boolean existsByTitle(String title);
    //List<Blog> findAllByOrderByBlogIDDesc();
    boolean existsByBlogID(int blogId);
    @Query("SELECT b FROM Blog b INNER JOIN b.category c INNER JOIN b.user u WHERE b.active = 1 ORDER BY b.blogID DESC")
    List<Blog> findAllBlogWithActive();

}
