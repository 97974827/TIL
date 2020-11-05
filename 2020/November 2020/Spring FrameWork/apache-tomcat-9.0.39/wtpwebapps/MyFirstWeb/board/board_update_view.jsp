<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jspf" %>
<style>
header.masthead {
	
	display: none;
}	
</style>
<br/><br/>
<div class="container">
<%--
   1. form태그의 action url = "BUpdate.board"
   2. Service클래스 이름은 BoardUpdateService
   3. DAO의 수정 메서드이름은 updateArticle
   4. 수정 완료 후 안내할 UI는 게시글 목록입니다.
--%>
<div class="row">
  <div class="col-lg-12">
    <div class="card">
      <div class="card-header text-white" style="background-color: #ff52a0;">${contents.bId}번 게시물 수정</div>
      <div class="card-body">
        <form role="form" action="/izone/board/BUpdate.izone" method="post">
		  
		  <input type="hidden" name="bId" value="${contents.bId}">
          
          <div class="form-group">
            <label>작성자</label>
            <input type="text" class="form-control" name='bName' value="${contents.bName}" readonly>
          </div>
          
          <div class="form-group">
            <label>제목</label>
            <input type="text" class="form-control" name='bTitle' value="${contents.bTitle}">
          </div>

          <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" rows="5" name='bContent'>${contents.bContent}</textarea>
          </div>

          
          <input class="btn" type="submit" value="수정" style="background-color: orange; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"/>
          <a class="btn" href="Blist.izone"
		style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">목록</a>&nbsp;&nbsp;
          
        </form>
      </div>
    </div>
  </div>
</div>
</div>
<%@ include file="../include/footer.jspf" %>