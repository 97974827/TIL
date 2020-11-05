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

<div class="row">
  <div class="col-lg-12">
    <div class="card">
      <div class="card-header text-white" style="background-color: #ff52a0;">${contents.bId}번 게시물 내용</div>
      <div class="card-body">       
        
          <div class="form-group">
            <label>작성자</label>
            <input type="text" class="form-control" name='bName' value="${contents.bName}" readonly>
          </div>
          
          <div class="form-group">
            <label>제목</label>
            <input type="text" class="form-control" name='bTitle' value="${contents.bTitle}" readonly>
          </div>

          <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" rows="5" name='bContent' readonly>${contents.bContent}</textarea>
          </div>
         
          <a class="btn" href="Blist.izone"
		style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">목록</a>&nbsp;&nbsp;
          <a class="btn" href="BUpdateView.izone?bId=${contents.bId}"
		style="background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">수정</a>&nbsp;&nbsp;
          <a class="btn" href="BDelete.izone?bId=${contents.bId}" onclick="return confirm('정말로 삭제하시겠습니까?')"
		style="background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">삭제</a>&nbsp;&nbsp;
       
      </div>
    </div>
  </div>
</div>
</div>
<%@ include file="../include/footer.jspf" %>