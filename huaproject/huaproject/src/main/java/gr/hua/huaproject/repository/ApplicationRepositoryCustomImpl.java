package gr.hua.huaproject.repository;

import gr.hua.huaproject.entity.Application;
import gr.hua.huaproject.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom{

    private EntityManager entityManager;

    @Autowired
    public ApplicationRepositoryCustomImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Application> getApplicationsToBeChecked() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Application> theQuery = currentSession.createQuery("from Application app where app.checked='null'", Application.class);

        List<Application> applicationsToBeChecked = theQuery.getResultList();

        return applicationsToBeChecked;
    }

    @Override
    @Transactional
    public List<Application> getApplicationsToBeEvaluated() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Application> theQuery = currentSession.createQuery("from Application app where " +
                "app.checked='true'" + " AND app.evaluated='null' ", Application.class);

        List<Application> applicationsToBeEvaluated = theQuery.getResultList();

        return applicationsToBeEvaluated;
    }

    @Override
    @Transactional
    public void checkApplication(int id, String checked) {

        Session currentSession = entityManager.unwrap(Session.class);
        Application application = currentSession.get(Application.class, id);
        application.setChecked(checked);

        currentSession.save(application);
    }

    @Override
    @Transactional
    public void evaluateApplication(int id, String evaluated) {
        Session currentSession = entityManager.unwrap(Session.class);
        Application application = currentSession.get(Application.class, id);
        application.setEvaluated(evaluated);

        currentSession.save(application);
    }

    @Override
    @Transactional
    public Student findCurrentlyLoggedInStudent(){
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();

        String username = ((UserDetails)principal).getUsername();

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Student> theQuery = currentSession.createQuery("from Student student where " +
                "student.email='"+ username +"'", Student.class);

        Student st = theQuery.getSingleResult();

        return st;
    }

    @Override
    @Transactional
    public List<Application> getApplicationsForOneStudentId() {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();

        String username = ((UserDetails)principal).getUsername();

        Session currentSession = entityManager.unwrap(Session.class);
        Student student = findCurrentlyLoggedInStudent();
        int id = student.getId();
        Query<Application> theQuery = currentSession.createQuery("from Application app where app.student.id="+id, Application.class);

        List<Application> myApplications = theQuery.getResultList();

        return myApplications;
    }




}
