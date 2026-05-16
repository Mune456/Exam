package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "select * from test where student_no = ? and subject_cd = ? and school_cd = ? and no = ?";
	
    public Test get(Student student,Subject subject, School school,int no) throws Exception {
    	
    	Test test= null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, student.getSchool().getCd());            
            statement.setInt(4, no);
            rSet = statement.executeQuery();

            if (rSet.next()) {
            	test = new Test();
            	test.setStudent(student);
            	test.setSubject(subject);
                test.setSchool(student.getSchool());
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
            } 
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return test;
    }
    
    
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        
    	List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
            	Test test=new Test();
            	// student作成
            	Student student = new Student();
            	student.setNo(rSet.getString("student_no"));
                
                //Subject作成
            	Subject subject = new Subject();
            	subject.setCd(rSet.getString("subject_cd"));
            	
                test.setStudent(student);
                test.setSubject(subject);
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setSchool(school);
                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public List<Test> filter(int entYear,String classNum, Subject subject, int num, School school) throws Exception{

    	List<Test> list = new ArrayList<>();
    	
    	Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        
        try {
        	String sql =
        			"select t.* from test t " +
        			"join student s on t.student_no = s.no " +
        			"where s.ent_year=? and s.class_num=? and t.subject_cd=? and t.no=? and t.school_cd=?";
        	statement = connection.prepareStatement(sql);
        	statement.setInt(1, entYear);
        	statement.setString(2, classNum);
        	statement.setString(3, subject.getCd());
        	statement.setInt(4, num);
        	statement.setString(5, school.getCd());

        	rSet = statement.executeQuery();
        	list = postFilter(rSet,school);
        	
        }finally {
        	if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
        	
        }
    
    
    public boolean save(List<Test> list) throws Exception{
    	
    	Connection connection = getConnection();
    	boolean result = false;
    	
        try {

        	for(Test test:list) {
        		save(test, connection);
        	}
        	result = true;
        	
        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return result;
    }
    
    
    private boolean save(Test test, Connection connection) throws Exception {
    	
    	PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(
               "update test set point = ? where student_no = ? and subject_cd = ? and no = ? and school_cd = ?"
            );

            statement.setInt(1, test.getPoint());
            statement.setString(2, test.getStudent().getNo());
            statement.setString(3, test.getSubject().getCd());
            statement.setInt(4, test.getNo());
            statement.setString(5, test.getSchool().getCd());
            
            count = statement.executeUpdate();
            
            statement.close();
            
            if (count == 0) {
                statement = connection.prepareStatement(
                    "insert into test(student_no, subject_cd, school_cd, no, point, class_num) values (?, ?, ?, ?, ?, ?)"
                );
                
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getStudent().getClassNum());
                
                count = statement.executeUpdate();
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return count > 0;
    }

}
