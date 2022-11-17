package com.bicycle.map.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicycle.map.board.dto.Board;
import com.bicycle.map.board.mapper.BoardMapper;



@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	public List<Board> selectAll(){
		return boardMapper.selectAll();
	}

	public long write(Board board) {
		return boardMapper.write(board);
	}

	public Board boardArticle(int board_idx) {
		// TODO Auto-generated method stub
		return boardMapper.boardArticle(board_idx);
	}
	
	public long modify(Board board) {
		return boardMapper.modify(board);
	}
	
	public boolean deleteArticle(int code) {
		return boardMapper.deleteArticle(code);
	}


}