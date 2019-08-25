package pt.raphaelneves.bao.test.codingchallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElement;

public interface DataElementRepository extends JpaRepository<DataElement, String> { }
