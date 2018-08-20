package com.devpimentel.restapi.restapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Launch.class)
public abstract class Launch_ {

	public static volatile SingularAttribute<Launch, String> note;
	public static volatile SingularAttribute<Launch, Person> person;
	public static volatile SingularAttribute<Launch, LocalDate> dueDate;
	public static volatile SingularAttribute<Launch, String> description;
	public static volatile SingularAttribute<Launch, LocalDate> payday;
	public static volatile SingularAttribute<Launch, Long> id;
	public static volatile SingularAttribute<Launch, LaunchType> type;
	public static volatile SingularAttribute<Launch, Category> category;
	public static volatile SingularAttribute<Launch, BigDecimal> value;

}

