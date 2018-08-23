package br.com.whereit.orvil.Model;

import android.support.annotation.NonNull;

import java.util.List;


public class Livro {

    int Id;
    String Titulo;
    String Subtitulo;
    String[] Autores;
    String Editora;
    String Descricao;
    String Tags;
    String Situacao;
    String Tenho;
    int VezesLido;
    int NumeroPaginas;
    String DataPublicacao;
    String Idioma;
    String ExtraLarge;
    String Large;
    String Medium;
    String Small;
    String SmallThumbnail;
    String Thumbnail;
    String[] Categorias;
    List<Identificadores> Identificadores;

    public Livro() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getSubtitulo() {
        return Subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        Subtitulo = subtitulo;
    }

    public String[] getAutores() {
        return Autores;
    }

    public void setAutores(String[] autores) {
        Autores = autores;
    }

    public String getEditora() {
        if(Editora == null)
            return "Não Informado";
        return Editora;
    }

    public void setEditora(String editora) {
        Editora = editora;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String situacao) {
        Situacao = situacao;
    }

    public String getTenho() {
        return Tenho;
    }

    public void setTenho(String tenho) {
        Tenho = tenho;
    }

    public int getVezesLido() {
        return VezesLido;
    }

    public void setVezesLido(int vezesLido) {
        VezesLido = vezesLido;
    }

    public int getNumeroPaginas() {
        return NumeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        NumeroPaginas = numeroPaginas;
    }

    public String getDataPublicacao() {
        return DataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        DataPublicacao = dataPublicacao;
    }

    public String getIdioma() {
        return Idioma;
    }

    public void setIdioma(String idioma) {
        Idioma = idioma;
    }

    public String getExtraLarge() {
        return ExtraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        ExtraLarge = extraLarge;
    }

    public String getLarge() {
        return Large;
    }

    public void setLarge(String large) {
        Large = large;
    }

    public String getMedium() {
        return Medium;
    }

    public void setMedium(String medium) {
        Medium = medium;
    }

    public String getSmall() {
        return Small;
    }

    public void setSmall(String small) {
        Small = small;
    }

    public String getSmallThumbnail() {
        return SmallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        SmallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String[] getCategorias() {
        return Categorias;
    }

    public void setCategorias(String[] categorias) {
        Categorias = categorias;
    }

    public List<Identificadores> getIdentificadores() {
        return Identificadores;
    }

    public void setIdentificadores(List<Identificadores> identificadores) {
        Identificadores = identificadores;
    }

    public String getTituloFormatado() {
        if(Subtitulo !=null){
            return  String.format("%s : %s", Titulo, Subtitulo);
        } else{
            return Titulo;
        }
    }

    public String getAutorFormatado(){
        return explode(Autores);
    }

    public String getCategoriasFormatado(){
        return explode(Categorias);
    }

    @NonNull
    private String explode(String[] mArray) {
        try {
            if(mArray.length >0) {
                StringBuilder rString = new StringBuilder();
                String separator = ", ";
                for (int i = 0; i < mArray.length; i++) {
                    if (i > 0 && i <= mArray.length - 1) {
                        rString.append(separator).append(mArray[i]);
                    } else {
                        rString.append(mArray[i]);
                    }
                }

                return rString.toString();
            } else {
                throw new Exception();
            }
        } catch (Exception ex){
            return "Não Informado";
        }
    }

    public String getAno(){
        try {
            if(DataPublicacao.isEmpty())
                throw new Exception();
            return DataPublicacao.split("-")[0];

        }
        catch (Exception ex){
            return "Não Informado";
        }

    }
}
