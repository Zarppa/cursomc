package com.guiPalma.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guiPalma.cursomc.domain.Categoria;
import com.guiPalma.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findById(Integer id);
}
