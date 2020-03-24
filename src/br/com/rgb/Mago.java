package br.com.rgb;

/**
 * Define um tipo mago, sendo uma subclasse de ClassePura. Especifica o método levelUp e a inicialização dos 
 * valores dos atributos para ser diferente das outras subclasses.
 * 
 * @author RGB 
 * @version 21/02/16
 */
public class Mago extends ClassePura
{
    /**
     * Cria um objeto Arqueiro com os valores iniciais específicos dos atributos para esta classe.
     */
    public Mago()
    {
        super(Mensagem.MAGO,0,1,3,3,1);
        setAtaque(new Ataque("Bola de fogo",PosStats.MAGIA,PosStats.MAGIA,PosStats.VIDA));
    }

    /**
     * Determina como a classe Mago deve mudar os stats a cada level. E especifica em qual level um novo ataque
     * será liberado.
     */
    public void levelUp()
    {
        Stats adicionau = new Stats(Dado.rolarD(2),Dado.rolarD(5),Dado.rolarD(5),Dado.rolarD(2),Dado.rolarD(5),
        Dado.rolarD(3));//soma é 22
        adicionarStats(adicionau);
        levelMais();
        if(getLevel() == 2)
        {
            setAtaque(new Ataque("Armadilha arcana",PosStats.MAGIA,PosStats.SORTE,PosStats.FORCA));
        }
        if(getLevel() == 8)
        {
            setAtaque(new Ataque("Toque de morte",PosStats.FORCA,PosStats.MAGIA,PosStats.VIDA));
        }
    }
}
