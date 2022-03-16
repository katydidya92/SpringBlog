package com.blog.controller;

import com.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.allList(pageable));
        return "index";
    }

    @GetMapping("/auth/board")
    public String board() {
        return "/board/boardForm";
    }

    @GetMapping("/board/{id}")
    public String myBoard(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.view(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.view(id));
        return "board/updateForm";
    }

}
