package com.elielnascimento.crud_restful_escola.repository;


        import com.elielnascimento.crud_restful_escola.model.Aluno;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}