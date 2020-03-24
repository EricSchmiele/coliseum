package br.com.rgb;

/**
 * Interface para comunicação entre Engine e GUI. Especifica os métodos que devem ser sobrescritos para que uma
 * comunicação satisfatória seja instaurada.
 * Utilizado na Engine, pois ela é a classe a ser observada.
 * 
 * @author RGB
 * @version 28/02/16
 */
public interface Observavel
{
    /**
     * Método que deverá adicionar objetos do tipo Observador para que sempre que necessário estes objetos possam
     * ser notificados corretamente e agir conforme devido.
     * @param novoObservador Observador a ser inserido na lista (ou vetor, dependendo da implementação) de
     * Observadores.
     */
    public abstract void adicionarObservador(Observador novoObservador);
    
    /**
     * Método para retirar um objeto Observador da lista de observadores de modo que ele não irá mais receber
     * notificações quando mudanças ocorrerem.
     * @param aSerRetirado Observador que será retirado da lista (ou vetor, dependendo da implementação) de
     * Observadores.
     */
    public abstract void removerObservador(Observador aSerRetirado);
    
    /**
     * Método para notificar alguma mudança neste objeto e informar a todos os observadores "cadastrados".
     * (deve chamar o método update dos objetos do tipo Observador).
     */
    public abstract void notificarObservadores();
}
