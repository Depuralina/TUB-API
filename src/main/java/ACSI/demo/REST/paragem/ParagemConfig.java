package ACSI.demo.REST.paragem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParagemConfig {
    @Bean
    CommandLineRunner inicializarParagem(ParagemRepository repository) {
        return args -> {
//            Paragem paragem1 = new Paragem("Ponte de Prado","41.594079223736614", "-8.459348645833831");
//            Paragem paragem2 = new Paragem("Merelim S. Roque","41.58262647285251","-8.467006107577262 ");
//            Paragem paragem3 = new Paragem("caires","41.64222714799434"," -8.357663712817349");
//            Paragem paragem4 = new Paragem("25 de abril (parque infantil)","41.54800195302043","-8.419265442121052");
//            Paragem paragem5 = new Paragem("Bom jesus","41.554445132302604","-8.381090401263272");
//            repository.saveAll(List.of(paragem1, paragem2, paragem3, paragem4));
        };
    }

}
