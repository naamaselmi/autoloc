package tn.esprit.naamaselmi4cce11.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.naamaselmi4cce11.domain.Vehicule;
import tn.esprit.naamaselmi4cce11.service.IVehiculeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicule")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @PostMapping ("add")
    public Vehicule add(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @GetMapping("get/{idVehicule}")
    public Vehicule get(@PathVariable long idVehicule) {
        return vehiculeService.findById(idVehicule);
    }
}
