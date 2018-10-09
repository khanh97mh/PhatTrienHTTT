package edu.tdt;

import java.util.List;
import javax.ejb.Remote;
@Remote
    public interface PersistentBeanRemote {

    void addStudent(Student studentName);
    void addScore(Score score);
    void addScorePK(ScorePK scorepk);
    Boolean updateScore(String student_id, String subject_id, String type, String number);
    List<Score> getScores();
    List<ScorePK> getScorePK();
    List<Student> getStudents();
    List<User> getUsers();
    List<Subject> getSubjects();
    List<Subject> searchById(String subject_id);
    List<Teacher> getTeachers();
    //List<Class> findClassID(class_id);
    Boolean deleteScore(String student_id, String subject_id, String type);
    Boolean addUser(String user_id, String user_password, String user_role);
    Boolean deleteUser(String user_id);
}
