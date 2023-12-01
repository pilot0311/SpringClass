package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.doit.ik.service.MemberShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/customer/*")
public class CustomerController {
	@Autowired
	private NoticeMapper noticeDao;
	@Autowired
	private MemberShipService memberShipService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	// ?dir=customer/upload&file=${ notice.filesrc  }
    @RequestMapping( "/download.htm")
    public void download(
          @RequestParam("dir")   String d
          , @RequestParam("file") String fname
          , HttpServletResponse response
          , HttpServletRequest request
          ) throws Exception{ 

       response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));      

       String fullPath = request.getServletContext().getRealPath(   d + "/" + fname);

       FileInputStream fin = new FileInputStream(fullPath);
       ServletOutputStream sout = response.getOutputStream();  
       byte[] buf = new byte[1024];
       int size = 0;
       while((size = fin.read(buf, 0, 1024)) != -1) {
          sout.write(buf, 0, size); 
       }
       fin.close();
       sout.close();

    }
	
	@GetMapping("/noticeDel.htm")
	public String del(@RequestParam("seq")String seq,@RequestParam("filesrc")String delFilesrc, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		 String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		 File delFile = new File(uploadRealPath, delFilesrc);
		 if (delFile.exists()) delFile.delete(); 
			
		
		int row = this.noticeDao.delete(seq);
		 if (row == 1) {
				return "redirect:notice.htm";
		}else {
			return "redirect:noticeDetail.htm?seq="+seq+"&error";
		}
		
		}
		
	@PostMapping("/noticeEdit.htm")
	   public String noticeEdit(NoticeVO notice, HttpServletRequest request,
			   @RequestParam("o_filesrc")String oFilesrc) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		
		CommonsMultipartFile multipartFile = notice.getFile();
		//서버에 배포했을 경우의 실제 저장 경로
		String uploadRealPath = null;
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			
			//File saveDir = new File(uploadRealPath);
			//if (!saveDir.exists()) saveDir.mkdirs();
				
			System.out.println(uploadRealPath);
			
			// 이전 첨부파일은 삭제
			File delFile = new File(uploadRealPath, oFilesrc);
			if (delFile.exists()) {
				delFile.delete();
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			String filesystemName =  getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest);
			notice.setFilesrc(filesystemName);
		}else {
			notice.setFilesrc(oFilesrc);
		}
		
		
	      int updateCount = this.noticeDao.update(notice);
	      if(updateCount == 1) {
	         return "redirect:noticeDetail.htm?seq=" + notice.getSeq();
	      } else {   
	         return "redirect:notice.htm";
	      }
	   }
	
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq")String seq, Model model) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice",notice);
		return "customer.noticeEdit";
	}
	
	
	@PostMapping(value = "/noticeReg.htm")
	public String noticeReg(String title, String content, NoticeVO notice,HttpServletRequest request)throws ClassNotFoundException, SQLException, IllegalStateException, IOException{
		 // 첨부된 파일 유무 확인
		CommonsMultipartFile multipartFile = notice.getFile();
		//서버에 배포했을 경우의 실제 저장 경로
		String uploadRealPath = null;
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			
			//File saveDir = new File(uploadRealPath);
			//if (!saveDir.exists()) saveDir.mkdirs();
				
			System.out.println(uploadRealPath);
			
			String originalFilename = multipartFile.getOriginalFilename();
			String filesystemName =  getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest);
			notice.setFilesrc(filesystemName);
		}
		// 작성자 X 로그인 햐야지만 글작성 가능(spring.sequrity)
		// 세션 사용
		notice.setWriter("pilot");
		//int insertCount = this.noticeDao.insert(notice);
		int insertCount = 1;
		//this.noticeDao.insertAndPointUpofMember(notice, "pilot");
		this.memberShipService.insertAndPointUpofMember(notice, "pilot");
		if (insertCount == 1) {
			return "redirect:notice.htm";
		}else {
			return "noticeReg.jsp?error";
		}
		
	}
	
	
	@GetMapping(value = "/noticeReg.htm")
	public String noticeReg() throws ClassNotFoundException, SQLException {
		return "customer.noticeReg";
	}
	
	@GetMapping(value = "/noticeDetail.htm")
	public String noticeDetail( @RequestParam("seq")String seq, Model model ) throws ClassNotFoundException, SQLException {
		 this.noticeDao.hitUp(seq); //조회수 1증가
		 NoticeVO noticeVO = this.noticeDao.getNotice(seq);
		 model.addAttribute("notice", noticeVO);
		 
		return "customer.noticeDetail";
	}
	
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
	      int index = 1;      
	      while( true ) {         
	         File f = new File(uploadRealPath, originalFilename);         
	         if( !f.exists() ) return originalFilename;         
	         // upload 폴더에 originalFilename 파일이 존재한다는 의미         a.txt (4자리)
	         String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
	         String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
	         // asdfasf-3.txt
	         originalFilename = fileName+"-"+(index)+ext;

	         index++;
	      } // while 
	   }
	
	@GetMapping(value = "/notice.htm")
	public String notices(@RequestParam(value="page",defaultValue = "1")int page,
			@RequestParam(value = "field",defaultValue = "title")String field,
			@RequestParam(value = "query", defaultValue = "")String query,
			Model model) throws ClassNotFoundException, SQLException {
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("name", "hello");
		
		return "customer.notice";
	}
	
	
	/*
	@GetMapping(value = "/notice.htm")
	public ModelAndView notice(Locale locale, Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		ModelAndView mav = new ModelAndView();
		 
		 String ppage = request.getParameter("page");
		 String pfield = request.getParameter("field");
		 String pquery = request.getParameter("query");
		 
		 int page = 1;
		 String field = "title";
		 String query = "";
		 
		 if (ppage != null && !ppage.equals("")) { page = Integer.parseInt(ppage); }
		 if (pfield != null && !pfield.equals("")) { field =pfield; }
		 if (pquery != null && !pquery.equals("")) { query = pquery; }
			
		 List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		 
		 mav.addObject("list", list);
		 mav.addObject("name", "pilot");
		 mav.setViewName("notice.jsp");
		 
		return mav;
	}
	*/
}
