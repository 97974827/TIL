# Spring MyBatis 동적 SQL

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
			"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
			
<mapper namespace="com.spring.mvc.board.repository.IBoardMapper">
	
	<!-- type : 경로 작성 -->
	<resultMap id="BoardMap" type="com.spring.mvc.board.model.BoardVO">
		<id property="boardNo" column="board_no" /> <!-- 기본키 -->
		<result property="title" column="title" />
		<result property="content" column="content" />
		<result property="writer" column="writer" />
		<result property="regDate" column="reg_date" />
		<result property="viewCnt" column="view_cnt" />
	</resultMap>
	
	<!-- 중복되는 동적 SQL 문에 대한 처리 -->
	<sql id="search">
		<if test="condition == 'title'">
		WHERE title LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'writer'">
		WHERE writer LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'content'">
		WHERE content LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'titleContent'">
		WHERE title LIKE CONCAT('%', #{keyword}, '%')
		OR content LIKE CONCAT('%', #{keyword}, '%')
		</if>
	</sql>
	
	<insert id="insert" >
		INSERT INTO mvc_board
		(title, content, writer) 
		VALUES(#{title}, #{content}, #{writer})
	</insert>
	
	<select id="getArticleList" resultMap="BoardMap">
		SELECT * 
		FROM mvc_board
		
		<!-- 동적 SQL 구문  include-->
		<include refid="search" />
		ORDER BY board_no DESC
		LIMIT #{page}, #{countPerPage}
	</select>
	
	<select id="countArticles" resultType="Integer">
		SELECT COUNT(*) 
		FROM mvc_board 
		<!-- 동적 SQL 구문  include-->
		<include refid="search" />
	</select>
	
	<!--  
	<select id="getArticleListPaging" resultMap="BoardMap">
		SELECT * 
		FROM mvc_board 
		ORDER BY board_no DESC 
		LIMIT #{page}, #{countPerPage}
	</select>
	
	<select id="getArticleListByTitle" resultMap="BoardMap">
		SELECT * 
		FROM mvc_board
		WHERE title LIKE CONCAT('%', #{keyword}, '%') 
		ORDER BY board_no DESC
		LIMIT #{page}, #{countPerPage}
	</select> -->
	
	<!-- 자료형 리턴 : resultType -->
	<!--  
		
	<select id="countArticleByTitle" resultType="Integer">
		SELECT COUNT(*) 
		FROM mvc_board 
		WHERE title LIKE CONCAT('%', #{keyword}, '%')
	</select>
	-->
	<select id="getArticle" resultMap="BoardMap">
		SELECT * 
		FROM mvc_board 
		WHERE board_no=#{boardNo}
	</select>
	
	<update id="update">
		UPDATE mvc_board 
		SET title = #{title}, content = #{content}
		WHERE board_no = #{boardNo}
	</update>
	
	<delete id="delete">
		DELETE FROM mvc_board 
		WHERE board_no = #{boardNo}
	</delete>
	
</mapper>
```

