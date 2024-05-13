package com.caching.demo.app.service;

import com.caching.demo.app.entity.ChemicalCompoundEntity;
import com.caching.demo.app.repository.CCRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@CacheConfig(cacheNames={"chemical_compounds"})
public class CCService {

    @Autowired
    private CCRepository repository;

    @CachePut(value = "chemical_compounds", key="#root.args[0]")
    public ChemicalCompoundEntity create(String ccName, String ccFormula) {
       return repository.save(ChemicalCompoundEntity.builder().ccName(ccName).ccFormula(ccFormula).build());
    }

    @Cacheable(value = "chemical_compounds", key="#root.args[0]")
    public ChemicalCompoundEntity getByName(String name) {
        return repository.findByCcName(name).get();
    }

    @CachePut(value = "chemical_compounds", key="#root.args[0]")
    public ChemicalCompoundEntity updateFormula(String ccName, String ccFormula) {
        Optional<ChemicalCompoundEntity> compound = repository.findByCcName(ccName);
        if(compound.isPresent()){
            ChemicalCompoundEntity chemComp = compound.get();
            chemComp.setCcFormula(ccFormula);
            return repository.save(chemComp);
        }
        return null;

    }

    @Transactional
    @CacheEvict(value="chemical_compounds", key="#root.args[0]")
    public void deleteCompound(String ccName) {
        repository.deleteByCcName(ccName);
    }

    @CacheEvict(value="chemical_compounds", allEntries = true, key="#root.args[0]")
    public void deleteAllCompound() {
        repository.deleteAll();
    }

}
