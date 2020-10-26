# MVC model 2 게시판 실습 



#### DB Table 

```mysql
# 번호, 작성자이름, 글제목, 내용, 작성날짜, 조회수 
CREATE TABLE izone_board(
	board_id INT PRIMARY KEY AUTO_INCREMENT,
	board_name VARCHAR(30) NOT NULL,
	board_title VARCHAR(60) NOT NULL,
	board_content VARCHAR(300), 
	board_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	board_hit INT DEFAULT 0
);

INSERT INTO izone_board (board_name, board_title, board_content)
VALUES ('홍길동', '테스트용 게시물입니다.', '가나다라마바사아자차카타파하');

SELECT * FROM izone_board;

```



