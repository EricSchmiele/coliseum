package br.com.rgb;

/**
 * Engine básica para o funcionamento deste jogo em sua versão inicial.
 * 
 * @author RGB
 * @version 28/02/16
 */
public class EngineBasica extends Engine
{
    /**
     * Construtor idêntico ao da superclasse.
     */
    public EngineBasica(String nome, Mensagem classe, int dificuldade, Observador observador)
    {
        super(nome,classe,dificuldade,observador);
    }

    /**
     * Sobrescrita do método para iniciar uma classe à partir de um objeto Mensagem utilizando a classe
     * ClassePuraFactoryBasica.
     * @param classe Mensagem com o nome da classe a ser iniciada.
     */
    public ClassePura fabricarClasse(Mensagem classe)
    {
        try
        {
            return new ClassePuraFactoryBasica().criaClassePura(classe);
        }
        catch(Exception e)
        {
            return new ClassePuraFactoryBasica().criaClassePura(Mensagem.ARQUEIRO);
        }
    }
    
    /**
     * Sobrescrita do método para iniciar uma classe à partir de um int utilizando a classe ClassePuraFactoryBasica.
     * Utilizada para casos de criação de uma nova classe de maneira randômica.
     * @param classe int com o número indicador da classe a ser iniciada.
     */
    public ClassePura fabricarClasse(int classe)
    {
        try
        {
            return new ClassePuraFactoryBasica().criaClassePura(classe);
        }
        catch(Exception e)
        {
            return new ClassePuraFactoryBasica().criaClassePura(0);
        }
    }
}
