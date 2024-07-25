package com.example.demo.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
