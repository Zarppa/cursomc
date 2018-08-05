package com.guiPalma.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiPalma.cursomc.domain.Pedido;
import com.guiPalma.cursomc.repositories.PedidoRepository;
import com.guiPalma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}


