package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Employee;
import javafx.collections.ObservableList;

public interface EmployeeBo extends SuperBo {

    boolean persist(Employee employee);
    boolean update(Employee employee);

    boolean delete(String id);

    Employee search(String id);

    ObservableList<Employee> searchAll();

    String getLatestId();

    int count();
}
