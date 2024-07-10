package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ComuneRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComuneService {
    @Autowired
    ComuneRepository comuneRepository;
    @Autowired
    ProvincieService provincieService;
    @Autowired
    ProvinciaRepository provinciaRepository;
    @PostConstruct
    public void init() {
        loadCsvData();
    }
    public void loadCsvData() {
        try (Reader provinciaReader = new FileReader("province-italiane.csv")) {
            List<Provincia> province = new CsvToBeanBuilder<Provincia>(provinciaReader)
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

            try (Reader comuneReader = new FileReader("comuni-italiani.csv")) {
                List<Comune> comuni = new CsvToBeanBuilder<Comune>(comuneReader)
                        .withType(Comune.class)
                        .withSeparator(';')
                        .withSkipLines(1)
                        .build()
                        .parse();

                for (Comune comune : comuni) {
                    List<Provincia> provinciaList = provinciaRepository.findByName(comune.getProvincia());

                    if (provinciaList.isEmpty()) {
                        throw new IllegalArgumentException("Provincia non trovata per il comune: " + comune.getName());
                    } else if (provinciaList.size() > 1) {

                        comune.setProvinciaList(provinciaList.get(0));
                    } else {
                        comune.setProvinciaList(provinciaList.get(0));
                    }
                }

                comuneRepository.saveAll(comuni);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore");
        }
    }
  public List<Comune> findByNameAndProvincia(String name, Provincia provincia){
        List<Comune> comuneList = new ArrayList<>(comuneRepository.findByNameAndProvincia(name, provincia));
       return comuneList;
    }
}
//    public List<Comune> findByProvincia(String provinciaName){
//        List<Comune> comuneList = new ArrayList<>();
//        if(comuneRepository.findByProvincia(provinciaName) != null){
//
//      comuneList.addAll(comuneRepository.findByProvincia(provinciaName) ) ;
//        } else {
//            System.out.println("Errore");
//            // da gestire meglio
//        }
//      return comuneList;
//    }

