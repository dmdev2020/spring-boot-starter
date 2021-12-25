package com.dmdev.springboot.lesson.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeNameView {

    @Value("#{target.id + ' ' + target.firstName}")
    String getFirstName();

    String getLastName();
}
