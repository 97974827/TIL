<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = (String)session.getAttribute("user_name");
	if(userName == null) response.sendRedirect("/izone");
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

<form action="/izone/users/user_delete.jsp" method="post" style="margin-bottom: 0;">
<table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
	<tr>
		<td style="text-align: left">
			<p><strong>비밀번호를 입력하세요.</strong>&nbsp;&nbsp;&nbsp;<span id="oldCheck"></span></p>
		</td>
	</tr>
	<tr>
		<td><input type="password" size="17" maxlength="20" id="check_pw"
			name="chkPw" class="form-control tooltipstered" 
			maxlength="20" required="required" aria-required="true"
			style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
			placeholder="로그인 당시의 비밀번호"></td>
	</tr>

	<tr>
		<td style="width: 100%; text-align: center; colspan: 2;"><input
			type="submit" value="확인" class="btn form-control tooltipstered" id="del_check_btn"
			style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
		</td>
	</tr>
</table>
</form>

</div>
<div class="col-md-3"></div>
</div>

<br/><br/>


<%@ include file="../include/footer.jspf" %>