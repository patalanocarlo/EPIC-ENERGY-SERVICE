package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ProvinciaRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/provincia")
public class ProvinceController {
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @GetMapping("/{provinciaId}")
    public List<Comune> getComuniByProvincia(@PathVariable UUID provinciaId) {
        return comuneService.findByProvinciaId(provinciaId);
    }

    @GetMapping
    public List<Provincia> getAllProvince() {
        List<Provincia> province = provinciaRepository.findAll();
        return province;
    }
}
