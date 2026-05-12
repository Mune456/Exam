<%-- 成績登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- ① 見出し -->
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <!-- ② 登録完了メッセージ -->
            <div class="alert alert-success mx-3">
                登録が完了しました
            </div>

            <!-- ③④ リンク -->
            <div class="mx-3 mt-3">

                <!-- 戻る -->
                <a href="TestRegist.action" class="me-3">
                    戻る
                </a>

                <!-- 成績参照 -->
                <a href="TestListStudent.action">
                    成績参照
                </a>

            </div>

        </section>
    </c:param>
</c:import>