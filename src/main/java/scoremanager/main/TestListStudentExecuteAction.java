package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ取得
        String studentNo = req.getParameter("student_no");

        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (studentNo == null || studentNo.isEmpty()) {
            errors.put("student_no", "学生番号を入力してください");

            req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // Student生成
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);
        student.setSchool(school);
        
        // DAO
        TestListStudentDao dao = new TestListStudentDao();

        List<TestListStudent> testList = dao.filter(student);

        // データがない場合
        if (testList.isEmpty()) {
            errors.put("no_data", "成績情報が存在しませんでした");

            req.setAttribute("errors", errors);
            req.setAttribute("student", student);

            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // JSPへ渡す
        req.setAttribute("test_list", testList);
        req.setAttribute("student", student);

        // フォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}