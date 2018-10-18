package br.ufc.russas.n2s.chronos.model;
import br.ufc.russas.n2s.chronos.model.exceptions.IllegalCodeException;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="usuario")
public class UsuarioChronos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codUsuario")
    private long codUsuario;
    @Column(name = "codUsuarioControleDeAcesso")
    private long codUsuarioControleDeAcesso;
    private String nome;
    @Column
    @Enumerated
    @ElementCollection(targetClass = EnumPermissao.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EnumPermissao> permissoes;

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        if(codUsuario>0)
            this.codUsuario = codUsuario;
        else
            throw new IllegalCodeException("Código de usuário deve ser maior de zero!");
    }

    public List<EnumPermissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<EnumPermissao> permissoes) {
        this.permissoes = permissoes;
    }

    public long getCodUsuarioControleDeAcesso() {
        return codUsuarioControleDeAcesso;
    }

    public void setCodUsuarioControleDeAcesso(long codUsuarioControleDeAcesso) {
        this.codUsuarioControleDeAcesso = codUsuarioControleDeAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public UsuarioChronos adicionaNivel(UsuarioChronos usuario, EnumPermissao permisao) throws IllegalAccessException{
        if (!usuario.getPermissoes().contains(permisao)) {
            usuario.getPermissoes().add(permisao);
            return usuario;
        } else {
            throw new IllegalArgumentException("Usuário já possui a permição de ".concat(permisao.toString()));
        }
    }
    
    public UsuarioChronos removeNivel(UsuarioChronos usuario, EnumPermissao permisao) throws IllegalAccessException{
        if (usuario.getPermissoes().contains(permisao)) {
            usuario.getPermissoes().remove(permisao);
            return usuario;
        } else {
            throw new IllegalArgumentException("Usuário não possui a permição de ".concat(permisao.toString()));
        }
    }
    
    @Override
    public boolean equals(Object o){
        return (this.getCodUsuario() == ((UsuarioChronos) o).getCodUsuario());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.codUsuario ^ (this.codUsuario >>> 32));
        return hash;
    }
    
    public UsuarioChronos atualizaNiveis(UsuarioChronos usuario, List<EnumPermissao> permissoes) throws IllegalAccessException{
        if (usuario!= null && permissoes!= null) {
            usuario.setPermissoes(permissoes);
            return usuario;
        } else {
            throw new IllegalArgumentException("Erro ao tentar atualizar as permiss�es do usu�rio");
        }
    }
}

