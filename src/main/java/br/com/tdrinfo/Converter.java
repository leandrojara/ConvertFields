package br.com.tdrinfo;

import br.com.tdrinfo.annotations.ConvertField;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ConvertFields
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * ***********************************************
 * Nome do arquivo: Converter.java
 * Criado por : Leandro de Souza Jara
 * Data da criação : 17/12/2018
 * Observação : classe responsável por transformar um objeto ou uma lista de objetos em objetos que possam ser
 * usados para gerar um layout automático no front-end, e também fazer o caminho contrário
 * ***********************************************
 */
public class Converter {

    /**
     * @param list  recebe uma lista de objetos
     * @param clazz tipo de classe da list
     * @return lista do tipo MyClass
     */
    public static List<MyClass> build(List list, Class clazz) {
        if (list != null && clazz != null) {
            List<MyClass> myClasses = new ArrayList<MyClass>();
            for (Object obj : list) {
                myClasses.add(build(obj, clazz));
            }
            return myClasses;
        }
        return null;
    }

    /**
     * @param obj   recebe um objeto
     * @param clazz recebe o tipo de classe do obj
     * @return objeto do tipo MyClass
     */
    public static MyClass build(Object obj, Class clazz) {
        if (obj != null && clazz != null) {
            MyClass myClass = new MyClass();
            myClass.setClassName(clazz.getName());
            myClass.setFields(new ArrayList<MyField>());

            for (Field field : clazz.getDeclaredFields()) {
                MyField myField = new MyField();
                myField.setFieldName(field.getName());
                myField.setFieldType(FieldType.OBJECT);

                for (FieldType fieldType : FieldType.values()) {
                    for (Class classe : fieldType.getClasses()) {
                        if (classe.equals(field.getType())) {
                            myField.setFieldType(fieldType);
                        }
                    }
                }

                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof ConvertField) {
                        myField.setLabelName(((ConvertField) annotation).labelName());
                        myField.setPercentWidth(((ConvertField) annotation).percentWidth());
                        myField.setSearchURI(((ConvertField) annotation).searchURI());
                    } else if (annotation instanceof Column && !((Column) annotation).nullable()) {
                        myField.setRequired(true);
                    } else if (annotation instanceof ManyToOne && !((ManyToOne) annotation).optional()) {
                        myField.setRequired(true);
                    } else if (annotation instanceof NotNull) {
                        myField.setRequired(true);
                    } else if (annotation instanceof NotEmpty) {
                        myField.setRequired(true);
                    } else if (annotation instanceof NotBlank) {
                        myField.setRequired(true);
                    } else if (annotation instanceof Size) {
                        myField.setMinLength(((Size) annotation).min());
                        myField.setMaxLength(((Size) annotation).max());
                    }
                }


                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.getName().equalsIgnoreCase("get" + field.getName()) || method.getName().equalsIgnoreCase("is" + field.getName())) {
                        try {
                            if (myField.getFieldType().equals(FieldType.OBJECT)) {
                                myField.setFieldValue(build(method.invoke(obj), field.getType()));
                            } else if (myField.getFieldType().equals(FieldType.LIST)) {
                                List list = (List) method.invoke(obj);
                                if (list == null || list.isEmpty()) {
                                    myField.setFieldValue(null);
                                } else {
                                    myField.setFieldValue(build(list, list.get(0).getClass()));
                                }
                            } else {
                                myField.setFieldValue(method.invoke(obj));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }

                myClass.getFields().add(myField);
            }
            return myClass;
        }
        return null;
    }

    public static List unbuild(List<MyClass> list) {
        if (list != null && !list.isEmpty()) {
            List<Object> retorno = new ArrayList<Object>();
            for (MyClass myClass : list) {
                retorno.add(unbuild(myClass));
            }
            return retorno;
        }
        return null;
    }

    public static Object unbuild(MyClass myClass) {
        try {
            Class clazz = Class.forName(myClass.getClassName());
            Object obj = clazz.newInstance();

            for (MyField myField : myClass.getFields()) {
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.getName().equalsIgnoreCase("set" + myField.getFieldName())) {
                        if (!myField.getFieldType().equals(FieldType.OBJECT) && !myField.getFieldType().equals(FieldType.LIST)) {
                            method.invoke(obj, myField.getFieldValue());
                        } else if (myField.getFieldType().equals(FieldType.OBJECT)) {
                            method.invoke(obj, unbuild((MyClass) myField.getFieldValue()));
                        } else if (myField.getFieldType().equals(FieldType.LIST)) {
                            method.invoke(obj, unbuild((List<MyClass>) myField.getFieldValue()));
                        }
                    }
                }
            }

            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
