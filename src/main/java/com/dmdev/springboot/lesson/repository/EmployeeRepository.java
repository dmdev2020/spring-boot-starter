package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByFirstNameContaining(String firstName);

//    @Query("select e from EmployeeEntity e where e.firstName = :name and e.salary = :salary")
    @Query(value = "select e.* from employee e where e.first_name = :name and e.salary = :salary",
            nativeQuery = true)
    List<EmployeeEntity> findAllByFirstNameAndSalary(@Param("name") String firstName, @Param("salary") Integer salary);
}
