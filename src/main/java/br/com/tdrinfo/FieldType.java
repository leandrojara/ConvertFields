package br.com.tdrinfo;

import java.util.List;
import java.util.Set;

/**
 * gsea
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: FieldType.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação :
 * ***********************************************
 */
public enum FieldType {

    INTEGER(Integer.class, int.class, Long.class, long.class, Short.class, short.class),
    DECIMAL(Double.class, double.class, Float.class, float.class),
    TEXT(String.class, CharSequence.class),
    LIST(List.class, Object[].class, Set.class),
    BOOLEAN(Boolean.class, boolean.class),
    OBJECT();

    private final Class[] classes;

    FieldType(Class... classes) {
        this.classes = classes;
    }

    public Class[] getClasses() {
        return classes;
    }
}
