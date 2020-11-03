package com.lrm.dao;

import com.lrm.po.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by limi on 2017/10/20.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Override
    List<Note> findAll();
}
