package ACSI.demo.REST.movimento;

import ACSI.demo.REST.brt.Brt;
import ACSI.demo.REST.brt.BrtRepository;
import ACSI.demo.REST.camara.Camara;
import ACSI.demo.REST.camara.CamaraRepository;
import ACSI.demo.REST.paragem.Paragem;
import ACSI.demo.REST.paragem.ParagemRepository;
import ACSI.demo.REST.viagem.EstadoViagem;
import ACSI.demo.REST.viagem.Viagem;
import ACSI.demo.REST.viagem.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentoService {

    private final MovimentoRepository movimentoRepository;
    private final CamaraRepository camaraRepository;

    private final BrtRepository brtRepository;

    private final ViagemRepository viagemRepository;
    @Autowired
    public MovimentoService(MovimentoRepository movimentoRepository, CamaraRepository camaraRepository, BrtRepository brtRepository,ViagemRepository viagemRepository ) {
        this.movimentoRepository = movimentoRepository;
        this.camaraRepository = camaraRepository;
        this.brtRepository = brtRepository;
        this.viagemRepository = viagemRepository;
    }

    public List<Movimento> getMovimentos() {
        return movimentoRepository.findAll();
    }

    @Transactional
    public void addNewMovimento(MovimentoCamara movimentoCamara) {
        Long camara_id = movimentoCamara.getId_camera();

        Camara existingCamara = camaraRepository.findById(camara_id).orElseThrow(() -> new IllegalStateException("Camara not found"));

        Brt brt = brtRepository.findBrtByCamera(camara_id).orElseThrow(() -> new IllegalStateException("A Camara não está em nenhum BRT"));

        System.out.println("\n \n\n\n\n " + brt.getMatricula() + " \n \\n\\n\\n\\n");
        EstadoViagem estadoViagem = EstadoViagem.EM_VIAGEM;

        Viagem viagem = viagemRepository.findViagemByBrt(brt.getId(), estadoViagem).orElseThrow(() -> new IllegalStateException("O brt não está em Viagem"));

        System.out.println("\n \n\n\n\n " + viagem.getRota().getNome() + " \n \\n\\n\\n\\n");



        //ir buscar a paragem (de alguma forma, não importa como ajaajaa?)
        //ir buscar o tempo de paragem, de alguma forma tambem
        //ir à viagem e adicionar o movimento à sua lista




        Movimento movimento = new Movimento(null, movimentoCamara.getEntradaPassageiros(), movimentoCamara.getSaidaPassageiros(), 12.55);
//
        movimentoRepository.save(movimento);

        viagem.getMovimentos().add(movimento);

//        Optional<Movimento> paragemByNome = movimentoRepository.findById(movimento.getId());
//
//        if (paragemByNome.isPresent()) {
//            throw new IllegalStateException("Ja existe uma Paragem com esse nome!");
//        }
//
//        movimentoRepository.save(movimento);
    }

    public void deleteMovimento(Long id) {
        boolean bool = movimentoRepository.existsById(id);

        if (!bool) {
            throw new IllegalStateException(("Não existe nenhum Movimento com esse Id"));

        }

        movimentoRepository.deleteById(id);
    }
    @Transactional
    public void uptadeMovimento(Long id, Movimento movimento) {
        Movimento existingMovimento = movimentoRepository.findById(id).orElseThrow(() -> new IllegalStateException("Movimento not found"));

        existingMovimento.setParagem(movimento.getParagem());
        existingMovimento.setEntradaPassageiros(movimento.getEntradaPassageiros());
        existingMovimento.setSaidaPassageiros(movimento.getSaidaPassageiros());
        existingMovimento.setTempoParagem(movimento.getTempoParagem());
    }

    public Movimento getMovimentoById(Long id) {
        Movimento existingMovimento = movimentoRepository.findById(id).orElseThrow(() -> new IllegalStateException("Movimento not found"));


        return existingMovimento;

    }

}
