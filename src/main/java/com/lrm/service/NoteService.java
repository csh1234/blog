package com.lrm.service;

import com.lrm.po.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 * 2020/11/02 csh  nanjing
 */
public interface NoteService {

    public String getLastNew();

    Page<Note> listNote(Pageable pageable);

    Note getNote(Long id);

    Note saveNote(Note Note);

    Note updateNote(Long id, Note Note);

    void deleteNote(Long id);

}
