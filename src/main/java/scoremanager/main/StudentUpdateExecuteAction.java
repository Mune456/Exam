package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の指定
        int ent_year = 0;
        String no = "";
        String name = "";
        String class_num = "";
        String isAttendStr = "";
        boolean isAttend = false;
        Student student = new Student();
        StudentDao studentDao = new StudentDao();

        // リクエストパラメーターの取得
        ent_year = Integer.parseInt(req.getParameter("ent_year"));
        no = req.getParameter("no");
        name = req.getParameter("name");
        class_num = req.getParameter("class_num");
        isAttendStr = req.getParameter("is_attend");

        // ビジネスロジック
        if (isAttendStr != null) {
            isAttend = true;
        }

        // studentに学生情報をセット
        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(class_num);
        student.setAttend(isAttend);

        // 変更内容を保存
        studentDao.save(student);

        // JSPへフォワード
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
    }

}