package com.codingrecipe.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    // board_table : board_file_table = 1 : n 관계를 설정 -> board_file_table 기준으로 설정함
    // 아래의 설정을 공식 처럼 사용함(자식 기준)
    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY 고정 : 부모를 조회 할 때 자식을 필요한 상황에만 조회
    @JoinColumn(name = "board_id") // DB에 만들어지는 컬럼 이름 설정
    private BoardEntity boardEntity; // 반드시 부모의 Entity 타입으로 설정 해야함 -> 실제 DB에 들어갈 때는 id 값만 들어감

    public static  BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity);

        return boardFileEntity;
    }
}
