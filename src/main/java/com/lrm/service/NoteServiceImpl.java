package com.lrm.service;

import com.lrm.NotFoundException;
import com.lrm.dao.NoteRepository;
import com.lrm.po.Note;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 2020/11/02 csh  nanjing
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public String getLastNew() {
        List<Note> all = noteRepository.findAll();
        String result = "";
        if(!CollectionUtils.isEmpty(all)){
            Note max = all.stream().max(Comparator.comparingInt(o -> o.getId().intValue())).get();
            long begin = max.getCreateTime().getTime();
            long end = new Date().getTime();
            long eds = (end - begin)/1000/3600/24;
            //7 天=604800000 毫秒
            if(eds>=0&&eds<=7){
                result = max.getNotice();
            }
        }
        return result;
    }

    @Transactional
    @Override
    public Page<Note> listNote(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Note getNote(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public Note saveNote(Note note) {
        note.setCreateTime(new Date());
        return noteRepository.save(note);
    }

    @Transactional
    @Override
    public void deleteNote(Long id) {
        noteRepository.delete(id);
    }

    @Transactional
    @Override
    public Note updateNote(Long id, Note note) {
        Note t = noteRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该公告");
        }
        //添加日期
        note.setCreateTime(new Date());
        BeanUtils.copyProperties(note,t);
        return noteRepository.save(t);
    }


}
