package com.spring.mvc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController // 스프링 4 이상부터 사용가능 - ResponseBody를 안써도 됨 
@RequestMapping("/rest")
public class RestControllerTest {
	
	/*
	 * 
	 # @ResponseBody
	 - 리턴 데이터를 ViewResolver에게 전달하지 않고
	 클라이언트에게 해당 데이터를 바로 응답하게 한다.
	 
	 */
	
	@GetMapping("/hello")
	@ResponseBody
	public String Hello() {
		return "Hello World!"; // TODO : 결과 문자열을 바로 쏴줌
	} // Hello World!
	
	@GetMapping("/hobbys")
	@ResponseBody
	public List<String> hobbys(){
		List<String> hobbys = Arrays.asList("축구", "수영", "음악감상");
		return hobbys;
	} // ["축구","수영","음악감상"]
	
	@GetMapping("/study")
	@ResponseBody
	public Map<String, Object> study(){
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "Java");
		subject.put("JSP", "Java Server Pages");
		subject.put("Spring", "Spring FrameWork");
		return subject;
	} // {"JSP":"Java Server Pages","자바":"Java","Spring":"Spring FrameWork"}
	
	@GetMapping("/person")
	@ResponseBody
	public Person person() {
		Person p = new Person();
		p.setName("김철수");
		p.setAge(30);
		p.setHobbys(Arrays.asList("수영", "탁구", "요가"));
		return p;
	} // {"name":"김철수","age":30,"hobbys":["수영","탁구","요가"]}

	
}
