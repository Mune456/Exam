package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の指定
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher)session.getAttribute("user");
        int ent_year = 0; // 選択された入学年度
        String student_no = ""; // 入力された学生番号
        String student_name = ""; // 入力された氏名
        String class_num = ""; // 選択されたクラス番号
        Student student = new Student();
        StudentDao studentDao = new StudentDao();
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // リクエストパラメーターの取得
        ent_year = Integer.parseInt(req.getParameter("ent_year"));
        student_no = req.getParameter("no");
        student_name = req.getParameter("name");
        class_num = req.getParameter("class_num");

        // ビジネスロジック
        if (ent_year == 0) {
            errors.put("1", "入学年度を選択してください");
            req.setAttribute("errors", errors);
        } else {
            if (studentDao.get(student_no) != null) {
                errors.put("2", "学生番号が重複しています");
                req.setAttribute("errors", errors);
            } else {
                student.setNo(student_no);
                student.setName(student_name);
                student.setEntYear(ent_year);
                student.setClassNum(class_num);
                student.setAttend(true);
                student.setSchool(teacher.getSchool());

                studentDao.save(student);
            }
        }

        // レスポンス値をセット
        req.setAttribute("ent_year", ent_year);
        req.setAttribute("no", student_no);
        req.setAttribute("name", student_name);
        req.setAttribute("class_num", class_num);

        // JSPへフォワード
        if (errors.isEmpty()) {
            req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("StudentCreate.action").forward(req, res);
        }
    }

}