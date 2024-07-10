package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ProvinciaRepository;
import BuildWeekU5.EPIC.ENERGY.SERVICE.exceptions.NotFoundException;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvincieService {
    @Autowired
    ProvinciaRepository provinciaRepository;
    @PostConstruct
    public void init() {
        loadCsvData();
    }
    public void loadCsvData() {
        try (Reader reader = new FileReader("province-italiane.csv")) {
            List<Provincia>province = new CsvToBeanBuilder<Provincia>(reader)
                    .withType(Provincia.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .build()
                    .parse();
            for (Provincia provincia : province) {
                if (provincia.getSigla().equals("Roma")) {
                    provincia.setSigla("RM");
                }
            }
           provinciaRepository.saveAll(province);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore");
        }

    }
    public Provincia findBySigla(String sigla){
        return provinciaRepository.findBySigla(sigla).orElseThrow(()-> new NotFoundException("Provincia non trovata"));
    }
    public List<Provincia> findByProvincia(String provincia){
        List<Provincia> provincias = new ArrayList<>();
                provincias.add(provinciaRepository.findByProvincia(provincia).orElseThrow(()-> new NotFoundException("Provincia non trovata")));
        return provincias;
    }
}
