package com.lrm.service;

import com.lrm.dao.NoteRepository;
import com.lrm.po.NoteNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2020/11/02 csh  nanjing
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public String getLastNew() {
        List<NoteNew> all = noteRepository.findAll();
        StringBuffer s = new StringBuffer();
        for (NoteNew noteNew:all
             ) {
            s.append(noteNew);
        }

        return s.toString();
    }
}
