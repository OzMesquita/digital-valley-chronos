package Beans;

public interface Beans {

    /**
     * @return Object
     */
    Object toBusiness();

    /**
     *
     * @param object
     * object - Um objeto de negócio qualquer para ser convertido para uma
     * instância do tipo Beans
     * @return Beans
     */
    Beans toBeans(Object object);
}
