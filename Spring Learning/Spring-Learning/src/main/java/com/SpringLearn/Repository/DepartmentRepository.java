package com.SpringLearn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringLearn.Entities.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer > {

}
