import java.util.ArrayList;
import java.util.List;


interface Departamento {
    void imprimir();
}


class DepartamentoComposto implements Departamento {
    private String nome;
    private List<Departamento> subDepartamentos = new ArrayList<>();

    public DepartamentoComposto(String nome) {
        this.nome = nome;
    }

    public void adicionarDepartamento(Departamento departamento) {
        subDepartamentos.add(departamento);
    }

    @Override
    public void imprimir() {
        System.out.println("Departamento: " + nome);
        for (Departamento departamento : subDepartamentos) {
            departamento.imprimir();
        }
    }
}


class DepartamentoMatematica implements Departamento {
    @Override
    public void imprimir() {
        System.out.println("Departamento de Matemática");
    }
}

class DepartamentoCiencias implements Departamento {
    @Override
    public void imprimir() {
        System.out.println("Departamento de Ciências");
    }
}


interface Funcionario {
    void trabalhar();
    void atualizar(String mensagem);
}


class Professor implements Funcionario {
    @Override
    public void trabalhar() {
        System.out.println("Ensinando alunos");
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Professor recebeu a mensagem: " + mensagem);
    }
}


class Administrador implements Funcionario {
    @Override
    public void trabalhar() {
        System.out.println("Gerenciando tarefas administrativas");
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Administrador recebeu a mensagem: " + mensagem)
    }
}


abstract class FabricaFuncionario {
    abstract Funcionario criarFuncionario();
    
    public void gerenciarTrabalho() {
        Funcionario funcionario = criarFuncionario();
        funcionario.trabalhar();
    }
}


class FabricaProfessor extends FabricaFuncionario {
    @Override
    Funcionario criarFuncionario() {
        return new Professor();
    }
}


class FabricaAdministrador extends FabricaFuncionario {
    @Override
    Funcionario criarFuncionario() {
        return new Administrador();
    }
}


interface ChefeDepartamento {
    void adicionarObservador(Funcionario funcionario);
    void removerObservador(Funcionario funcionario);
    void notificarObservadores(String mensagem);
}


class ChefeDepartamentoMatematica implements ChefeDepartamento {
    private List<Funcionario> observadores = new ArrayList<>();
    
    @Override
    public void adicionarObservador(Funcionario funcionario) {
        observadores.add(funcionario);
    }

    @Override
    public void removerObservador(Funcionario funcionario) {
        observadores.remove(funcionario);
    }

    @Override
    public void notificarObservadores(String mensagem) {
        for (Funcionario observador : observadores) {
            observador.atualizar(mensagem);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Padrão Composite
        DepartamentoComposto universidade = new DepartamentoComposto("Universidade");
        
        DepartamentoComposto faculdadeEngenharia = new DepartamentoComposto("Faculdade de Engenharia");
        faculdadeEngenharia.adicionarDepartamento(new DepartamentoMatematica());
        faculdadeEngenharia.adicionarDepartamento(new DepartamentoCiencias());
        
        DepartamentoComposto faculdadeMedicina = new DepartamentoComposto("Faculdade de Medicina");
        faculdadeMedicina.adicionarDepartamento(new DepartamentoCiencias());
        
        universidade.adicionarDepartamento(faculdadeEngenharia);
        universidade.adicionarDepartamento(faculdadeMedicina);
        
        universidade.imprimir();
        
       
        FabricaFuncionario fabricaProfessor = new FabricaProfessor();
        fabricaProfessor.gerenciarTrabalho();
        
        FabricaFuncionario fabricaAdministrador = new FabricaAdministrador();
        fabricaAdministrador.gerenciarTrabalho();
        
    
        ChefeDepartamento chefeMatematica = new ChefeDepartamentoMatematica();
        Funcionario professorMatematica = new Professor();
        Funcionario administradorMatematica = new Administrador();
        
        chefeMatematica.adicionarObservador(professorMatematica);
        chefeMatematica.adicionarObservador(administradorMatematica);
        
        chefeMatematica.notificarObservadores("Reunião às 10:00");
    }
}
