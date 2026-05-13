<%-- 学生一覧JSP --%>
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

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>
            
            <%-- 科目情報検索  --%>
            <form method="get" action="TestListStudentExecute.action">

                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==f2}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subj" items="${subject_set}">
                                <option value="${subj.cd}">
                                    ${subj.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2 text-center">
                        <button class="btn btn-secondary">検索</button>
                    </div>

                </div>
            </form>
            
            <%-- 学生番号 --%>
           	<form method="get" action="TestListStudentExecute.action">

                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col-3">
                        <label class="form-label">学生番号</label>
                    </div>

                    <div class="col-5">
                        <input type="text" class="form-control"
                               name="studentNo"
                               placeholder="学生番号を入力してください">
                    </div>

                    <div class="col-2 text-center">
                        <button class="btn btn-secondary">検索</button>
                    </div>

                </div>
            </form>

            <p class="text-info">
                科目情報を選択または学生番号を入力して検索ボタンをクリックしてください
            </p>

        </section>
    </c:param>
</c:import>