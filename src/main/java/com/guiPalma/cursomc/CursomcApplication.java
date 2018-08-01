package com.guiPalma.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guiPalma.cursomc.domain.Categoria;
import com.guiPalma.cursomc.domain.Cidade;
import com.guiPalma.cursomc.domain.Cliente;
import com.guiPalma.cursomc.domain.Endereco;
import com.guiPalma.cursomc.domain.Estado;
import com.guiPalma.cursomc.domain.Produto;
import com.guiPalma.cursomc.domain.enums.TipoCliente;
import com.guiPalma.cursomc.repositories.CategoriaRepository;
import com.guiPalma.cursomc.repositories.CidadeRepository;
import com.guiPalma.cursomc.repositories.ClienteRepository;
import com.guiPalma.cursomc.repositories.EnderecoRepository;
import com.guiPalma.cursomc.repositories.EstadoRepository;
import com.guiPalma.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.save(Arrays.asList(cat1,cat2));
		produtoRepo.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepo.save(Arrays.asList(est1,est2));
		cidadeRepo.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Guilherme Palma","gomes.lmc@gmail.com", "02828653064", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("51991100404", "5130313790"));
		Endereco e1 =  new Endereco(null, "La Salle", "278", "casa", "Mal. Rondon", "92020230", cli1, c1);
		Endereco e2 =  new Endereco(null, "La Salle", "278", "casa", "Mal. Rondon", "92020230", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(Arrays.asList(cli1));
		enderecoRepo.save(Arrays.asList(e1,e2));
	}
}
