package com.guiPalma.cursomc;

import java.text.SimpleDateFormat;
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
import com.guiPalma.cursomc.domain.ItemPedido;
import com.guiPalma.cursomc.domain.Pagamento;
import com.guiPalma.cursomc.domain.PagamentoComBoleto;
import com.guiPalma.cursomc.domain.PagamentoComCartao;
import com.guiPalma.cursomc.domain.Pedido;
import com.guiPalma.cursomc.domain.Produto;
import com.guiPalma.cursomc.domain.enums.EstadoPagamento;
import com.guiPalma.cursomc.domain.enums.TipoCliente;
import com.guiPalma.cursomc.repositories.CategoriaRepository;
import com.guiPalma.cursomc.repositories.CidadeRepository;
import com.guiPalma.cursomc.repositories.ClienteRepository;
import com.guiPalma.cursomc.repositories.EnderecoRepository;
import com.guiPalma.cursomc.repositories.EstadoRepository;
import com.guiPalma.cursomc.repositories.ItemPedidoRepository;
import com.guiPalma.cursomc.repositories.PagamentoRepository;
import com.guiPalma.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
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
		Estado est3 = new Estado(null,"Rio Grande do Sul");
		
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Porto Alegre", est2);
		Cidade c5 = new Cidade(null, "Canoas", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		est3.getCidades().addAll(Arrays.asList(c4,c5));
		
		estadoRepo.save(Arrays.asList(est1,est2,est3));
		cidadeRepo.save(Arrays.asList(c1,c2,c3,c4,c5));
		
		Cliente cli1 = new Cliente(null,"Guilherme Palma","gomes.lmc@gmail.com", "02828653064", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("51991100404", "5130313790"));
		Endereco e1 =  new Endereco(null, "La Salle", "278", "casa", "Mal. Rondon", "92020230", cli1, c5);
		Endereco e2 =  new Endereco(null, "La Salle", "278", "casa", "Mal. Rondon", "92020230", cli1, c4);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(Arrays.asList(cli1));
		enderecoRepo.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepo.save(Arrays.asList(ped1, ped2));
        pagamentoRepo.save(Arrays.asList(pagto1, pagto2));
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll((Arrays.asList(ip1)));
        p2.getItens().addAll((Arrays.asList(ip3)));
        p3.getItens().addAll((Arrays.asList(ip2)));

        itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
