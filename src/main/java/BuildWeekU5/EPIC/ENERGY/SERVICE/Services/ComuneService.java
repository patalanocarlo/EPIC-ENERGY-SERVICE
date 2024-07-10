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
import java.util.Objects;
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
               } else if (provincia.getName().equals("La-Spezia")) {
                 provincia.setName("La Spezia");
             } else if (provincia.getName().equals("Reggio-Emilia")) {
                 provincia.setName("Reggio Emilia");
             } else if (provincia.getName().equals("Reggio-Calabria")) {
                 provincia.setName("Reggio Calabria");
             } else if (provincia.getName().equals("Forli-Cesena")) {
                 provincia.setName("Forlì Cesena");
             } else if (provincia.getName().equals("Pesaro-Urbino")) {
                 provincia.setName("Pesaro e Urbino");
             } else if (provincia.getName().equals("Ascoli-Piceno")) {
                 provincia.setName("Ascoli Piceno");
             } else if (provincia.getName().equals("Vibo-Valentia")) {
                 provincia.setName("Vibo Valentia");
             }
            }
            Provincia verbanoCusioOssola = new Provincia();
            verbanoCusioOssola.setName("Verbano-Cusio-Ossola");
            verbanoCusioOssola.setSigla("VCO");
            verbanoCusioOssola.setRegione("Piemonte");
            province.add(verbanoCusioOssola);
            Provincia Aosta = new Provincia();
            Aosta.setName("Aosta");
            Aosta.setSigla("AO");
            Aosta.setRegione("Valle D'Aosta");
            province.add(Aosta);

            Provincia MonzaBrianza = new Provincia();
            MonzaBrianza.setName("Monza e della Brianza");
            MonzaBrianza.setSigla("MB");
            MonzaBrianza.setRegione("Lombardia");
            province.add(MonzaBrianza);

Provincia sudSardegna = new Provincia();
sudSardegna.setName("Sud Sardegna");
sudSardegna.setSigla("SU");
sudSardegna.setRegione("Sardegna");
province.add(sudSardegna);
            provinciaRepository.saveAll(province);

            try (Reader comuneReader = new FileReader("comuni-italiani.csv")) {
                List<Comune> comuni = new CsvToBeanBuilder<Comune>(comuneReader)
                        .withType(Comune.class)
                        .withSeparator(';')
                        .withSkipLines(1)
                        .build()
                        .parse();
                for (Comune comune : comuni) {
                   if (Objects.equals(comune.getProvincia(), "Valle d'Aosta/Vallée d'Aoste")) {
                        comune.setProvincia("Aosta");} else if (Objects.equals(comune.getProvincia(), "Bolzano/Bozen")) {
comune.setProvincia("Bolzano");
                   } else if (Objects.equals(comune.getProvincia(), "Reggio nell'Emilia")) {
                     comune.setProvincia("Reggio Emilia");
                   } else if (Objects.equals(comune.getProvincia(), "Forlì-Cesena")) {
                       comune.setProvincia("Forlì Cesena");
                   }
                }
                for (Comune comune : comuni) {

                    List<Provincia> provinciaList = provinciaRepository.findByName(comune.getProvincia());

                    if (provinciaList.isEmpty()) {
                        System.out.println("Provincia non trovata per il comune: " + comune.getName());
                        continue;
                    } else if (provinciaList.size() > 1) {
                        comune.setProvinciaList(provinciaList.get(0));
                    } else if (Objects.equals(comune.getProvincia(), "Valle d'Aosta/Vallée d'Aoste")) {
                        comune.setProvincia("Aosta");
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
        List<Comune> comuneList = new ArrayList<>(comuneRepository.findByNameAndProvincia(name, provincia.getName()));
       return comuneList;
    }
}


