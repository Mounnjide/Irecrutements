package com.irecrutements.spring.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irecrutements.spring.models.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer>{
//	public Page<Ville> findByNomVille(String e, Pageable pageable);
	@Query("select e from Ville e where e.nomVille like :x")
	public Page<Ville> chercher(@Param("x") String e, Pageable pageable);
}
