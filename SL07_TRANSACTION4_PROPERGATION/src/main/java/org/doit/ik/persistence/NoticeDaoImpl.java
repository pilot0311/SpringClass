package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	//@Autowired
	//private DataSourceTransactionManager transactionManager;
	//@Autowired
	//private TransactionTemplate transactionTemplate;
	
	
	// 공지사항 총 갯수를 반환
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE :query";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("query", "%"+query+"%");
		return this.npJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
		//return this.jdbcTemplate.queryForObject(sql, Integer.class,"%"+query+"%");
	}
	// 공지 사항 목록
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * FROM ";
		sql += " (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE :query ORDER BY REGDATE DESC) N) ";
		sql += " WHERE NUM BETWEEN :srow AND :erow ";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		
		return this.npJdbcTemplate.query(sql, paramMap,new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
		
	}
	// 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
	
		String sql = "DELETE FROM NOTICES WHERE SEQ=:seq";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);
	
		return this.npJdbcTemplate.update(sql, parameterSource);
		
	}
	
	// 공지사항 수정
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{
		
		String sql = "UPDATE NOTICES SET TITLE=:title, CONTENT=:content, FILESRC=:filesrc WHERE SEQ=:seq";
		
		SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
		
		return this.npJdbcTemplate.update(sql, parameterSource);
		
		/*
	      MapSqlParameterSource parameterSource = new MapSqlParameterSource();
	      parameterSource.addValue("title", notice.getTitle());
	      parameterSource.addValue("content", notice.getContent());
	      parameterSource.addValue("filesrc",notice.getFilesrc());
	      parameterSource.addValue("seq", notice.getSeq() );
	      
	      return this.npJdbcTemplate.update(sql, parameterSource);
	      */
		
	}
	
	// 공지사항 조회
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT * FROM NOTICES WHERE SEQ= :seq ";

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);
	
		return this.npJdbcTemplate.queryForObject(sql, parameterSource,new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	
	}
	
	// 2. 공지사항 등록 + 작성자 포인트 1증가
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		// a. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		//a
		SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
		npJdbcTemplate.update(sql, parameterSource);
		
		// b. 작성자 포인트 1증가
		String sql2 = "UPDATE member SET point = point+1 WHERE id = :id ";
		//b
		MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
		parameterSource2.addValue("id", "pilot");
		int updateCount = npJdbcTemplate.update(sql2, parameterSource2);
		return updateCount;
	}
	
	/*
	// 6. 전파 방식 설명하기 위해 insertAndPointUpofMember 수정
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		insert(notice);
		
		notice.setTitle(notice.getTitle()+"-2");
		insert(notice);
	}
	*/
	
	/*
	// 1. 공지사항 추가
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
		return this.npJdbcTemplate.update(sql, parameterSource); 
		
	}
	*/
	
	/*
	// 5. 어노테이션 트랜잭션 처리
		@Override
		@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
		public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
			// a. 공지사항 쓰기
			String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
			//a
			SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
			npJdbcTemplate.update(sql, parameterSource);
			
			// b. 작성자 포인트 1증가
			String sql2 = "UPDATE member SET point = point+1 WHERE id = :id ";
			//b
			MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
			parameterSource2.addValue("id", id);
			int updateCount = npJdbcTemplate.update(sql2, parameterSource2);			
				
		}
		*/
	
	/*
	// 4. 선언적 트랜잭션 처리
	@Override
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// a. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		//a
		SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
		npJdbcTemplate.update(sql, parameterSource);
		
		// b. 작성자 포인트 1증가
		String sql2 = "UPDATE member SET point = point+1 WHERE id = :id ";
		//b
		MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
		parameterSource2.addValue("id", id);
		int updateCount = npJdbcTemplate.update(sql2, parameterSource2);			
			
	}
	*/
	
	/*
	// 3. transactionTemplate 
	@Override
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// a. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		// b. 작성자 포인트 1증가
		String sql2 = "UPDATE member SET point = point+1 WHERE id = :id ";
		
			//p.514
		// 																							WithoutResult: 결과 값이 없는 경우
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				//a
				SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
				npJdbcTemplate.update(sql, parameterSource);
				//b
				MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
				parameterSource2.addValue("id", id);
				int updateCount = npJdbcTemplate.update(sql2, parameterSource2);
				
			}
		});
			
	}
	*/
	
	
	/*
	// 2. transactionManager을 사용해서 트랜잭션 처리
	@Override
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// a. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		// b. 작성자 포인트 1증가
		String sql2 = "UPDATE member SET point = point+1 WHERE id = :id ";
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction(definition );
		
		try {
			//a
			SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
			this.npJdbcTemplate.update(sql, parameterSource);
			//b
			MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
			parameterSource2.addValue("id", id);
			int updateCount = this.npJdbcTemplate.update(sql2, parameterSource2);
			
			
			this.transactionManager.commit(status);
		} catch (Exception e) {
			this.transactionManager.rollback(status);
			e.printStackTrace();
		}
			
	}
	*/
	
	/*
	//1 트랜잭션 처리가 안된 메서드
	@Override
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// a. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1,1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		SqlParameterSource parameterSource  = new BeanPropertySqlParameterSource(notice);
		this.npJdbcTemplate.update(sql, parameterSource);
		// b. 작성자 포인트 1증가
		sql = "UPDATE member SET point = point+1 WHERE id = :id ";
		MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
		parameterSource2.addValue("id", id);
		int updateCount = this.npJdbcTemplate.update(sql, parameterSource2);
	}
	*/
}
