package com.elielnascimento.crud_restful_escola.controller;

        import com.elielnascimento.crud_restful_escola.model.Aluno;
        import com.elielnascimento.crud_restful_escola.model.Curso;
        import com.elielnascimento.crud_restful_escola.repository.AlunoRepository;
        import com.elielnascimento.crud_restful_escola.repository.CursoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Aluno aluno) {

        if (aluno.getCurso() != null && aluno.getCurso().getId() != null) {
            Optional<Curso> cursoOpt = cursoRepository.findById(aluno.getCurso().getId());
            if (cursoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso com ID " + aluno.getCurso().getId() + " não encontrado.");
            }

            aluno.setCurso(cursoOpt.get());
        }

        try {
            Aluno novoAluno = alunoRepository.save(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar aluno: " + e.getMessage());
        }
    }


    @GetMapping
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        return alunoRepository.findById(id)
                .map(aluno -> {

                    aluno.setNome(alunoDetails.getNome());
                    aluno.setMatricula(alunoDetails.getMatricula());
                    aluno.setStatus(alunoDetails.getStatus());


                    if (alunoDetails.getCurso() != null && alunoDetails.getCurso().getId() != null) {
                        Optional<Curso> cursoOpt = cursoRepository.findById(alunoDetails.getCurso().getId());
                        if (cursoOpt.isEmpty()) {

                            throw new RuntimeException("Curso com ID " + alunoDetails.getCurso().getId() + " não encontrado.");
                        }
                        aluno.setCurso(cursoOpt.get());
                    } else if (alunoDetails.getCurso() == null) {
                        aluno.setCurso(null);
                    }

                    Aluno alunoAtualizado = alunoRepository.save(aluno);
                    return ResponseEntity.ok(alunoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    alunoRepository.delete(aluno);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}