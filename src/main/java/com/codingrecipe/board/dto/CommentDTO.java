package com.codingrecipe.board.dto;

import com.codingrecipe.board.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {

    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) { //, Long boardId
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        // commentDTO.setBoardId(boardId);
        // commentEntity.getBoardEntity().getId() 이 구조 사용 시
        // 반드시 호출하는 Service 메서드에 @Transactional을 붙여야함
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());

        return commentDTO;
    }
}
