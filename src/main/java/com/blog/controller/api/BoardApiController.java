package com.blog.controller.api;

import com.blog.config.auth.PrincipalDetail;
import com.blog.dto.ResponseDto;
import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.User;
import com.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.save(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Integer> delete(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.update(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable("boardId") Integer boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.replyWrite(principalDetail.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@AuthenticationPrincipal PrincipalDetail principalDetail, @PathVariable("boardId") Integer boardId, @PathVariable("replyId") Integer replyId) {

        User replyWriter = boardService.replyWriterCheck(replyId);
        if (replyWriter == principalDetail.getUser()) {
            boardService.replyDelete(replyId);
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        } else {
            return new ResponseDto<Integer>(HttpStatus.FORBIDDEN.value(), 1);
        }
    }
}
