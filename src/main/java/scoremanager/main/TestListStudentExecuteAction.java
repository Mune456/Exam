package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	    // セッション取得
	    Teacher teacher = (Teacher) req.getSession().getAttribute("user");
	    if (teacher == null) {
	        req.getRequestDispatcher("/test_list.jsp").forward(req, res);
	        return;
	    }

	    School school = teacher.getSchool();

	    // 検索条件取得
	    String entYearStr = req.getParameter("f1");
	    String classNum = req.getParameter("f2");
	    String subjectCd = req.getParameter("f3");
	    String countStr = req.getParameter("f4");

	    int entYear = 0;
	    int count = 0;

	    if (entYearStr != null && !entYearStr.equals("0")) {
	        entYear = Integer.parseInt(entYearStr);
	    }
	    if (countStr != null && !countStr.equals("0")) {
	        count = Integer.parseInt(countStr);
	    }

	    // DAO呼び出し
	    TestListStudentDao dao = new TestListStudentDao();
	    Student student = new Student();
	    student.setSchool(school);
	    String studentNo = req.getParameter("studentNo");
	    student.setNo(studentNo);
	    List<TestListStudent> list = dao.filter(student);

	    // JSP に渡す
	    req.setAttribute("list", list);
	    req.setAttribute("entYear", entYear);
	    req.setAttribute("classNum", classNum);
	    req.setAttribute("subjectCd", subjectCd);
	    req.setAttribute("count", count);

	    req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}