package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.SupplierBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.SupplierDao;
import com.clothify.pos.dto.Supplier;
import com.clothify.pos.entity.SupplierEntity;
import com.clothify.pos.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {

    private final SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean persist(Supplier supplier) {
        return supplierDao.persist(mapper.map(supplier, SupplierEntity.class));
    }

    @Override
    public boolean update(Supplier supplier) {
        return supplierDao.update(mapper.map(supplier, SupplierEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public Supplier search(String id) {
        return mapper.map(supplierDao.search(id),Supplier.class);
    }

    @Override
    public ObservableList<Supplier> searchAll() {
        ObservableList<SupplierEntity> supplierEntities = supplierDao.searchAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        supplierEntities.forEach(entity ->
               suppliers.add(mapper.map(entity,Supplier.class))
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
    public int count() {
        return supplierDao.count();
    }
}
