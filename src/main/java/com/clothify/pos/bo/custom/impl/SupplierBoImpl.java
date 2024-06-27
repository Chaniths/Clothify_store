package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.SupplierBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.SupplierDao;
import com.clothify.pos.dto.Supplier;
import com.clothify.pos.entity.SupplierEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {

    private final SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public boolean persist(Supplier supplier) {
        System.out.println("Bo");
        SupplierEntity supplierEntity = mapper.convertValue(supplier, SupplierEntity.class);
        System.out.println("Entity:"+supplierEntity);
        return supplierDao.persist(supplierEntity);
    }

    @Override
    public boolean update(Supplier supplier) {
        return supplierDao.update(mapper.convertValue(supplier, SupplierEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public Supplier search(String id) {
           return mapper.convertValue(supplierDao.search(id),Supplier.class);
    }

    @Override
    public ObservableList<Supplier> searchAll() {
        ObservableList<SupplierEntity> supplierEntities = supplierDao.searchAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        supplierEntities.forEach(entity ->
               suppliers.add(mapper.convertValue(entity,Supplier.class))
                );
        return suppliers;
    }

    @Override
    public ObservableList<String> getAllIds() {
        return supplierDao.getAllIds();
    }

    @Override
    public String getLatestId() {
        return supplierDao.getLatestId();
    }

    @Override
    public long count() {
        return supplierDao.count();
    }
}
