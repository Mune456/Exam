package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ローカル変数
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        List<String> classNumSet = null;

        ClassNumDao classNumDao = new ClassNumDao();

        // 入学年度リスト作成
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        // クラス一覧取得
        classNumSet = classNumDao.filter(teacher.getSchool());

        // JSP へ渡す
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);

        // フォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
