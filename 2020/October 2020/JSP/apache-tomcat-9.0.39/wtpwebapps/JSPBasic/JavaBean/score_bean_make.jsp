<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.koo.jspbasic.score.ScoreBean"%>
<jsp:useBean id="score" class="kr.co.koo.jspbasic.score.ScoreBean" scope="request" />

<% request.setCharacterEncoding("utf-8"); %>

<jsp:setProperty name="score" property="*" />

<%
		// ScoreBean score = (ScoreBean) request.getAttribute("score");
	int total = score.getKor() + score.getEng() + score.getMath();
	double avg = (double) total / 3;
	avg = Double.parseDouble(String.format("%.1f", avg));
%>
	
<jsp:setProperty name="score" property="total" value="<%= total %>"/>
<jsp:setProperty name="score" property="avg" value="<%= avg %>"/>


<jsp:forward page="score_get.jsp" />