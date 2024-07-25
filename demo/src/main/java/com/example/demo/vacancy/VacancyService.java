package com.example.demo.vacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    public List<Vacancy> getAllTasks(){
        return vacancyRepository.findAll();
    }

    public Optional<Vacancy> getVacancyById(Long id){
        return vacancyRepository.findById(id);
    }

    public Vacancy saveVacancy(Vacancy task){
        return vacancyRepository.save(task);
    }

    public void deleteVacancy(Long id){
        vacancyRepository.deleteById(id);
    }

    public List<Vacancy> getFilteredVacancies(String search, String jobType, Double minSalary, Double maxSalary){
        Stream<Vacancy> result = vacancyRepository.findAll().stream();
        if(search != null) {
            if (!search.isEmpty()) {
                result = result.filter(x -> x.getTitle().toLowerCase().contains(search.toLowerCase()));
            }
        }
        if(jobType != null && !jobType.isEmpty()){
            result = result.filter(x->x.getJobType().equals(jobType));
        }
        if(minSalary != null){
            result = result.filter(x->x.getSalary() >= minSalary);
        }
        if(maxSalary != null){
            result = result.filter(x->x.getSalary() <= maxSalary);
        }
        return result.collect(Collectors.toList());
    }
}
