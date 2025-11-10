package com.elielnascimento.crud_restful_escola.repository;


        import com.elielnascimento.crud_restful_escola.model.Curso;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}