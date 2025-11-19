package br.com.pathcat.config;

import br.com.pathcat.domain.*;
import br.com.pathcat.repository.CompetenciaRepository;
import br.com.pathcat.repository.MatriculaRepository;
import br.com.pathcat.repository.TrilhaRepository;
import br.com.pathcat.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {
    
    private final UsuarioRepository usuarioRepository;
    private final TrilhaRepository trilhaRepository;
    private final CompetenciaRepository competenciaRepository;
    private final MatriculaRepository matriculaRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Iniciando seed de dados...");
        
        // Criar usuários
        Usuario ana = new Usuario();
        ana.setNome("Ana Silva");
        ana.setEmail("ana.silva@example.com");
        ana.setAreaAtuacao("Dados");
        ana.setNivelCarreira(NivelCarreira.JUNIOR);
        usuarioRepository.save(ana);
        
        Usuario bruno = new Usuario();
        bruno.setNome("Bruno Costa");
        bruno.setEmail("bruno.costa@example.com");
        bruno.setAreaAtuacao("IA");
        bruno.setNivelCarreira(NivelCarreira.PLENO);
        usuarioRepository.save(bruno);
        
        Usuario carla = new Usuario();
        carla.setNome("Carla Mendes");
        carla.setEmail("carla.mendes@example.com");
        carla.setAreaAtuacao("Gestão");
        carla.setNivelCarreira(NivelCarreira.SENIOR);
        usuarioRepository.save(carla);
        
        log.info("Usuários criados: {}", usuarioRepository.count());
        
        // Criar competências
        Competencia machineLearning = new Competencia();
        machineLearning.setNome("Machine Learning");
        machineLearning.setCategoria(CategoriaCompetencia.TECNOLOGIA);
        machineLearning.setDescricao("Desenvolvimento de modelos de aprendizado de máquina");
        competenciaRepository.save(machineLearning);
        
        Competencia empatia = new Competencia();
        empatia.setNome("Empatia");
        empatia.setCategoria(CategoriaCompetencia.HUMANA);
        empatia.setDescricao("Capacidade de compreender e compartilhar sentimentos dos outros");
        competenciaRepository.save(empatia);
        
        Competencia python = new Competencia();
        python.setNome("Python");
        python.setCategoria(CategoriaCompetencia.TECNOLOGIA);
        python.setDescricao("Programação em Python para ciência de dados e IA");
        competenciaRepository.save(python);
        
        Competencia comunicacao = new Competencia();
        comunicacao.setNome("Comunicação Efetiva");
        comunicacao.setCategoria(CategoriaCompetencia.HUMANA);
        comunicacao.setDescricao("Habilidade de comunicar-se de forma clara e assertiva");
        competenciaRepository.save(comunicacao);
        
        Competencia lideranca = new Competencia();
        lideranca.setNome("Liderança");
        lideranca.setCategoria(CategoriaCompetencia.GESTAO);
        lideranca.setDescricao("Capacidade de liderar equipes e projetos");
        competenciaRepository.save(lideranca);
        
        log.info("Competências criadas: {}", competenciaRepository.count());
        
        // Criar trilhas
        Trilha trilhaIA = new Trilha();
        trilhaIA.setNome("Trilha Inteligência Artificial");
        trilhaIA.setDescricao("Aprenda os fundamentos e aplicações práticas de IA");
        trilhaIA.setNivel(NivelTrilha.INTERMEDIARIO);
        trilhaIA.setCargaHoraria(40);
        trilhaIA.setFocoPrincipal("IA");
        trilhaIA.setCompetencias(Set.of(machineLearning, python));
        trilhaRepository.save(trilhaIA);
        
        Trilha trilhaSoftSkills = new Trilha();
        trilhaSoftSkills.setNome("Trilha Soft Skills Colaborativas");
        trilhaSoftSkills.setDescricao("Desenvolva habilidades essenciais para trabalho em equipe");
        trilhaSoftSkills.setNivel(NivelTrilha.INICIANTE);
        trilhaSoftSkills.setCargaHoraria(20);
        trilhaSoftSkills.setFocoPrincipal("Soft Skills");
        trilhaSoftSkills.setCompetencias(Set.of(empatia, comunicacao));
        trilhaRepository.save(trilhaSoftSkills);
        
        Trilha trilhaLideranca = new Trilha();
        trilhaLideranca.setNome("Trilha Liderança e Gestão");
        trilhaLideranca.setDescricao("Desenvolva habilidades de liderança e gestão de equipes");
        trilhaLideranca.setNivel(NivelTrilha.AVANCADO);
        trilhaLideranca.setCargaHoraria(30);
        trilhaLideranca.setFocoPrincipal("Gestão");
        trilhaLideranca.setCompetencias(Set.of(lideranca, comunicacao));
        trilhaRepository.save(trilhaLideranca);
        
        log.info("Trilhas criadas: {}", trilhaRepository.count());
        
        // Criar matrículas
        Matricula matricula1 = new Matricula();
        matricula1.setUsuario(ana);
        matricula1.setTrilha(trilhaSoftSkills);
        matricula1.setStatus(StatusMatricula.ATIVA);
        matriculaRepository.save(matricula1);
        
        Matricula matricula2 = new Matricula();
        matricula2.setUsuario(bruno);
        matricula2.setTrilha(trilhaIA);
        matricula2.setStatus(StatusMatricula.ATIVA);
        matriculaRepository.save(matricula2);
        
        Matricula matricula3 = new Matricula();
        matricula3.setUsuario(carla);
        matricula3.setTrilha(trilhaLideranca);
        matricula3.setStatus(StatusMatricula.ATIVA);
        matriculaRepository.save(matricula3);
        
        log.info("Matrículas criadas: {}", matriculaRepository.count());
        log.info("Seed de dados concluído com sucesso!");
    }
}
