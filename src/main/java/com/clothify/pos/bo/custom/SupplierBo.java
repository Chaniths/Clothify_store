package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Supplier;
import javafx.collections.ObservableList;

public interface SupplierBo extends SuperBo {

    boolean persist(Supplier supplier);

    boolean update(Supplier supplier);

    boolean delete(String id);

    Supplier search(String id);

    ObservableList<Supplier> searchAll();

    ObservableList<String> getAllIds();

    String getLatestId();

    long count();
}
