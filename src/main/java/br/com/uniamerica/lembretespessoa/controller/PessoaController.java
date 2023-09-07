package br.com.uniamerica.lembretespessoa.controller;

import br.com.uniamerica.lembretespessoa.entity.PessoaEntity;
import br.com.uniamerica.lembretespessoa.repository.PessoaRepository;
import br.com.uniamerica.lembretespessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping("/{id}")
    public ResponseEntity<PessoaEntity> teste(@PathVariable Long id) {
        try {
            PessoaEntity pessoa = pessoaService.findById(id);
            return ResponseEntity.ok(pessoa);
        } catch (Exception e) {
            return null;
        }
    }


    @GetMapping ("/nome/{nome}")
        public ResponseEntity <?> findByName (@PathVariable ("nome") final String nome){
            final PessoaEntity pessoa = this.pessoaRepository.findByNome(nome);

            return ResponseEntity.ok(this.pessoaRepository.findByNome(nome));
        }


    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.pessoaRepository.findAll());
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final PessoaEntity pessoa){
        try {
            this.pessoaService.validaPessoa(pessoa);
            return ResponseEntity.ok("Registro cadastrado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final PessoaEntity pessoa){
        try {
            this.pessoaService.validaPessoa(pessoa);
            return ResponseEntity.ok("Registro atualizado com sucesso. ");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id, PessoaEntity pessoa){
        try {
            this.pessoaService.deletaPessoa(pessoa);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
