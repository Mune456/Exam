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
                "and test.school_cd = ? " +
                "where student.school_cd = ? " +
                "and student.ent_year = ? " +
                "and student.class_num = ? " +
                "order by student.no, test.no";

            st = con.prepareStatement(sql);

            // 点数テーブル用
            st.setString(1, subject.getCd());
            st.setString(2, school.getCd());

            // 学生テーブル用
            st.setString(3, school.getCd());
            st.setInt(4, entYear);
            st.setString(5, classNum);

            rs = st.executeQuery();

            // 学生ごとにまとめる
            Map<String, TestListSubject> map = new LinkedHashMap<>();

            while (rs.next()) {

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

                // 点数セット
                int testNo = rs.getInt("test_no");
                int point = rs.getInt("point");

                if (testNo != 0) {
                    t.getPoints().put(testNo, point);
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