package br.ufc.russas.n2s.chronos.model;


import java.time.LocalDateTime;

/**
*
* @author Gutenberg Filho
*/
public enum EnumEstadoAtividade{
	ABERTA(1), ANDAMENTO(2), FINALIZADA(3);

	
	int estado;
    
    EnumEstadoAtividade() {
        
    }
    
    EnumEstadoAtividade(int estado) {
        setEstado(estado);
    }

    
    public int getEstado() {
        return estado;
    }
    
    private void setEstado(int estado) {
        if (estado>=1 && estado <= 3) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado da Atividade deve ser maior igual a um e menor igual a tres!");
        }
    }

	
	
}
