<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			
				<form method="get" action="TestRegist.action">
				
					<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
						<div class="col-2">
							<label class="form-label" for="f1-in-select">入学年度</label>
							<select class="form-select" id="f1-in-select" name="entYear">
								<option value="">--------</option>
								<c:forEach var="year" items="${ent_year_set }">
									<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
									<option value="${year }" <c:if test="${year == entYear }">selected</c:if>>${year }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2">
							<label class="form-label" for="f2-class-select">クラス</label>
							<select class="form-select" id="f2-class-select" name="classNum">
								<option value="">--------</option>
								<c:forEach var="num" items="${class_num_set }">
									<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
									<option value="${num }" <c:if test="${num == classNum }">selected</c:if>>${num }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-4">
							<label class="form-label" for="f3-subject-select">科目</label>
							<select class="form-select" id="f3-subject-select" name="subject">
								<option value="">--------</option>
								<c:forEach var="subject" items="${subject_list }">
									<%-- 現在のsubjectと選択されていたf3が一致していた場合selectedを追記 --%>
									<option value="${subject.cd }" <c:if test="${subject.cd == subjectCd}">selected</c:if>>${subject.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2">
							<label class="form-label" for="f4-count-select">回数</label>
							<select class="form-select" id="f4-count-select" name="no">
								<option value="">--------</option>
								<c:forEach var="cnt" items="${count_list }">
									<%-- 現在のcntと選択されていたf4が一致していた場合selectedを追記 --%>
									<option value="${cnt }" <c:if test="${cnt == no }">selected</c:if>>${cnt }</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<div class="mt-2 text-warning">${errors.entYear} ${errors.classNum} ${errors.subject} ${errors.no}</div>
					</div>
					
					<c:if test="${not empty student_list}">
					<h5 class="mx-3">
					  科目：${subject.name}（${no}回）
					</h5>
			</form>
					
			<form method="post" action="TestRegistExecute.action">
				<table class="table table-bordered mx-3">
					<thead>
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>点数</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="s" items="${student_list}">
						<tr>
							<td>${s.entYear}</td>
							<td>${s.classNum}</td>
							<td>${s.no}</td>
							<td>${s.name}</td>
							<td>
								<input type="text"
									name="point_${s.no}"
									class="form-control">
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- hidden：検索条件を保持 -->
				<input type="hidden" name="entYear" value="${param.entYear}">
				<input type="hidden" name="classNum" value="${param.classNum}">
				<input type="hidden" name="subject" value="${subjectCd}">
				<input type="hidden" name="no" value="${no}">
					
				<div class="mx-3">
					<button class="btn btn-success">登録して終了</button>
				</div>
				</form>
				</c:if>
				
		</section>
	</c:param>
</c:import>