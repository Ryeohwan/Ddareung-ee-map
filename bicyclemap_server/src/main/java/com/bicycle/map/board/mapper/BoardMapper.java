package com.bicycle.map.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.bicycle.map.board.dto.Board;



@Mapper
public interface BoardMapper {
	
	public List<Board> selectAll() throws DataAccessException;

	public long write(Board board);

	public Board boardArticle(int board_idx);
	
	public long modify(Board board);
	
	boolean deleteArticle(int code);

}