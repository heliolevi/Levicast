package br.com.seuprojeto.Principal;

import br.com.seuprojeto.dao.MusicaDAO; // Importe o novo MusicaDAO
import br.com.seuprojeto.dao.PodcastDAO; // Importe o novo PodcastDAO
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

        // Instanciando os novos DAOs especializados
        MusicaDAO musicaDAO = new MusicaDAO();
        PodcastDAO podcastDAO = new PodcastDAO();
        Autenticador autenticador = new Autenticador();
        boolean isAdmin = autenticador.validarAcesso();

        System.out.println(" Carregando catálogo do banco de dados...");
        // Carregando as listas separadamente e unindo no ArrayList principal
        listaDeAudios.addAll(musicaDAO.listar());
        listaDeAudios.addAll(podcastDAO.listar());
        System.out.println("✅ Catálogo carregado com sucesso!\n");

        while (opcao != 9) {
            if (isAdmin) {
                System.out.println("6 - [ADMIN] Excluir Áudio");
            }
            System.out.println("Seja bem vindo ao LeviCast!\n");
            System.out.println(" 1 - Cadastrar Música\n 2 - Cadastrar Podcast\n 3 - Verificar Podcasts e músicas\n 4 - Ouvir audios\n 9 - Sair");
            System.out.print("Sua opção: ");

            try {
                opcao = escolhadoUsuario.nextInt();
            } catch (Exception e) {
                System.out.println("Erro: Digite apenas números!");
                escolhadoUsuario.nextLine();
                continue;
            }
            escolhadoUsuario.nextLine();

            switch (opcao) {
                case 1:
                    Musica novaMusica = new Musica();
                    System.out.println("Digite o nome da música: ");
                    String nome = escolhadoUsuario.nextLine();

                    while (nome.trim().isEmpty()) {
                        System.out.println("O nome não pode ser vazio! Digite novamente");
                        nome = escolhadoUsuario.nextLine();
                    }

                    System.out.println("Digite o álbum: ");
                    String album = escolhadoUsuario.nextLine();

                    while (album.trim().isEmpty()) {
                        System.out.println("O álbum não pode ser vazio! Digite novamente");
                        album = escolhadoUsuario.nextLine();
                    }

                    boolean jaExiste = false;
                    for (Audio item : listaDeAudios) {
                        if (item instanceof Musica musicaExistente) {
                            if (musicaExistente.getTitulo().equalsIgnoreCase(nome) &&
                                    musicaExistente.getAlbum().equalsIgnoreCase(album)) {
                                jaExiste = true;
                                break;
                            }
                        }
                    }

                    if (jaExiste) {
                        System.out.println(" Erro: Esta música já está cadastrada neste álbum!");
                    } else {
                        novaMusica.setTitulo(nome);
                        novaMusica.setAlbum(album);
                        listaDeAudios.add(novaMusica);

                        // Usando o DAO específico de Música
                        musicaDAO.salvar(novaMusica);
                        System.out.println("✅ Música '" + nome + "' cadastrada com sucesso!");
                    }
                    break;

                case 2:
                    Podcast novoPodcast = new Podcast();
                    System.out.println("Digite o nome do podcast: ");
                    String podcastTitulo = escolhadoUsuario.nextLine();

                    while (podcastTitulo.trim().isEmpty()) {
                        System.out.println("O nome do podcast não pode ser vazio!");
                        podcastTitulo = escolhadoUsuario.nextLine();
                    }

                    System.out.println("Qual é o nome do host? ");
                    String host = escolhadoUsuario.nextLine();

                    while (host.trim().isEmpty()) {
                        System.out.println("O host não pode ser vazio!");
                        host = escolhadoUsuario.nextLine();
                    }

                    System.out.println("Qual a descrição do podcast? ");
                    String descricao = escolhadoUsuario.nextLine();

                    boolean podcastJaExiste = false;
                    for (Audio item : listaDeAudios) {
                        if (item instanceof Podcast pExistente) {
                            if (pExistente.getTitulo().equalsIgnoreCase(podcastTitulo) &&
                                    pExistente.getHost().equalsIgnoreCase(host)) {
                                podcastJaExiste = true;
                                break;
                            }
                        }
                    }

                    if (podcastJaExiste) {
                        System.out.println(" Erro: Este podcast com este host já está cadastrado!");
                    } else {
                        novoPodcast.setTitulo(podcastTitulo);
                        novoPodcast.setHost(host);
                        novoPodcast.setDescricao(descricao);
                        listaDeAudios.add(novoPodcast);

                        // Usando o DAO específico de Podcast
                        podcastDAO.salvar(novoPodcast);
                        System.out.println("✅ Podcast '" + podcastTitulo + "' cadastrado com sucesso!");
                    }
                    break;

                case 3:
                    System.out.println("\n*** Seu Catálogo LeviCast ***");
                    if (listaDeAudios.isEmpty()) {
                        System.out.println("O catálogo está vazio.");
                    } else {
                        for (Audio item : listaDeAudios) {
                            String tipo = (item instanceof Musica) ? "[MÚSICA]" : "[PODCAST]";
                            System.out.println(tipo + " Nome: " + item.getTitulo());
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
                        System.out.println("▶️ Tocando agora: " + selecionado.getTitulo());
                    } else {
                        System.out.println("⚠️ Opção inválida!");
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