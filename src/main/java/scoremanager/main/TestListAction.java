package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学校
        var school = teacher.getSchool();

        // 年度
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();

        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // DAO
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        // クラス一覧
        List<String> classNumSet = classNumDao.filter(school);

        // 科目一覧
        List<Subject> subjectList = subjectDao.filter(school);
        List<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            countList.add(i);
        }

        // JSPへ渡す
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_list", subjectList);
        req.setAttribute("count_list", countList);

        // 空の状態で表示（最初は一覧なし）
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}