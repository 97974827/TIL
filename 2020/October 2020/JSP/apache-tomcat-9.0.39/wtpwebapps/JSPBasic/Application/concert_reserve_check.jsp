<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 예매가 확정된 좌석정보를 저장할 리스트	
	List<String> list = new ArrayList<>();
	
	if(application.getAttribute("complete_list") != null) {
		list = (List<String>) application.getAttribute("complete_list");
	}

	// 사용자가 예매를 희망하는 좌석정보가 담겨있는 배열 
	String[] seat = request.getParameterValues("seat"); // 선택좌석 여러개 가져오기
	
	// 예약 선점여부를 확인할 로직
	// 예약 확정전에 임시로 좌석정보를 저장할 리스트
	
	int count = 0; // 몇자리를 예약할 수 있는지 여부 체크 
	List<String> temp = new ArrayList<>();
	
	for(String s : seat){
		if(list.contains(s)){ // 선점되있다면 
			break;
		} else {
			temp.add(s);
			count++;
		}
	}
	
	if (count == seat.length){
		list.addAll(temp);
	}
	
	application.setAttribute("complete_list", list); // 확정 리스트 저장 
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>