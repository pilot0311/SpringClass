package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 기준, 검색 기준
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	
	public Criteria() {
		this(1,5);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
		
	// ?pageNum=1&amount=10&type=T&keyword=홍길동
	   public String getListLink() {
	      UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
	            .queryParam("pageNum", this.getPageNum())
	            .queryParam("amount", this.getAmount())
	      		.queryParam("type", this.getPageNum())
	      		.queryParam("keyword", this.getPageNum());
	      return builder.toUriString();
	   }
	
	   public String [] getTypeArr() {
		   return type == null ? new String [] {} : this.type.split("");
	   }
}
