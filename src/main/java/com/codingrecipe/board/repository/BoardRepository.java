package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // update board_table set board_hits = board_hits+1 where id=?
    // @Query : Entity 기준으로 쿼리 작성. nativeQuery = true을 설정하는 경우 일반적인 쿼리를 사용할 수 있음
    // @Modifying : update 나 delete 같은 경우 필수로 사용해야함
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
