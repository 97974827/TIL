# 게시판 페이징 처리



#### Test

```java
package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.commons.PageVO;
import com.spring.mvc.board.repository.IBoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})

public class PagingAlgorithmTest {
	
	/*
	 *** 페이징 알고리즘 만들기
	 
	  1. 사용자가 보게 될 페이지 화면 
	  - 한 화면에 페이지를 10개씩 끊어서 보여준다면?  
	  ex) 1 2 3 4 ... 9 10 [다음] // [이전] 31 32 33 ... 39 40 [다음]
	  
	  - 만약에 총 게시물의 수가 67개라면?
	  ex) 1 2 3 4 5 6 7 // 이전, 다음 비활성화  
	  
	  - 총 게시물 수가 142이고 현재 12페이지에 사용자가 머물러 있다면?
	  ex) [이전] 11 12 13 14 15
	  
	  2. 총 게시물 수 조회
	  - 총 게시물 수는 DB로부터 수를 조회하는  SQL작성 
      
      3. 사용자가 현재 위치한 페이지를 기준으로 
                  끝 페이지 번호를 계산하는 로직 작성
       - 만약 현재 사용자가 보고있는 페이지가 3페이지고 한 화면에 보여줄 페이지가 10페이지씩이라면? 
                  끝페이지번호는? 10
       - 만약 현재 사용자가 보고있는 페이지가 37페이지고 한 화면에 보여줄 페이지가 20페이지씩이라면? 
                  끝페이지번호는? 40
                  
       - 공식 : Math.ceil올림(현재위치한 페이지번호 / 한 화면당 보여질 페이지 수) * 한 화면당 보여질 페이지수
          ex)                   37                20                         20
       
       4. 시작 페이지 번호 계산 
       - 현재 위치한 페이지가 15페이지, 한 화면당 보여줄 페이지가 10페이지씩이라면?
        -> 시작페이지 번호 : 11
                  현재 위치한 페이지가 73페이지, 한 화면당 보여줄 페이지가 20페이지씩이라면?
        -> 시작페이지 번호 : 61
        
       - 공식 : (끝 페이지 번호 - 한 화면에 보여줄 페이지수 ) + 1
                    20              10            
                    80              20
                    
       5. 끝 페이지 보정 (다음버튼이 존재하지 않을떄)
       - 총 게시물 수가 324개 , 한페이지당 10개 게시물보여줌, 한 화면당 10개 페이지를 보여줌 
       - 현재 이사람은 31페이지를 보고있다 , 위공식 적용하여 끝페이지는? 40, 실제 끝페이지는? 33
       
       # 5-1. 이전 버튼활성여부설정
       - 언제 이전버튼을 비활성화 할 것인가? 시작페이지가 1인 부분에서만 비활성 
       - 시작 페이지번호가 1로 구해진 시점에서 비활성 처리, 나머지는 활성
      
       # 5-2. 끝 페이지 값 보정
       - 다음버튼이 비활성화 되었을떄 총 게시물 수에 맞춰 끝페이지 번호를 재보정한다.
       - 공식 : Math.ceil올림(총 게시물의 수 / 한페이지에 보여줄 게시물 수)
       
       # 5-3. 다음 버튼활성여부 설정
       - 언제 다음버튼을 비활성화 할 것인가? 
       - (보정전 끝 페이지번호  * 한 화면당 보여질 게시물 수) >= 총 게시물 수   -> 비활성
       
       
       
	 */
	
	@Autowired
	private IBoardMapper mapper;
	
	// 메서드 테스트
	// 총 게시물 수 
	@Test
	public void pagingAlgorithmTest() {
		System.out.println("총 게시물수 : " + mapper.countArticles());
		
		// 끝 페이지 계산 테스트
		PageVO paging = new PageVO();
		paging.setPage(31);   // 사용자 선택 페이지 위치
		int displayPage = 10; // 한 화면당 보여질 페이지수
		
		int endPage = (int) Math.ceil(paging.getPage() / (double)displayPage) * displayPage;
		System.out.println("끝페이지 번호 : " + endPage);
		
		int startPage = (endPage - displayPage) + 1;
		System.out.println("시작 페이지 번호 : " + startPage);
		
		boolean isPrev = (startPage == 1) ? false : true;
		System.out.println("이전 버튼 활성화 여부 : " + isPrev);
		
		// 끝페이지 보정
		int temp = (int) Math.ceil(mapper.countArticles() / (double)paging.getCountPerPage());
		
		// 재보정 여부 판단하기
		if(endPage > temp) {
			endPage = temp;
		}
		System.out.println("보정 후 페이지 번호 : " + endPage);
		
		boolean isNext = (mapper.countArticles() <= (endPage * paging.getPage())) ? false : true;
		System.out.println("다음 버튼 활성화 여부 : " + isNext);
	}
	  
	
}

```



VO

```java
package com.spring.mvc.board.commons;

public class PageCreator {
	
	// 페이지번호와 한 페이지당 들어갈 게시물 수를 갖고있는 객체 
	private PageVO paging;
	private Integer articleTotalCount; // 게시판의 총 게시물 수
	private Integer beginPage; // 시작페이지 번호
	private Integer endPage; // 끝페이지 번호
	private boolean prev; // 이전 버튼활성화 여부
	private boolean next; // 다음 버튼활성화 여부
	private final Integer displayPageNum = 10; // 한 화면에 보여질 페이지 수 : final은 setter 못만듬
	
	// 페이징 알고리즘을 수행할 메서드 선언
	private void calcDataOfPage() { 
	
		// 보정 전 끝 페이지 구하기
		endPage = (int) (Math.ceil(paging.getPage() / (double)displayPageNum) * displayPageNum);
		
		// 시작 페이지 번호 구하기
		beginPage = (endPage - displayPageNum) + 1;
		
		// 현재 시작페이지가 1이라면 이전버튼 활성화 여부를 false로 지정
		prev = (beginPage == 1) ? false : true;
		
		// 마지막 페이지인지 여부 확인후 다음 버튼 비활성
		next = (articleTotalCount <= (endPage * paging.getCountPerPage())) ? false : true;
		
		// 재보정 여부 판단하기
		if(!isNext()) {
			// 끝 페이지 재보정하기
			endPage = (int) Math.ceil(articleTotalCount / (double)paging.getCountPerPage());
		}

	}
	
	public PageVO getPaging() {
		return paging;
	}
	public void setPaging(PageVO paging) {
		this.paging = paging;
	}
	public Integer getArticleTotalCount() {
		return articleTotalCount;
	}
	public void setArticleTotalCount(Integer articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}
	public Integer getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(Integer beginPage) {
		this.beginPage = beginPage;
	}
	public Integer getEndPage() {
		return endPage;
	}
	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public Integer getDisplayPageNum() {
		return displayPageNum;
	}
	
	
	
}

```

