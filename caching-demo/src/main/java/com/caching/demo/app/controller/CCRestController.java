package com.caching.demo.app.controller;

import com.caching.demo.app.entity.ChemicalCompoundEntity;
import com.caching.demo.app.service.CCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CCRestController {

    @Autowired
    private CCService ccService;

    @GetMapping("/create")
    public ResponseEntity<String> create(@RequestParam("name") String ccName, @RequestParam("formula") String ccFormula){
        ccService.create(ccName,ccFormula);
        return ResponseEntity.ok("Chemical Compound added");
    }

    @GetMapping("/get")
    public ResponseEntity<ChemicalCompoundEntity> getChemical(@RequestParam("name") String name){
        return ResponseEntity.ok(ccService.getByName(name));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFormula(@RequestParam("name") String ccName, @RequestParam("formula") String ccFormula){
        ccService.updateFormula(ccName,ccFormula);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("name") String ccName){
        ccService.deleteCompound(ccName);
        return ResponseEntity.ok("Deleted");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll(){
        ccService.deleteAllCompound();
        return ResponseEntity.ok("All Deleted.");
    }
}
