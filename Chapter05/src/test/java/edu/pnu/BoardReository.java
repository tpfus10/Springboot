package edu.pnu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Board;

@Repository
public interface BoardReository extends JpaRepository<Board, Long>{
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(String searchKeyword);
	
	@Query("SELECT b.seq, b.title, b.writer, b.createDate "
			+ "FROM Board b "
			+ "WHERE b.title like %?1% "
			+ "ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest3(@Param("searchKeyword") String searchKeyword);

	@Query(value="select seq, title, writer, create_date "
			+ "from board where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest4(String searchKeyword);
}
