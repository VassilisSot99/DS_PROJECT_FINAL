package gr.hua.huaproject.repository;

import gr.hua.huaproject.entity.Application;
import gr.hua.huaproject.entity.Student;
import org.hibernate.query.Query;

import java.util.List;

public interface ApplicationRepositoryCustom {

    public List<Application> getApplicationsToBeChecked();
    public List<Application> getApplicationsToBeEvaluated();
    public void checkApplication(int id, String checked);
    public void evaluateApplication(int id, String evaluated);
    public Student findCurrentlyLoggedInStudent();
    public List<Application> getApplicationsForOneStudentId();

}
