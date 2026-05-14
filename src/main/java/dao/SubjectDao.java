package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
 
public class SubjectDao extends Dao {
 
 
    public Subject get(String cd, School school) throws Exception {
 
        Subject subject = null;
 
        Connection con = getConnection();
 
        String sql =
 
            "SELECT * FROM subject WHERE cd = ? AND school_cd = ?";
 
        PreparedStatement st = con.prepareStatement(sql);
 
        st.setString(1, cd);
 
        st.setString(2, school.getCd());
 
        ResultSet rs = st.executeQuery();
 
        if (rs.next()) {
 
            subject = new Subject();
 
            subject.setCd(rs.getString("cd"));
 
            subject.setName(rs.getString("name"));
 
            subject.setSchool(school);
 
        }
 
        st.close();
 
        con.close();
 
        return subject;
 
    }
 
    

    public List<Subject> filter(School school) throws Exception {
 
        List<Subject> list = new ArrayList<>();
 
        Connection con = getConnection();
 
        String sql =
 
            "SELECT * FROM subject WHERE school_cd = ?";
 
        PreparedStatement st = con.prepareStatement(sql);
 
        st.setString(1, school.getCd());
 
        ResultSet rs = st.executeQuery();
 
        while (rs.next()) {
 
            Subject subject = new Subject();
 
            subject.setCd(rs.getString("cd"));
 
            subject.setName(rs.getString("name"));
 
            subject.setSchool(school);
 
            list.add(subject);
 
        }
 
        st.close();
 
        con.close();
 
        return list;
 
    }
 
 
    public boolean save(Subject subject) throws Exception {
 
        Connection con = getConnection();
 
        String sql =
 
            "INSERT INTO subject(cd, name, school_cd) VALUES(?, ?, ?)";
 
        PreparedStatement st = con.prepareStatement(sql);
 
        st.setString(1, subject.getCd());
 
        st.setString(2, subject.getName());
 
        st.setString(3, subject.getSchool().getCd());
 
        int line = st.executeUpdate();
 
        st.close();
 
        con.close();
 
        return line > 0;
    }
 
 
    public boolean delete(Subject subject) throws Exception {
 
        Connection con = getConnection();
 
        String sql =
 
            "DELETE FROM subject WHERE cd = ?";
 
        PreparedStatement st = con.prepareStatement(sql);
 
        st.setString(1, subject.getCd());
 
        int line = st.executeUpdate();
 
        st.close();
 
        con.close();
 
        return line > 0;
    }
}
 