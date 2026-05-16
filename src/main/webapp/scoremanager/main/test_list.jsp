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
		<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>	
		<form method="get" action="TestListSubjectExecute.action">
			<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
		
			<!-- 入学年度 -->
				<div class="col-2">
					<label class="form-label">入学年度</label>
					<select class="form-select" name="entYear">
						<option value="">--------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}"
								<c:if test="${year == param.entYear}">selected</c:if>>${year}
							</option>
						</c:forEach>
					</select>
				</div>

			<!-- クラス -->
				<div class="col-2">
					<label class="form-label">クラス</label>
					<select class="form-select" name="classNum">
						<option value="">--------</option>
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}"
							<c:if test="${num == param.classNum}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

			<!-- 科目 -->
				<div class="col-3">
					<label class="form-label">科目</label>
					<select class="form-select" name="subject">
						<option value="">--------</option>
						<c:forEach var="subj" items="${subject_list}">
							<option value="${subj.cd}"
							<c:if test="${subj.cd == param.subject}">selected</c:if>>${subj.name}</option>
						</c:forEach>
					</select>
				</div>

			<!-- 検索ボタン -->
				<div class="col-2 text-center">
					<button class="btn btn-secondary mt-4">検索</button>
				</div>
			</div>
		</form>
  	<!-- エラー -->
		<div class="text-danger mx-3">${errors.entYear} ${errors.classNum} ${errors.subject} ${errors.no} </div>

	<!-- 学生検索 -->
		<form method="get" action="TestListStudentExecute.action">
		<div class="row border mx-3 mb-3 py-2 align-items-center rounded">
			<div class="col-2">
				<label class="form-label">学生番号</label>
			</div>

			<div class="col-5">
				<input type="text" class="form-control" name="student_no" value="${param.student_no}" required placeholder="学生番号を入力してください">
			</div>

			<div class="col-2 text-center">
				<button class="btn btn-secondary">検索</button>
			</div>
		</div>
		</form>

	<!-- エラー -->
		<div class="text-danger mx-3">${errors.student_no}</div>

	<!-- メッセージ -->
		<p class="text-info mx-3">条件を指定するか、学生番号を入力して検索してください</p>
	</section>
  </c:param>
</c:import>
