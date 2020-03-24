T�TULO DO PROJETO: Coliseum

PROP�SITO DO PROJETO: criar um simples jogo de RPG do tipo arena para prop�sitos de entretenimento e aprendizado de conceitos de programa��o orientada a objeto utilizando a linguagem JAVA

VERS�O: 0.3

COMO INICIAR O PROJETO: Executar a classe GUI ou GUI_2 (dependendo da resolu��o da sua tela)

AUTORES: Adolfo Santos Cardoso / Eric Ferreira Schmiele / Gabriel Heinrique Giarolla Silveira

INSTRU��ES AO USU�RIO: na vers�o atual do programa:
	- As �nicas diferen�as entre os modos de dificuldade � a diferen�a de level entre jogador e inimigo (f�cil: jogador>inimigo; m�dio: jogador=inimigo; dif�cil: jogador<inimigo)
	- O ataque do jogador e do inimigo s�o regidos por seus stats e por rolagens de dados, se for necess�rio mudar o rate de acerto basta mudar as linhas com o coment�rio //calculo_de_ataque
	- O ataque do jogador  e do inimigo s�o regidos por calculos que dependem de seu level atual, se for necess�rio mudar o valor dos ataques basta mudar as linhas com o coment�rio //valor_de_ataque
	- H� um c�lculo para deixar um item do inimigo dispon�vel para equipar ou n�o, se for necess�rio mudar este c�lculo basta mudar as linhas com o coment�rio //item_deixado
	- O jogo est� configurado com condi��o de vit�ria: atingir o level 10, se for necess�rio mudar esta condi��o basta mudar a linha com o coment�rio //condicao_de_vitoria
	- Atualmente o jogador eleva de n�vel conforme um c�lculo, se for necess�rio mudar este c�lculo basta mudar a linha com o coment�rio  //experiencia_adquirida

!!!!!!!!!!!
OBSERVA��O: por algum motivo o jogo est� mais lento em execu��o. Suspeita-se do uso do padr�o de projeto Observer, mas ainda ser� necess�rio um reestudo. Pedimos a compreens�o do analista.