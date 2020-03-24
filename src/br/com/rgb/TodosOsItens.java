package br.com.rgb;

import java.io.*;
import java.util.ArrayList;

/**
 * Esta classe lê um arquivo de itens e os guarda em uma lista para que eles possam ser acessados posteriormente.
 * 
 * @author RGB
 * @version 29/02/16
 */
public class TodosOsItens
{
    /**
     * Lista de listas de itens. Uma lista contendo várias listas (uma para cada "tipo" de item).
     */
    private ArrayList<ArrayList<Item>> todosOsItens;
    
    /**
     * Cria um objeto de TodosOsItens com as listas de itens já iniciadas (feita a leitura dos arquivos de itens).
     */
    public TodosOsItens()
    {
        todosOsItens = new ArrayList<ArrayList<Item>>();
        for(int i = 0; i < ParteDoCorpo.MAXIMO.valor(); i++)
        {
            todosOsItens.add(new ArrayList<Item>());
        }
        iniciaListas();
    }
    
    /**
     * Inicia as listas de cada "tipo" de item, uma de cada vez.
     */
    private final void iniciaListas()
    {
        /* @TODO caso faça a melhoria na classe Item*/
        for(ParteDoCorpo p: ParteDoCorpo.values())
        {
            if(p != ParteDoCorpo.MAXIMO)
            {
                abreArquivoItens("items/todos_os_itens_" + p.valor() + ".itm",p);
            }
        }
    }

    /**
     * Abre o arquivo com todos os itens e os coloca na lista correta dentro da lista de listas.
     */
    private final void abreArquivoItens(String nomeArquivo, ParteDoCorpo parteDoCorpo)
    {
        try
        {
            ObjectInputStream arquivo = new ObjectInputStream(new FileInputStream(nomeArquivo));
            Item itemTmp = (Item)arquivo.readObject();
            while(itemTmp != null)
            {
                //ArrayList<Item> arrayTmp = todosOsItens.get(parteDoCorpo.valor());
                //arrayTmp.add(itemTmp);
                //todosOsItens.add(arrayTmp);
                todosOsItens.get(parteDoCorpo.valor()).add(itemTmp);
                itemTmp = (Item)arquivo.readObject();
            }
            arquivo.close();
        }
        catch(Exception e)
        {
        }
    }
    
    /**
     * Retorna o item de uma posição específica de um array de itens de uma parte específica do corpo.
     * Joga uma exceção caso a ParteDoCorpo for inválida ou a posição não existir.
     * @param Posição específica no array.
     * @param Parte específica do corpo do item desejado.
     */
    public Item getItem(int pos, ParteDoCorpo parteDoCorpo)
    {
        try
        {
            return todosOsItens.get(parteDoCorpo.valor()).get(pos).clone();
        }
        catch(Exception e)
        {
            if(parteDoCorpo == ParteDoCorpo.MAXIMO)
            {
                throw new RuntimeException("ParteDoCorpo inválida.");
            }
            else
            {
                throw new RuntimeException("Posição inválida.");
            }
        }
    }
}
