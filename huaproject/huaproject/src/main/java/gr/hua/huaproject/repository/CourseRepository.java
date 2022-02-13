package gr.hua.huaproject.repository;

import gr.hua.huaproject.entity.Application;
import gr.hua.huaproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
