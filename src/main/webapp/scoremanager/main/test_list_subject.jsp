<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">
    得点管理システム
  </c:param>

  <c:param name="content">

	<section class="me-4">
  	<!-- タイトル -->
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>
	<!-- 条件表示 -->
		<div class="mx-3 mb-3">
			入学年度：${entYear}
			クラス：${classNum}
			科目：${subject.name}
		</div>

	<!-- データなし -->
		<c:if test="${not empty errors.no_data}">
			<div class="text-danger mx-3 mb-3">${errors.no_data}</div>
		</c:if>

	<!-- テーブル -->
		<c:if test="${not empty test_list}">
			<table class="table table-bordered mx-3">
		<!-- ヘッダー -->
			<thead class="table-light">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>1回</th>
					<th>2回</th>
				</tr>
			</thead>
		<!-- データ -->
			<tbody>
				<c:forEach var="t" items="${test_list}">
				<tr>
					<td>${t.entYear}</td>
					<td>${t.classNum}</td>
					<td>${t.studentNo}</td>
					<td>${t.studentName}</td>
				<!-- 点数 -->
					<td><c:out value="${t.points[1]}" default="-" /></td>
					<td><c:out value="${t.points[2]}" default="-" /></td>
				</tr>
				</c:forEach>
			</tbody>
			</table>
  		</c:if>
	</section>
  </c:param>
</c:import>
