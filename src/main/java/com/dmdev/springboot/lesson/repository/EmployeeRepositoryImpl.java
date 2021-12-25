package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findCustomQuery() {
        return Collections.emptyList();
    }
}
