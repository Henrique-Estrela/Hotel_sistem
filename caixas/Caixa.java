import usuarios.Funcionario;

package caixas;
class Caixa {
    private int codigo;
    private Funcionario funcionario;
   
   //Criação dos Getters e Setters dos atributos de caixa
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    
}