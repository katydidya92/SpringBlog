package com.blog.service;

import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import com.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public List<Board> allBoard() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public void save(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> allList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    // 카테고리별 글 목록
    @Transactional(readOnly = true)
    public Page<Board> categoryList(Pageable pageable, String category) {
        return boardRepository.findByCategory(pageable, category);
    }

    @Transactional(readOnly = true)
    public List<Board> list(User user) {
        return boardRepository.findAllByUserOrderByIdDesc(user);
    }

    public Board view(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다."));
        board.setCount(board.getCount() + 1);
        return board;
    }

    public void delete(int id) {
        boardRepository.deleteById(id);
    }

    public void update(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다."));
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        board.setCategory(requestBoard.getCategory());
    }

    @Transactional
    public void replyWrite(User user, int boardId, Reply requestReply) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 : 게시글 ID를 찾을 수 없습니다."));
        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }

    public void replyDelete(int replyId) {
        replyRepository.deleteById(replyId);
    }

    public User replyWriterCheck(int replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("댓글 주인을 찾을 수 없습니다."));
        return reply.getUser();
    }

}
