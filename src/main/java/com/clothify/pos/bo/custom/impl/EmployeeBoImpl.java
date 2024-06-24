package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.EmployeeBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.EmployeeDao;
import com.clothify.pos.dto.Employee;
import com.clothify.pos.entity.EmployeeEntity;
import com.clothify.pos.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class EmployeeBoImpl implements EmployeeBo {

    private final EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean persist(Employee employee) {
        return employeeDao.persist(mapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(mapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return employeeDao.delete(id);
    }

    @Override
    public Employee search(String id) {
       return mapper.map(employeeDao.search(id),Employee.class);
    }

    @Override
    public ObservableList<Employee> searchAll() {
        ObservableList<EmployeeEntity> employeeEntities = employeeDao.searchAll();
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employeeEntities.forEach(entity -> employees.add(mapper.map(entity, Employee.class)));
        return employees;
    }

    @Override
    public String getLatestId() {
        return employeeDao.getLatestId();
    }

    @Override
    public int count() {
        return employeeDao.count();
    }
}
