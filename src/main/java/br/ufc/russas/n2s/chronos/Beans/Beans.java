package br.ufc.russas.n2s.chronos.Beans;

public interface Beans {

    /**
     * @return Object
     */
    Object toBusiness();

    /**
     *
     * @param object
     * object - Um objeto de neg�cio qualquer para ser convertido para uma
     * inst�ncia do tipo Beans
     * @return Beans
     */
    Beans toBeans(Object object);
}
