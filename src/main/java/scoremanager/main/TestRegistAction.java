package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        
        // ローカル変数
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        //入学年度
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
        	entYearSet.add(i);
        }
        
        //クラス科目
        ClassNumDao classNumDao = new ClassNumDao();
//        SubjectDao subjectDao = new SubjectDao();
        StudentDao studentDao = new StudentDao();
        
        List<String> classNumSet = classNumDao.filter(school);
//        List<Subject> subjectList = subjectDao.filter(school);
        
        //リクエストパラメータ―の取得
        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
//        String subjectCd = req.getParameter("subject");
        
        List<Student> studentlist = null;
        
        //学生一覧取得
        if(entYearStr != null && classNum != null && !entYearStr.isEmpty() && !classNum.isEmpty()) {
        	int entYear = Integer.parseInt(entYearStr);
        	studentlist = studentDao.filter(school, entYear, classNum, false);
        }
        
        // JSP へ渡す
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
//        req.setAttribute("subject_list", subjectList);
        req.setAttribute("student_list", studentlist);

        // フォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
