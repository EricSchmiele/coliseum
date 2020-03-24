TÍTULO DO PROJETO: Coliseum

PROPÓSITO DO PROJETO: criar um simples jogo de RPG do tipo arena para propósitos de entretenimento e aprendizado de conceitos de programação orientada a objeto utilizando a linguagem JAVA

VERSÃO: 0.3

COMO INICIAR O PROJETO: Executar a classe GUI ou GUI_2 (dependendo da resolução da sua tela)

AUTORES: Adolfo Santos Cardoso / Eric Ferreira Schmiele / Gabriel Heinrique Giarolla Silveira

INSTRUÇÕES AO USUÁRIO: na versão atual do programa:
	- As únicas diferenças entre os modos de dificuldade é a diferença de level entre jogador e inimigo (fácil: jogador>inimigo; médio: jogador=inimigo; difícil: jogador<inimigo)
	- O ataque do jogador e do inimigo são regidos por seus stats e por rolagens de dados, se for necessário mudar o rate de acerto basta mudar as linhas com o comentário //calculo_de_ataque
	- O ataque do jogador  e do inimigo são regidos por calculos que dependem de seu level atual, se for necessário mudar o valor dos ataques basta mudar as linhas com o comentário //valor_de_ataque
	- Há um cálculo para deixar um item do inimigo disponível para equipar ou não, se for necessário mudar este cálculo basta mudar as linhas com o comentário //item_deixado
	- O jogo está configurado com condição de vitória: atingir o level 10, se for necessário mudar esta condição basta mudar a linha com o comentário //condicao_de_vitoria
	- Atualmente o jogador eleva de nível conforme um cálculo, se for necessário mudar este cálculo basta mudar a linha com o comentário  //experiencia_adquirida

!!!!!!!!!!!
OBSERVAÇÃO: por algum motivo o jogo está mais lento em execução. Suspeita-se do uso do padrão de projeto Observer, mas ainda será necessário um reestudo. Pedimos a compreensão do analista.