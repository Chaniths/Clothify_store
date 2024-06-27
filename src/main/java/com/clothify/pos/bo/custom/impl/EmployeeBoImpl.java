package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.EmployeeBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.EmployeeDao;
import com.clothify.pos.dto.Employee;
import com.clothify.pos.entity.EmployeeEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeBoImpl implements EmployeeBo {

    private final EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean persist(Employee employee) {
        return employeeDao.persist(mapper.convertValue(employee, EmployeeEntity.class));
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(mapper.convertValue(employee, EmployeeEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return employeeDao.delete(id);
    }

    @Override
    public Employee search(String id) {
       return mapper.convertValue(employeeDao.search(id),Employee.class);
    }

    @Override
    public ObservableList<Employee> searchAll() {
        ObservableList<EmployeeEntity> employeeEntities = employeeDao.searchAll();
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employeeEntities.forEach(entity -> employees.add(mapper.convertValue(entity, Employee.class)));
        return employees;
    }

    @Override
    public String getLatestId() {
        return employeeDao.getLatestId();
    }

    @Override
    public long count() {
        return employeeDao.count();
    }
}
