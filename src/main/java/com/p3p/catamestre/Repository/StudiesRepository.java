package com.p3p.catamestre.Repository;

import com.p3p.catamestre.Domain.Studies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudiesRepository extends JpaRepository<Studies, Long> {

    Optional<Studies> findByCode(String code);

    Page<Studies> findAll(Pageable pageable);


    Optional<Studies> findByName(String name);

    List<Studies> findAllByTerm(String term);

    List<Studies> findAllByModality(Studies.Modality modality);

}
