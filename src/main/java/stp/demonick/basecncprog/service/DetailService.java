package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.repository.DetailRepository;

import java.util.Collection;

@Service
public class DetailService {
    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }


    public Collection<Detail> findAllDetails() {
        return detailRepository.findByOrderByDrawingNumberAsc();
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
}
