package br.com.whereit.orvil;

import org.junit.Assert;
import org.junit.Test;

import br.com.whereit.orvil.Model.Livro;

public class LivroTeste {

    /* Serve para Categorias também */
    @Test
    public void testAutorEmpty(){
        String expected = "Não Informado";
        Livro livro = new Livro();
        livro.setAutores(new String[]{});
        Assert.assertEquals(livro.getAutorFormatado(),expected);
    }

    /* Serve para Categorias também */
    @Test
    public void testAutorNull(){
        String expected = "Não Informado";
        Livro livro = new Livro();
        Assert.assertEquals(livro.getAutorFormatado(),expected);
    }

    @Test
    public void TestAnoNull(){
        String expected = "Não Informado";
        Livro livro = new Livro();
        Assert.assertEquals(livro.getAno(),expected);
    }

    @Test
    public void TestAnoEmpty(){
        String expected = "Não Informado";
        Livro livro = new Livro();
        livro.setDataPublicacao("");
        Assert.assertEquals(livro.getAno(),expected);
    }

    @Test
    public void TestAnoBadFormat(){
        String expected = "Não Informado";
        Livro livro = new Livro();
        livro.setDataPublicacao("a");
        Assert.assertEquals(livro.getAno(),expected);
    }
}
