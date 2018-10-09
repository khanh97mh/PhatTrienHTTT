package edu.tdt;
import java.util.List;
import java.util.Scanner;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
    public class PersistentBean implements PersistentBeanRemote {

    @PersistenceContext(unitName = "AssignmentModulePU")
    private EntityManager entityManager;

    public PersistentBean() {

    }
    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }
    @Override
    public void addScore(Score score) {
        entityManager.persist(score);
    }
    @Override
    public void addScorePK(ScorePK scorepk) {
        entityManager.persist(scorepk);
    }
    
    @Override
    public List<Student> getStudents() {
        return entityManager.createNamedQuery("Student.findAll").getResultList();
    }
    
    @Override
    public List<Teacher> getTeachers() {
        return entityManager.createNamedQuery("Teacher.findAll").getResultList();
    }
    /*public List<Class> findClassID(Class class_id){
        return entityManager.createNamedQuery("Class.findByClassId").setParameter("class_id", class_id).getResultList();
    }*/
    
    @Override
    public List<Score> getScores() {
        return entityManager.createNamedQuery("Score.findAll").getResultList();
    }
    @Override
    public List<ScorePK> getScorePK() {
        return entityManager.createNamedQuery("ScorePK.findALL").getResultList();
    }
    @Override
    public List<User> getUsers() {
        return entityManager.createNamedQuery("User.findAll").getResultList();
    }
    @Override
    public List<Subject> getSubjects() {
        return entityManager.createNamedQuery("Subject.findAll").getResultList();
    }
    
    @Override
    public List<Subject> searchById(String subject_id) {
        return entityManager.createNamedQuery("Subject.findBySubjectId").setParameter("subjectId",subject_id).getResultList();
    }

    @Override
    public Boolean updateScore(String student_id, String subject_id, String type, String number) {
        try{
            List<Score> ScoresList = entityManager.createNamedQuery("Score.findAll").getResultList();
            if(ScoresList == null) return false;
            int i = 0;
            for(i = 0; i < ScoresList.size(); i++)
            {
                if((ScoresList.get(i).scorePK.getStudentId().equals(student_id))
                    && (ScoresList.get(i).scorePK.getSubjectId().equals(subject_id))
                       && (ScoresList.get(i).scorePK.getScoreType() == Integer.parseInt(type)))
                {
                    ScoresList.get(i).setScore(Float.parseFloat(number));
                }
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    @Override
    public Boolean deleteScore(String student_id, String subject_id, String type) {
        try{
            List<Score> ScoresList = entityManager.createNamedQuery("Score.findAll").getResultList();
            if(ScoresList == null) return false;
            int i = 0;
            for(i = 0; i < ScoresList.size(); i++)
            {
               if((ScoresList.get(i).scorePK.getStudentId().equals(student_id))
                    && (ScoresList.get(i).scorePK.getSubjectId().equals(subject_id))
                       && (ScoresList.get(i).scorePK.getScoreType() == Integer.parseInt(type)))
               {
                   entityManager.remove(ScoresList.get(i));
               }
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    @Override
    public Boolean addUser(String user_id, String user_password, String user_role) {
        try{
            User user = new User();
            user.setUserName(user_id);
            user.setUserPassword(user_password);
            user.setUserRole(user_role);
            entityManager.persist(user);
            /*if(user_role.equals("student"))
            {
                Scanner sc = new Scanner(System.in);
                System.out.print("Class_id: ");
                String class_id = sc.nextLine();
                Student student = new Student();
                student.setClassId(findClassID(class_id));
                
            }*/
            /*if(user_role.equals(teacher))
            {
                Teacher teacher = new Teacher();
                Subject subject = new Subject();
                teacher.setTeacherId(user_id);
                Scanner sc = new Scanner(System.in);
                System.out.print("Subject_ID: ");
                String s = sc.nextLine();
                subject.setSubjectId(s);
                entityManager.persist(teacher);
                return true;
            }*/
            
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    @Override
    public Boolean deleteUser(String user_id) {
        try{
            List<User> UsersList = entityManager.createNamedQuery("User.findAll").getResultList();
            if(UsersList == null) return false;
            int i = 0;
            for(i = 0; i < UsersList.size(); i++)
            {
                if(UsersList.get(i).getUserName().equals(user_id))
                    entityManager.remove(UsersList.get(i));
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}