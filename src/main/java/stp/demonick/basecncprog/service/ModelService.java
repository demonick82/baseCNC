package stp.demonick.basecncprog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.repository.MemStore;

import java.io.*;
import java.util.Collection;

@Service
public class ModelService {
    private final MemStore store;

    public ModelService(MemStore store) {
        this.store = store;
    }

    public Collection<Detail> findAllDetails() {
        return store.findAllModels();
    }

    public void save(Detail detail) {
        store.save(detail);
    }

    public void addModel(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"Cp1251"));
            reader.lines().forEach(builder::append);
            Detail model3 = mapper.readValue(builder.toString().replace("\\","\\\\"), Detail.class);
            store.save(model3);
            System.out.println(model3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
