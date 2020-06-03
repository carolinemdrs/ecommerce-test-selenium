package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {
	@Test
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		assertThat(homePage.contarProdutos(), is(8));
	}
	
	@Test
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		System.out.println(produtosNoCarrinho);
		assertThat(produtosNoCarrinho, is(0));
	}
	ProdutoPage produtoPage;
	@Test
	public void testValidarDetalhesDoProduto_DescricaoEValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
	
		System.out.println(nomeProduto_HomePage);
		System.out.println(precoProduto_HomePage);
		 
		produtoPage = homePage.clicarProduto(indice);
		
		String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
		
		System.out.println(nomeProduto_ProdutoPage);
		System.out.println(precoProduto_ProdutoPage);
	
		assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
		assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
					
	}
	
	LoginPage loginPage;
	
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		
		loginPage = homePage.clicarBotaoSignIn();
		
		loginPage.preencherEmail("carol@tester.com");
		loginPage.preencherPassword("123456");
		
		loginPage.clicarBotaoSignIn();
		
		assertThat(homePage.estaLogado("Carol Lopes"), is(true));
		
	}
	
	public void  incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
		if (!homePage.estaLogado("Carol Lopes")) {
			testLoginComSucesso_UsuarioLogado();
		}
		testValidarDetalhesDoProduto_DescricaoEValorIguais();
		
		carregarPaginaInicial();
	}
}

