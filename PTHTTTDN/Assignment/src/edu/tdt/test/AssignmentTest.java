package edu.tdt.test;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import edu.tdt.Score;
import edu.tdt.ScorePK;
import edu.tdt.Student;
import edu.tdt.PersistentBean;
import edu.tdt.PersistentBeanRemote;
import edu.tdt.Subject;
import edu.tdt.Teacher;

import edu.tdt.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Kelvin
 */
public class AssignmentTest {
    
    private Properties props;
    private InitialContext ctx;
    
    public AssignmentTest()
    {
        readJNDI();
    }
    private void readJNDI()
    {
        props = new Properties();
        
        try
        {
            props.load(new FileInputStream("jndi.properties"));
        } catch(IOException e)
        {
            System.err.println(e.toString());
        }
        
        try
        {
            ctx = new InitialContext(props);
            //ctx.close();
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    private String getJNDI()
    {
        String appName = "";
        String moduleName = "AssignmentModule";
        String distinctName = "";
        String sessionBeanName = PersistentBean.class.getSimpleName();
        String viewClassName = PersistentBeanRemote.class.getName();
        return "ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+sessionBeanName+"!"+viewClassName;
    }
    private void showGUI()
    {
        System.out.println("\n=========================");
        System.out.println("SCORE MANAGEMENT FOR STUDENT");
        System.out.println("=========================");
        System.out.print("Options: \n1. View Score \n2. Log out \n3. Exit \nEnter Choice: ");
    }
    private void showGUITeacher()
    {
        System.out.println("\n=========================");
        System.out.println("SCORE MANAGEMENT FOR TEACHER");
        System.out.println("=========================");
        System.out.print("Options: \n1. View Score \n2. Score Management \n3. Log out \n4. Exit \nEnter Choice: ");
    }
    
    private void showGUIAdmin()
    {
        System.out.println("\n=========================");
        System.out.println("SCORE MANAGEMENT FOR ADMIN");
        System.out.println("=========================");
        System.out.print("Options: \n1. View Score \n2. Score Management \n3. Add user  \n4. Delete user \n5. Log out \n6. Exit \nEnter Choice: ");
    }
    
    private void showGUILogin()
    {
        System.out.println("\n=========================");
        System.out.println("         LOG IN");
        System.out.println("=========================");
    }
    
    private void showGUIManagementTeacher()
    {
        System.out.println("\n=========================");
        System.out.println("SCORE MANAGEMENT FOR TEACHER");
        System.out.println("=========================");
        System.out.print("Options: \n1. Add Score \n2. Update Score  \n3. Delete Score  \n4. Back \nEnter Choice: ");
    }
    private void showGUIManagementAdmin()
    {
        System.out.println("\n=========================");
        System.out.println("SCORE MANAGEMENT FOR ADMIN");
        System.out.println("=========================");
        System.out.print("Options: \n1. Add Score \n2. Update Score  \n3. Delete Score  \n4. Back \nEnter Choice: ");
    }
    
    
    public void testEntityAssignnment()
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            PersistentBeanRemote libBean = (PersistentBeanRemote) ctx.lookup(getJNDI());
            int check = 0, vitri = 0;
            String idS = null;
            while((check == 0) || (check == -1))
            {
                this.showGUILogin();
                System.out.print("\nUsername: ");
                String user = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();
                List<User> UsersList = libBean.getUsers();
                int i;
                for (i = 0; i < UsersList.size(); i++)
                {
                    if((UsersList.get(i).getUserName().equals(user)) && (UsersList.get(i).getUserPassword().equals(pass)))
                    {
                       if(UsersList.get(i).getUserRole().equals("student")) 
                       {
                           check = 1;
                           idS = user;
                       }
                       else if(UsersList.get(i).getUserRole().equals("admin")) 
                       {
                           check = 2;
                           vitri = i;
                           break;
                       }
                       else if(UsersList.get(i).getUserRole().equals("teacher")) 
                       {
                           check = 3;
                           vitri = i;
                           break;
                       }
                       else check = -1;
                    }
                }
            //student
            if(check == 1)
            {
                int choice = 0;
                while((choice != 2) && (choice != 3))
                {
                    this.showGUI();
                    choice = Integer.parseInt(sc.nextLine());    
                    if(choice == 1)
                    {
                        ViewScore(idS);
                    }
                }
                if (choice == 2) check = 0;
            }
            //admin
            else if(check == 2)
            {
                int choice = 0;
                while((choice != 5) && (choice != 6))
                {
                    this.showGUIAdmin();
                    choice = Integer.parseInt(sc.nextLine());    
                    if(choice == 1)
                    {
                        System.out.print("Student ID: ");
                        String id = sc.nextLine();
                        ViewScore(id);
                    }
                    else if(choice == 2)
                    {
                        //score management
                        int choice1 = 0;
                        while(choice1 != 4)
                        {
                            this.showGUIManagementAdmin();
                            choice1 = Integer.parseInt(sc.nextLine());
                            if(choice1 == 1)
                            {
                                //add score
                                System.out.print("Student_ID: ");
                                String student_id = sc.nextLine();
                                System.out.print("Subject_ID: ");
                                String subject_id = sc.nextLine();
                                System.out.print("Type Score: ");
                                String type = sc.nextLine();
                                System.out.print("Score: ");
                                String number = sc.nextLine();
                                
                                Score score = new Score();
                                ScorePK scorepk = new ScorePK();
                                scorepk.setStudentId(student_id);
                                scorepk.setSubjectId(subject_id);
                                scorepk.setScoreType(Integer.parseInt(type));
                                score.setScorePK(scorepk);
                                score.setScore(Float.parseFloat(number));
                                
                                
                                libBean.addScore(score);
                                
                                System.out.println("Added!");
                            }
                            else if(choice1 == 2)
                            {
                                //update score
                                System.out.print("Student_ID: ");
                                String student_id = sc.nextLine();
                                System.out.print("Subject_ID: ");
                                String subject_id = sc.nextLine();
                                System.out.print("Type Score: ");
                                String type = sc.nextLine();
                                System.out.print("Score: ");
                                String number = sc.nextLine();
                                if(libBean.updateScore(student_id, subject_id, type, number))
                                    System.out.println("Updated!");
                                else System.out.println("Update failed!");
                            }
                            
                            else if(choice1 == 3)
                            {
                                //delete score
                                System.out.print("Student_ID: ");
                                String student_id = sc.nextLine();
                                System.out.print("Subject_ID: ");
                                String subject_id = sc.nextLine();
                                System.out.print("Type Score: ");
                                String type = sc.nextLine();
                                if(libBean.deleteScore(student_id, subject_id, type))
                                    System.out.println("Deleted!");
                                else System.out.println("Delete failed!");
                            }
                        }  
                    }
                    else if(choice == 3)
                    {
                        //add user
                        System.out.print("user_ID: ");
                        String user_id = sc.nextLine();
                        System.out.print("user_password: ");
                        String user_password = sc.nextLine();
                        System.out.print("user_role: ");
                        String user_role = sc.nextLine();
                        if(libBean.addUser(user_id, user_password, user_role))
                            System.out.println("Add user success!");
                        else System.out.println("Add user failed!");
                    }
                    
                    else if(choice == 4)
                    {
                        //delete user
                        System.out.print("user_ID: ");
                        String user_id = sc.nextLine();
                        if(libBean.deleteUser(user_id))
                            System.out.println("Delete user success!");
                        else System.out.println("Delete user failed!");
                    }
                    else if(choice == 5) check = 0;
                }
            }
            else if(check == 3)
            {
                //teacher
                int choice = 0;
                while((choice != 3) && (choice != 4))
                {
                    this.showGUITeacher();
                    choice = Integer.parseInt(sc.nextLine());    
                    if(choice == 1)
                    {
                        System.out.print("Student ID: ");
                        String id = sc.nextLine();
                        ViewScore(id);
                    }
                    if(choice == 2)
                    {
                        //score management
                        int choice1 = 0;
                        while(choice1 != 4)
                        {
                            this.showGUIManagementTeacher();
                            choice1 = Integer.parseInt(sc.nextLine());
                            if(choice1 == 1)
                            {
                                //add score
                                System.out.print("Student_ID: ");
                                String student_id = sc.nextLine();
                                System.out.print("Subject_ID: ");
                                String subject_id = sc.nextLine();
                                System.out.print("Type Score: ");
                                String type = sc.nextLine();
                                System.out.print("Score: ");
                                String number = sc.nextLine();
                                
                                
                                Score score = new Score();
                                ScorePK scorepk = new ScorePK();
                                scorepk.setStudentId(student_id);
                                scorepk.setSubjectId(subject_id);
                                scorepk.setScoreType(Integer.parseInt(type));
                                score.setScorePK(scorepk);
                                score.setScore(Float.parseFloat(number));
                                
                                List<Teacher> TeachersList = libBean.getTeachers();
                                for (i = 0; i < TeachersList.size(); i++)
                                {
                                    if(TeachersList.get(i).getTeacherId().equals(UsersList.get(vitri).getUserName()))
                                    {
                                        if(TeachersList.get(i).getSubjectId().getSubjectId().equals(subject_id))
                                        {
                                            //System.out.println(UsersList.get(vitri).getUserName());
                                            libBean.addScore(score);
                                            System.out.println("Added!");
                                        }
                                        else System.out.println("Add failed!");
                                    }
                                }
                            }
                            else if(choice1 == 2)
                            {
                                //update score
                                System.out.print("Student_ID: ");
                                String student_id = sc.nextLine();
                                System.out.print("Subject_ID: ");
                                String subject_id = sc.nextLine();
                                System.out.print("Type Score: ");
                                String type = sc.nextLine();
                                System.out.print("Score: ");
                                String number = sc.nextLine();
                                
                                List<Teacher> TeachersList = libBean.getTeachers();
                                for (i = 0; i < TeachersList.size(); i++)
                                {
                                    if(TeachersList.get(i).getTeacherId().equals(UsersList.get(vitri).getUserName()))
                                    {
                                        if(TeachersList.get(i).getSubjectId().getSubjectId().equals(subject_id))
                                        {
                                            //System.out.println(UsersList.get(vitri).getUserName());
                                            if(libBean.updateScore(student_id, subject_id, type, number));
                                            System.out.println("Updated!");
                                        }
                                        else System.out.println("Update failed!");
                                    }
                                }
                            }
                            
                            else if(choice1 == 3)
                            {
                                //delete score
                                System.out.print("Student_ID: ");
                                String student_id = sc.nextLine();
                                System.out.print("Subject_ID: ");
                                String subject_id = sc.nextLine();
                                System.out.print("Type Score: ");
                                String type = sc.nextLine();
                                if(libBean.deleteScore(student_id, subject_id, type))
                                    System.out.println("Deleted!");
                                else System.out.println("Delete failed!");
                            }
                            
                        }  
                        
                    }
                    else if(choice == 3) check = 0;
                }
            }
        }
            sc.close();
            
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    private void ViewScore(String id)
    {
        float[][] array = new float [4][4];
        int i, j;
        for (i = 0; i < 4; i ++)
            for (j = 0; j < 4; j ++)
            {
                array[i][j] = -1;
            }
        try
        {
        PersistentBeanRemote libBean = (PersistentBeanRemote) ctx.lookup(getJNDI());
        List<Score> ScoresList = libBean.getScores();
        for (i = 0; i < ScoresList.size(); i++)
        {
            if(ScoresList.get(i).getScorePK().getStudentId().equals(id))
            {
                System.out.println(ScoresList.get(i).getScore());
                int a,b;
                
                a = ScoresList.get(i).getScorePK().getScoreType();
                b = Integer.parseInt(ScoresList.get(i).getScorePK().getSubjectId());
                array[b-1][a-1] = ScoresList.get(i).getScore();
            }        
        }
        
        System.out.println("\n=========================");
        System.out.println("Score of Student: " + id);
        System.out.println("=========================");
        System.out.println("             1    2    3    4");
        List<Subject> SubjectsList = libBean.getSubjects();
        for (i = 0; i < 4; i++)
        {
            for (j = 0; j < 4; j++)
            {
                if(i == 0 && j == 0)
                    System.out.print("Math        ");
                else if(i == 1 && j == 0)
                    System.out.print("Literature  ");
                else if(i == 2 && j == 0)
                    System.out.print("Chemistry   ");
                else if(i == 3 && j == 0)
                    System.out.print("Physical    ");
                if(array[i][j] > 0) System.out.print(array[i][j] + "  ");
                else System.out.print("     ");
            }
        System.out.println();
        }    
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    /*private void Statistic()
    {
        try
        {
        PersistentBeanRemote libBean = (PersistentBeanRemote) ctx.lookup(getJNDI());
        List<Student> StudentsList = libBean.getStudents();
        List<Score> ScoresList = libBean.getScores();
        List<Subject> SubjectsList = libBean.getSubjects();
        float[][] array = new float[StudentsList.size()][SubjectsList.size()];
        System.out.println(" Student_id       Class       Math     Literature    Chemistry    Physical");
        int i = 0, j = 0;
        
        for (j = 0; j < StudentsList.size()-1; j++)
        {
            for (i = 0; i < SubjectsList.size()-1; i++)
            {
                array[j][i] = -1f;
                System.out.print(array[j][i] + "  ");
            }
            System.out.println();
        }
        
        /*for (j = 0; j < StudentsList.size(); j++)
        {
            for (i = 0; i < ScoresList.size(); i++)
            {
                if(ScoresList.get(i).getScorePK().getStudentId().equals(StudentsList.get(j).getStudentId()))
                {
                    int a,b;
                    a = ScoresList.get(i).getScorePK().getScoreType();
                    b = Integer.parseInt(ScoresList.get(i).getScorePK().getSubjectId());
                    array[a-1][b-1] = ScoresList.get(i).getScore();
                }
            }
        }
        
        for (j = 0; j < StudentsList.size(); j++)
        {
            for (i = 0; i < ScoresList.size(); i++)
            {
                System.out.print(array[j][i]+ "  ");
            }
            System.out.println();
        }
        
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }*/
}