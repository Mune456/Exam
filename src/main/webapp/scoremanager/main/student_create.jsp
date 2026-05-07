<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				学生情報登録
			</h2>

			<form action="/exam/scoremanager/main/StudentCreateExecute.action" method="post">

				<div class="mb-3">
					<label class="form-label">入学年度</label>
					<select class="form-select" name="ent_year">
						<c:forEach var="y" items="${ent_year_set}">
							<option value="${y}">${y}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<label class="form-label">学生番号</label>
					<input class="form-control" type="text" name="no">
				</div>

				<div class="mb-3">
					<label class="form-label">氏名</label>
					<input class="form-control" type="text" name="name">
				</div>

				<div class="mb-3">
					<label class="form-label">クラス</label>
					<select class="form-select" name="class_num">
						<c:forEach var="c" items="${class_num_set}">
							<option value="${c}">${c}</option>
						</c:forEach>
					</select>
				</div>

				<div class="text-center">
					<button class="btn btn-secondary">登録して終了</button>
				</div>

			</form>

			<div class="mt-3">
				<a href="/exam/scoremanager/main/StudentList.action">戻る</a>
			</div>

		</section>
	</c:param>
</c:import>