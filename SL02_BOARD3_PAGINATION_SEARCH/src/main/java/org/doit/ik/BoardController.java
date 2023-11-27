package org.doit.ik;


import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.IpData;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService boardService;
	// 페이징 처리된 메서든
	@GetMapping("/list")
	public void list(Criteria criteria,Model model) {
		log.info("BoardController... get");
		model.addAttribute("list",this.boardService.getListWithPaging(criteria));
		int total = this.boardService.getTotal(criteria);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}
	
	/* 페이징 처리 안된 메서드
	@GetMapping("/list")
	public void list(Model model) {
		log.info("BoardController... get");
		model.addAttribute("list",this.boardService.getList());
	}
	*/
	
	@GetMapping("/register")
	public void register() {
		log.info("register... get");
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr, HttpServletRequest request) {
		log.info("register... post");
		
		this.boardService.register(board);
		
		String ip = request.getRemoteAddr();
		String log = request.getRequestURI();
		Long bno = board.getBno();
		this.boardService.insertIp(new IpData(ip,log,bno));
		// 스프링 리다이렉트
		rttr.addFlashAttribute("result",board.getBno()+"번 게시글 작성 성공");
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model, @ModelAttribute("criteria") Criteria criteria) {
		log.info("BoardController... 글상세보기 or 수정");
		model.addAttribute("board",this.boardService.get(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,  RedirectAttributes rttr,HttpServletRequest request,  @ModelAttribute("criteria") Criteria criteria) {
		log.info("BoardController.post ... 수정");
		
		if(this.boardService.modify(board)) {
			String ip = request.getRemoteAddr();
			String log = request.getRequestURI();
			Long bno = board.getBno();
			this.boardService.insertIp(new IpData(ip,log,bno));
			
			rttr.addFlashAttribute("result",board.getBno()+"번 수정완료");
		}
		rttr.addFlashAttribute("pageNum",criteria.getPageNum());
		rttr.addFlashAttribute("amount",criteria.getAmount());
		//return "redirect:/board/list";
		return "redirect:/board/list" + criteria.getListLink();
		
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr,HttpServletRequest request,  @ModelAttribute("criteria") Criteria criteria) {
		log.info("BoardController... delete");
		if (this.boardService.remove(bno)) {
			rttr.addFlashAttribute("result",bno+"번이 삭제 되었습니다");
			String ip = request.getRemoteAddr();
			String log = request.getRequestURI();
			this.boardService.insertIp(new IpData(ip,log,bno));
		}
		rttr.addFlashAttribute("pageNum",criteria.getPageNum());
		rttr.addFlashAttribute("amount",criteria.getAmount());
		return "redirect:/board/list" + criteria.getListLink();
	}
	
}
