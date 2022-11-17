package com.bicycle.map.board.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bicycle.map.board.dto.Board;
import com.bicycle.map.board.service.BoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@CrossOrigin(origins = "http://localhost:8080") // 메서드에서 설정
	@PostMapping("/selectAll")  // 게시판 글 목록을 보여주기 위함입니다.
	@ResponseBody
	public PageInfo<Board> selectAll(HttpServletRequest request) {
		System.out.println(request.getParameter("pageNum"));
		PageHelper.startPage(request);
		List<Board> list = boardService.selectAll();
		return PageInfo.of(list);
	}
	
	
	@GetMapping("/article")  // 게시판 글 상세를 보여주기 위함입니다. 겟 매핑을 해서 값을 받아옵니다.
	public String read(@ModelAttribute("search") Board search, @RequestParam("board_idx") int board_idx, Model model) {
		
		Board boardContents = boardService.boardArticle(board_idx);
		model.addAttribute("boardContents", boardContents);
		
		return "article";
	}
	
	@CrossOrigin(origins = "http://localhost:8080") // 메서드에서 설정
	@PostMapping("/article")  // 게시판 글 상세를 보여주기 위함입니다. 겟 매핑을 해서 값을 받아옵니다.	
	public ResponseEntity<Board> read(@RequestParam("code") int code) {
		System.out.println("code="+code);
		Board boardContents = boardService.boardArticle(code);
		//model.addAttribute("boardContents", boardContents);
		
		return new ResponseEntity<Board>(boardContents, HttpStatus.OK);
	}

	

	@GetMapping("/write")
	public String writeBoard(HttpSession session) {
		// CSRF 토큰 하나 발급
		String csrfToken = UUID.randomUUID().toString();
		System.out.println(csrfToken);
		session.setAttribute("CSRF_TOKEN", csrfToken);
		return "boardWrite";
	}
	
	 @CrossOrigin(origins = "http://localhost:8080")
		@PostMapping("/write")  // CSRF 토큰을 사용하여 로그인한 사용자만 글을 쓸 수 있도록 설정해주었습니다.
		@ResponseBody
		public String writeBoard(@RequestParam("files") List<MultipartFile> files
				,Board board,HttpSession session	) throws Exception {
			System.out.println(board);
			System.out.println(files);
			String uploadPath = session.getServletContext().getRealPath("/")+"WEB-INF/upload/";
			System.out.println(uploadPath);
			String multiFileName="";
			for (MultipartFile file : files) {
	            System.out.println(file.getOriginalFilename());
	            String fileName=file.getOriginalFilename();
	            File f=new File(uploadPath+fileName);
	            if(f.exists()) {
	            	 fileName= new Date().getTime() + fileName;
	            }
	            file.transferTo(new File(uploadPath+fileName));
	            multiFileName += fileName+",";
	        }
			System.out.println(multiFileName);
			board.setFileName(multiFileName);
			long cnt = boardService.write(board);
			if(cnt > 0) {
				return "file and article ok";
			}
			
//			System.out.println("받은 토큰:" + csrf_token);
//			String CSRF_TOKEN=(String)session.getAttribute("CSRF_TOKEN");
//			
//			if(CSRF_TOKEN!=null && CSRF_TOKEN.equals(csrf_token)) {
//				try {
//					long cnt = boardService.write(board);
//					file.transferTo(new File("C:\\tool\\temp\\"+file.getOriginalFilename()));  // 업로드할 때는 C의 경로로 넣어주어야 작동이 됩니다.
//					if(cnt > 0) {
//						return "file and article ok";
//					}
//				}catch (IllegalStateException e) {
//					e.printStackTrace();
//					return "upload fail!!!";
//				} catch (Exception e) {
//					e.printStackTrace();
//					return "upload fail!!!";
//				}
//			}else {
//				System.out.println(request.getRemoteAddr()+" 해킹 시도 감지");  // 토큰 없이 글을 쓰려고 한다면 해킹 시도라고 간주합니다.
//			}
			return "article write fail";
			
		}

	 @CrossOrigin(origins = "http://localhost:8080") // 메서드에서 설정
	 @PostMapping("/modify")
	 @ResponseBody
	 public ResponseEntity<String> writeBoard(Board board) {
	    long cnt = boardService.modify(board);
	    if (cnt > 0) {
	        return new ResponseEntity<>("file and article ok", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("file and article no", HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
	 @CrossOrigin(origins = "http://localhost:8080") // 메서드에서 설정
	 @PostMapping("/delete")
	 @ResponseBody
	 public ResponseEntity<String> deleteBoard(int code) {
	 	if (boardService.deleteArticle(code)) {
	 		return new ResponseEntity<>("file deleted", HttpStatus.OK);
	 	}
	 	return new ResponseEntity<>("file delete fail", HttpStatus.BAD_REQUEST);
	 }
	
	
	
}
