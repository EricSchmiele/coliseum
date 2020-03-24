package br.com.rgb;

import java.util.ArrayList;
import java.io.*;

/**
 * Classe utilizada apenas para iniciar os arquivos contendo todos os itens necessários para o jogo.
 * Inicia um arquivo para cada "tipo" de item (separados por qual parte do corpo devem ser equipados).
 * 
 * @author RGB
 * @version 28/02/16
 */
public class GerarArquivoItens
{
    private static ArrayList<Item> todosOsItens;

    /**
     * Cria cada um dos itens que será utilizado no jogo e guarda cada um deles em seus respectivos arquivos.
     */
    public static void gerarItensEEscreverEmArquivo()
    {
        todosOsItens = new ArrayList<Item>();
        todosOsItens.add(new Item("Chapeu",0,1,0,0,3,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.CABECA,0,0));
        todosOsItens.add(new Item("Camiseta",0,3,0,0,0,1, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.TRONCO,0,0));
        todosOsItens.add(new Item("Bracadeiras",3,0,1,0,0,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.BRACOS,0,0));
        todosOsItens.add(new Item("Sandalias",1,0,3,0,0,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.PES,0,0));
        
        todosOsItens.add(new Item("Capacete de Couro",0,7,0,0,12,0, 0,7,0,0,0,20,Mensagem.TODOS,ParteDoCorpo.CABECA,1,0));
        todosOsItens.add(new Item("Armadura de Couro",0,12,0,0,0,7, 0,7,0,0,0,20,Mensagem.TODOS,ParteDoCorpo.TRONCO,1,0));
        todosOsItens.add(new Item("Bracadeiras de Couro",12,0,7,0,0,0, 0,7,0,0,0,20,Mensagem.TODOS,ParteDoCorpo.BRACOS,1,0));
        todosOsItens.add(new Item("Sandalias de Couro",7,0,12,0,0,0, 0,7,0,0,0,20,Mensagem.TODOS,ParteDoCorpo.PES,1,0));
        
        todosOsItens.add(new Item("Cartola",0,0,0,14,0,16, 12,0,0,23,0,0,Mensagem.TODOS,ParteDoCorpo.CABECA,2,0));
        todosOsItens.add(new Item("Smokin",0,16,0,0,0,14, 12,0,0,23,0,0,Mensagem.TODOS,ParteDoCorpo.TRONCO,2,0));
        todosOsItens.add(new Item("Nadadeiras",16,14,0,0,0,0, 12,0,0,23,0,0,Mensagem.TODOS,ParteDoCorpo.BRACOS,2,0));
        todosOsItens.add(new Item("Pes de pato",0,0,16,0,14,0, 12,0,0,23,0,0,Mensagem.TODOS,ParteDoCorpo.PES,2,0));
        
        todosOsItens.add(new Item("Governar",2,2,0,0,0,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.ANEL,0,0));
        todosOsItens.add(new Item("Encontra-los",0,0,2,2,0,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.ANEL,0,0));
        todosOsItens.add(new Item("Trazer",14,14,0,0,0,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.ANEL,0,0));
        todosOsItens.add(new Item("Aprisiona-los",0,0,14,14,0,0, 0,0,0,0,0,0,Mensagem.TODOS,ParteDoCorpo.ANEL,0,0));
        
        todosOsItens.add(new Item("Espada",16,2,0,0,-2,0, 0,0,0,0,0,0,Mensagem.CAVALEIRO,ParteDoCorpo.MAO1,0,1));
        todosOsItens.add(new Item("Escudo",0,14,-7,0,0,12, 0,0,0,0,0,0,Mensagem.CAVALEIRO,ParteDoCorpo.MAO2,0,1));
        todosOsItens.add(new Item("Sabre de Luz",27,13,7,2,7,-14, 0,0,0,0,0,0,Mensagem.CAVALEIRO,ParteDoCorpo.MAO1,0,1));
        
        todosOsItens.add(new Item("Adaga",2,0,1,0,7,0, 0,0,0,0,0,0,Mensagem.ARQUEIRO,ParteDoCorpo.MAO1,0,1));
        todosOsItens.add(new Item("Arco",9,0,12,0,2,0, 0,0,0,0,0,0,Mensagem.ARQUEIRO,ParteDoCorpo.MAO2,0,1));
        todosOsItens.add(new Item("Arco do Legolas",13,0,14,-12,32,-12, 0,0,0,0,0,0,Mensagem.ARQUEIRO,ParteDoCorpo.MAO2,0,1));
        
        todosOsItens.add(new Item("Cajado Magico",0,-2,-2,20,7,0, 0,0,0,0,0,0,Mensagem.MAGO,ParteDoCorpo.MAO1,0,1));
        todosOsItens.add(new Item("Grimorio",0,0,-7,13,0,0, 0,0,0,0,0,0,Mensagem.MAGO,ParteDoCorpo.MAO2,0,1));
        todosOsItens.add(new Item("Varinha das Varinhas",-7,1,14,37,23,-16, 0,0,0,0,0,0,Mensagem.MAGO,ParteDoCorpo.MAO1,0,1));
        
        salvarEmArquivo();
    }
    
    /**
     * Este método cria um arquivo para cada "tipo" de item e escreve os itens em seus respectivos arquivos.
     */
    private static final void salvarEmArquivo()
    {
        int i = 0;
        Item item = todosOsItens.get(i);
        ArrayList<ObjectOutputStream> arquivos = new ArrayList<ObjectOutputStream>();
        try
        {
            for(ParteDoCorpo p: ParteDoCorpo.values())
            {
                if(p != ParteDoCorpo.MAXIMO)
                {
                    arquivos.add(new ObjectOutputStream(new FileOutputStream("todos_os_itens_" + p.valor() + ".itm")));
                }
            }
            while(item != null)
            {
                arquivos.get(item.getParteDoCorpo().valor()).writeObject(item);
                i++;
                item = todosOsItens.get(i);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            for(ParteDoCorpo p: ParteDoCorpo.values())
            {
                if(p != ParteDoCorpo.MAXIMO)
                {
                    arquivos.get(p.valor()).close();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}