package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {

        BoardEntity saveBoardEntity = BoardEntity.toSaveBoardEntity(boardDTO);
        boardRepository.save(saveBoardEntity);

    }

    public List<BoardDTO> findAll() {

        List<BoardDTO> boardDTOList = new ArrayList<>();
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        for (BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }

    public BoardDTO findById(Long id) {

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        if (optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            return null;
        }

    }

    @Transactional // JPA가 제공하는 메서드 외에 직접 만들어서 사용하는 메서드의 경우에는 @Transactional 꼭 붙여야 함
    public void updateHits(Long id) {

        boardRepository.updateHits(id);
    }

    public BoardDTO update(BoardDTO boardDTO) {

        boardRepository.save(BoardEntity.toUpdateBoardEntity(boardDTO));

        return findById(boardDTO.getId());
    }

    public void delete(Long id) {

        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // 요청하는 페이지 -> DB로 넘기는 page 위치에 있는 값은 0 부터 시작
        int pageLimit = 3; // 한 페이지에 보여줄  글 갯수
        // 한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순으로 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id"))); // id는 Entity 기준

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호 -> 현재 페이지-1(0 부터 시작임)
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id , writer, title, hits, createdTime
        // 필요한 필드만 있는 생성자 만들어서 변환 함
        // board는 boardEntities 한개의 Row라고 생각하면 됨
        // map을 이용하여 변환 함
        Page<BoardDTO> boardDTOS
                = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));

        return boardDTOS;
    }
}
