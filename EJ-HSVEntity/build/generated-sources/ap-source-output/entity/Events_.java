package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-21T21:43:43")
@StaticMetamodel(Events.class)
public class Events_ { 

    public static volatile SingularAttribute<Events, String> date;
    public static volatile SingularAttribute<Events, String> duration;
    public static volatile SingularAttribute<Events, String> eventid;
    public static volatile SingularAttribute<Events, Integer> cost;
    public static volatile SingularAttribute<Events, Integer> availableslots;
    public static volatile SingularAttribute<Events, String> description;
    public static volatile SingularAttribute<Events, String> eventname;

}