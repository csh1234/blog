package com.lrm.web.admin;

import com.lrm.po.Note;
import com.lrm.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author: CSH
 * @description: 最新公告控制层
 * @create: 2020-11-03 13:22
 **/
@Controller
@RequestMapping("/admin")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping("/notes")
    public String notes(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page",noteService.listNote(pageable));
        return "admin/notes";
    }

    @RequestMapping("/notes/input")
    public String input(Model model) {
        model.addAttribute("note", new Note());
        return "admin/notes-input";
    }

    @RequestMapping("/notes/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.getNote(id));
        return "admin/notes-input";
    }

    @PostMapping("/notes")
    public String post(@Valid Note note, RedirectAttributes attributes) {
//        if (result.hasErrors()) {
//            return "admin/notes-input";
//        }
        Note t = noteService.saveNote(note);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/notes";
    }

    @PostMapping("/notes/{id}")
    public String editPost(@Valid Note note,@PathVariable Long id, RedirectAttributes attributes) {
//        if (result.hasErrors()) {
//            return "admin/notes-input";
//        }
        Note t = noteService.updateNote(id,note);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/notes";
    }

    @RequestMapping("/notes/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        noteService.deleteNote(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/notes";
    }

}
