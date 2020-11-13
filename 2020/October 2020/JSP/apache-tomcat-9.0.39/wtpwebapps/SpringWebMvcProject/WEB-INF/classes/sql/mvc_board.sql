CREATE TABLE mvc_board(
	board_no INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	content TEXT NULL,
	writer VARCHAR(50) NOT NULL,
	reg_date TIMESTAMP DEFAULT NOW(),
	view_cnt INT DEFAULT 0
);
SELECT * FROM mvc_board ORDER BY board_no DESC LIMIT 0,10

SELECT * FROM mvc_board WHERE title LIKE '%33%' ORDER BY board_no DESC

SELECT COUNT(*) FROM mvc_board WHERE title LIKE '%99%' ORDER BY board_no desc