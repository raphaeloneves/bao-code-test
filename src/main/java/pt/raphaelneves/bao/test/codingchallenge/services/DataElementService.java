package pt.raphaelneves.bao.test.codingchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElement;
import pt.raphaelneves.bao.test.codingchallenge.repositories.DataElementRepository;

import java.util.List;

@Service
public class DataElementService {

    private final DataElementRepository repository;

    @Autowired
    public DataElementService(DataElementRepository repository) {
        this.repository = repository;
    }

    public List<DataElement> findAll() {
        return repository.findAll();
    }
}
