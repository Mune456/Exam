package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(
            int entYear,
            String classNum,
            Subject subject,
            School school) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql =
                "select student.ent_year, student.no as student_no, student.name as student_name, student.class_num, " +
                "test.no as test_no, test.point " +
                "from student " +
                "left join test " +
                "on student.no = test.student_no " +
                "and test.subject_cd = ? " +
                "and test.class_num = student.class_num " +
                "where student.school_cd = ? " +
                "and student.ent_year = ? " +
                "and student.class_num = ? " +
                "order by student.no, test.no";

            st = con.prepareStatement(sql);

            // 点数テーブル用
            st.setString(1, subject.getCd());

            // 学生テーブル用
            st.setString(2, school.getCd());
            st.setInt(3, entYear);
            st.setString(4, classNum);

            rs = st.executeQuery();
            System.out.println("====== SQL 結果 ======");

            Map<String, TestListSubject> map = new LinkedHashMap<>();

            while (rs.next()) {

                // ✅ ログはここに入れる（1つのwhile内）
                System.out.println(
                    "student_no=" + rs.getString("student_no")
                    + ", test_no=" + rs.getObject("test_no")
                    + ", point=" + rs.getObject("point")
                );

                String studentNo = rs.getString("student_no");

                TestListSubject t = map.get(studentNo);

                if (t == null) {
                    t = new TestListSubject();
                    t.setEntYear(rs.getInt("ent_year"));
                    t.setStudentNo(studentNo);
                    t.setStudentName(rs.getString("student_name"));
                    t.setClassNum(rs.getString("class_num"));
                    t.setPoints(new HashMap<>());
                    map.put(studentNo, t);
                }

                Integer testObject = (Integer) rs.getObject("test_no");
                Integer pointObject = (Integer) rs.getObject("point");

                if (testObject != null) {
                    t.getPoints().put(testObject, pointObject);
                }
            }
            list = new ArrayList<>(map.values());

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return list;
    }
}