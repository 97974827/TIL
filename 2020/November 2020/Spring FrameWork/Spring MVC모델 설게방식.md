# Spring MVC 모델



- **컨트롤러**
  - `@Controller("별칭안써도됨")`
  - `@RequestMapping("/공통 URL")`
  - private 서비스 객체 - `@Autowired`
  - 서비스에 할일 떠넘김
  - 매개변수 - 커맨드객체 (VO클래스 이용)
  - `@GetMapping("/매핑할 URL")`
  - `@PostMapping("매핑할 URL")`

- **서비스** 
  - 인터페이스 설계필요 (유지보수)
  - `@Service` - implement
  - DAO 객체 - `@Autowired`
  - 잡다한 작업 처리 후 실질적 데이터 접근은 DAO 클래스에 넘김
- **DAO, VO (모델)**
  - DAO
    - 인터페이스 설계필요 (유지보수)
    - `@Repository` - implement
    - 기본은 싱글톤 빈생성 
      - 원하지 않으면 `@Scope("protype")` - 이방법은 거의 쓰지않음 
    - DB에 액세스 이후 데이터 넘겨줌 
- **뷰**
  - 얻어온 데이터를 웹 페이지에 띄워줌 
  - jsp 파일



### 예제

컨트롤러

```java
package com.spring.database.jdbc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.database.jdbc.board.model.BoardVO;
import com.spring.database.jdbc.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service; // 서비스 의존성주입 
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("art", service.getArticles());
	}
	
	@GetMapping("/write")
	public void write() {}
	
	@PostMapping("/write")
	public String write(Model model, BoardVO article) {
		service.insertArticle(article);
		model.addAttribute("art", article); // 모델에 잠시 데이터 저장
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String delete(int boardNo) {
		service.deleteArticle(boardNo);
		return "redirect:/board/list"; // 재요청 
	}
	
	@GetMapping("/content")
	public void content(int boardNo, Model model) {
		model.addAttribute("article", service.getContent(boardNo));
	}
	
	@GetMapping("/modify")
	public void modify(int boardNo, Model model) {
		model.addAttribute("article", service.getContent(boardNo));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO article, int boardNo, Model model) {
		service.modifyArticle(article, boardNo);
		model.addAttribute("article", article);
		return "redirect:/board/content?boardNo=" + boardNo;
	}
	
	@GetMapping("/searchList")
	public String searchList(String keyword, Model model) {
		List<BoardVO> list = service.getSearchList(keyword);
		model.addAttribute("art", list);
		return "board/list";
	}
	
}
```



서비스

```java
package com.spring.database.jdbc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.database.jdbc.board.model.BoardVO;
import com.spring.database.jdbc.board.repository.IBoardDAO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO dao;
	
	@Override
	public List<BoardVO> getArticles() {
		return dao.getArticles();
	}

	@Override
	public void insertArticle(BoardVO article) {
		dao.insertArticle(article);
	}

	@Override
	public void deleteArticle(int boardNo) {
		dao.deleteArticle(boardNo);
	}

	@Override
	public BoardVO getContent(int boardNo) {
		return dao.getContent(boardNo);
	}

	@Override
	public void modifyArticle(BoardVO article, int boardNo) {
		dao.modifyArticle(article, boardNo);
	}
	
	@Override
	public List<BoardVO> getSearchList(String keyword) {
		keyword = "%" + keyword + "%"; // SQL문에서 '%'문자열'%' 형태 방지하기위한 문자열 변경
		return dao.getSearchList(keyword);
	}

}
```



리파지토리 = DAO

```java
package com.spring.database.jdbc.board.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.database.jdbc.board.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {

	@Autowired
	private JdbcTemplate template; // 의존성 주입
	
	// select 쿼리 내부클래스 
	class BoardMapper implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setBoardNo(rs.getInt("board_no"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			return board;
		}
	}
	
	@Override
	public List<BoardVO> getArticles() {
		String sql = "SELECT * FROM jdbc_board";
		return template.query(sql, new BoardMapper());
	}

	@Override
	public void insertArticle(BoardVO article) {
		String sql = "INSERT INTO jdbc_board (writer, title, content) VALUES (?,?,?)";
		template.update(sql, article.getWriter(), article.getTitle(), article.getContent());
	}

	@Override
	public void deleteArticle(int index) {
		String sql = "DELETE FROM jdbc_board WHERE board_no=?";
		template.update(sql, index);
	}

	@Override
	public BoardVO getContent(int index) {
		String sql = "SELECT * FROM jdbc_board WHERE board_no=?";
		return template.queryForObject(sql, new BoardMapper(), index);
	}

	@Override
	public void modifyArticle(BoardVO article, int index) {
		String sql = "UPDATE jdbc_board SET writer=?, title=?, content=? WHERE board_no=?";
		template.update(sql, article.getWriter(), article.getTitle(), article.getContent(), index);
	}
	
	@Override
	public List<BoardVO> getSearchList(String keyword) {
		String sql = "SELECT * FROM jdbc_board WHERE writer LIKE ? ORDER BY board_no desc";
		return template.query(sql, new BoardMapper(), keyword);
	}

}

```



모델 - VO

```java
package com.spring.database.jdbc.board.model;

public class BoardVO {
	
	private Integer boardNo;
	private String writer;
	private String title;
	private String content;
	
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "BoardVO [writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
	public String getTitle() {
		return title;
	}
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}
```



뷰 

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${art.size() <= 0}">
		<p>게시물이 존재하지 않습니다.</p>
	</c:if>
	
	<c:if test="${art.size() > 0}">
		<h2>게시글 목록</h2>
		<table border="1">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>비고</td>
			</tr>
			
			<c:forEach var="i" items="${art}">
				<tr>
					<td>${i.boardNo}</td>
					<td>${i.writer}</td>
					<td>
						<a href="/database/board/content?boardNo=${i.boardNo}">${i.title}</a>
					</td>
					<td>
						<a href="/database/board/delete?boardNo=${i.boardNo}">[삭제]</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<form action="/database/board/searchList">
		<input type="text" name="keyword" placeholder="작성자 이름을 입력하세요">
		<input type="submit" value="검색">
	</form>
	
	<p>
		<a href="/database/board/write">게시글 작성하기</a>
	</p>
	
</body>
</html>
```

