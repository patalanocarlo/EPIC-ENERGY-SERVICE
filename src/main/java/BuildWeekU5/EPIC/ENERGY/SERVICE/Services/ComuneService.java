package BuildWeekU5.EPIC.ENERGY.SERVICE.Services;

import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Comune;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Provincia;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Repository.ComuneRepository;
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
    @PostConstruct
    public void init() {
        loadCsvData();
    }
    public void loadCsvData() {
        try (Reader reader = new FileReader("comuni-italiani.csv")) {
            List<Comune> comuni = new CsvToBeanBuilder<Comune>(reader)
                    .withType(Comune.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .build()
                    .parse();
            comuneRepository.saveAll(comuni);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore");
        }

    }
}
