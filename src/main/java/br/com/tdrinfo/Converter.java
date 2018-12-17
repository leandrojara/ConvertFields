package br.com.tdrinfo;

import br.com.tdrinfo.annotations.LabelName;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
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
 * Observação :
 * ***********************************************
 */
public class Converter {

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

    public static MyClass build(Object obj, Class clazz) {
        if (obj != null && clazz != null) {
            MyClass myClass = new MyClass();
            myClass.setClassName(clazz.getName());
            myClass.setFields(new ArrayList<MyField>());

            for (Field field : clazz.getDeclaredFields()) {
                MyField myField = new MyField();
                myField.setFieldName(field.getName());

                for (FieldType fieldType : FieldType.values()) {
                    for (Class classe : fieldType.getClasses()) {
                        if (classe.equals(field.getType())) {
                            myField.setFieldType(fieldType);
                        }
                    }
                }

                if (myField.getFieldType() == null) {
                    myField.setFieldType(FieldType.OBJECT);
                }

                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation instanceof LabelName) {
                        myField.setLabelName(((LabelName) annotation).value());
                    } else if (annotation instanceof Column) {
                        myField.setRequired(!((Column) annotation).nullable());
                    } else if (annotation instanceof ManyToOne) {
                        myField.setRequired(!((ManyToOne) annotation).optional());
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
}
