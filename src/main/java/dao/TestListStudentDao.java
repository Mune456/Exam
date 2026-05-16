package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private String baseSql = "select test.subject_cd, subject.name as subject_name, test.no, test.point " +
            "from test " +
            "join subject on test.subject_cd = subject.cd " +
            "where test.student_no = ? and test.school_cd = ?";

    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        while (rSet.next()) {

            TestListStudent testListStudent = new TestListStudent();

            testListStudent.setSubjectCd(rSet.getString("subject_cd"));
            testListStudent.setSubjectName(rSet.getString("subject_name"));
            testListStudent.setNum(rSet.getInt("no"));
            testListStudent.setPoint(rSet.getInt("point"));

            list.add(testListStudent);
        }

        return list;
    }
    public List<TestListStudent> filter(Student student) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);

            statement.setString(1, student.getNo());
            statement.setString(2, student.getSchool().getCd());

            rSet =  statement.executeQuery();

            list = postFilter(rSet);

        } finally {
            if (statement != null)  statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }
    
}
