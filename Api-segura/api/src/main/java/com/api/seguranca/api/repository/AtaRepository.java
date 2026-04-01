package com.api.seguranca.api.repository;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.seguranca.api.model.AtaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.api.seguranca.api.model.ColaboradorEntity;
import java.util.Optional;




public interface AtaRepository extends JpaRepository<AtaEntity, Integer> {

    Optional<AtaEntity> findByIdAndWorkshopId(Integer id, Integer workshopId);
    @Query("""
        SELECT DISTINCT c 
        FROM AtaEntity a
        JOIN a.colaboradores c
        ORDER BY c.nome ASC
    """)
    List<ColaboradorEntity> listarColaboradoresOrdenados();

    @Query("""
        SELECT a 
        FROM AtaEntity a
        WHERE LOWER(a.workshop.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
    """)
    List<AtaEntity> findByWorkshopNome(@Param("nome") String nome);

    @Query("""
        SELECT a 
        FROM AtaEntity a
        WHERE DATE(a.workshop.dataRealizacao) = :data
    """)
    List<AtaEntity> findByWorkshopData(@Param("data") LocalDate data);
}
