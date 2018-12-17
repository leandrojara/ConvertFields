package br.com.tdrinfo.annotations;

/**
 * ConvertFields
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: LabelName.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação :
 * ***********************************************
 */
public @interface LabelName {

    public String value() default "";
}
