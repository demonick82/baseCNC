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
    private final TextFormat format;


    public DetailService(MemDetailRepository store, TextFormat format) {
        this.detailRepository = store;
        this.format = format;
    }

    public Collection<Detail> findAllDetails() {
        return detailRepository.findAll();
    }

    public void save(Detail detail) {
        detailRepository.save(detail);
    }

    public void addModel(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Cp1251"));
            reader.lines().forEach(builder::append);
            Detail model3 = mapper.readValue(format.updateJson(builder), Detail.class);
            model3.setPartName(format.getPartName(model3.getPath()));
            detailRepository.save(model3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Detail findDetailById(int id) {
        return detailRepository.findById(id);
    }
}
