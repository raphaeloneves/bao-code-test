package pt.raphaelneves.bao.test.codingchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElementGroup;
import pt.raphaelneves.bao.test.codingchallenge.repositories.DataElementGroupRepository;

import java.util.List;

@Service
public class DataElementGroupService {

    private final DataElementGroupRepository repository;

    @Autowired
    public DataElementGroupService(DataElementGroupRepository repository) {
        this.repository = repository;
    }

    public List<DataElementGroup> findAll() {
        return repository.findAll();
    }
}
