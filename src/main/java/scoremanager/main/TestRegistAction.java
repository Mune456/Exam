package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
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
        
        //Dao
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        StudentDao studentDao = new StudentDao();
        
        List<String> classNumSet = classNumDao.filter(school);
        List<Subject> subjectList = subjectDao.filter(school);
        
        
        //リクエストパラメーターの取得
        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subject");
        String noStr = req.getParameter("no");
        
        List<Student> studentlist = new ArrayList<>();
    	Subject selectSubject = null;

        //学生一覧取得
        if(entYearStr != null && classNum != null 
        		&& !entYearStr.isEmpty() && !classNum.isEmpty()) {
        	int entYear = Integer.parseInt(entYearStr);
        	studentlist = studentDao.filter(school, entYear, classNum, false);
        }
        
        
        if (subjectCd != null && !subjectCd.isEmpty()) {
        	for (Subject s : subjectList) {
        		if (s.getCd().equals(subjectCd)) {
                   selectSubject = s;
                    break;
               }
            }
        }
        
        // JSPへ渡す
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_list", subjectList);
        req.setAttribute("student_list", studentlist);
        req.setAttribute("no", noStr);
        req.setAttribute("subjectCd", subjectCd);
        req.setAttribute("subject", selectSubject);



        List<Integer> countList = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
        	countList.add(i);
        }
        req.setAttribute("count_list",countList);
        
        // フォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
