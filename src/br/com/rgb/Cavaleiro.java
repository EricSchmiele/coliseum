package br.com.rgb;

/**
 * Define um tipo cavaleiro, sendo uma subclasse de ClassePura. Especifica o método levelUp e a inicialização dos 
 * valores dos atributos para ser diferente das outras subclasses.
 * 
 * @author RGB 
 * @version 21/02/16
 */
public class Cavaleiro extends ClassePura
{
    /**
     * Cria um objeto Cavaleiro com os valores iniciais específicos dos atributos para esta classe.
     */
    public Cavaleiro()
    {
        super(Mensagem.CAVALEIRO,3,3,1,0,0);
        setAtaque(new Ataque("Ataque cortante",PosStats.FORCA,PosStats.DEFESA,PosStats.VIDA));
    }

    /**
     * Determina como a classe Cavaleiro deve mudar os stats a cada level. E especifica em qual level um novo ataque
     * será liberado.
     */
    public void levelUp()
    {
        Stats adicionau = new Stats(Dado.rolarD(5),Dado.rolarD(6),Dado.rolarD(2),Dado.rolarD(2),Dado.rolarD(2),
        Dado.rolarD(5));//soma é 22
        adicionarStats(adicionau);
        levelMais();
        if(getLevel() == 4)
        {
            setAtaque(new Ataque("Investida",PosStats.DEFESA,PosStats.SORTE,PosStats.MAGIA));
        }
        if(getLevel() == 5)
        {
            setAtaque(new Ataque("Ataque misterioso",PosStats.FORCA,PosStats.MAGIA,PosStats.VIDA));
        }
    }
}
