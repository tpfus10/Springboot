package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // test order 정하기(숫자가 작을 수록 먼저 실행: 음수도 가능)
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스터");
		board.setContent("잘 등록되나요?");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board); //sql 자동 생성 후 실행
	}
	
	@Test
	@Order(2)
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get(); //where id = ?
		
		System.out.println(board);
	}
	
	@Test
	@Order(3)
	public void testUpdateBoard() {
		System.out.println("== 1번 게시글 조회 ==");
		Board board = boardRepo.findById(1L).get();
		System.out.println("== 게시글 제목 수정 ==");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}
	
	
	@Test
	@Order(4)
	public void testDeleteBoard() {
		System.out.println("== 1번 게시물 삭제 ==");
		boardRepo.deleteById(1L);
	}
}
