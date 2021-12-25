package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.IntegrationTestBase;
import com.dmdev.springboot.lesson.dto.EmployeeFilter;
import com.dmdev.springboot.lesson.entity.EmployeeEntity;
import com.dmdev.springboot.lesson.projection.EmployeeNameView;
import com.dmdev.springboot.lesson.projection.EmployeeNativeView;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.dmdev.springboot.lesson.entity.QEmployeeEntity.employeeEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeRepositoryTest extends IntegrationTestBase {

    private static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindById() {
        Optional<EmployeeEntity> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName() {
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContaining("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomQuery() {
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .build();
        if (filter.getFirstName() != null) {

        }
        if (filter.getLastName() != null) {

        }
        List<EmployeeEntity> customQuery = employeeRepository.findByFilter(filter);
        assertThat(customQuery, hasSize(1));
    }

    @Test
    void testQuerydslPredicates() {
        BooleanExpression predicate = employeeEntity.firstName.containsIgnoreCase("ivaN")
                .and(employeeEntity.salary.goe(1000));
        Page<EmployeeEntity> allValues = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(allValues.getContent(), hasSize(1));
    }

}
