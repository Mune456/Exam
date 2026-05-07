<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section>
            <h2>学生登録完了</h2>
            <p>登録が完了しました。</p>

            <a href="StudentList.action">一覧へ戻る</a>
        </section>
    </c:param>
</c:import>