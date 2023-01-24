package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.repository.DetailRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Service
public class DetailService {
    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }


    public List<Detail> findAllDetails() {
        return sortingByNumber(detailRepository.findAll());
    }

    public void save(Detail detail) {
        detailRepository.save(detail);
    }


    public Detail findDetailById(long id) {
        return detailRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Detail not found"));
    }

    public void deleteDetail(long id) {
        detailRepository.deleteById(id);
    }

    public byte[] downLoadDrawing(Detail detail) {
        try (FileInputStream fis = new FileInputStream(detail.getDrawing().getPath())) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Detail> findDetailByNumber(String number) {
        return sortingByNumber(detailRepository.findByDrawingNumberContainingIgnoreCase(number));
    }

    private List<Detail> sortingByNumber(List<Detail> list) {
        list.sort(Comparator.comparing(Detail::getDrawingNumber, String.CASE_INSENSITIVE_ORDER));
        return list;
    }
}
