<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${user_name == null}">
	<script>
		alert("로그인이 필요한 서비스입니다.");
		location.href='/izone';
	</script>
</c:if>

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
						
						<c:forEach var="article" items="${articles.messageList}">
						<tr style="color: #ff52a0;">
							<!-- 게시글리스트가 들어갈 행 -->
							<td>${article.bId}</td>
							<td>${article.bName}</td>
							
							<td>
								<a href="/izone/board/BContent.izone?bId=${article.bId}" style="color: orange;">
								${article.bTitle}
								</a>
							</td>
							
							<td>
								<fmt:formatDate pattern="yyyy년 MM월 dd일" value="${article.bDate}" />    
							</td>
							<td>${article.bHit}</td>
						</tr>		
						</c:forEach>				
						
					</table>
					
					<!-- 페이징 처리 시작 -->
					<ul class="pagination justify-content-center">
                       	<li class="page-item">
							<a class="page-link" href="#" 
							style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; "><<</a>
						</li>

						
						<c:forEach var="pageNum" begin="1" end="${articles.pageTotalCount}" >
						    <li class="page-item">
						    	<a class="page-link" style="margin-top: 0; height: 40px; color: pink; border: 1px solid pink;"
						    	href="Blist.izone?page=${pageNum}">${pageNum}</a>
						    </li>
						</c:forEach>
					   
					    <li class="page-item">
					      <a class="page-link" href="#" 
					      style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; ">>></a>
					    </li>
				    </ul>
				<!-- 페이징 처리 끝 -->
					
				</div>
				<div class="col-lg-2">
				</div>
			</div>
		</div>
	</div>
<%@ include file="../include/footer.jspf" %>


