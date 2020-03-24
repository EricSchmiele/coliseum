package br.com.rgb;

/**
 * Interface para comunicação entre Engine e GUI. Especifica o método que deve ser sobrescritos para que uma
 * comunicação satisfatória seja instaurada.
 * Utilizado na GUI, pois ela é a classe a observar.
 * 
 * @author RGB
 * @version 28/02/16
 */
public interface Observador
{
    /**
     * Método que será ativado sempre que uma atualização dos objetos do tipo Observavel ocorrerer.
     * Nele as atividades tomadas pelo Observador, devido à atualização dos objetos observaveis, devem ser inclusas.
     */
    public abstract void update(Observavel observavelMudado);
}
