package com.pj.service;

import com.pj.model.Appuser;

import java.util.List;
import java.util.Optional;


public interface AppuserService {
    List<Appuser> findAll();

    Optional<Appuser> findById(String id);

    Appuser insertAppuser(Appuser appuser);

    void insertAllAppuser(Iterable<Appuser> appuser);

    Appuser updateAppuser(Appuser appuser);

    void deleteAppuser(Appuser appuser);

    void deleteById(String id);

    void deleteAll();
}
