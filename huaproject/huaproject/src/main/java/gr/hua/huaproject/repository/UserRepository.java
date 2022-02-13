package gr.hua.huaproject.repository;

import gr.hua.huaproject.entity.Course;
import gr.hua.huaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
