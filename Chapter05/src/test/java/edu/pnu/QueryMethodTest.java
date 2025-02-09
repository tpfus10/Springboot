package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;
	
	//@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스트 제목 10");
	
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		
		System.out.println("[findByContentContaining] 검색 결과");
		for(Board board: boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	//@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17", "18");
		
		System.out.println("[findByTitleContainingOrContentContaining] 검색 결과");
		for(Board board: boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	//@Test
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("17");
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	//@Test //페이징
//	public void testFindByTitleContaining() {
//		Pageable paging = PageRequest.of(0, 5);
//		
//		List<Board> boardList = boardRepo.findByTitleContaining("제목", paging);
//		
//		 System.out.println("검색 결과");
//		 for(Board board : boardList) {
//			 System.out.println("--->" + board.toString());
//		 }
//	}
	
//	@Test //페이징 + 정렬
//	public void testFindByTitleContainingSorted() {
//		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
//		
//		List<Board> boardList = boardRepo.findByTitleContaining("제목", paging);
//		
//		 System.out.println("검색 결과");
//		 for(Board board : boardList) {
//			 System.out.println("--->" + board.toString());
//		 }
//	}
	
	@Test //Page<T> 타입 활용하기
	public void testFindByTitleContainingSorted() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		
		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목", paging);
		
		System.out.println("PAGE SIZE " + pageInfo.getSize());
		System.out.println("TOTAL PAGES " + pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT " + pageInfo.getTotalElements());
		System.out.println("NEXT : " + pageInfo.nextPageable());
		
		List<Board> boardList = pageInfo.getContent();
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	
	
}
