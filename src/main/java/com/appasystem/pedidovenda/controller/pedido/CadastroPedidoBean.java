package com.appasystem.pedidovenda.controller.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.date.ManipulacaoDataHora;
import com.appasystem.pedidovenda.event.PedidoAlteradoEvent;
import com.appasystem.pedidovenda.model.cliente.Cliente;
import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamentoItens;
import com.appasystem.pedidovenda.model.pedido.EnderecoEntrega;
import com.appasystem.pedidovenda.model.pedido.FormaPagamento;
import com.appasystem.pedidovenda.model.pedido.ItemPedido;
import com.appasystem.pedidovenda.model.pedido.ParcelaPedido;
import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.model.produto.Produto;
import com.appasystem.pedidovenda.model.usuario.Usuario;
import com.appasystem.pedidovenda.repository.Clientes;
import com.appasystem.pedidovenda.repository.CondicoesPagamentos;
import com.appasystem.pedidovenda.repository.Produtos;
import com.appasystem.pedidovenda.repository.Usuarios;
import com.appasystem.pedidovenda.service.CadastroPedidoService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;
import com.appasystem.pedidovenda.util.number.Money;
import com.appasystem.pedidovenda.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private List<Usuario> vendedores;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private CondicoesPagamentos condicoesPagamentos;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private Produtos produtos;
	
	private Produto produtoLinhaEditavel;
	private String sku;
	private Date dataBaseParcela; 
	private List<ParcelaPedido> parcelasPedido = new ArrayList<>();
	private boolean alterarDataBt;
	private boolean alterarValorBt;

	
	public CadastroPedidoBean() {
		limpar();
	}
	
	private void limpar(){
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	//Captura o pedido do evento CDI alterado por outra classe
	public void PedidoAlterado(@Observes PedidoAlteradoEvent event){
		this.pedido = event.getPedido();
	}
	
	public void salvar() {
		this.pedido.removerItemVazio();
		
		if(this.pedido.getDataBaseParcela() == null){
			this.pedido.setDataBaseParcela(new Date());
		}
		
		if(!this.parcelasPedido.isEmpty() || this.parcelasPedido != null)
			this.pedido.setParcelas(parcelasPedido);
		
		try{
			this.pedido = cadastroPedidoService.salvar(this.pedido);
			FacesUtil.addInfoMessage("Pedido salvo com Sucesso!");
		}finally{
			this.pedido.adicionarItemVazio();
		}
	}
	
	public void inicializar(){
		if(FacesUtil.isNotPostback()){
			vendedores = usuarios.raizes(); 
			
			this.pedido.adicionarItemVazio();
			
			this.recalcularPedido();
			
			if(this.pedido.getDataBaseParcela() != null){
				this.dataBaseParcela = new Date();
			}else{
				this.dataBaseParcela = this.pedido.getDataBaseParcela();
			}
			
			if(this.pedido.getId() != null)
				parcelasPedido = this.pedido.getParcelas();
			
			
			System.out.println(this.alterarDataBt);
		}
	}
	
	//faz o calculo dos totais do pedido
	public void recalcularPedido(){
		if(this.pedido != null){
			this.pedido.recalcularValorTotal();
		}
	}
	
	//Carrega produtos da linha editável
	public void carregarprodutoLinhaEditavel(){
		ItemPedido item = this.pedido.getItens().get(0);
		
		if(this.produtoLinhaEditavel != null){
			if(this.existeLinhaComProduto(this.produtoLinhaEditavel)){
				FacesUtil.addErrorMessage("Já existe o produto "+sku+" neste pedido!");
			}else{
				item.setProduto(produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = "";
				
				this.pedido.recalcularValorTotal();
			}
		}else{
			FacesUtil.addErrorMessage("O produto "+sku+" informado não existe!");
		}
		
	}
	
	//Carrega produtos da linha editavel por SKU
	public void carregarProdutoPorSku(){
		if(!this.sku.isEmpty() && this.sku != null){
			this.produtoLinhaEditavel = produtos.porSku(this.sku);
			this.carregarprodutoLinhaEditavel();
		}
	}
	
	//verifica se existe linha com o produto
	private boolean existeLinhaComProduto(Produto produto){
		
		boolean existeItem = false;
		
		for (ItemPedido item : this.pedido.getItens()) {
			if(item.getProduto().equals(produto)){
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	//Atualiza a quantidade de itens da linha
	public void atualizaQuantidade(ItemPedido item, int linha){
		
		if(item.getQuantidade() < 1){
			if(linha == 0){
				item.setQuantidade(1);
			}else{
				this.pedido.getItens().remove(linha);
			}
		}
		
		this.pedido.recalcularValorTotal();
	}
	
	//carrega as parcelas com base na condição de pagamento e a data base das parcelas
	public void recalcularParcelas(){
		
		parcelasPedido.clear();
		
		if(this.pedido.getValorTotal().equals(BigDecimal.ZERO)){
			FacesUtil.addErrorMessage("O pedido precisa ter um valor para gerar parcelas!");
		}else if(this.pedido.getCondicaoPagamento() == null){
			FacesUtil.addErrorMessage("É necessário informar a condição de pagamento!");
		}else if(this.pedido.getDataBaseParcela() == null){
			FacesUtil.addErrorMessage("É necessário informar a data base para geração das parcelas!");
		}else{
			
			List<CondicaoPagamentoItens> parcelas = this.pedido.getCondicaoPagamento().getCondicaoPagamentoItens();
			
			Money money = new Money();
			int quantidadeParcelas = parcelas.size();
			double valorParcela = money.converterMoney(this.pedido.getValorTotal()) / quantidadeParcelas;
			
			double valorParcelaD = money.converterMoedaDouble(valorParcela);
			
			double diferenca = 0;
			
			ParcelaPedido controle;
			ManipulacaoDataHora m = new ManipulacaoDataHora(null, null);
			
			for (CondicaoPagamentoItens item : parcelas) {
				controle = new ParcelaPedido();
				controle.setData(m.dataAdicionar(this.pedido.getDataBaseParcela(), item.getDias()));
				controle.setValor(new BigDecimal(valorParcela));
				controle.setAjuste(false);
				controle.setSequencia(item.getParcela());
				controle.setPedido(this.pedido);
				parcelasPedido.add(controle);
			}
			
			//Ajuste Parcela
			System.out.println("Valor parcela Double: "+valorParcelaD);
			System.out.println("Valor total double "+money.converterMoney(this.pedido.getValorTotal()));
			if((valorParcelaD * quantidadeParcelas) != money.converterMoney(this.pedido.getValorTotal())){
				diferenca = money.converterMoney(new BigDecimal((valorParcelaD * quantidadeParcelas) - money.converterMoney(this.pedido.getValorTotal())));
				System.out.println("Diferenca "+diferenca);
				
				if(diferenca > 0){
					parcelasPedido.get(0).setValor(new BigDecimal(valorParcelaD - diferenca));
				}else{
					parcelasPedido.get(0).setValor(new BigDecimal(valorParcelaD + diferenca));
				}
				parcelasPedido.get(0).setAjuste(true);
			}
			
			
			FacesUtil.addInfoMessage("Parcelas geradas com sucesso!");
		}	
	}
	
	public List<Produto> completarProduto(String nome){
		return produtos.porNome(nome);
	}
	
	public List<Cliente> completarCliente(String nome){
		return clientes.porNome(nome);
	}
	
	public List<CondicaoPagamento> completarCondicaoPagamento(String nome){
		return condicoesPagamentos.porNome(nome);
	}
	
	public FormaPagamento[] getFormasPagamento(){
		return FormaPagamento.values();
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando(){
		return this.pedido.getId() != null;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public Date getDataBaseParcela() {
		return dataBaseParcela;
	}

	public void setDataBaseParcela(Date dataBaseParcela) {
		this.dataBaseParcela = dataBaseParcela;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public List<ParcelaPedido> getParcelasPedido() {
		return parcelasPedido;
	}

	public void setParcelasPedido(List<ParcelaPedido> parcelasPedido) {
		this.parcelasPedido = parcelasPedido;
	}

	public boolean isAlterarDataBt() {
		return alterarDataBt;
	}
	 
    public void setAlterarDataBt(boolean alterarDataBt) {
        this.alterarDataBt = alterarDataBt;
    }
    
    public boolean isAlterarValorBt() {
		return alterarValorBt;
	}
	 
    public void setAlterarValorBt(boolean alterarValorBt) {
        this.alterarValorBt = alterarValorBt;
    }	
}