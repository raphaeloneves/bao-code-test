package pt.raphaelneves.bao.test.codingchallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElementGroup;

public interface DataElementGroupRepository extends JpaRepository<DataElementGroup, String> { }
