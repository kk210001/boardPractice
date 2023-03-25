package com.spring.board.board.dao;

import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.paging.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Mapper
@Repository("boardDAO")
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

	private final SqlSession sqlSession;

	@Override
	public List<ArticleDTO> selectAllArticlesList(Pagination pagination) throws DataAccessException {
		List<ArticleDTO> articlesList = sqlSession.selectList("selectAllArticlesList", pagination);
		System.out.println("게시글 리스트 호출 완료");
		return articlesList;
	}

	@Override
	public void insertNewArticle(Map articleMap) throws DataAccessException {
		log.info("insert article info : {}", articleMap);
		sqlSession.insert("insertNewArticle",articleMap);
		System.out.println(" 삽입 완료");
	}


	@Override
	public void addViewCount(int articleNO) throws DataAccessException {
		sqlSession.selectOne("addViewCount", articleNO);
	}

    @Override
    public int getBoardAllCount() throws Exception {
		System.out.printf("게시글 리스트 호출 완료");
        return sqlSession.selectOne("tableAllCount");
    }


	@Override
	public ArticleDTO selectArticle(int articleNO) throws DataAccessException {
		System.out.println("게시글 호출 완료 " );
		return sqlSession.selectOne("selectArticle", articleNO);
	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("updateArticle", articleMap);
		System.out.println(" 수정 완료 " );
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("deleteArticle", articleNO);
		System.out.println(" 삭제 완료 " );
		
	}


	@Override
	public int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("selectNewArticleNO");
	}
	


}
