package br.com.seuprojeto.Principal;

import br.com.seuprojeto.dao.AudioDAO;
import br.com.seuprojeto.modelos.Audio;
import br.com.seuprojeto.modelos.Musica;
import br.com.seuprojeto.modelos.Podcast;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {
    public void exibiMenu() {

    int opcao = 0;
    Scanner escolhadoUsuario = new Scanner(System.in);
    ArrayList<Audio> listaDeAudios = new ArrayList<>();

    while (opcao != 9){
        System.out.println("Seja bem vindo ao LeviCast!\n");
        System.out.println(" 1 - Cadastrar Música\n 2 - Cadastrar Podcast\n 3 - Verificar Podcasts e músicas\n 4 - Ouvir audios\n 9 - Sair");
        System.out.print("Sua opção: ");
        try {
            opcao = escolhadoUsuario.nextInt();
        } catch (Exception e) {
            System.out.println("Erro: Digite apenas números!");
            escolhadoUsuario.nextLine();
            continue; // voltar para o inicio do loop
        }
        escolhadoUsuario.nextLine();
        switch (opcao) {
            case 1:
                Musica novaMusica = new Musica();
                System.out.println("Digite o nome da música: ");
                String nome = escolhadoUsuario.nextLine();

                while (nome.trim().isEmpty()){
                    System.out.println("O nome não pode ser vazio! Digite novamente");
                    nome = escolhadoUsuario.nextLine();
                }

                System.out.println("Digite o album: ");
                String album = escolhadoUsuario.nextLine();

                while (album.trim().isEmpty()){
                    System.out.println("O nome não pode ser vazio! Digite novamente");
                    album = escolhadoUsuario.nextLine();
                }

                boolean jaExiste = false;
                for (Audio item : listaDeAudios) {
                    if (item instanceof  Musica musicaExistente){
                        if (musicaExistente.getTitulo().equalsIgnoreCase(nome) &&
                                musicaExistente.getAlbum().equalsIgnoreCase(album)) {
                            jaExiste = true;
                            break;
                        }
                    }
                }

                if (jaExiste) {
                    System.out.println(" Erro: Esta música já está cadastrada neste álbum!");
                }
                else{
                    novaMusica.setTitulo(nome);
                    novaMusica.setAlbum(album);
                    listaDeAudios.add(novaMusica);
                    AudioDAO dao = new AudioDAO();
                    dao.salvarMusica(novaMusica);

                    System.out.println("Música '" + nome + "' cadastrada com sucesso!");
                }
                break;
            case 2:



                Podcast novoPodcast = new Podcast();
                System.out.println("Digite o nome do podcast: ");
                String podcast = escolhadoUsuario.nextLine();



                while (podcast.trim().isEmpty()){
                    System.out.println("O nome do podcast não pode ser vazio! Digite novamente");
                    podcast = escolhadoUsuario.nextLine();
                }

                System.out.println("Qual é o nome do host? ");
                String host = escolhadoUsuario.nextLine();

                while (host.trim().isEmpty()){
                    System.out.println("O host não pode ser vazio! Digite novamente");
                    host = escolhadoUsuario.nextLine();
                }

                System.out.println("Qual a descrisção do podcast? ");
                String descricao = escolhadoUsuario.nextLine();

                while (descricao.trim().isEmpty() || !descricao.matches(".*[a-zA-Z].*")){
                    System.out.println("A descrição não pode ser vazio! Ou ser númerico Digite novamente");
                    descricao = escolhadoUsuario.nextLine();
                }

                boolean podcastJaExiste = false;
                for (Audio item : listaDeAudios) {
                    if (item instanceof Podcast podcastExistente) {
                        // Comparamos o Título e o Host para saber se é o mesmo programa
                        if (podcastExistente.getTitulo().equalsIgnoreCase(podcast) &&
                                podcastExistente.getHost().equalsIgnoreCase(host)) {
                            podcastJaExiste = true;
                            break;
                        }
                    }
                }
                            if (podcastJaExiste) {
                                System.out.println(" Erro: Este podcast com este host já está cadastrado!");
                            } else {
                                novoPodcast.setTitulo(podcast);
                                novoPodcast.setHost(host);
                                novoPodcast.setDescricao(descricao);
                                listaDeAudios.add(novoPodcast);
                                AudioDAO dao = new AudioDAO();
                                dao.salvarPodcast(novoPodcast);
                                System.out.println("Podcast '" + podcast + "' cadastrado com sucesso!");
                            }
                break;
            case 3:
                System.out.println("\n*** Seu Catálogo LeviCast ***");
                if (listaDeAudios.isEmpty()) {
                    System.out.println("O catálogo está vazio.");
                } else {
                    // O loop 'for each' percorre cada item da lista
                    for (Audio item : listaDeAudios) {
                        item.reproduz();
                        System.out.println("Título: " + item.getTitulo() );
                    }
                }
                break;
            case 4:
                if (listaDeAudios.isEmpty()) {
                    System.out.println(" Não há áudios cadastrados para ouvir!");
                    break;
                }
                System.out.println("Qual áudio deseja ouvir?");
                for (int i = 0; i < listaDeAudios.size(); i++) {
                    System.out.println(i + " - " + listaDeAudios.get(i).getTitulo());
                }
                int indice = escolhadoUsuario.nextInt();

                if (indice >= 0 && indice < listaDeAudios.size()) {
                    Audio selecionado = listaDeAudios.get(indice);
                    selecionado.reproduz();
                    System.out.println("Tocando agora: " + selecionado.getTitulo());
                } else {
                    System.out.println("⚠️ Opção inválida! Escolha um número que aparece na lista.");
                }
                break;
            case 9:
                System.out.println("Encerrando o LeviCast. Até logo!");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }
}
}
