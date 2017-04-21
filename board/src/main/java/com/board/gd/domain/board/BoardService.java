package com.board.gd.domain.board;

import com.board.gd.exception.BoardException;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by gd.godong9 on 2017. 4. 20.
 */

@Transactional(readOnly = true)
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }

    public Page<Board> findAll(Predicate predicate, Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new BoardException("Invalid pageable!");
        }
        return boardRepository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = false)
    public Board create(BoardDto boardDto) {
        return boardRepository.save(Board.builder()
                .title(boardDto.getTitle())
                .code(boardDto.getCode())
                .build());
    }

    public long count() {
        return count(null);
    }

    public long count(Predicate predicate) {
        return boardRepository.count(predicate);
    }

    @Transactional(readOnly = false)
    public void deleteAll() {
        boardRepository.deleteAll();
    }
}
