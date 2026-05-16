package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ
        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subject");
        String noStr = req.getParameter("no");

        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (entYearStr == null || entYearStr.isEmpty()) {
            errors.put("entYear", "入学年度を選択してください");
        }
        if (classNum == null || classNum.isEmpty()) {
            errors.put("classNum", "クラスを選択してください");
        }
        if (subjectCd == null || subjectCd.isEmpty()) {
            errors.put("subject", "科目を選択してください");
        }

        // エラー
        if (!errors.isEmpty()) {

            ClassNumDao classNumDao = new ClassNumDao();
            SubjectDao subjectDao = new SubjectDao();

            req.setAttribute("class_num_set", classNumDao.filter(school));
            req.setAttribute("subject_list", subjectDao.filter(school));

            req.setAttribute("errors", errors);

            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        int entYear = Integer.parseInt(entYearStr);

        // 科目取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        // DAO
        TestListSubjectDao dao = new TestListSubjectDao();

        List<TestListSubject> testList =
                dao.filter(entYear, classNum, subject, school);

        // データなし
        if (testList.isEmpty()) {
            errors.put("no_data", "成績情報が存在しませんでした");
            req.setAttribute("errors", errors);
        }

        // JSPへ
        req.setAttribute("test_list", testList);
        req.setAttribute("subject", subject);
        req.setAttribute("no", noStr);
        req.setAttribute("entYear", entYearStr);
        req.setAttribute("classNum", classNum);
        req.setAttribute("subjectCd", subjectCd);

        // フォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}
