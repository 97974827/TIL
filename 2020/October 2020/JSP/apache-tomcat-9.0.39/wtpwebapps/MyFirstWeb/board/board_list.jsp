<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@ include file="../include/header.jspf" %>
<style>
header.masthead {
	
	display: none;
}	
</style>

<br/><br/> 
 
    <!-- Begin Page Content -->
	
	<div class="container">
		<div class="row">
			<div class="col-lg-2">
			</div>
			<div class="col-lg-8">
				<div class="panel-body">
				<h2 class="page-header"><span style="color: #ff52a0;">IZONE</span> 자유 게시판
					<a href="/izone/board/BWriteView.izone" class="btn float-right" style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">글쓰기</a>
				</h2>
					<table class="table table-bordered table-hover">
						
						<tr style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							<th>#번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
						
						<c:forEach var="article" items="${articles}">
						<tr>
							<!-- 게시글리스트가 들어갈 행 -->
							<td>${article.bId}</td>
							<td>${article.bName}</td>
							<td>${article.bTitle}</td>
							<td>${article.bDate}</td>
							<td>${article.bHit}</td>
						</tr>		
						</c:forEach>				
						
					</table>
					
				</div>
				<div class="col-lg-2">
				</div>
			</div>
		</div>
	</div>
<%@ include file="../include/footer.jspf" %>


