
<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = (String)session.getAttribute("user_id");
	String name = (String)session.getAttribute("user_name");
	//비밀번호 맞는지 검사
	String pw = request.getParameter("chkPw");
	
	MemberDAO dao = MemberDAO.getInstance();
	int check = dao.memberLogin(id, pw);
%>
<%@ include file="../include/header.jspf" %>
<style>
header.masthead {
	
	display: none;
}	
</style>

<br/><br/>
<div class="row">
<div class="col-md-3"></div>

<div class="col-md-6">

<ul class="nav nav-tabs nav-justified">
   <li class="nav-item" ><a class="nav-link" href="/izone/users/mypage_change_pw.jsp" style="font-size: 20px;"><strong>비밀번호 변경</strong></a></li>
   <li class="nav-item"><a class="nav-link" href="/izone/users/mypage_user_update.jsp" style="font-size: 20px;"><strong>회원정보 수정</strong></a></li>
   <li class="nav-item"><a class="nav-link active" href="/izone/users/mypage_delete_check.jsp" style="font-size: 20px;"><strong>회원 탈퇴하기</strong></a></li>
   
</ul>
<br/><br/>

<h4 style="color: #ff52a0;">회원 탈퇴 페이지</h4><hr/><br/>

<div align="center">

	
	<% if(check == MemberDAO.LOGIN_SUCCESS) { %>
		<h4><%= name %>(<%= id %>)님 정말로 회원정보를 삭제하시겠습니까?</h4>
		
		<a class="btn" href="/izone/users/user_delete_ok.jsp"
		style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">예</a>&nbsp;&nbsp;
		<a class="btn btn-danger" href="/izone/users/mypage_delete_check.jsp"
		style="margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">아니오</a>
	
	<% } else { %>
		<script>
			alert("비밀번호를 다시 확인해주세요.")
			history.back()
		</script>
	<% } %>

</div>
</div>
<div class="col-md-3"></div>
</div>

<br/><br/>


<%@ include file="../include/footer.jspf" %>