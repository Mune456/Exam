<%-- 成績一覧JSP（検索後ページ） --%>
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
                成績管理
            </h2>

            <!-- 検索条件表示 -->
            <div class="px-4 mb-3">
                入学年度：
                <c:choose>
                    <c:when test="${entYear != 0}">
                        ${entYear}
                    </c:when>
                    <c:otherwise>指定なし</c:otherwise>
                </c:choose>
                ／ クラス：
                <c:choose>
                    <c:when test="${not empty classNum}">
                        ${classNum}
                    </c:when>
                    <c:otherwise>指定なし</c:otherwise>
                </c:choose>
                ／ 科目：
                <c:choose>
                    <c:when test="${not empty subjectCd}">
                        ${subjectCd}
                    </c:when>
                    <c:otherwise>指定なし</c:otherwise>
                </c:choose>
                ／ 回数：
                <c:choose>
                    <c:when test="${count != 0}">
                        ${count}
                    </c:when>
                    <c:otherwise>指定なし</c:otherwise>
                </c:choose>
            </div>

            <!-- 件数表示 -->
            <div class="px-4 mb-2">
                検索結果：${list.size()}件
            </div>

            <!-- 成績一覧 -->
            <c:choose>
                <c:when test="${list.size() > 0}">
                    <table class="table table-hover mx-4">
                        <thead>
                            <tr>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="t" items="${list}">
                                <tr>
                                    <td>${t.num}</td>
                                    <td>${t.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <div class="px-4">
                        成績情報が存在しませんでした
                    </div>
                </c:otherwise>
            </c:choose>

            <!-- 戻る -->
            <div class="px-4 mt-3">
                <a href="TestList.action">戻る</a>
            </div>

        </section>
    </c:param>
</c:import>