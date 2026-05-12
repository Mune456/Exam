package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
//import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	
	private String baseSql="";
	
	private List<TestListSubject> postFilter(ResultSet rSet){
		
		List<TestListSubject> list = new ArrayList<>();
		
		
		try {
			while (rSet.next()) {
				TestListSubject testListSubject = new TestListSubject();
				
				testListSubject.setEntYear(rSet.getInt("ent_year"));
				testListSubject.setStudentNo(rSet.getString("student_no"));
				testListSubject.setStudentName(rSet.getString("student_name"));
				testListSubject.setClassNum(rSet.getString("class_num"));
				testListSubject.setPoints(new HashMap<>());
				
				// リストに学生情報を追加
				list.add(testListSubject);
			}
		}catch(SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception{

		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		ResultSet resultSet = null;
			
		baseSql = "" ;
				
		try {
			
			baseSql += "select " +
					"student.ent_year, " +
					"student.no as student_no, " +
					"student.name as student_name, " +
					"student.class_num " +
					"from student " +
					"where student.school_cd = ? " +
					"and student.ent_year = ? " +
					"and student.class_num = ? ";
			
			// プリペアードステートメントにSQL文をセット
			statement = connection
					.prepareStatement(baseSql);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			
			// プリペアードステートメントを実行
			resultSet = statement.executeQuery();
			list = postFilter(resultSet);
				// リザルトセットを全件走査
			} catch (Exception e) {
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

