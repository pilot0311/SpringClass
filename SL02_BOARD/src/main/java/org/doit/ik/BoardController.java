package org.doit.ik;


import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("BoardController... get");
		model.addAttribute("list",this.boardService.getList());
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register... get");
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register... post");
		this.boardService.register(board);
		// 스프링 리다이렉트
		rttr.addFlashAttribute("result",board.getBno()+"번 게시글 작성 성공");
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("BoardController... 글상세보기 or 수정");
		model.addAttribute("board",this.boardService.get(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,  RedirectAttributes rttr) {
		log.info("BoardController.post ... 수정");
		
		if(this.boardService.modify(board)) {
			rttr.addFlashAttribute("result",board.getBno()+"번 수정완료");
		}
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("BoardController... delete");
		if (this.boardService.remove(bno)) {
			rttr.addFlashAttribute("result",bno+"번이 삭제 되었습니다");
		}
		return "redirect:/board/list";
	}
	
}
