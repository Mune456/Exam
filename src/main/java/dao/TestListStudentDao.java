package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	private String baseSql = "select no, point from test where school_cd = ? and student_no = ? order by no";

	public List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
    	
        List<TestListStudent> list = new ArrayList<>();

        while (rSet.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setNum(rSet.getInt("no"));
            testListStudent.setPoint(rSet.getInt("point"));
            list.add(testListStudent);
        }
        return list;
    }
	
	public List<TestListStudent> filter(Student student) throws Exception{
	
		List<TestListStudent> list = new ArrayList<>();
	
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		ResultSet resultSet = null;
			
		try {
			statement = connection.prepareStatement(baseSql);
			statement.setString(1, student.getSchool().getCd());
			statement.setString(2, student.getNo());
			resultSet = statement.executeQuery();
			list = postFilter(resultSet);
		}catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
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
}