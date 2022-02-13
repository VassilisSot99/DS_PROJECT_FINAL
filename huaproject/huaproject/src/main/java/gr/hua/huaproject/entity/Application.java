package gr.hua.huaproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "undergr_prgm")
    private String undergrPrgm;

    @Column(name = "gpa")
    private int gpa;

    @Column(name = "teacher1")
    private String teacher1;

    @Column(name = "teacher2")
    private String teacher2;

    @Column(name = "checked")
    private String checked;

    @Column(name = "evaluated")
    private String evaluated;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="course_id")
    private Course course;

    @Transient
    private int tempCourseId;

    public Application() {
        super();
    }

    public Application(String undergrPrgm,int gpa,String teacher1,String teacher2, int courseid) {
        super();
        this.undergrPrgm=undergrPrgm;
        this.gpa = gpa;
        this.teacher1=teacher1;
        this.teacher2=teacher2;
        this.checked = "no";
        this.evaluated = "no";
        //helpers
        this.tempCourseId = courseid;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUndergrPrgm() {
        return undergrPrgm;
    }

    public void setUndergrPrgm(String undergrPrgm) {
        this.undergrPrgm = undergrPrgm;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public String getTeacher1() {
        return teacher1;
    }

    public void setTeacher1(String teacher1) {
        this.teacher1 = teacher1;
    }

    public String getTeacher2() {
        return teacher2;
    }

    public void setTeacher2(String teacher2) {
        this.teacher2 = teacher2;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String promoted) {
        this.checked = promoted;
    }

    public String getEvaluated() {
        return evaluated;
    }

    public void setEvaluated(String evaluated) {
        this.evaluated = evaluated;
    }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTempCourseId() {
        return tempCourseId;
    }

    public void setTempCourseId(int tempCourseId) {
        this.tempCourseId = tempCourseId;
    }

    @Override
    public String toString() {
        return "application{" +
                "id=" + id +
                ", undergr_prgm='" + undergrPrgm + '\'' +
                ", gpa=" + gpa +
                ", teacher1='" + teacher1 + '\'' +
                ", teacher2='" + teacher2 + '\'' +
                ", course='" + course + '\'' +
                ", promoted=" + checked +
                ", evaluated=" + evaluated +
                '}';
    }
}