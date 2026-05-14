<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>

<c:param name="content">
<section class="me-4">

<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
    成績管理
</h2>

<!-- ① 科目表示 -->
<div class="px-4 mb-3">
    科目：${subject}（${count}回）
</div>

<!-- フォーム -->
<form action="TestRegistExecute.action" method="post">

<!-- ② テーブル -->
<table class="table table-hover mx-4">

    <!-- ヘッダー -->
    <thead>
        <tr>
            <th>入学年度</th>
            <th>クラス</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>点数</th>
        </tr>
    </thead>

    <!-- データ -->
    <tbody>
        <c:forEach var="test" items="${test_list_student}">
            <tr>
                <!-- 入学年度 -->
                <td>${test.entYear}</td>

                <!-- クラス -->
                <td>${test.classNum}</td>

                <!-- 学生番号 -->
                <td>${test.studentNo}</td>

                <!-- 氏名 -->
                <td>${test.studentName}</td>

                <!-- 点数 -->
                <td>
                    <input type="text"
                        name="point_${test.studentNo}"
                        value="${test.point}">
                </td>
            </tr>

            <!-- hidden（学生番号）-->
            <input type="hidden" name="regist" value="${test.studentNo}">
        </c:forEach>
    </tbody>

</table>

<!-- hidden -->
<input type="hidden" name="count" value="${count}">
<input type="hidden" name="subject" value="${subject}">

<!-- 登録ボタン -->
<div class="px-4 mt-3">
    <input type="submit" value="登録して終了" class="btn btn-secondary">
</div>

</form>

</section>
</c:param>
</c:import>