package com.elielnascimento.crud_restful_escola.controller;

        import com.elielnascimento.crud_restful_escola.model.Curso;
        import com.elielnascimento.crud_restful_escola.repository.CursoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        Curso novoCurso = cursoRepository.save(curso);
        return ResponseEntity.status(201).body(novoCurso);
    }


    @GetMapping
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoDetails.getNome());
                    curso.setCargaHoraria(cursoDetails.getCargaHoraria());
                    Curso cursoAtualizado = cursoRepository.save(curso);
                    return ResponseEntity.ok(cursoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    cursoRepository.delete(curso);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}