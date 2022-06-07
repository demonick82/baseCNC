package stp.demonick.basecncprog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.repository.MemDetailRepository;
import stp.demonick.basecncprog.utils.TextFormat;

import java.io.*;
import java.util.Collection;

@Service
public class DetailService {
    private final MemDetailRepository detailRepository;


    public DetailService(MemDetailRepository store) {
        this.detailRepository = store;
    }

    public Collection<Detail> findAllDetails() {
        return detailRepository.findAll();
    }

    public void save(Detail detail) {
        detailRepository.save(detail);
    }

    public Detail findDetailById(int id) {
        return detailRepository.findById(id);
    }
}
