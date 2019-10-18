package com.quem.arrisca.repository;

import com.quem.arrisca.model.ExcelFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<ExcelFile, Long> {
}
