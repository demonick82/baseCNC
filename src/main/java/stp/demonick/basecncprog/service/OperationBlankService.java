package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.OperationBlank;

import java.io.IOException;

@Service
public class OperationBlankService {
    public void saveBlank(String comment, MultipartFile file) {

        try {
            byte[] fileBytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  /*      System.out.println("path= " + path);
        System.out.println("comment= " + comment);
        OperationBlank blank = new OperationBlank(comment, path);*/
    }
}
