package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeCustomRepository {

    List<EmployeeEntity> findCustomQuery();
}
