package org.doit.ik;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.IpData;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.domain.SampleVO;
import org.doit.ik.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@RestController
@Log4j
@RequestMapping("/sample/*")
public class SampleController {
	
	
	
	// 1. 단순 문자열 반환
	// produces = "text/plain; charset=UTF-8" : 해당 메서드가 생성하는 MIME 타입
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE:" + MediaType.TEXT_PLAIN_VALUE);
		return "Hello";
	}
	//스프링 5.2 부터 MediaType.APPLICATION_JSON_VALUE
	@GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
	
	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(113, "스타", "로드");
	}
	// 컬렉션 타입의 객체 반환
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		List<SampleVO> list = new ArrayList<SampleVO>();
		list.add(new SampleVO(1, "First1", "last1"));
		list.add(new SampleVO(2, "First2", "last2"));
		list.add(new SampleVO(3, "First3", "last3"));
		list.add(new SampleVO(4, "First4", "last4"));
		return list;
	}
	
	@GetMapping(value = "/getList2")
	public List<SampleVO> getList2() {
		
		return IntStream.range(1,10).mapToObj(i->new SampleVO(i,"First"+i,"Last"+i)).collect(Collectors.toList());
	}
	
	// 맵
	@GetMapping(value = "/getMap")
	public Map<String,SampleVO> getMap() {
		
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(1, "First1", "last1"));
		map.put("second", new SampleVO(2, "First2", "last2"));
		return map;
	}
	
	// ResponseEntity
	// REST 방식은 순수한 문자열, json,xml 데이터 송,수신
	// 정상적인 데이터인지 비정상적인 데이터인지 구분 필요
	// ResponseEntity 타입 = 응답 json 데이터 + Http의 상태 코드
	// height 파라미터 값이 150 기준으로 미만이면 502 상태코드
	@GetMapping(value = "/check",params = {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		ResponseEntity<SampleVO> result = null;
		SampleVO vo = new SampleVO(1, height+"", weight+"");
		if (height>=150) {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}
		return result;
	}
	
	// @PathVariable
	// REST 방식: URL 내에 최대한 많은 정보를 담으려고 한다
	//				?파라미터=값&...
	
	//localhost/sample/{no}
	//localhost/sample/{no}/page{page}
	@GetMapping(value = "/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		return new String [] {"category:"+cat,"productid:"+pid};
	}
	
	@PostMapping("/samplevo")
	public SampleVO convert(@RequestBody SampleVO vo) {
		log.info("convert"+vo);
		return vo;
	}
	
}
