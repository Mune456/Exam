package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	
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
	    String entYearStr = req.getParameter("entYear");
	    String classNum = req.getParameter("classNum");
	    String subjectCd = req.getParameter("subject");
	    String noStr = req.getParameter("no");
	    Map<String, String> errors = new HashMap<>();

	    
	    if (entYearStr == null || entYearStr.isEmpty()) {
	        errors.put("entYear", "入学年度を選択してください");
	    }
	    if (classNum == null || classNum.isEmpty()) {
	        errors.put("classNum", "クラスを選択してください");
	    }
	    if (subjectCd == null || subjectCd.isEmpty()) {
	        errors.put("subject", "科目を選択してください");
	    }
	    if (noStr == null || noStr.isEmpty()) {
	        errors.put("no", "回数を選択してください");
	    }
	    
	    int entYear = 0;
	    int no = 0;
	    try {
	    	entYear = Integer.parseInt(entYearStr);
	    	no = Integer.parseInt(noStr);
	    }finally {
	    	
	    }
	    
	    // DAO 呼び出し
	    StudentDao studentDao = new StudentDao();
//	    SubjectDao subjectDao = new SubjectDao();
	    TestDao testDao = new TestDao();
	    
//	    Subject subject = subjectDao.get(subjectCd,school);
	    List<Student> studentList = studentDao.filter(school, entYear, classNum,false);
	    
	    List<Test> testList = new ArrayList<>();
	    for(Student student: studentList) {
	    	String pointStr = req.getParameter("point_" + student.getNo());
	    	if(pointStr == null || pointStr.isEmpty()) continue;
	    	int point = Integer.parseInt(pointStr);
	    	if(point < 0 || point > 100) {
	    		errors.put("point", "0～100の範囲で入力してください");
	    	}
	    	Test test = new Test();
	    	test.setStudent(student);
//	    	test.setSubject(subject);
	    	test.setSchool(school);
	    	test.setNo(no);
	    	test.setPoint(point);
	    	testList.add(test);
	    }
	    testDao.save(testList);
	    req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}