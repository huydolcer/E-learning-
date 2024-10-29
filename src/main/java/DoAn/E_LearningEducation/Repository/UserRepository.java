package DoAn.E_LearningEducation.Repository;

import DoAn.E_LearningEducation.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String userName);
    boolean existsByUserID(String id);

    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :username, u.role = :role, u.firstname = :firstname, u.lastname = :lastname, u.email = :email, u.address = :address WHERE u.userID = :userId")
    void updateUserWithoutPassword(@Param("userId") String userId,
                                   @Param("username") String username,
                                   @Param("role") String role,
                                   @Param("firstname") String firstname,
                                   @Param("lastname") String lastname,
                                   @Param("email") String email,
                                   @Param("address") String address);

}

