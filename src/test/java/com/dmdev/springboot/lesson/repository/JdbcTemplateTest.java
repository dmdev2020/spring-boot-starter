package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.IntegrationTestBase;
import com.dmdev.springboot.lesson.entity.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcTemplateTest extends IntegrationTestBase {

    private static final String INSERT_SQL = "insert into company (name) values (?);";
    private static final String DELETE_RETURNING_SQL = "DELETE FROM company WHERE name = ? RETURNING *";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void testInsert() {
        int result = jdbcOperations.update(INSERT_SQL, "Microsoft");
        assertEquals(1, result);
    }

    @Test
    void testReturning() {
        String companyName = "Microsoft";
        jdbcOperations.update(INSERT_SQL, companyName);


        List<CompanyEntity> result = jdbcOperations.query(DELETE_RETURNING_SQL,
                (rs, rowNum) -> CompanyEntity.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build(), companyName);
        assertThat(result, hasSize(1));
    }
}
